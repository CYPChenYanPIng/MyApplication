package util;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * @author chenyanping
 * @date 2020-07-22
 */
public class CommonUtils {
    /**
     * 获取手机屏幕宽度通用方法
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        if (null == context) {
            return 0;
        }
        Point sizePoint = new Point();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (manager != null) {
            Display display = manager.getDefaultDisplay();
            display.getSize(sizePoint);
        }
        return sizePoint.x;
    }

    /**
     * 屏幕尺寸dip转px换算方法
     *
     * @param context
     * @param dipValue dip值
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        if (null == context) {
            return 0;
        }
        final float scaleValue = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scaleValue + 0.5f);
    }
}
