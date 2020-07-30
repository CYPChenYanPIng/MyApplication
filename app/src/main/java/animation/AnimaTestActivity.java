package animation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.uxin.myapplication.R;

/**
 * @author chenyanping
 * @date 2020-07-22
 */
public class AnimaTestActivity extends Activity {
    private ProgressBarAnima progressBarAnima;

    public static void launch(Context context) {
        Intent starter = new Intent(context, AnimaTestActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anima_test);
        initView();
    }
    private void initView(){
        progressBarAnima = findViewById(R.id.progress_anima);
    }
    private void initData(){
    }
}
