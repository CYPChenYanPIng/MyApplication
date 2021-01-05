package kotlintest

import android.content.Context
import java.util.*

/**
 * Created by shuipingyue@uxin.com on 2020/10/28.
 */
//class Test {
//
//    var age:Int = 12
//    val name:String = "chen"
//    val name2: String? = null
//
//
//}

var age:Int = 3
var name: String = "chen"
var name2 :String? = null


fun main() {
//    name = name2!!
//    name2 = name
    printlen(name)


}

// 编译生成的方法是public final static printlen
fun printlen(str: String): String {
    println("这个字符串是：$str")
    return str
}

fun testInnerClass(){
    // 内部类的调用 不用像java需要创建事例    ？？？？？
    Person.sayMessage("内部类")
}

// 以object开头 后面跟类的声明  是匿名内部类
object Person {
    fun sayMessage(msg: String) {
        println(msg)
        var java = JaveTest::class.java
    }
}

// 类的class
// java





