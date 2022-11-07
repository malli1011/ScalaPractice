package com.practice.eg
package com.practice.eg.part1

import scala.annotation.tailrec
import scala.jdk.Accumulator

object Recursion extends App {

  //Normal recursion,but it may not work well for large numbers. It gives stack overflow error
  def factorial(n: Int): BigInt = {
    if (n == 1) 1
    else
      n * factorial(n - 1)
  }

  //Tail recursion, works well for larger numbers also.

  def anotherFactorial(i: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factHelper(x - 1, accumulator * x)
    }

    factHelper(i, 1)
  }

  println(factorial(10))
  //println(factorial(50000)) // this will cause stack overflow
  //println(anotherFactorial(50000)) //this will work

  def concatString(str: String, num: Int): String = {
    @tailrec
    def concatStringTail(aString: String, i: Int, accumulator: String): String = {
      if (i <= 0) accumulator
      else concatStringTail(aString, i - 1, accumulator + aString)
    }

    concatStringTail(str, num, "")
  }

  println(concatString("Malli", 10))

  def isPrime(i: Int): Boolean = {
    @tailrec
    def isPrimeTail(n: Int, isPrimeAcc: Boolean): Boolean = {
      if (!isPrimeAcc) false
      else if (n <= 1) true
      else isPrimeTail(n - 1, i % n != 0 && isPrimeAcc)
    }

    isPrimeTail(i / 2, true)
  }

  println(isPrime(2003))
  println(isPrime(629))
  println(isPrime(1000000007))


  def fibonacciNormal(i:Int):Int={
    if (i<=2) 1
    else fibonacciNormal(i-1)+fibonacciNormal(i-2)
  }
  println(fibonacciNormal(8))

    def fibonacci(i : Int) :Int={
      @tailrec
      def fibonacciTail(n:Int, last:Int, lastButOne:Int):Int = {
        if(n>=i) last
        else fibonacciTail(n+1, last+lastButOne, last)
      }

      if(i<=2) 1
      else fibonacciTail(2,1,1)

    }

    println(fibonacci(8))

}
