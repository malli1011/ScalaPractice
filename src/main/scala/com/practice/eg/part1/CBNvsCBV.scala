package com.practice.eg
package com.practice.eg.part1

object CBNvsCBV extends App {

  //The argument expression is evaluated only once
  def callByValue(x: Long): Unit = {
    println("call By Value : " + x)
    println("call By Value : " + x)
    println("call By Value : " + x)
  }

  //The argument expression is evaluated everytime the value is used.
  //The expression is not evaluated until it is used or called.
  def callByName(x: => Long): Unit = {
    println("call By Name : " + x)
    println("call By Name : " + x)
    println("call By Name : " + x)
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x:Int, y: =>Int): Unit = println(x)

  //println(infinite()) // this will cause stack overflow exception

  printFirst(10,infinite()) // this will work. infinite is call by reference and it is never evaluated inside the function.

}
