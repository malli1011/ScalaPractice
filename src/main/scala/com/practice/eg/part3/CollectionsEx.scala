package com.practice.eg
package com.practice.eg.part3

import scala.util.Random

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

  val sequence = Seq(1, 2, 3, 4)

  val range = 1 to 10
  println(range)

  // both will range from 1 to 10
  (1 to 10).foreach(println)
  (1 until 10).foreach(println)

  //prepend 0 and append 5 to the list
  val aList = 0 +: sequence :+ 5
  println(aList)

  val apples_5 = List.fill(5)("apple")
  println(apples_5)
  println(apples_5.mkString(","))

  //Arrays are mutable
  val arrayOfNums: Array[Int] = Array(1, 2, 3)
  println(arrayOfNums.mkString(","))

  //mutation
  arrayOfNums(1) = 5;
  println(arrayOfNums)

  //Initiates an Array of length 3 with default values (null for string)
  val strings = Array.ofDim[String](3)
  println(strings.mkString(" "))

  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  //Vector is faster than lists while performing more updates to the collection

  //Vector vs List

  val maxRuns = 1000
  val maxCapacity = 10000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random()
    val times = for {
      n <- 1 to maxRuns
    } yield {
      val start = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - start
    }

    times.sum * 1.0 / maxRuns;

  }

  println(getWriteTime((1 to maxCapacity).toList))
  println(getWriteTime((1 to maxCapacity).toVector))

}
