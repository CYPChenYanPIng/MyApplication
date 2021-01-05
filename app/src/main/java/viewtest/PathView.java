package viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shuipingyue@uxin.com on 2020/12/15.
 */
public class PathView extends View {

    private Path path ;
    private ShapeDrawable shapeDrawable;

    public PathView(Context context) {
        this(context,null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        path = new Path();
        path.moveTo(50,0);
        path.lineTo(0,50);
        path.lineTo(50,100);
        path.lineTo(100,50);
        path.close();
        shapeDrawable = new ShapeDrawable(new PathShape(path,200,200));
        Paint paint = shapeDrawable.getPaint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        shapeDrawable.setBounds(50,50,1000,1000);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shapeDrawable.draw(canvas);
    }
}
