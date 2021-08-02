package span;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.ReplacementSpan;
import android.widget.TextView;
import com.example.uxin.myapplication.R;

public class SpanTestActivity extends AppCompatActivity {
    
    public static void start(Context context) {
        Intent starter = new Intent(context, SpanTestActivity.class);
        //starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        setContentView(textView);
        iconReplace(textView);

    }

    private void iconReplace(TextView textView) {
        String str = "$Hello World!";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);

        // api中的解释，把0-1的字符替换为span中的内容 SPAN_EXCLUSIVE_EXCLUSIVE 不包括start，end的位置，但是length不能为0
        // 实际执行的结果：SPAN_EXCLUSIVE_EXCLUSIVE/SPAN_INCLUSIVE_INCLUSIVE/SPAN_INCLUSIVE_EXCLUSIVE/SPAN_EXCLUSIVE_INCLUSIVE 效果都一样
        // 2-0 = 2 ，从下标0开始，替换2个字符
        spannableStringBuilder.setSpan(span, 0, 2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        //设置Text文字颜色
        //  SPAN_EXCLUSIVE_INCLUSIVE /SPAN_EXCLUSIVE_EXCLUSIVE /SPAN_INCLUSIVE_EXCLUSIVE/SPAN_INCLUSIVE_INCLUSIVE 效果一样
        // 3-1 = 2,从下标为1开始，颜色两个字符
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), 1, 3, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        //设置图片
        textView.setText(spannableStringBuilder);
    }

    private void test(TextView textView) {
        String str = " Hello World!";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);

        //设置图片
        spannableStringBuilder.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //设置Text文字颜色
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), 1, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //设置图片
        textView.setText(spannableStringBuilder);
    }
}
