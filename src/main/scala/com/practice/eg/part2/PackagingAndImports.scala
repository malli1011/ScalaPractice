package com.practice.eg
package com.practice.eg.part2

//Malli as Don or Malli => Don . both are same

import playground.{Malli as Don, Sakthi => Devil}
import java.util.Date
import java.sql.Date as SqlDate


object PackagingAndImports extends App {

  val sakthi = new Devil()
  val malli = new Don

  sakthi.hello()
  malli.hello()

  //Defined in package object so accessible in all the classes under this package
  helloWorld()
  println(SPEED_OF_LIGHT)
}
