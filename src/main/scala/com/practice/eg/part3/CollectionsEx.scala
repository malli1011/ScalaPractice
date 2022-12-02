package com.practice.eg
package com.practice.eg.part3

object CollectionsEx extends App {

  val nums = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("white", "black")

  private val combinations = chars.flatMap(x => nums.map(a => s"$x$a"))
  private val combinations2 = chars.flatMap(x => nums.flatMap(a => colors.map(cols => s"$x$a$cols")))
  println(combinations2)

  //the above can be simplified using for-comprehensions

  private val forCombinations = for {
    x <- nums
    a <- chars
    cols <- colors
  } yield s"$x$a$cols"

  println(forCombinations)

  private val forEvenCombinations = for {
    x <- nums if x % 2 == 0
    a <- chars
    cols <- colors
  } yield s"$x$a$cols"

  println(forEvenCombinations)


}
