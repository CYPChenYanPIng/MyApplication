package annotation;

import android.util.Log;

/**
 * Created by shuipingyue@uxin.com on 2021/1/6.
 */
@TestAnnotation(name = "00",getCode = 0)
public class Person {

    private String TAG = "Person";

    @TestAnnotation(name = "123")
    private String name = "www";

    @TestAnnotation(name = "4", getCode = 4)
    public void gotoWork(int time) {
        Log.i(TAG, "gotoWork: ");
    }
}
