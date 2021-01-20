package fragmenttest.pageradapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import fragmenttest.frag.DynamicFragment;
import fragmenttest.frag.RecommedFragment;

/**
 * 固定的fragemnt的数量
 * Created by shuipingyue@uxin.com on 2021/1/19.
 */
public class MyLivingFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    private List<String> mTitles ;

    public MyLivingFragmentPagerAdapter(FragmentManager fm,List<String> titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        switch (i) {
            case 0:
                fragment = new DynamicFragment();
                break;
            case 1:
            default:
                fragment = new RecommedFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles != null ? mTitles.size() : 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
