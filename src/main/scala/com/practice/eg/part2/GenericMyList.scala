package com.practice.eg
package com.practice.eg.part2

abstract class GenericMyList[+A] {
  def head: A

  def tail: GenericMyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): GenericMyList[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"
}

object Empty extends GenericMyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: GenericMyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): GenericMyList[B] = new Cons[B](element, Empty)

  override def printElements: String = ""
}

class Cons[+A](h: A, t: GenericMyList[A]) extends GenericMyList[A] {
  override def head: A = h

  override def tail: GenericMyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): GenericMyList[B] = new Cons[B](element, this)

  override def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"
}

object GenericListTest extends App {
  val listInt = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listStrings = new Cons("Malli", new Cons("Arjun", new Cons("Goki", Empty)))

  println(listInt)
  println(listStrings)
}