package com.practice.eg
package com.practice.eg.part2

import scala.language.postfixOps

object Exercise1 extends App {

  val author = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  val imposter = new Writer("Charles", "Dickens", 1812)

  println(novel.authorAge)
  println(novel.isWrittenBy(imposter))

  val counter = new Counter

  counter.print

  counter.increment.increment(5).print
  counter.decrement.print

}


class Writer(firstName: String, surname: String, val year: Int) {
  def fullName: String = firstName + surname
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge: Int = year - author.year

  def isWrittenBy(author: Writer): Boolean = author == this.author

  def copy(newYear: Int): Novel = new Novel(name, newYear, author)

}

class Counter(val count: Int = 0) {
  def currentCount: Int = count

  def increment: Counter = {
    println("Incrementing..")
    new Counter(count + 1)
  }

  def decrement: Counter = {
    println("Decrementing..")
    new Counter(count - 1)
  }

  def increment(newVal: Int): Counter = {
    if (newVal <= 0) this
    else increment.increment(newVal - 1)

  }

  def decrement(newVal: Int): Counter = {
    if (newVal <= 0) this
    else decrement.decrement(newVal - 1)
  }

  def print: Unit = println(count)
}