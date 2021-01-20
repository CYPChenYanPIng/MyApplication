package fragmenttest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uxin.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import fragmenttest.loop.LoopViewPagerAdapter;
import fragmenttest.transformer.MyPagerTransformer;


/**
 * loopAdapter
 * @author chenyanping
 * @date 2020-07-30
 */
public class HomeCombinationFragment extends Fragment {

    private String TAG = "HomeCombinationFragment";

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
        // container 写不写都一样，布局没有改变
        View view = inflater.inflate(R.layout.fragment_home_combination,container,false);
        Log.i(TAG, "onCreateView: ");
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mViewPager = view.findViewById(R.id.vp_loop);
    }

    private void initData() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.bg_live_rest_card_placeholder);
        list.add(R.drawable.radio_mb_bj_role_default_fuzzy);
        list.add(R.mipmap.zhanwei);

        LoopViewPagerAdapter viewPagerAdapter = new LoopViewPagerAdapter(list,mViewPager);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setPageTransformer(false,new MyPagerTransformer(mViewPager));

        viewPagerAdapter.startLoop();
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
