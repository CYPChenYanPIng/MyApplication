package kotlintest

// 扩展函数，是静态函数，在java中可以通过文件名.方法名，直接调用，扩展函数不能被重写
fun String.lastChar():Char = get(length -1)

fun main(args: Array<String>) {
    val dataModel = DataModel(false)
    dataModel.setup()
    println(dataModel.name1)
}

class DataModel (var good:Boolean){

    var name: String? = null  // ??
    lateinit var name1: String
    val age: Int = 2

//    constructor(tt:Int,ww:Int) {
//        println(": constructor")
//    }
    init {
        name = "ww"
        good = true
        println("init: ")
    }

    fun setup() {
        name1 = "lala"
        println(name)
        println(good)
    }

    fun test() {
        // 不能访问里面的name，age
        val p = PersonW("", 2)
    }


}

class Student(val name: String, var age: Int) {
    // 自定义属性访问器
    val isSquare: Boolean
        get() {
            return name == "lala"
        }
}
