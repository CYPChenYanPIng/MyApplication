package cross.scene.animation;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Window;

import com.example.uxin.myapplication.R;

/**
 *
 * 转场动画是api21之后官方提供的功能
 *
 *
 */
public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        //  1。 默认是开的
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        //2. 设置ShareElement之外其他的View退出方式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 从屏幕左边滑出
            getWindow().setExitTransition(new Slide(Gravity.LEFT));
        }

    }
}
