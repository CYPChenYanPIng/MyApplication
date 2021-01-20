package fragmenttest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RadioGroup;

import com.example.uxin.myapplication.R;

import java.util.ArrayList;

import fragmenttest.transformer.MyPagerTransformer;

/**
 * fragment+viewpager
 * @author chenyanping
 * @date 2020-07-30
 */
public class FragmentTestActivity extends FragmentActivity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
//    private MyViewpagerFragmentAdapter viewpagerAdapter;
    private MyStatePagerAdapter viewpagerAdapter;
    private String TAG = "FragmentTestActivity";
    private RadioGroup radioGroup;

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
        radioGroup = findViewById(R.id.radio_group);
    }


    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeCombinationFragment());
        fragments.add(new LivingFragment());
        fragments.add(new ChatFragment());
        fragments.add(new PersonFragment());

//        viewPager.setOffscreenPageLimit(fragments.size());
//        viewpagerAdapter = new MyViewpagerFragmentAdapter(getSupportFragmentManager(),fragments);
        viewpagerAdapter = new MyStatePagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(viewpagerAdapter);

        viewPager.setPageTransformer(false,new MyPagerTransformer(viewPager));

        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);

        radioGroup.check(R.id.first);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int currentItem = viewPager.getCurrentItem();

                switch (checkedId) {
                    case R.id.first:
                        Log.i(TAG, "onCheckedChanged: first"+currentItem);
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.live:
                        Log.i(TAG, "onCheckedChanged: live"+currentItem);
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.chat:
                        Log.i(TAG, "onCheckedChanged: chat"+currentItem);
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.my:
                        Log.i(TAG, "onCheckedChanged: my"+currentItem);
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    @Override
    public void onPageScrolled(int position, float positionOffset,  int positionOffsetPixels) {
        // 先走onPageSelected方法，再走这个方法
        // postion：如果是左滑动pos从0到1， 当前页面的pos，手指触碰的当前页面的pos，调用很多次，最后一次是滑动后的页面的pos，positionOffset:0.0,positionOffsetPixels0
        // position     如果是右滑pos从1到0，pos是selectedPos，
        // positionOffset 当前页面偏移的百分比  左滑从pos为0到1，递增，反之，递减，变化范围0-1
        // positionOffsetPixels 当前页面偏移的像素数,变化氛围0-手机宽的像素数，
        // positionOffsetPixels：如果是左滑从pos为0滑动到pos为1，positionOffsetPixels变化趋势是递增，反过来，递减
        Log.i(TAG, "onPageScrolled: "+ position +",positionOffset:"+positionOffset+",positionOffsetPixels"+positionOffsetPixels);
    }


    @Override
    public void onPageSelected(int position) {
        // 只调用一次，滑动完，你当前选中的页面pos      一般在state为2后，开始走此方法
        Log.i(TAG, "onPageSelected: "+position);
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        switch (position) {
            case 0:
                radioGroup.check(R.id.first);
                break;
            case 1:
                radioGroup.check(R.id.live);
                break;
            case 2:
                radioGroup.check(R.id.chat);
                break;
            case 3:
                radioGroup.check(R.id.my);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // 状态发生改变时调用，一次一面切换需要经历状态过程为，1：用户拖拽-->2:用户手指抬起-->0：滑动动画做完
        // 如果是用户滑动引起的页面的滑动，状态会有1，如果是点击tablayout的标签，引起的页面的滑动，没有状态1，是2--->0
        Log.i(TAG, "onPageScrollStateChanged: "+state);
    }
}
