package fragmenttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @author chenyanping
 * @date 2020-07-30
 */
public class MyViewpagerFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();

    public MyViewpagerFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments != null && position >-1&& position>fragments.size()) {
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
