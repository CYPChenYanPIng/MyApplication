package shijianfenfa;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.uxin.myapplication.R;

import viewtest.MyConstraitLayout;
import viewtest.MyTextView;

/**
 * Created by shuipingyue@uxin.com on 2020/12/17.
 */
public class DispatchTestActivity extends Activity implements View.OnClickListener{
    private MyConstraitLayout root1;

    private MyLinearLayout linearLayout;

    private MyTextView textView;

    private MyRelativeLayout relativeLayout;
    private String TAG = "DispatchTestActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, DispatchTestActivity.class);
//    starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_layout);

        initView();

        initLinstener();
    }

    private void initLinstener() {
//        root1.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
//        textView.setOnClickListener(this);
//        relativeLayout.setOnClickListener(this);
        root1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "onTouch: root1");
                return false;
            }
        });
        
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "onTouch: linearLayout");
                return true;
            }
        });
        
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "onTouch: textView");
                return false;
            }
        });
        
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "onTouch: relativeLayout");
                return false;
            }
        });

    }

    private void initView() {
        root1 = findViewById(R.id.root1);
        linearLayout = findViewById(R.id.root_linear);
        textView = findViewById(R.id.mytextview);
        relativeLayout = findViewById(R.id.myrelative);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.root1:
                Log.i(TAG, "onClick: root1");
                break;
            case R.id.root_linear:
                Log.i(TAG, "onClick: root_linear");
                break;
            case R.id.mytextview:
                Log.i(TAG, "onClick: mytextview");
                break;
            case R.id.myrelative:
                Log.i(TAG, "onClick: myrelative");
                break;
            default:
                break;
        }
    }
}
