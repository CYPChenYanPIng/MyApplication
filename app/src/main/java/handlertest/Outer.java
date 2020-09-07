package handlertest;


/**
 * @author chenyanping
 * @date 2020-08-03
 */
public class Outer {
    private int a = 10;
    private int getA(){
        return a;
    }

    private void setA(int a){
        this.a = a;
    }

    private void callInnerVar(){
        Inner inner = new Inner();
        inner.b = 2;
        inner.callOuterMethod();
    }

    class Inner{
        private int b = 20;
        private void callOuterMethod(){
            setA(20);
            System.out.println(getA());
        }
    }
}
