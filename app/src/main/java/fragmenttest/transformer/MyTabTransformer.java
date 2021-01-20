package fragmenttest.transformer;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by shuipingyue@uxin.com on 2021/1/20.
 */
public class MyTabTransformer implements ViewPager.OnPageChangeListener , TabLayout.BaseOnTabSelectedListener {
    //缩放比列,默认0.7
    private float scale = 0.7f;
    //记录上一次滑动的positionOffsetPixels值
    private int lastPositionOffsetPixels = 0;
    //是否手指滑动
    private boolean isDragging = false;
    //选中和上次选中的位置
    private int selectPosition = 0, lastSelectPosition = 0;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public MyTabTransformer(TabLayout tabLayout, ViewPager viewPager) {
        this.tabLayout = tabLayout;
        this.viewPager = viewPager;
        tabLayout.addOnTabSelectedListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }


    /**
     * tab动画
     *
     * @param lastSelectPosition
     * @param selectPosition
     * @param variableScale
     */
    private void tabScale(int lastSelectPosition, int selectPosition, float variableScale) {
        if (tabLayout == null) {
            return;
        }
        TabLayout.Tab lastSelectTab = tabLayout.getTabAt(lastSelectPosition);
        TabLayout.Tab selectedTab = tabLayout.getTabAt(selectPosition);
        if (lastSelectTab != null && selectedTab != null) {
            View lastSelectView = lastSelectTab.getCustomView();
            View selectedView = selectedTab.getCustomView();
            if (lastSelectView != null && selectedView != null) {
                lastSelectView.setPivotX(lastSelectView.getMeasuredWidth() / 2.0f);
                lastSelectView.setPivotY(lastSelectView.getMeasuredHeight());
                selectedView.setPivotX(selectedView.getMeasuredWidth() / 2.0f);
                selectedView.setPivotY(selectedView.getMeasuredHeight());
                //缩小
                lastSelectView.setScaleY((1 + scale) - variableScale);
                lastSelectView.setScaleX((1 + scale) - variableScale);
                //放大
                selectedView.setScaleY(1 + variableScale);
                selectedView.setScaleX(1 + variableScale);
            }
        }
    }

    /**
     * 直接放大到 1+scale 倍数
     * @param selectPosition
     */
    private void tabScale(int selectPosition) {
        TabLayout.Tab selectedTab = tabLayout.getTabAt(selectPosition);
        if (selectedTab != null) {
            View selectedView = selectedTab.getCustomView();
            if (selectedView != null) {
                selectedView.setPivotX(selectedView.getMeasuredWidth() / 2.0f);
                selectedView.setPivotY(selectedView.getMeasuredHeight());
                selectedView.setScaleY(1 + scale);
                selectedView.setScaleX(1 + scale);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (isDragging) {
            //滑动
            if (lastPositionOffsetPixels != 0 && positionOffset != 0) {
                if (lastPositionOffsetPixels > positionOffsetPixels) {
                    //右滑 1-0 屏幕宽度-0
                    tabScale(position + 1, position, (1 - positionOffset) * scale);
                } else if (lastPositionOffsetPixels < positionOffsetPixels) {
                    //左滑 0-1 0-屏幕宽度
                    tabScale(position, position + 1, positionOffset * scale);
                }
            }
            lastPositionOffsetPixels = positionOffsetPixels;
        } else {
            //点击
            if (selectPosition > lastSelectPosition) {
                //左滑 0-1 0-屏幕宽度
                //只有滑动到最后一页做缩放操作，防止中间间隔多页tab缩放动画跳动异常
                //选中的时候position == selectPosition，最后一页滑动的时候position == selectPosition - 1
                if(position == selectPosition && positionOffset == 0){
                    tabScale(lastSelectPosition, selectPosition, scale);
                }else if(position == selectPosition - 1){
                    tabScale(lastSelectPosition, selectPosition, positionOffset * scale);
                }
            } else if (selectPosition < lastSelectPosition) {
                //右滑 1-0 屏幕宽度-0
                //只有滑动到最后一页做缩放操作，防止中间间隔多页tab缩放动画跳动异常 position == selectPosition
                if (position == selectPosition && positionOffset == 0) {
                    tabScale(lastSelectPosition, selectPosition, scale);
                } else if (position == selectPosition) {
                    tabScale(lastSelectPosition, selectPosition, (1 - positionOffset) * scale);
                }
            } else if (selectPosition == 0 && positionOffset == 0) {
                //默认选中第一个tab
                //positionOffset 值为0，只来一次回调
                tabScale(selectPosition);
            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            isDragging = false;
        } else if (state == ViewPager.SCROLL_STATE_DRAGGING) {
            isDragging = true;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        selectPosition = tab.getPosition();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        lastSelectPosition = tab.getPosition();
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
