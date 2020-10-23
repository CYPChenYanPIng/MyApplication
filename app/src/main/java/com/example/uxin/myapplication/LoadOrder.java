package com.example.uxin.myapplication;

import android.util.Log;
import android.util.SparseIntArray;

/**
 * 代码块，构造方法
 * @author chenyanping
 * @date 2020-06-17
 */
public class LoadOrder extends PrarentLoadOrder{
    private static SparseIntArray map = new SparseIntArray();

    private int num = 30;

    //静态代码块:随着类的加载而加载，只加载一次，加载类时需要做的一些初始化，比如加载驱动
    static {
        map.put(1,100);
        map.put(2,101);
        map.put(3,103);
        Log.i("cyp","静态代码块");
    }

    //构造代码块,提取构造方法中的共性，每次创建对象都会执行，并且在构造方法之前执行
    {
        map.put(4,104);
        map.put(5,105);
        Log.i("cyp","普通代码块");
    }

    public LoadOrder(String s) {
        // 子类默认会调用父类的无参构造方法
        Log.i("cyp","构造方法");
    }

    public static int getIntValue(int index){
        Log.i("cyp","静态方法");
        return map.get(index);
    }

    public int getNum(){
        return num;
    }
}
