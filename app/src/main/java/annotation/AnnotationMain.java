package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by shuipingyue@uxin.com on 2021/1/6.
 */
class AnnotationMain {
    public static void main(String[] args) {
        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();
        // aClass对应的类是否被注解
        if (aClass.isAnnotationPresent(TestAnnotation.class)) {
            // 获取指定的注解
            TestAnnotation annotation = aClass.getAnnotation(TestAnnotation.class);
            // 获取类Person上的所有的注解
            Annotation[] annotations = aClass.getAnnotations();
            System.out.println("jieguo:" + annotation.name() + "," + annotation.getCode());
        }
        // 获取方法上的注解
        try {
            Class<?> personClass = Class.forName("annotation.Person");
            Method gotoWork = personClass.getMethod("gotoWork", int.class);
            if (gotoWork.isAnnotationPresent(TestAnnotation.class)) {
                TestAnnotation annotation = gotoWork.getAnnotation(TestAnnotation.class);
                System.out.println("method:" + annotation.name() + "," + annotation.getCode());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // 属性上的注解
        Class<?> aClass1 = null;
        try {
//            aClass1 = Class.forName("annotation.Person");
            aClass1 = person.getClass();
            // name 在person中是public 修饰的属性，可以用aClass1.getField("name");
            // 如果name不是用public修饰的，需要用getDeclaredField，他可以获取person中的所有的属性，找到name
            Field name = aClass1.getDeclaredField("name");
            name.setAccessible(true);// 设置为可以访问，因为name属性为private，不可访问
//            name.set();
            System.out.println(name.get(person));// 获取name的值，比如name = "www"
            // 修改name的值为"sss"
            name.set(person,"sss");
            System.out.println(name.get(person));
            if (name.isAnnotationPresent(TestAnnotation.class)) {
                TestAnnotation annotation = name.getAnnotation(TestAnnotation.class);
                System.out.println("feild:" + annotation.name() + "," + annotation.getCode());
            }
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
