package com.practice.eg
package com.practice.eg.part3

object MapsTuplesEx {

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    network + (person -> Set())
  }

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val frnd1 = network(a)
    val frnd2 = network(b)

    network + (a -> (frnd1 + b)) + (b -> (frnd2 + a))

  }

  def unFriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val frnd1 = network(a)
    val frnd2 = network(b)

    network + (a -> (frnd1 - b)) + (b -> (frnd2 - a))

  }

  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  def mostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1
  }

  def noFriendsCount(network: Map[String, Set[String]]): Int = {
    network.count(_._2.isEmpty)
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    null
  }

  def socialConnections(network: Map[String, Set[String]], a: String, b: String): Boolean = true

}
