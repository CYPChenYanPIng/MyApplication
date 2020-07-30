package com.example.uxin.myapplication

/**
 * @author chenyanping
 * @date 2020-01-17
 */

data class Person (var name:String,var age:Int ? = null)

fun main(args:Array<String>){
    var persons = listOf(Person("jk"),Person("ww",age = 29))
    var oldest = persons.maxBy { it.age ?:0}
    println("the oldest is : $oldest")

}