package assetstest;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.uxin.myapplication.ChenApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author chenyanping
 * @date 2020-05-12
 */
public class CopyUtil {

    private static final int TYPE_LIVE_CACHE = 0x01;

    /**
     * 标示获取内部的data/data/的直播缓存目录(data/data/AboutLive)
     */
    private static final int TYPE_DATA_CACHE = 0x03;

    /**
     * 直播内部缓存根目录名
     */
    private static final String CACHE_LIVE_DIR = "AboutChen";

    /**
     * 对应一些下载的第三方缓存目录
     */
    private static final String CACHE_NOT_LIVE_DIR = "downloads-0";

    public static String getSdCardPath(){
        return getPath(TYPE_LIVE_CACHE,"images")+ "/del_btn.png";
    }


    /**
     * 把asset中的文件copy到sdcard中
     * @param context
     * @param assetPath
     * @param filePath
     * @return
     */
    public static File copyAssetsToSdCard(Context context,String assetPath,String filePath){
        File file = new File(filePath);
        Log.i("cyp","filePath:"+filePath);
        InputStream open = null;
        FileOutputStream outputStream = null;
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();

            open = context.getAssets().open(assetPath);
            outputStream = new FileOutputStream(file);

            byte [] buff = new byte[1024];

            int count = open.read(buff);
            while (count> 0) {
                outputStream.write(buff);
                count = open.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (open != null){
                try {
                    open.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * 返回basePath/childDir指定路径
     *
     * @param childDir
     * @return
     */
    private static String getPath(final int type, final String childDir) {
        File dir = getPathEx(type, childDir);
        if (dir != null) {
            return dir.getAbsolutePath();
        } else {
            return "";
        }
    }

    /**
     * 返回basePath/childDir指定路径
     *
     * @param childDir
     * @return
     */
    private static File getPathEx(final int type, final String childDir) {
        final File file = getBasePath(type);
        if (file != null) {
            final File child = new File(file, childDir);
            return mkdirs(child) ? child : null;
        }
        return null;
    }

    private static File getBasePath(final int type) {
        File file = null;
        if (type != TYPE_DATA_CACHE) {
            final String name = (type == TYPE_LIVE_CACHE ? CACHE_LIVE_DIR : CACHE_NOT_LIVE_DIR);
            file = getOuterPath(name);
            if (file == null) {
                file = getInnerPath(name);
            }
        } else {
            file = getInnerPath(CACHE_LIVE_DIR);
        }
        return file;
    }

    /**
     * 获取/data/data对应目录
     *
     * @return
     */
    private static File getInnerPath(final String name) {
        File file = new File("/data/data/" + getPackageName(ChenApplication.getContext()), name);
        if (!mkdirs(file)) {
            return null;
        }
        return file;
    }
    /**
     *
     * getPackageName:得到应用包名. <br/>
     *
     * @author wangheng
     * @return
     */
    public static String getPackageName(Context context){
        return context.getPackageName();
    }

    /**
     * 获取SD卡的保存文件路径。部分手机自带SD卡，自带的SD卡文件夹命名为sdcard-ext或其它，用系统自带方法无法检测出
     * 返回格式为 "/mnt/sdcard/AboutYX/" 或 "/mnt/sdcard-ext/AboutYX/"
     *
     * @return
     */
    private static File getOuterPath(final String child) {
        File file = null;
        String state = "";
        //酷派4.2.2系统报空指针错误
        File tmpFile = null;
        try {
            state = Environment.getExternalStorageState();
            tmpFile = Environment.getExternalStorageDirectory();
        } catch (Exception e) {
        }
        if (tmpFile == null) {
            return null;
        }

        if (state.equals(Environment.MEDIA_MOUNTED)) {
            file = new File(tmpFile, child);
            if (!mkdirs(file)) {
                return null;
            }
        } else {
            File mntFile = new File("/mnt");
            File[] mntFileList = mntFile.listFiles();
            if (mntFileList != null) {
                for (int i = 0; i < mntFileList.length; i++) {
                    String mmtFilePath = mntFileList[i].getAbsolutePath();
                    String sdPath = tmpFile.getAbsolutePath();
                    if (!mmtFilePath.equals(sdPath) && mmtFilePath.contains(sdPath)) {
                        file = new File(mmtFilePath, child);
                        if (!mkdirs(file)) {
                            return null;
                        }
                        break;
                    }
                }
            }
        }

        return file;
    }

    /**
     * 创建目录
     *
     * @param dir
     * @return
     */
    private static boolean mkdirs(File dir) {
        if (dir != null) {
            if (!dir.exists()) {
                final boolean mk = dir.mkdirs();
                if (!mk) {
                    Log.i("LivePath", "mkdirs: " + dir.getAbsolutePath() + ", perform is failed!!");
                }
                return mk;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
