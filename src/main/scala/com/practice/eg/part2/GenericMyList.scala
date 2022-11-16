package com.practice.eg
package com.practice.eg.part2

import scala.runtime.Nothing$

abstract class GenericMyList[+A] {
  def head: A

  def tail: GenericMyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): GenericMyList[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A, B]): GenericMyList[B]

  def flatMap[B](transformer: MyTransformer[A, GenericMyList[B]]): GenericMyList[B]

  def filter(myPredicate: MyPredicate[A]): GenericMyList[A]

  def ++[B >: A](list: GenericMyList[B]): GenericMyList[B]
}

object Empty extends GenericMyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: GenericMyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): GenericMyList[B] = new Cons[B](element, Empty)

  override def printElements: String = ""


  def map[B](transformer: MyTransformer[Nothing, B]): GenericMyList[B] = Empty

  def flatMap[B](transformer: MyTransformer[Nothing, GenericMyList[B]]): GenericMyList[B] = Empty

  def filter(myPredicate: MyPredicate[Nothing]): GenericMyList[Nothing] = Empty

  def ++[B >: Nothing](list: GenericMyList[B]): GenericMyList[B] = list
}

class Cons[+A](h: A, t: GenericMyList[A]) extends GenericMyList[A] {
  override def head: A = h

  override def tail: GenericMyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): GenericMyList[B] = new Cons[B](element, this)

  override def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"

  override def filter(myPredicate: MyPredicate[A]): GenericMyList[A] =
    if (myPredicate.test(h)) new Cons(h, t.filter(myPredicate))
    else t.filter(myPredicate)

  override def map[B](transformer: MyTransformer[A, B]): GenericMyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))

  def ++[B >: A](list: GenericMyList[B]): GenericMyList[B] = new Cons[B](h, t ++ list)

  def flatMap[B](transformer: MyTransformer[A, GenericMyList[B]]): GenericMyList[B] = transformer.transform(h) ++ t.flatMap(transformer)
}

trait MyPredicate[-T] {
  def test(ele: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(elem: A): B
}

object GenericListTest extends App {
  val listInt = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listStrings = new Cons("Malli", new Cons("Arjun", new Cons("Goki", Empty)))

  println(listInt)
  println(listStrings)

  val squareTrans = new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * elem
  }

  val evenPredicate = new MyPredicate[Int] {
    override def test(ele: Int): Boolean = ele % 2 == 0
  }

  println(listInt.map(squareTrans))
  println(listInt.filter(evenPredicate))

  println(listInt ++ listInt)

  val anotherTransformer = new MyTransformer[Int, GenericMyList[Int]] {
    override def transform(elem: Int): GenericMyList[Int] = new Cons[Int](elem, new Cons[Int](elem + 1, Empty))
  }

  println(listInt flatMap anotherTransformer)

}