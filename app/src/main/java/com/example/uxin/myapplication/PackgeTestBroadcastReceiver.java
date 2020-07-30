package com.example.uxin.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author chenyanping
 * @date 2019-10-11
 */
public class PackgeTestBroadcastReceiver extends BroadcastReceiver {

    private static final String TEST_PACKGE = "com.uxin.live.image.test";


    @Override
    public void onReceive(Context context, Intent intent) {
        if (TEST_PACKGE.equals(intent.getAction())){
            Toast.makeText(context,"收到广播 ",Toast.LENGTH_LONG).show();
        }
    }
}
