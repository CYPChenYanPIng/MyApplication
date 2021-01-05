package viewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;

import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;


import com.example.uxin.myapplication.R;

/**
 * Created by shuipingyue@uxin.com on 2020/12/14.
 */
public class GradiendAndShader extends FrameLayout {

    private Paint mPaint;
    Bitmap drawingCache;

    Matrix matrix;
    ShapeDrawable shapeDrawable;

    BitmapShader shader;

    public GradiendAndShader(Context context) {
        super(context);
    }

    public GradiendAndShader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.i("cyp", "GradiendAndShader: ");
        setWillNotDraw(false);
        matrix = new Matrix();
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);// 平滑的，反锯齿的
        mPaint.setStrokeWidth(3f);// 边框的粗细
        mPaint.setStyle(Paint.Style.FILL);//只绘制图像内容
        mPaint.setTextSize(20);//单位px

        if (shapeDrawable == null) {
            shapeDrawable = new ShapeDrawable(new OvalShape());
        }
        if (drawingCache == null) {
            drawingCache = BitmapFactory.decodeResource(getResources(), R.drawable.icon_filter_default);
        }

        shader = new BitmapShader(drawingCache, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        destroyDrawingCache();
//        setDrawingCacheEnabled(true);
    }

    public GradiendAndShader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i("cyp", "GradiendAndShader: ");

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("cyp", "onMeasure: ");
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        Log.i("cyp", "onMeasure: width;"+width+",height;"+height);
        int getw = getWidth();// 刚开始一直为0，知道size发生改变走onsizechanged方法
        int geth = getHeight();
        Log.i("cyp", "onMeasure: "+getw);
        setMeasuredDimension(400,300);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("cyp", "onSizeChanged: w:"+w+",h:"+h+",oldw:+"+oldw+",oldh:"+oldh);
        Log.i("cyp","height:"+getHeight()+",wh:"+getWidth()+",mew:"+getMeasuredWidth());
        //w = width  = measuredWidth
        //h = height = measuredHeight
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int white = 0x99FFFFFF;
        int transparent = Color.TRANSPARENT;
        int[] colors = {transparent, transparent, white, Color.WHITE, white, transparent, transparent};
        float[] positions = {0, 0.35f, 0.45f, 0.5f, 0.55f, 0.65f, 1};

//        LinearGradient linearGradient = new LinearGradient(0,0,100,300,Color.RED,Color.BLUE, Shader.TileMode.CLAMP);
        LinearGradient linearGradient = new LinearGradient(0,0,getWidth(),getHeight(),colors,positions, Shader.TileMode.CLAMP);


        // 对bitmap进行缩放   0。6，原图片的宽高都缩小0。6
        matrix.setScale(0.6f,0.6f);

        shader.setLocalMatrix(matrix);
        shapeDrawable.getPaint().setShader(shader);
        shapeDrawable.setBounds(0,0,getWidth(),getHeight());

        shapeDrawable.draw(canvas);
//        ComposeShader composeShader = new ComposeShader(shader,linearGradient, PorterDuff.Mode.SRC_IN);
//
//        mPaint.setShader(composeShader);
//        canvas.drawRect(0,0,getWidth(),250,mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i("cyp", "onLayout: "+changed+",left:"+left+",top:"+top+",right:"+right+",bottom:"+bottom);
        if (changed) {
            int childCount = getChildCount();
            Log.i("cyp", "onLayout: childCount:"+childCount);
            for (int i = 0;i<childCount;i++){
                View childAt = getChildAt(i);
                childAt.layout(200,0,childAt.getMeasuredWidth()+200,childAt.getMeasuredHeight());
            }
        }
    }
}
