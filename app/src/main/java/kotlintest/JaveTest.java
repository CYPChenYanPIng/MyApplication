package kotlintest;

import java.io.IOException;

/**
 * Created by shuipingyue@uxin.com on 2020/10/28.
 */
class JaveTest {

    public static void main(String[] args) {
        TestKt.printlen("java 测试");

        Class<JaveTest> javeTestClass = JaveTest.class;
        Class<TestKt> testKtClass = TestKt.class;
    }

}
