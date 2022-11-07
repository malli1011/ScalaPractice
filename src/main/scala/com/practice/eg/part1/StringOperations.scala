package com.practice.eg
package com.practice.eg.part1

object StringOperations extends App {
  val str = "Hello world of Scala"
  println(str.length)
  println(str.toUpperCase())
  println(str.replace(' ', '_'))

  //Scala specific functions
  val aNum = "2"
  var num: Int = aNum.toInt
  println(num)
  println(str.reverse)
  println(str.take(5))

  //Scala string interpolators
  //S-interpolators
  val name = "David"
  val age = 25
  val msg = s"Hello My name is $name. I am $age years old"
  val msg2 = s"Hello My name is $name. I will be turning ${age + 1} years old"
  println(msg)
  println(msg2)

  //F-interpolators
  //We can specify the format of the arguments and can type the arguments

  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  //Below line will throw error as $name is not an integer.
  //val myth2 = f"$name%d can eat $speed%2.2f burgers per minute"
  println(myth)

  //raw-interpolator

  println(raw"this is a \n new line")

  val escaped = "this is a \n new line"
  println(raw"$escaped")

}
