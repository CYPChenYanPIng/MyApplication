package bezier;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uxin.myapplication.R;

/**
 *
 * 贝塞尔曲线
 *
 * @author chenyanping
 * @date 2020-07-14
 */
public class BezierActivity extends Activity {

    private ObjectAnimator animator;

    private ImageView animaImag;

    private TextView mTvFinish;

    private TextView mTvLogic;

    private ConstraintLayout constraintLayout;

    private int [] childLocation = new int[2];

    private int [] endLocation = new int[2];


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 贝塞尔曲线
     */
    private void testBezier(){

        mTvLogic.getLocationInWindow(childLocation);
        childLocation[0] = childLocation[0] + mTvLogic.getWidth()/2;
        childLocation[1] = childLocation[1] + mTvLogic.getHeight()/2;
        mTvFinish.getLocationInWindow(endLocation);

        if (animaImag == null) {
            animaImag = new ImageView(this);
            animaImag.setImageResource(R.drawable.oval_fffff_w8h8);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(20, 20);
            animaImag.setLayoutParams(layoutParams);
        }
        if (null == animaImag.getParent()) {
            constraintLayout.addView(animaImag);
        }

        animaImag.setX(childLocation[0]);
        animaImag.setY(childLocation[1]);

        PointF startP = new PointF();
        PointF endP = new PointF();
        PointF controlP = new PointF();

        startP.x = childLocation[0];
        startP.y = childLocation[1];

        endP.x = endLocation[0];
        endP.y = endLocation[1];

        controlP.x = (childLocation[0]+endLocation[0])/2;
        controlP.y = (childLocation[1]+endLocation[1])/2;

        Log.i("cyp","x:"+childLocation[0]+",y:"+childLocation[1]);
        PointFVirtualEvaluator evaluator = new PointFVirtualEvaluator(controlP);

        animator = ObjectAnimator.ofObject(animaImag,"mPointF",evaluator,startP,endP);

        animator.setDuration(800);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                animaImag.setX(pointF.x);
                animaImag.setY(pointF.y);
                animaImag.invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Object target = ((ObjectAnimator)animation).getTarget();

            }
        });
        animator.start();
    }

    private class PointFVirtualEvaluator implements TypeEvaluator<PointF> {

        private PointF mFlagPoint;

        public PointFVirtualEvaluator(PointF mFlagPoint) {
            this.mFlagPoint = mFlagPoint;
        }

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            return caclulateBezierPointForQuadratic(fraction, startValue, mFlagPoint, endValue);
        }

        private PointF caclulateBezierPointForQuadratic(float t, PointF p0, PointF p1, PointF p2) {
            PointF pointF = new PointF();
            float temp = 1 - t;
            pointF.x = temp * temp * p0.x + 2 * t * temp * p1.x + t * t * p2.x;
            pointF.y = temp * temp * p0.y + 2 * t * temp * p1.y + t * t * p2.y;
            Log.i("cyp","caclulate x:"+pointF.x+",y:"+pointF.y);
            return pointF;
        }
    }

}
