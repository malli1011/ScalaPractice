package com.practice.eg
package com.practice.eg.part3

object LambdaExample extends App {

  val square: Int => Int = (x: Int) => x * x

  val multiply: (Int, Int) => Int = (x: Int, y: Int) => x * y

  //Function1[Int,Function1[Int,Int]]
  val incTimes: Int => Int => Int = (i: Int) => (j: Int) => i * j

  val inc3Times = incTimes(3)
  val inc5Times = incTimes(5)

  println(s"Double : ${square(5)}")
  println(s"Multiply ${multiply(5, 10)}")

  println(s"Increment 3 times: ${inc3Times(10)}")
  println(s"Increment 5 times: ${inc5Times(10)}")

  //careful
  var noArgsFunc: () => String = () => "Hello Scala Developers"
  //This will print the object reference value
  println(noArgsFunc)
  //This will print the function returned string
  println(noArgsFunc())

  //MOAR syntactic sugar : this will work only when the parameters are used once and in the same order they are defined.

  val niceIncrement: Int => Int = _ + 1 //equivalent to x => x+1
  val niceAdder: (Int, Int) => Int = _ + _ //equivalent to (x,y) => x+Y
  //val square: (Int) => Int = _ + _ //This will not work as we need to use one variable two to double it.
}
