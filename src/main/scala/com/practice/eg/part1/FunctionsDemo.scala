package com.practice.eg.part1

import scala.annotation.tailrec

object FunctionsDemo extends App {

  def aFunction(s: String, i: Int): String = {
    s + "" + i
  }

  def aParameterLessFunction(): Int = 21

  println(aParameterLessFunction())
  println(aFunction("Malli", 10))


  def recursiveFunction(s: String, i: Int): String = {
    if (i == 1) s
    else s + " " + recursiveFunction(s, i - 1)
  }

  println(recursiveFunction("Malli", 5))

}
