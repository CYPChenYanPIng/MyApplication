package scrollnumber;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ViewAnimator;


import com.example.uxin.myapplication.R;

/**
 * 公告轮播
 */
public class BulletinBoardView extends ViewAnimator {

    /**
     * 轮播间隔 3 秒
     */
    private static final int DEFAULT_INTERVAL = 3000;
    private static final int DEFAULT_ENTER_ANIM = R.anim.bulletboard_in_enter;
    private static final int DEFAULT_LEAVE_ANIM = R.anim.bulletboard_out_enter;

    private int mFlipInterval = DEFAULT_INTERVAL;
    private int mBulletinEnterAnim = DEFAULT_ENTER_ANIM;
    private int mBulletinLeaveAnim = DEFAULT_LEAVE_ANIM;

    private static String TAG = "BulletinBoardView";

    private boolean mVisible = false;

    private boolean mRunning = false;

    public BulletinBoardView(Context context) {
        super(context);
        init();
    }

    public BulletinBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BulletinBoardView);
        mFlipInterval = a.getInt(R.styleable.BulletinBoardView_bulletinBoardInterval, DEFAULT_INTERVAL);
        mBulletinEnterAnim = a.getResourceId(R.styleable.BulletinBoardView_bulletinBoardEnterAnim, DEFAULT_ENTER_ANIM);
        mBulletinLeaveAnim = a.getResourceId(R.styleable.BulletinBoardView_bulletinBoardLeaveAnim, DEFAULT_LEAVE_ANIM);
        a.recycle();
        init();
    }

    private void init() {

        setInAnimation(AnimationUtils.loadAnimation(getContext(), mBulletinEnterAnim));
        setOutAnimation(AnimationUtils.loadAnimation(getContext(), mBulletinLeaveAnim));
    }

    public void addFlippingView(View startView,View endView){
        addView(startView);
        addView(endView);
        updateRunning();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        super.onDetachedFromWindow();
        mVisible = false;
        updateRunning();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        mVisible = visibility == VISIBLE;
        updateRunning(false);
    }

    private void updateRunning() {
        updateRunning(true);
    }

    private void updateRunning(boolean flipNow) {
        boolean running = mVisible ;
        if (running != mRunning) {
            if (running) {
//                showOnly(mWhichChild, flipNow);
                postDelayed(mFlipRunnable, mFlipInterval);
            } else {
                removeCallbacks(mFlipRunnable);
            }
            mRunning = running;
        }

        Log.d(TAG, "updateRunning() mVisible=" + mVisible
                    + ", mRunning=" + mRunning);

    }

    private final Runnable mFlipRunnable = new Runnable() {
        @Override
        public void run() {
            if (mRunning) {
                showNext();
            }
        }
    };
}