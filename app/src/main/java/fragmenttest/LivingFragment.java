package fragmenttest;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uxin.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import fragmenttest.pageradapter.MyLivingFragmentPagerAdapter;
import fragmenttest.transformer.MyTabTransformer;
import viewtest.MyTextView;


/**
 * @author chenyanping
 * @date 2020-07-30
 */
public class LivingFragment extends Fragment {

    private String TAG = "LivingFragment";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_living, null);
        mViewPager = inflate.findViewById(R.id.viewpager);
        mTabLayout = inflate.findViewById(R.id.tab_layout);
        //必须和Mode是MODE_FIXED才管用，
        // GRAVITY_FILL：平分tablayout的宽度给每个标签
        // GRAVITY_CENTER：所有的标签居中展示，标签的宽度根据标签内容的宽度
//        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        // Mode MODE_FIXED：tablayout的宽度是固定的，如果标签过多，会挤压每个标签，比如title展示为2行，末尾打点
        // MODE_SCROLLABLE：tablayout的宽度不是固定的，如果标签过多可以滚动展示
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        // 标签下面的指示器的宽度，是否占满整个标签，false：可以根据标签内容的宽度适当的展示一个剧中指示器
        mTabLayout.setTabIndicatorFullWidth(true);

        List<String> title = new ArrayList<>();
        title.add("开发jkfjkdk");
        title.add("推荐");
        title.add("开发jkfjkdk");
        title.add("推荐");
        title.add("开发jkfjkdk");
        title.add("推荐");
        title.add("开发jkfjkdk");
        title.add("推荐");
        title.add("开发jkfjkdk");
        title.add("推荐");
        title.add("开发jkfjkdk");
        title.add("推荐");

        MyLivingFragmentPagerAdapter viewPagerAdapter = new MyLivingFragmentPagerAdapter(getChildFragmentManager(),title);
        mViewPager.setAdapter(viewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);


        // tab中的tabView是LinearLayout，icon是add到index为0的位置
//        tabAt.setIcon(R.drawable.icon_live);
        // 修改title的文案
//        tabAt.setText("开发jkfjkdk");
        // 不管用  
//        mTabLayout.setTabTextColors(R.color.color_646161,R.color.color_ff8383);
        // TODO: 2021/1/20 5:12 PM cyp onCreateView

        TabLayout.Tab tabAt = mTabLayout.getTabAt(0);
        tabAt.setCustomView(R.layout.layout_tatlayout_custom_tab);
        tabAt.setIcon(R.drawable.gashapon_share_logo);


        // TODO: 2021/1/20 8:33 PM cyp onCreateView
        MyTabTransformer tabTransformer = new MyTabTransformer(mTabLayout,mViewPager);
        tabTransformer.setScale(0.2f);

        mViewPager.setCurrentItem(0);

        mTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i(TAG, "onTabSelected: "+tab.getPosition());
                // 标签被选中时    当前选中的标签
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i(TAG, "onTabUnselected: "+tab.getPosition());
                // 选中的标签被取消选中时  上一个选中的标签
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i(TAG, "onTabReselected: "+tab.getPosition());
                // 标签被再次选中
            }
        });


        return inflate;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach: ");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG, "setUserVisibleHint: "+isVisibleToUser);
    }
}
