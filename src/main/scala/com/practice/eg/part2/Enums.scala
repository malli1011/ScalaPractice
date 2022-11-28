package com.practice.eg
package com.practice.eg.part2

object Enums {
  enum Permissions{
    case READ, WRITE, EXECUTE, NONE, ALL

    //add fields/methods
    def openDoc():Unit =
      if(this == READ || this == ALL) println("opening document ....")
      else println("reading not allowed.")

  }

  //constructor args
  enum PermissionsWithBits(bits: Int){
    case READ extends PermissionsWithBits(4) //100
    case WRITE extends PermissionsWithBits(2) //010
    case EXECUTE extends PermissionsWithBits(1) //001
    case NONE extends PermissionsWithBits(0) //000
    case ALL extends PermissionsWithBits(7) //111
  }

  val myPermissions: Permissions = Permissions.ALL

  //Standard API
  val ordinal = myPermissions.ordinal // gives the index of the Enum value
  val allPermissions = PermissionsWithBits.values // array of all possible values of the enum
  val readPermission: Permissions = Permissions.valueOf("READ")



  def main(args: Array[String]):Unit = {
    myPermissions.openDoc()
    println(ordinal)
    println(allPermissions.mkString("Permissions[", ", ", "]"))
    println(readPermission)

  }
}
