package threadtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.uxin.myapplication.R;

import javax.security.auth.login.LoginException;

public class ThreadTestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "cyp";

    private LockObject lockObject;
    private Button btOne;

    private Button bTthread1;

    private Button btTwo;
    
    private Button btSleep;
    
    private ThreadTest1 thread1;

    private Thread one;

    private Thread two;

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
        lockObject = new LockObject();
        thread1 = new ThreadTest1(lockObject);
//        thread1.setPriority(5);
        thread1.start();
//        btSleep.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                thread1.interrupt();
//                Log.i(TAG, "onCreate: "+thread1.isInterrupted());
//                Log.i(TAG, "onCreate: "+thread1.interrupted());
//            }
//        },2000);


        Test1Runnable runnable = new Test1Runnable(lockObject);
        one = new Thread(runnable);
        one.setName("线程one");
//        one.setPriority(10);
        one.start();

//        Test1Runnable runnable1 = new Test1Runnable(lockObject);
//        Thread three = new Thread(runnable1);
//        three.setName("线程three");
//        three.setPriority(1);
//        three.start();
//
//        Test1Runnable runnable2 = new Test1Runnable(lockObject);
//        Thread four = new Thread(runnable2);
//        four.setPriority(5);
//        four.setName("线程4");
//        four.start();



        initListener();

    }

    private void initListener() {
        btOne.setOnClickListener(this);
        bTthread1.setOnClickListener(this);
        btTwo.setOnClickListener(this);
        btSleep.setOnClickListener(this);
    }

    private void initView(){
        btOne = findViewById(R.id.one_state);
        bTthread1 = findViewById(R.id.thread1_state);
        btTwo = findViewById(R.id.two_tread);
        btSleep = findViewById(R.id.sleep);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_state:
                Log.i(TAG, "onClick :one " + one.getState());
                break;
            case R.id.thread1_state:
                Log.i(TAG, "onClick: thread1:"+thread1.getState());
                break;
            case R.id.two_tread:
                two = new Thread(new Test2Runnable(lockObject));
                two.start();
                break;
            case R.id.sleep:
                goToSleep();
                break;
            default:
                break;
        }
    }

    private void goToSleep()  {
        try {
            Log.i(TAG, "goToSleep: kaishi");
            Thread.sleep(1000);
            Log.i(TAG, "goToSleep: jieshu");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}