package viewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.uxin.myapplication.R;

/**
 * Created by shuipingyue@uxin.com on 2020/12/15.
 */
class HeadView extends View {

    private Matrix matrix;
    private ShapeDrawable shapeDrawable;

    private BitmapShader shader;
    private Bitmap drawingCache;

    public HeadView(Context context) {
        this(context,null);
    }

    public HeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        matrix = new Matrix();
        if (shapeDrawable == null) {
            // 椭圆
            shapeDrawable = new ShapeDrawable(new OvalShape());
        }
        if (drawingCache == null) {
            // 头像  bitmap
            drawingCache = BitmapFactory.decodeResource(getResources(), R.drawable.icon_filter_default);
            // 也可以从download的缓存中获取bitmap
            //       ivhead.destroyDrawingCache();
//        ivhead.setDrawingCacheEnabled(true);
            // 头像view
//            ivhead.getDrawingCache();
        }
        // 构建bitmapshader
        shader = new BitmapShader(drawingCache, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设置头像缩放的比例，可以让头像都显示到所给的imageview指定的大小中
        matrix.setScale(0.6f,0.6f);
//        matrix.setRotate(45.0f);
//        matrix.setTranslate(100,100);
        // 设置bitmapshader缩放
        shader.setLocalMatrix(matrix);
        shapeDrawable.getPaint().setShader(shader);
        shapeDrawable.setBounds(0,0,getWidth(),getHeight());
        // 画出来
        shapeDrawable.draw(canvas);
    }
}
