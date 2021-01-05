package shijianfenfa;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by shuipingyue@uxin.com on 2020/12/17.
 */
public class MyLinearLayout extends LinearLayout {

    private String TAG = "MyLinearLayout";

    public MyLinearLayout(Context context) {
        this(context,null);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent: "+ev.getAction() );
        boolean is = super.dispatchTouchEvent(ev);
        Log.i(TAG, "dispatchTouchEvent: myLinearLayout");
        return is;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent: "+ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: "+ event.getAction());
        return super.onTouchEvent(event);
    }
}
