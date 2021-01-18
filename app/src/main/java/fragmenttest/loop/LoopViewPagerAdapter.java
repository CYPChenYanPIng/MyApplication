package fragmenttest.loop;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuipingyue@uxin.com on 2021/1/18.
 */
public class LoopViewPagerAdapter extends PagerAdapter {

    private List<Integer> mResIdList ;

    private ViewPager mViewPager;

    public LoopViewPagerAdapter(List<Integer> list,ViewPager viewPager) {
        if (list != null) {
            mResIdList = list;
        } else {
            mResIdList = new ArrayList<>();
        }
        mViewPager = viewPager;

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        stopLoop();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        startLoop();
                        break;
                }
                return false;
            }
        });
    }




    @Override
    public int getCount() {
        return mResIdList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Integer resId = mResIdList.get(position);
        ImageView imageView = new ImageView(container.getContext());
        imageView.setImageResource(resId);
       container.addView(imageView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
               ViewGroup.LayoutParams.MATCH_PARENT));
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                startLoop();
            }
        }
    };

    public void startLoop(){
        int currentItem = mViewPager.getCurrentItem();
        currentItem++;
        if (currentItem % mResIdList.size() == 0) {
            mViewPager.setCurrentItem(0,false);
        } else {
            mViewPager.setCurrentItem(currentItem % mResIdList.size());
        }
        handler.sendEmptyMessageDelayed(1,2000);
    }

    public void stopLoop(){
        if (handler != null) {
            handler.removeMessages(1);
        }
    }



}
