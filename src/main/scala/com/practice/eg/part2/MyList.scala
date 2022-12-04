package com.practice.eg
package com.practice.eg.part2

abstract class MyList {

  def head: Int

  def tail: MyList

  def isEmpty: Boolean

  def add(element: Int): MyList

  def printElements: String

  //this is from the super class (Any). Just like toString method in Java Object class
  //hence it has the override keyword prefixed
  override def toString: String = "{ " + printElements + " }"
}

object Empty1 extends MyList {
  def head: Int = throw new NoSuchElementException()

  def tail: MyList = throw new NoSuchElementException()

  def isEmpty: Boolean = true

  def add(element: Int): MyList = new Cons1(element, Empty1)

  override def printElements: String = ""
}

class Cons1(h: Int, t: MyList) extends MyList {

  def head: Int = h

  def tail: MyList = t

  def isEmpty: Boolean = false

  def add(element: Int): MyList = new Cons1(2, this)

  override def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " "+ t.printElements
  }
}

object ListTest extends App {
  val list = new Cons1(1, new Cons1(2, new Cons1(3, Empty1)))
  println(list.tail.head)
  println(list.add(4).head)
  println(list)
}
