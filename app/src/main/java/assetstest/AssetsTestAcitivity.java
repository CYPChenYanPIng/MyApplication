package assetstest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.uxin.myapplication.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import music.MediaManager;

/**
 * @author chenyanping
 * @date 2020-05-11
 */
public class AssetsTestAcitivity extends Activity implements View.OnClickListener {

    private ImageView mAssets;
    private ImageView mSdcard;
    private ImageView mOss;
    private MediaPlayer mediaPlayer;

public static void launch(Context context) {
    Intent starter = new Intent(context, AssetsTestAcitivity.class);
//    starter.putExtra();
    context.startActivity(starter);
}
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_test);
        initView();
        addListener();
        mediaPlayer = new MediaPlayer();
    }

    private void initView() {
        mAssets = findViewById(R.id.iv_assets);
        mSdcard = findViewById(R.id.iv_sdcard);
        mOss = findViewById(R.id.iv_oss);
    }

    private void addListener() {
        mAssets.setOnClickListener(this);
        mSdcard.setOnClickListener(this);
        mOss.setOnClickListener(this);
        findViewById(R.id.bt_file).setOnClickListener(this);
        findViewById(R.id.bt_loadImage).setOnClickListener(this);
        findViewById(R.id.bt_music).setOnClickListener(this);
        findViewById(R.id.bt_sdcard).setOnClickListener(this);
        findViewById(R.id.bt_webview).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_loadImage:
                getAssetsImage();
                break;
            case R.id.bt_sdcard:
                loadImagToSdCard();
                break;
            case R.id.bt_webview:
                loadWebview();
                break;
            case R.id.bt_file:
                Log.i("cyp", loadFile());
                break;
            case R.id.bt_music:
                loadMusic();
                break;
            default:
                break;

        }
    }

    /**
     * 读取图片
     */
    private void getAssetsImage() {
        try {
            // 图片路径，从assets下的文件开始，不用写assets
            InputStream inputStream = getAssets().open("images/del_btn.png");
            // native 方法  节省内存
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            mAssets.setImageBitmap(bitmap);
            // 无用 提醒回收
            if (!bitmap.isRecycled()) {
                bitmap.isRecycled();
                System.gc();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 将assets中的文件写入到sdcard
     */
    private void loadImagToSdCard() {
        File file = CopyUtil.copyAssetsToSdCard(this, "images/del_btn.png",CopyUtil.getSdCardPath() );
        // 转化为URI
        Uri uri = Uri.fromFile(file);
    }

    /**
     * 加载assets下的网页文件，例如html
     */
    private void loadWebview() {
        // URI路径
        String path = "file:///android_asset/uxin_privacy.html";
//        Uri uri = Uri.parse(path);
        YxWebviewActivity.launch(this, path);
    }

    /**
     * 获取assets中的文件，例如.json
     */
    private String loadFile() {
        try {
            InputStream inputStream = getAssets().open("lottie_data_gashapon_start.json");
            StringBuffer stringBuffer = new StringBuffer();
            byte[] buf = new byte[1024];
            int count = inputStream.read(buf);
            while (count > 0) {
                stringBuffer.append(new String(buf));
                count = inputStream.read(buf);
            }
            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取音乐
     */
    private void loadMusic() {
        try {
            MediaManager.playAssetsSound(this, getAssets().openFd("music_default/test.mp3"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void requestAudioFocus() {
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        am.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
    }

    private void abandonAudioFocus(){
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        am.abandonAudioFocus(mOnAudioFocusChangeListener);
    }

    // TODO: 2020-05-16
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
           Log.i("cyp","onAudioFocusChange"+focusChange);
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    // 获得音频焦点，还原音量
                    Log.i("cyp", "Focus aquaired");
                    break;

                case AudioManager.AUDIOFOCUS_LOSS:
                    // 长久的失去音频焦点
                    Log.i("cyp", "Focus Loss AUDIOFOCUS_LOSS");

                    break;

                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    // 暂时失去音频焦点，暂停播放等待重新获得音频焦点
                    Log.i("cyp", "Focus Loss AUDIOFOCUS_LOSS_TRANSIENT");
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    // 失去音频焦点，无需停止播放，降低声音即可
                    Log.i("cyp", "Focus Loss AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
