package scrollnumber;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;

/**
 * @author chenyanping
 * @date 2020-06-29
 */
public class RiseNumberTextView extends android.support.v7.widget.AppCompatTextView implements RiseNumberBase{

    private static final int STOPPED = 0;

    private static final int RUNNING = 1;

    private int mPlayingState = STOPPED;

    private float number;

    private float fromNumber;

    private long duration = 3000;
    /**
     * 1.int 2.float
     */
    private int numberType = 1;

    private boolean flags = true;

    private EndListener mEndListener = null;

    final static int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE };


    public RiseNumberTextView(Context context) {
        super(context);
    }

    public RiseNumberTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RiseNumberTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface EndListener {
        void onEndFinish();
    }

    public boolean isRunning() {
        return (mPlayingState == RUNNING);
    }

    private void runFloat() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(fromNumber, number);
        valueAnimator.setDuration(duration);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (flags) {
                    setText(Utils.format("##0.00").format(Double.parseDouble(valueAnimator.getAnimatedValue().toString())));
                    if (valueAnimator.getAnimatedValue().toString().equalsIgnoreCase(number + "")) {
                        setText(Utils.format("##0.00").format(Double.parseDouble(number + "")));
                    }
                } else {
                    setText(Utils.format("##0.00").format(Double.parseDouble(valueAnimator.getAnimatedValue().toString())));
                    if (valueAnimator.getAnimatedValue().toString().equalsIgnoreCase(number +"" )) {
                        setText(Utils.format("##0.00").format(Double.parseDouble(number + "")));
                    }
                }
                if (valueAnimator.getAnimatedFraction() >= 1) {
                    mPlayingState = STOPPED;
                    if (mEndListener != null)
                        mEndListener.onEndFinish();
                }
            }
        });
        valueAnimator.start();
    }

    private void runInt() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt((int) fromNumber, (int) number);
        valueAnimator.setDuration(duration);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                setText(valueAnimator.getAnimatedValue().toString());
                if (valueAnimator.getAnimatedFraction() >= 1) {
                    mPlayingState = STOPPED;
                    if (mEndListener != null)
                        mEndListener.onEndFinish();
                }
            }
        });
        valueAnimator.start();
    }

    static int sizeOfInt(int x) {
        for (int i = 0;; i++)
            if (x <= sizeTable[i])
                return i + 1;
    }

    @Override
    public void start() {
        if (!isRunning()) {
            mPlayingState = RUNNING;
            if (numberType == 1)
                runInt();
            else
                runFloat();
        }
    }

    @Override
    public RiseNumberTextView withNumber(float number) {
        System.out.println(number);
        this.number = number;
        numberType = 2;
        fromNumber = 0;

        return this;
    }

    @Override
    public RiseNumberTextView withNumber(float number, boolean flag) {
        this.number = number;
        this.flags = flag;
        numberType = 2;
        fromNumber = (number + 9) % 10;

        return this;
    }

    @Override
    public RiseNumberTextView withNumber(int number) {
        this.number = number;
        numberType = 1;
        fromNumber = (number + 9) % 10;

        return this;
    }

    @Override
    public RiseNumberTextView setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public void setOnEnd(RiseNumberTextView.EndListener callback) {
        mEndListener = callback;
    }
}
