package scrollnum;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.uxin.myapplication.R;

import scrollnumber.BulletinBoardView;
import scrollnumber.RiseNumberTextView;

/**
 *
 * 数字滚动 规则：改变的数字滚动，不改变的保持不动
 * 比如17到18  7滚动到8，1保持不变
 *
 * @author chenyanping
 * @date 2020-07-14
 */
public class ScrollNumActivity extends Activity {

    private BulletinBoardView bulletinBoardView;

    private TextView sign;

    private RiseNumberTextView riseNumberTextView;

    /**
     *
     */
    private final static int SETTING_PAGE_BIND_PHONE = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        riseNumberTextView.withNumber(10).start();
    }

    /**
     * 数字滚动   17 到18 7滚动显示8， 这个是参考ViewFlipper   公告轮播可以使用ViewFlipper实现
     */
    private void  startScrollDigitView(){
        Integer number = 99;
        int nextNumber = number+1;
        char[] chars = number.toString().toCharArray();
        char[] chars1 = String.valueOf(nextNumber).toCharArray();
        String budong = "";
        String dongStart = "";
        String dongEnd = "";
        if (chars.length < chars1.length) {
            dongStart = number.toString();
            dongEnd = String.valueOf(nextNumber);
        } else {
            for (int i = 0;i < chars.length;i++){
                if (chars[i] == chars1[i]) {
                    budong = budong +chars[i];
                } else {
                    dongStart = number.toString().substring(i);
                    dongEnd = String.valueOf(nextNumber).substring(i);
                    break;
                }
            }
        }
        sign.setText(String.format(getString(R.string.continuous_sign),budong));

        TextView textView = new TextView(this);
        textView.setText(dongStart);
        textView.setTextSize(25);
        TextView textView1 = new TextView(this);
        textView1.setText(dongEnd);
        textView1.setTextSize(25);
        bulletinBoardView.addFlippingView(textView,textView1);
    }

    private void scrollAnim(){
        //        scrollingNumbersView.setInitialValue(9);
////        scrollingNumbersView.addScrollingNumber(new ScrollingNumbersView.ScrollingNumber(1,true));
//        scrollingNumbersView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                int [] arr = new int[]{1,0};
//                scrollingNumbersView.setNumberAnimatorValues(arr);
//                scrollingNumbersView.startNumberAnimator();
//            }
//        },3000);
    }



    private void testFragment(){
        Fragment fragment = new MyFragment();

    }


}
