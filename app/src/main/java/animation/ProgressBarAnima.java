package animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.uxin.myapplication.R;

import util.CommonUtils;

/**
 * 直播间pk  经验进度条
 *
 * @author chenyanping
 * @date 2020-07-22
 */
public class ProgressBarAnima extends FrameLayout {
    private Context mContext;
    private ProgressBar progressBar;
    private ImageView imageView;
    private int progressBarWidth ;

    public ProgressBarAnima(@NonNull Context context) {
        super(context);
        this.mContext = context;
        initView();
        initData();
    }

    /**
     * xml文件中走这个构造方法
     * @param context
     * @param attrs
     */
    public ProgressBarAnima(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
        initData();
    }

    public ProgressBarAnima(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
        initData();
    }

    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_progressbar_view,this,true);
        progressBar = view.findViewById(R.id.progress_bar);
        imageView = view.findViewById(R.id.iv_center);
    }

    private void initData(){
        progressBarWidth = CommonUtils.getScreenWidth(mContext);
        progressBar.setProgress(30);
        setImageLayout(30);
    }

    private void setImageLayout(int progress){
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
        int marginleft = progress*progressBarWidth/100 - CommonUtils.dip2px(mContext,10);
        layoutParams.leftMargin = marginleft;
        imageView.setLayoutParams(layoutParams);
        startAnima();
    }

    private void startAnima(){
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView,"scaleX",1f,1.5f,1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView,"scaleY",1f,1.5f,1);
        scaleX.setRepeatCount(ValueAnimator.INFINITE);
        scaleY.setRepeatCount(ValueAnimator.INFINITE);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.playTogether(scaleX,scaleY);
        animatorSet.start();
    }

}
