package interfacetest;

import android.app.Application;
import android.widget.Toast;

import com.example.uxin.myapplication.ChenApplication;

/**
 * Created by daishiwen@baidu.com on 2020/10/15.
 */
public class Worker implements IWork {


    @Override
    public void startWork(String s) {
        Toast.makeText(ChenApplication.getContext(),s,Toast.LENGTH_LONG).show();
    }

}
