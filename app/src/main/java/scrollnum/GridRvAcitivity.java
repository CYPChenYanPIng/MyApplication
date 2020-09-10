package scrollnum;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.uxin.myapplication.R;

/**
 *
 * 1.activity嵌套fragment
 * 2。fragment嵌套frament
 * 3。viewpager+fragment
 * 4。tabLayout+viewpager+fragment
 * @author chenyanping
 * @date 2020-09-07
 */
public class GridRvAcitivity extends Activity {


    public static void launch(Context context) {
        Intent starter = new Intent(context, GridRvAcitivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_rv);
        initFragment();
    }

    private void initFragment() {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MyFragment myFragment = new MyFragment();
        fragmentTransaction.add(R.id.container,myFragment,"container");
//        fragmentTransaction.addToBackStack();
        fragmentTransaction.commit();

//        Fragment container = fragmentManager.findFragmentByTag("container");

    }
}
