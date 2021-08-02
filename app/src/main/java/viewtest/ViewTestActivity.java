package viewtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uxin.myapplication.R;

import util.CommonUtils;


/**
 *
 * 测试view 设置gone  visible  invisible
 * 有哪些区别
 * @author chenyanping
 * @date 2020-09-09
 */
public class ViewTestActivity extends Activity {

    TextView mytextview;
    TextView customtextview;
    MyConstraitLayout constraitLayout;

    private GradiendAndShader gradiendAndShader;

    private PathView pathView;

    public static void launch(Context context) {
        Intent starter = new Intent(context, ViewTestActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);
        mytextview = findViewById(R.id.mytextview);
        mytextview.setVisibility(View.VISIBLE);

        //customtextview = findViewById(R.id.customtextview);
        //customtextview.setVisibility(View.VISIBLE);
        constraitLayout = findViewById(R.id.constraint);
        gradiendAndShader = findViewById(R.id.gradient);
        pathView = findViewById(R.id.path);
        initListener();
        testImageView();
        changeImageColor();

        int colorInt = textColor(0);

    }

    private void initListener() {
        final int dip2px = CommonUtils.dip2px(ViewTestActivity.this, 20);
        // 设置为gone invisible时，点击事件不可用
        mytextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("text", "onClick: mytextview");
                // invalidate方法只会走ondrawa  重绘
//                customtextview.invalidate();
                // requestLayout方法会走onMeasure，onLayout方法  重新
//                customtextview.requestLayout();
                addViewLocation();
            }
        });

//        customtextview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("text", "onClick: customtextview");
//                // 从gone设置为visible时，走了父布局的onMeasure  onLayout  ,自己的三个方法都走了
////                mytextview.setVisibility(View.VISIBLE);
//
//                // 代码设置.9图  会导致之前设置的padding失效
//                mytextview.setBackgroundResource(R.drawable.du_bg_label_new);
//                mytextview.setPadding(dip2px,dip2px,dip2px,dip2px);
//
//            }
//        });

    }

    /**
     * 动态添加view
     */
    private void addViewLocation(){
        MyTextView textView = new MyTextView(this);
        MyConstraitLayout.LayoutParams layoutParams = new MyConstraitLayout.LayoutParams(CommonUtils.dip2px(this,200), ViewGroup.LayoutParams.WRAP_CONTENT);
        //layoutParams.leftToLeft = R.id.customtextview;
        layoutParams.topToBottom = R.id.mytextview;
        textView.setText("动态添加view");
        // addView 走了textview的三个方法，MyConstraitLayout的方法没有走
        constraitLayout.addView(textView,layoutParams);

    }

    private void testImageView() {
        ImageView imageView = new ImageView(this);
        MyConstraitLayout.LayoutParams layoutParams = new MyConstraitLayout.LayoutParams(CommonUtils.dip2px(this,200), ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
        imageView.setImageResource(R.drawable.du_bg_label_new);
        imageView.setImageDrawable(null);
        constraitLayout.addView(imageView);

    }

    private void changeImageColor() {
        ImageView imageView = new ImageView(this);
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_video_outlined);
        drawable.setColorFilter(getResources().getColor( R.color.color_ff8383),PorterDuff.Mode.SRC_ATOP);
        imageView.setImageDrawable(drawable);
        //imageView.setColorFilter(getResources().getColor( R.color.color_ff8383));
        constraitLayout.addView(imageView);

        TextView textView = new TextView(this);
        textView.setTextColor(getResources().getColor(R.color.color_ff8383));
        textView.setText("cedddd");
         constraitLayout.addView(textView);
    }

    private int textColor(int color) {
        String toHexString = Integer.toHexString(color);
        if (toHexString.length() == 8) {
            toHexString = toHexString.substring(6, 8) + toHexString.substring(0, 6);
        }
        return Color.parseColor("#"+toHexString);
    }

    private void testSpan() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("$标题");
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_video_outlined);
        builder.setSpan(new ImageSpan(drawable),0,1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ImageSpan[] spans = builder.getSpans(0, builder.length(), ImageSpan.class);
        for (ImageSpan span : spans) {
            if (span.getDrawable() != null ) {
                Drawable mIconDrawable = span.getDrawable();
                mIconDrawable.setColorFilter(new PorterDuffColorFilter(getResources().getColor(R.color.color_ff8383),
                    PorterDuff.Mode.SRC_ATOP));
                ImageSpan imageSpan = new ImageSpan(mIconDrawable);
                //builder.removeSpan(span);
                builder.setSpan(imageSpan,0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }
}
