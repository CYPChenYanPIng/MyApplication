package fragmenttest.transformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shuipingyue@uxin.com on 2021/1/15.
 */
public class MyPagerTransformer implements ViewPager.PageTransformer {

    private ViewPager mViewPager;
    private String TAG = "MyPagerTransformer";

    Map<View,Integer> mViewList = new HashMap<>();

    public MyPagerTransformer(ViewPager viewPager) {
        mViewPager = viewPager;
    }

    /**
     * 这个方法会调用很多遍
     * 在这个方法中，一定要注意性能
     *
     * @param view  fragment的onCreateView方法返回的View，或者PagerAdapter中的instantiateItem返回的View
     * @param position 变化范围是-无穷到-1，-1到0，0到1，1到正无穷，-1代表的是当前页面的左边页面，0是当前页面，1是当前页面的右边页面
     */
    @Override
    public void transformPage(@NonNull View view, float position) {
        int viewPos = getViewPos(view);
        Log.i(TAG, "transformPage: viewPos:"+viewPos+",pos:"+position);


    }

    /**
     * 减少 执行的复杂度
     *
     * @param view
     * @return
     *
     *
     */
    private int getViewPos(View view){

        Integer integer = mViewList.get(view);
        int childCount = mViewPager.getChildCount();
        if (integer == null || mViewList.size() != childCount) {
            for (int i = 0; i < childCount; i++) {
                View childAt = mViewPager.getChildAt(i);
                mViewList.put(childAt,i);
            }
             integer = mViewList.get(view);
            if (integer == null) {
                return -1;
            }
        }
        return integer.intValue();
    }
}
