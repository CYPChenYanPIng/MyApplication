package kotlintest

 data class PersonW (val name:String?, var age:Int = 0) {

    // 构造函数执行之后，开始init
    init {

    }

     fun main(args:Array<String>) {
         print("hello,world")
         val a :Int = 3
         var name : String = "k"
        // 输出   hello k
        print("hello $name")
         // 输出 $name
         print("\$name")
     }

     fun testString(name:String){
         print(name)
         println(this.name)
         this.age = 2
     }
}