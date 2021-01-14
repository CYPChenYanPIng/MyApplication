package fragmenttest;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by shuipingyue@uxin.com on 2021/1/13.
 */
public class MyStatePagerAdapter extends FragmentStatePagerAdapter {

    private final String TAG = "StatePagerAdapter_Fragment";
    List<Fragment> fragments ;

    public MyStatePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.i(TAG, "instantiateItem: ");
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.i(TAG, "destroyItem: ");
        super.destroyItem(container, position, object);
    }
}
