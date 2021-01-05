package animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uxin.myapplication.R;

import org.w3c.dom.Text;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenyanping
 * @date 2020-07-22
 */
public class AnimaTestActivity extends Activity {
    private ProgressBarAnima progressBarAnima;

    private ImageView ivNovelChapterShare;

    private TextView tvAnim;

    public final String TAG = "cyp";

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
        initData();
    }
    private void initView(){
        progressBarAnima = findViewById(R.id.progress_anima);
        ivNovelChapterShare = (ImageView) findViewById(R.id.iv_novel_chapter_share);
        tvAnim = findViewById(R.id.tv_animation);
    }
    private void initData(){
        testScaleAnim();
        valueAnim();
    }

    private void testScaleAnim(){
        ObjectAnimator animY = ObjectAnimator.ofFloat(ivNovelChapterShare, "scaleY", 1f, 1.1f, 1.2f, 1.1f, 1f);
        ObjectAnimator animX = ObjectAnimator.ofFloat(ivNovelChapterShare, "scaleX", 1f, 1.1f, 1.2f, 1.1f, 1f);
        animY.setRepeatCount(2);
        animX.setRepeatCount(2);
        final AnimatorSet animator = new AnimatorSet();
        animator.playTogether(animY, animX);
        animator.setDuration(1200);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final Animator animation) {
                Log.i(TAG, "onAnimationEnd: ");
                ivNovelChapterShare.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "run: animator"+animation);
                        if (animator != null) {
                            Log.i(TAG, "run: ");
                            animator.start();
                        }
                    }
                }, 30 * 1000);
            }
        });
        animator.start();

        // 属性动画

        // 帧动画



    }

    // 补间动画
    private void buJianAnim() {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0.2f,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(2000);
        translateAnimation.setInterpolator(new LinearInterpolator());
        translateAnimation.setInterpolator(new AccelerateInterpolator());

    }

    /**
     * 属性动画
     */
    private void valueAnim(){
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f, 2.0f, 3.0f);
        animator.setDuration(5000);
        //new BounceInterpolator()  弹跳插值器   就像跳跳球掉落在地面一样的效果
        // 插值器，是一个函数，LinearInterpolator 线性插值器 函数y = t
        // AccelerateInterpolator加速插值器 t^(2f)  起始速度是0，速度越来越快，加速运动。factor的大小对曲线的影响看图吧。
        // DecelerateInterpolator减速插值器 起始速度不为0，速度越来越慢，最后速度为0，减速运动
        // AccelerateDecelerateInterpolator先加速后减速插值器  速度从0开始，先加速后加速，最后速度为0
        animator.setInterpolator(new BounceInterpolator());
        animator.setEvaluator(null);
        animator.removeAllUpdateListeners();
        final float scaleX = tvAnim.getScaleX();
        Log.i("cyp", "valueAnim: "+scaleX);
        ValueAnimator.AnimatorUpdateListener listener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 通过updatelistener  获取变化函数的斜率，斜率的变化和插值器的有关
                Object rate = animation.getAnimatedValue();
                Log.i("cyp", "onAnimationUpdate: "+rate);
                float scale = (float) rate;
                tvAnim.setScaleX(scaleX * (1+scale));
            }
        };

        animator.setTarget(null);
        animator.addUpdateListener(listener);
        animator.start();
    }

}
