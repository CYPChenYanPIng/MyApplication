package kotlintest

import android.util.Log
import java.lang.StringBuilder
import java.util.ArrayList
import java.util.TreeMap


class KotlinClass {
    fun testWhen(num: KotlinEnum) {
        val value = when (num) {
            KotlinEnum.RED,KotlinEnum.BLUE -> "k"
            else -> "else"
        }
        println("when:is $value")
    }

    fun whenTest(num1: KotlinEnum,num2: KotlinEnum){
        when  {
            num1 == KotlinEnum.BLUE && num2 == KotlinEnum.RED ||
                num1 == KotlinEnum.RED && num2 == KotlinEnum.BLUE -> 1
            else -> 2
        }
    }

    fun testSet(){
        // setOf返回的是不可变的有序的集合
        val  set = setOf<String>("2","3")
        // for + in 是对set集合的遍历
        for (name in set) {
            println(name)
        }
    }

    fun testIs(stu: Fu){
        // is 相当于java中的instanceOf，不同之处，java中需要显示的类型转换后才能使用，如果需要多次使用需要用局部变量保存，kotlin不需要
        if (stu is Stu) {
            // 能访问的属性必须是一个val属性，不能有自定义的访问器，否则不能保证每次返回同样的值
            println(stu.a)
        }
        // 特定类型的显示转换
        val stu1 = stu as Stu
        println(stu1.a)
    }

    fun testFor(){
        val array = IntArray(4)
        // array这个数组的下标
        for (i in array.indices){

        }
        // 包含右边的结束值的区间
        for (i in 100 downTo 1 step 2) {

        }
        // until 不包含100的区间
        for (i in 1 until 100) {

        }

        val treeMap:TreeMap<Char,String> = TreeMap()
        // map，通过map[key] 来读取值 ，map[key] = value 赋值，不用通过put，get方法
        treeMap['a'] = "aaaa"
        treeMap['b'] = "bbbb"
        // treemap的遍历
        for ((key,value) in treeMap) {
            println("$key = $value")
        }

        val list = arrayListOf<Int>(3,5)
        for ((index,element) in list.withIndex()) {

        }

        val percentage = 0
        if (percentage !in 0..100) {
            // throw是一个表达式
            throw IllegalArgumentException("")
        }

        // try是一个表达式
        // 如果一个try代码块执行一切正常代码块中的最后一个表达式就是结果，如果捕获了异常，相应的catch代码块中最后一个表达式就是结果
        val number = try {
            Integer.parseInt("34")
        } catch (e:NumberFormatException) {
            return
        }

        println(number)

    }
}

class Stu :Fu{
    val a = 10

    fun testCollection(){
        // to 中缀调用  to函数有infix修饰
        val map = hashMapOf<Int,String>(1 to "one",7 to "seven")

        val list = listOf<Int>(1,2,3)
        println(list)
        list.max()
        list.last()
        list.joinToString(separator = ";",prefix = " ")

    }

    fun testStringRegex(path:String){
        path.substringBeforeLast("/")
    }

    override fun click() {
        super.click()
    }
}

fun main() {
//    val stu = Stu();
//    stu.testCollection()
//    "kotlin".lastChar()
//
//    val listOf = listOf(PersonW("seg", 10), PersonW("weij", 56), PersonW("dew", 90))
//    println(listOf.maxBy { personW -> personW.age })

//    val list = ArrayList<Int>()
//    list.add(3)
//    list.add(4)
//    // 只能输出3，4
//    for (i in 0 until list.size) {
//        println(list[i])
//        if (i<2) {
//            list.add(i + 5)
//        }
//    }
//    // 可以输出3，4，5，6
//    var i = 0
//    while (i<list.size) {
//        println(list[i])
//        if (i<2) {
//            list.add(i + 5)
//        }
//        i++
//    }
    val stringBuilder: StringBuilder? = null
    stringBuilder.apply {
        System.out.println(111)
    }
}

interface Fu{
    fun click() {
        println("Fu click")
    }
}
