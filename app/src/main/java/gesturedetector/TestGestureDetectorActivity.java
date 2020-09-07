package gesturedetector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.uxin.myapplication.R;

/**
 * @author chenyanping
 * @date 2020-08-11
 */
public class TestGestureDetectorActivity extends Activity {

    private GestureDetector gestureDetector;

    private Button button;

    public static void launch(Context context) {
        Intent starter = new Intent(context, TestGestureDetectorActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_test_gesture_detector);
        initView();
    }

    private void initView() {
       gestureDetector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
           @Override
           public boolean onDoubleTap(MotionEvent e) {
               Toast.makeText(TestGestureDetectorActivity.this,"双击",Toast.LENGTH_LONG).show();
               return super.onDoubleTap(e);
           }
       }) ;
       button = findViewById(R.id.bottom);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.i("cyp", "onClick: ");
           }
       });

       button.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               Log.i("cyp", "onTouch: ");
               return false;
           }
       });

       button.post(new Runnable() {
           @Override
           public void run() {

           }
       });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        gestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

    }
}
