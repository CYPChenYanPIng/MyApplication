package animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.example.uxin.myapplication.R;

/**
 * @author chenyanping
 * @date 2020-07-22
 */
public class AnimaTestActivity extends Activity {
    private ProgressBarAnima progressBarAnima;

    private ImageView ivNovelChapterShare;

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
    }
    private void initData(){
        testScaleAnim();
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
    }
}
