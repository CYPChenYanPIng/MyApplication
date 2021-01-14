package fragmenttest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.uxin.myapplication.R;

/**
 * Created by shuipingyue@uxin.com on 2021/1/12.
 */
public class OneFragmentActivity extends FragmentActivity {


    public static void start(Context context) {
        Intent starter = new Intent(context, OneFragmentActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_fragment);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.container,new ChatFragment());
        fragmentTransaction.replace(R.id.container,new ChatFragment());
        fragmentTransaction.commit();
    }


}
