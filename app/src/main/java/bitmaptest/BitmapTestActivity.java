package bitmaptest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.example.uxin.myapplication.R;

/**
 * bitmap占用内存大小
 * <p>
 * 计算方式
 *
 * @author chenyanping
 * @date 2020-08-26
 */
public class BitmapTestActivity extends Activity {
    private static final  String TAG = "cyp";

    private ImageView ivBitmap;
    private ImageView ivImage;
    private ImageView ivMdpi;

    public static void launch(Context context) {
        Intent starter = new Intent(context, BitmapTestActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_test);
        initView();
        loadBitmap(ivBitmap);
//        ivBitmap.setImageResource(R.mipmap.icon_fox_sleep_0);
    }

    private void initView() {
        ivBitmap = findViewById(R.id.iv_bitmap);
        ivImage = findViewById(R.id.iv_img);
//        ivImage.setImageResource(R.mipmap.icon_fox_sleep_0);
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_fox_sleep_0);
        ivImage.setImageDrawable(drawable);
        ivMdpi = findViewById(R.id.iv_mdpi);
        ivMdpi.setImageResource(R.mipmap.icon_radio_22);


    }

    private void loadBitmap(final ImageView imageView) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 前提  imageView设置的是wrapcontent

        // 三张不同的图片，命名都是icon_fox_sleep_0，分别放到xhdpi（320）,xxhdpi（480）,xxxhdpi（640）
        // 运行手机屏幕密度 420，运行后用的是xxhdpi下的图片，图片在xxhdpi下是222*170，运行到手机中，宽高缩小420/480，是194*149
        // 图片在手机中的内存自然变小

        // 两张不同的图片，命名都是icon_fox_sleep_0，分别放到xhdpi（320）,xxxhdpi（640）
        // 运行手机屏幕密度 420，运行后用的是xxxhdpi下的图片，图片在xxxhdpi下是222*170，运行到手机中，宽高缩小420/640，是146*112
        // 图片在手机中的内存自然变小

        // 只有一张图片，放到xhdpi（320）中，从xxhdpi，xxxhdpi中都没有找到，就从低的xhdpi中寻找，然后放大420/320，
        // 内存也变大

        // 以上证明，APP查找图片资源时遵循先高后低原则，屏幕密度是420的手机，图片查找先从xxhdpi（480）查找，没有从xxxhdpi（640）查找，还是没有再从低的xhdpi查找，以此类推

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_fox_sleep_0, options);
        imageView.setImageBitmap(bitmap);
//        imageView.setImageResource(R.mipmap.icon_fox_sleep_0);

        // getByteCount获取图片的大小  图片大小计算方法  图片在运行手机中的宽高（所占用的像素点数），乘以图片每个像素点占用的大小（Config，比如：ARGB_8888占用4个字节）
        Log.i(TAG, "loadBitmap: bitmap getByteCount:" + bitmap.getByteCount() + "," + bitmap.getAllocationByteCount());

        // bitmap图片在运行手机中的宽高，计算方法：图片宽X手机的densityDpi/图片的densityDpi
        // 图片的densityDpi：xhdpi（超高）~320dpi
        // 此图的宽高在studio中是222*170  高170在运行手机的内存中大小为170*手机的densityDpi/图片的densityDpi  即170*480/320
        // 图片在手机中的内存大小 （222*480/320）*（170*480/320）*6
        Log.i(TAG, "loadBitmap: width "+bitmap.getWidth()+",height "+bitmap.getHeight());

        // options.inDensity为图片所在的文件夹对应的像素密度dpi，如：xhdpi（超高）~320dpi
        // options.inTargetDensity 运行的手机的像素密度，比如我的手机是480
        Log.i(TAG, "loadBitmap: "+options.inDensity+","+options.inTargetDensity);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        // 手机像素密度比例值 比如我的手机的像素密度是480  480/160 = 3
        float dens = displayMetrics.density;
        Log.i(TAG, "loadBitmap: dens "+dens);

        // 手机的像素密度 480
        int densityDpi = displayMetrics.densityDpi;
        Log.i(TAG, "loadBitmap: densityDpi"+densityDpi);

        // bitmap图片的argb格式，通过这个可以得到图片的每个像素点所占用的内存大小
        // ALPHA_8 1byte  RGB_565 2byte   ARGB_4444 2byte   ARGB_8888 4 bytes
        Bitmap.Config config = bitmap.getConfig();
        Log.i(TAG, "loadBitmap: config "+config);

        imageView.post(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "loadBitmap: "+imageView.getWidth()+","+imageView.getHeight());
            }
        });

    }

    private void compressImage(){
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 2;
//        BitmapFactory.decodeResource();
//        Bitmap bitmap = new Bitmap();


    }
}
