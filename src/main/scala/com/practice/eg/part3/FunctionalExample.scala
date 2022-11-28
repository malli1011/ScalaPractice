package com.practice.eg
package com.practice.eg.part3

object FunctionalExample extends App {

  val double = new Function1[Int, Int] {
    def apply(i: Int): Int = {
      i * i
    }
  }

  val multiply = new Function2[Int, Int, Int] {
    def apply(i: Int, j: Int): Int = {
      i * j
    }
  }
  //Function1[Int,Function1[Int,Int]]
  val incTimes: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(i: Int): Function1[Int, Int] = new Function[Int, Int] {
      override def apply(j: Int): Int = i * j
    }
  }

  val inc3Times = incTimes(3)
  val inc5Times = incTimes(5)

  println(s"Double : ${double(5)}")
  println(s"Multiply ${multiply(5, 10)}")

  println(s"Increment 3 times: ${inc3Times(10)}")
  println(s"Increment 5 times: ${inc5Times(10)}")
}
