package fragmenttest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.example.uxin.myapplication.R;

import java.util.ArrayList;

/**
 * @author chenyanping
 * @date 2020-07-30
 */
public class FragmentTestActivity extends FragmentActivity {
    private ViewPager viewPager;
    private MyViewpagerFragmentAdapter viewpagerAdapter;

    public static void launch(Context context) {
        Intent starter = new Intent(context, FragmentTestActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
        initView();
        initData();
    }

    private void initView() {
        viewPager = findViewById(R.id.vp);
    }

    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>(4);
        fragments.add(new HomeCombinationFragment());
        fragments.add(new LivingFragment());
        fragments.add(new ChatFragment());
        fragments.add(new PersonFragment());

        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(new MyViewpagerFragmentAdapter(getSupportFragmentManager(),fragments));
    }

}
