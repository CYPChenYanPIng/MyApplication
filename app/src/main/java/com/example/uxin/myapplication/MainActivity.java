package com.example.uxin.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import animation.AnimaTestActivity;
import assetstest.AssetsTestAcitivity;
import edittexttest.EditTextTestActivity;
import fragmenttest.FragmentTestActivity;
import gesturedetector.TestGestureDetectorActivity;
import scrollnum.GridRvAcitivity;
import scrollnum.SmothScrollTestActivity;
import viewtest.ViewTestActivity;

/**
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 跳转用的requestcode
     */
    private final static int REQUEST_CODE = 102;
    private TextView tvSend;

    private Button editTextTest;

    private Button startForResult;

    private Button smothScroll;

    private Button anima;

    private Button fragment;

    private Button gesture;

    private Button grid_rv_fragment;

    private Button viewTest;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
    }

    private void initView(){
        tvSend = findViewById(R.id.tv_send_broadcastreceiver);
        editTextTest = findViewById(R.id.editText);
        startForResult = findViewById(R.id.startForResut);
        smothScroll = findViewById(R.id.smothScrll);
        anima = findViewById(R.id.anima);
        fragment = findViewById(R.id.fragment);
        gesture = findViewById(R.id.gesture);
        grid_rv_fragment = findViewById(R.id.grid_rv_fragment);
        viewTest = findViewById(R.id.viewtest);
    }

    private void initData(){
        LoadOrder loadOrder = new LoadOrder();
        int num = loadOrder.getNum();
        Log.i("cyp","num:"+num);
    }

    private void initListener(){
        tvSend.setOnClickListener(this);
        editTextTest.setOnClickListener(this);
        startForResult.setOnClickListener(this);
        smothScroll.setOnClickListener(this);
        anima.setOnClickListener(this);
        fragment.setOnClickListener(this);
        gesture.setOnClickListener(this);
        grid_rv_fragment.setOnClickListener(this);
        viewTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_send_broadcastreceiver:
                Intent intent = new Intent();
                intent.setAction("com.uxin.live.image.test");
                sendBroadcast(intent);
                break;
            case R.id.editText:
                EditTextTestActivity.launch(MainActivity.this);
                break;
            case R.id.startForResut:
                JumpToCallBackActivity.launch(MainActivity.this,REQUEST_CODE);
                break;
            case R.id.smothScrll:
                SmothScrollTestActivity.launch(MainActivity.this);
                break;
            case R.id.anima:
                AnimaTestActivity.launch(this);
                break;
            case R.id.fragment:
                FragmentTestActivity.launch(MainActivity.this);
                break;
            case R.id.gesture:
                TestGestureDetectorActivity.launch(MainActivity.this);
                break;
            case R.id.grid_rv_fragment:
                GridRvAcitivity.launch(MainActivity.this);
                break;
            case R.id.viewtest:
                ViewTestActivity.launch(MainActivity.this);
                break;
               default:
                   break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("cyp","requestCode:"+requestCode+", resultCode:"+resultCode+", Intent:"+data);
    }


    /**
     * 匹配
     * @param textView
     */
    private void testMatch(TextView textView){

        String s = "中花ss%$-@·。，/？8";
        String subS = "中8";
        boolean isContains = s.contains(subS);
        System.out.println("s 是否包含 中"+isContains);
//        String newString = s.replace("^[\u4e00-\u9fa5]{0,}$","");
//        System.out.println("new string :"+newString);
        String REGEX = "[^\u4e00-\u9fa5^0-9]{0,}";
        String REPLACE = "";
        String newString = s.replaceAll(REGEX,REPLACE);
//        Pattern pattern = Pattern.compile(REGEX);
//        Matcher matcher = pattern.matcher(s);
//        String newString = matcher.replaceAll(REPLACE);
        textView.setText(newString);
    }

    /**
     *
     * @param ll_root
     */
    private void testAsset(LinearLayout ll_root){
        // 跳转到assetsTest页面
        TextView tvAssets = new TextView(this);
        tvAssets.setText("Assets验证");
        ll_root.addView(tvAssets);
        tvAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssetsTestAcitivity.launch(MainActivity.this);
            }
        });
    }

    private void testLinearLayout(LinearLayout ll_root){
        TextView mTv = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mTv.setText("收代理反馈及时的反馈说");
        ll_root.addView(mTv,layoutParams);

//        ViewGroup.LayoutParams layoutParams = mTv.getLayoutParams();
//        layoutParams.height = 60;
//        mTv.setLayoutParams(layoutParams);


        TextView textView = null;
        try {
            textView.setText("123");
        } catch (Exception e){
            e.printStackTrace();
            Log.i("cyp","jfei");
            textView = new TextView(this);
            textView.setText("123");
            ll_root.addView(textView);
        }

    }

    private void testSendBroadcastReceiver(){
        tvSend.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    tvSend.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                Log.i("cyp","wight :"+tvSend.getMeasuredWidth()+"height :"+tvSend.getMeasuredHeight());

            }
        });
    }

}
