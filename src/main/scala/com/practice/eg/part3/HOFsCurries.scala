package com.practice.eg
package com.practice.eg.part3

import scala.runtime.Nothing$

object HOFsCurries extends App {

  //Higher order function (HOF)
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  /*
    function that applies a function n times over a value x
    nTimes(f,n,x)
    nTimes(f,3,x) = f(f(f(x))) = nTimes(f,2,f(x)) = f(f(f(x)))
    nTimes(f,n,x) = f(f(...f(x))) = nTimes(f, n-1,f(x))
   */

  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))
  }

  val plusOne: Int => Int = x => x + 1
  println(nTimes(plusOne, 10, 1))

  //nTimesBetter(f,n) = x =>f(f(f(x)))

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))
  }

  val plus10 = nTimesBetter(plusOne, 10)

  println(plus10(1))


  //Curried functions

  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y

  val add3 = superAdder(3)
  println(add3(10))
  println(superAdder(3)(10))

  def curriedFormatter2: String => Double => String = (c: String) => (x: Double) => c.formatted(x)

  val standardForamt2: (Double => String) = curriedFormatter2("%4.2f")
  val preciseForamt2: (Double => String) = curriedFormatter2("%10.8f")

  println(standardForamt2(Math.PI))
  println(preciseForamt2(Math.PI))


  //above function can be written as function with multiple parameter lists
  //def curriedFormatter: String => Double => String
  def curriedFormatter(c: String)(x: Double): String = c.formatted(x)

  val standardForamt: (Double => String) = curriedFormatter("%4.2f")
  val preciseForamt: (Double => String) = curriedFormatter("%10.8f")

  println(standardForamt(Math.PI))
  println(preciseForamt(Math.PI))


}
