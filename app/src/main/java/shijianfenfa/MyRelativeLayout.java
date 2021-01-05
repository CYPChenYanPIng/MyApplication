package shijianfenfa;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by shuipingyue@uxin.com on 2020/12/17.
 */
public class MyRelativeLayout extends RelativeLayout {

    private String TAG = "MyRelativeLayout";

    public MyRelativeLayout(Context context) {
        this(context,null);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent: "+ev.getAction());
        return super.dispatchTouchEvent(ev);
//        return true;// 此方法消费，不会传给onInterceptTouchEvent或其子view，有消费其他的ACTION_MOVE等事件都会被消费
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent: "+ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: "+event.getAction());
        return super.onTouchEvent(event);
    }
}
