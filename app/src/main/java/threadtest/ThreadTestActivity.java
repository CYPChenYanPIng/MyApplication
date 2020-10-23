package threadtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.uxin.myapplication.R;

public class ThreadTestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "cyp";
    private Button state;
    
    private ThreadTest1 thread1;

    public static void start(Context context) {
        Intent starter = new Intent(context, ThreadTestActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);

        initView();
        LockObject lockObject = new LockObject();

        thread1 = new ThreadTest1(lockObject);
        thread1.start();

        test1Runnable runnable = new test1Runnable(lockObject);
        Thread one = new Thread(runnable);
        one.start();

        initListener();

    }

    private void initListener() {
        state.setOnClickListener(this);
    }

    private void initView(){
        state = findViewById(R.id.state);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.state:
                Log.i(TAG, "onClick :thread1 " + thread1.getState());
                break;
            default:
                break;
        }
    }
}