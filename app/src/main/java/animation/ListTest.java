package animation;


import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by shuipingyue@uxin.com on 2021/1/14.
 */
class ListTest {
    public static void main(String[] args) {
        arrayListTest();
    }

    private static void arrayListTest(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(0);
        for (Integer a:list) {
            System.out.println(a);
        }
        list.remove(Integer.valueOf(0));
        for (Integer b:list) {
            System.out.println(b);
        }
    }

    private static void linkedListTest(){

    }
}
