package com.example.uxin.myapplication;

import android.util.Log;

/**
 * Created by shuipingyue@uxin.com on 2020/10/22.
 */
public class PrarentLoadOrder {

    private String s = "kaishi";
    public PrarentLoadOrder() {
        System.out.println("PrarentLoadOrder的无参数构造方法");
        Log.i("cyp", "PrarentLoadOrder:的无参数构造方法 ");
    }

    public PrarentLoadOrder(String s) {
        this.s = s;
        System.out.println("PrarentLoadOrder的有 参数构造方法");
        Log.i("cyp", "PrarentLoadOrder: 的有 参数构造方法");
    }

    public void printString(){
        System.out.println("s   :"+s);
    }
}
