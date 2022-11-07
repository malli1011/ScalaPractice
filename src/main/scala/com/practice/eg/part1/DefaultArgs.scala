package com.practice.eg
package com.practice.eg.part1

import scala.jdk.Accumulator

object DefaultArgs extends App {
def trFact(n:Int, accumulator:Int =1):Int = {
  if(n<=1) accumulator
  else trFact(n-1,n*accumulator)
}

println(trFact(10))
println(trFact(10,2))

def savePic(format:String = "JPG", height:Int=800, width:Int=1000):Unit = println("Saving Picture..")

//Pass in every leading arguments
savePic("BTM")

//named arguments
savePic(width = 700)
savePic(height = 700, width = 200, format = "test")
savePic(format = "UNKNOWN")

}
