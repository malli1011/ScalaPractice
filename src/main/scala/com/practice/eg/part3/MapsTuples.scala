package com.practice.eg
package com.practice.eg.part3

object MapsTuples extends App {

  val tuple1 = Tuple2(1, "One")
  val tuple2 = (2, "Two") //same as Tuple2[Int,String](1, "One")

  println(tuple1)
  println(tuple2)

  println(s"${tuple1._1}  ${tuple1._2}")
  val tuple3 = tuple1.copy(_2 = "Two")
  println(tuple3)
  println(tuple3.swap)

  val map = Map(1 -> "One", 2 -> "Two", 3 -> "Three", 4 -> "Four", 5 -> "Five")
  println(map)

  println(map.get(1))
  println(map.get(10)) // this will return None
  //println(map(10)) // this will throw exception as we are using apply  method.

  val map2 = Map(1 -> "One", 2 -> "Two", 3 -> "Three", 4 -> "Four", 5 -> "Five", 6 -> "six").withDefaultValue("Not Found")
  println(s"With default value (${map2(10)})") // will print default value as there is not key 10

  //Map internally uses Tuple2 so they are inter operable
  println(Map(tuple1, tuple2))

  val map3 = map2 + (7 -> "Seven")
  println(map3)
  // functions on map map().flatMap(),filter()
  println(map3.map(pair => (pair._1, pair._2.toLowerCase())))

  println(map3.filter(pair => pair._1 > 5))
  println(map3.map(pair => (pair._1, pair._2.toLowerCase())).filter(pair => pair._2.startsWith("t")))

  // get all keys or values of a map and then apply functions
  println(map3.view.keys.toList.filter(key => key > 4))
  println(map3.view.mapValues(value => "Value: " + value).toList)

  val map4 = Map("Jim" -> 123, "Dan" -> 234, "jim" -> 765)
  println(map4.map(pair => (pair._1.toLowerCase(),pair._2)))
}