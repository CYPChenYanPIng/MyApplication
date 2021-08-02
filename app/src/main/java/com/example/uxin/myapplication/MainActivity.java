package com.example.uxin.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import color.ColorTestActivity;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import animation.AnimaTestActivity;
import assetstest.AssetsTestAcitivity;
import bitmaptest.BitmapTestActivity;
import edittexttest.EditTextTestActivity;
import fragmenttest.FragmentTestActivity;
import fragmenttest.OneFragmentActivity;
import gesturedetector.TestGestureDetectorActivity;
import interfacetest.IWork;
import interfacetest.Worker;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import scrollnum.GridRvAcitivity;
import scrollnum.SmothScrollTestActivity;
import shijianfenfa.DispatchTestActivity;
import span.SpanTestActivity;
import threadtest.ThreadTestActivity;
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

    private Button bitmap;

    private Button interfaceTest;

    private Button threadTest;

    private Button dispatch;

    private Button oneFrag;

    private Button span;

    private Button color;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
//        Request request = new Request.Builder()
//                .url("https://www.baidu.com")
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("okhttp3", "call: " + call + ", e: " + e);
//                Log.i("cyp", "onFailure: "+Thread.currentThread());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                Log.i("okhttp3", "call: " + call + ", response: " + response);
//                Log.i("cyp", "onResponse: "+Thread.currentThread());
//                // 自线程
////                Toast.makeText(MainActivity.this,"sssss",Toast.LENGTH_LONG).show();
//            }
//        });


        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://www.baidu.com")
                .addConverterFactory(StringConverterFactory.create())
//                .addCallAdapterFactory() //把数据
                .build();
//
        RetrofitApi api = retrofit.create(RetrofitApi.class);
//        Call<String> call = api.getResp();
        Call<List<Object>> call = api.contributors("cyp","android");
        call.enqueue(new Callback<List<Object>>() {
            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {
                Log.e("okhttp3", "call: " + call + ", e: " + t);
                Log.i("cyp", "onFailure: "+Thread.currentThread());
                // 主线程
            }

            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                Log.i("okhttp3", "call: " + call + ", response: " + response.body());
                Log.i("cyp", "onResponse: "+Thread.currentThread());
            }
        });
    }

    public interface RetrofitApi {
        @GET("/")
        Call<String> getResp();

        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Object>> contributors(@Path("owner") String owner, @Path("repo") String repo);
    }

    public static class StringConverterFactory extends Converter.Factory {
        public static StringConverterFactory create() {
            return new StringConverterFactory();
        }

        @Override
        public Converter<ResponseBody, String> responseBodyConverter(Type type, Annotation[] annotations,
                                                                     Retrofit retrofit) {
            return new StringResponseBodyConverter();
        }
    }

    public static class StringResponseBodyConverter implements Converter<ResponseBody, String> {
        @Override
        public String convert(ResponseBody value) throws IOException {
            try {
                return value.string();
            } finally {
                value.close();
            }
        }
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
        bitmap = findViewById(R.id.bitmap);
        interfaceTest = findViewById(R.id.interface_test);
        threadTest = findViewById(R.id.thread);
        dispatch = findViewById(R.id.dispatch);
        oneFrag = findViewById(R.id.oneFrag);
        span = findViewById(R.id.span);
        color = findViewById(R.id.color);
    }

    private void initData(){
        LoadOrder loadOrder = new LoadOrder("d");
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
        bitmap.setOnClickListener(this);
        interfaceTest.setOnClickListener(this);
        threadTest.setOnClickListener(this);
        dispatch.setOnClickListener(this);
        oneFrag.setOnClickListener(this);
        span.setOnClickListener(this);
        color.setOnClickListener(this);
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
            case R.id.bitmap:
                BitmapTestActivity.launch(MainActivity.this);
                break;
            case R.id.interface_test:
                testInterface();
                break;
            case R.id.thread:
                ThreadTestActivity.start(MainActivity.this);
                break;

            case R.id.dispatch:
                DispatchTestActivity.start(MainActivity.this);
                break;
            case R.id.oneFrag:
                OneFragmentActivity.start(MainActivity.this);
                break;
            case R.id.span:
                SpanTestActivity.start(MainActivity.this);
                break;
            case R.id.color:
                Intent color = new Intent(this,ColorTestActivity.class);
                startActivity(color);
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

    private void testInterface(){

//        IWork work = new Worker();
//        work.startWork("开心开心开心专注开心");

        Class c = null;
        try {
            c = Class.forName("interfacetest.Worker");
            Log.i("cyp","c:"+c);
            Object o = c.newInstance();
            IWork work = (IWork) o;
            work.startWork("周五周五周五开心下班");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

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
