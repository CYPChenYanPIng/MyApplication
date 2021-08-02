package com.example.uxin.myapplication;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 *
 * startActivityForResult 跳转
 *
 * 点击finish结束页面  点击手机返回键返回上一个页面，都会反给上一个页面的onActivityResult数据
 * 点击finish 上一个页面onActivityResult接收：requestCode:102, resultCode:-1, Intent:Intent { (has extras) }
 * 点击手机返回键，上一个页面onActivityResult接收：requestCode:102, resultCode:0, Intent:null
 * @author chenyanping
 * @date 2020-07-06
 */
public class JumpToCallBackActivity extends Activity {
    private TextView mTvFinish;

    public static void launch(Activity activity,int requestCode) {
        Intent starter = new Intent(activity, JumpToCallBackActivity.class);
//        starter.putExtra();
        activity.startActivityForResult(starter,requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_to_calllback);
        int background_avg_save_cover = R.drawable.background_avg_save_cover;

        initView();
        initListener();

    }

    private void initView(){
        mTvFinish = findViewById(R.id.tv_finish);
    }

    private void initListener(){
        mTvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result","ok");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
    
    
}
