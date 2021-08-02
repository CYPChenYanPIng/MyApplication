package kotlintest

enum class KotlinEnum(val a: Int) {
    RED(3), YELLOW(4), BLUE(5); //kotlin中唯一必须使用分号的地方，把常量和方法分开，没有方法可以不用；

    fun g() = a
}