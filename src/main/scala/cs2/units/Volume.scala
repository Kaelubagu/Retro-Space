package cs2.units

class Volume() {
  //Field - the volume stored in LITERS
  private var lit:Double = 0.0

  //Basic math operators to add, subtract, and scale volumes
  def + (other:Volume):Volume = {
    val a = this.lit + other.lit 
    return Volume(a)}
  
  def += (other:Volume):Unit  = {this.lit = this.lit + other.lit }

  def - (other:Volume):Volume = {
    val a = this.lit - other.lit
    Volume(a)
  }
  def -= (other:Volume):Unit  = {this.lit = this.lit - other.lit}

  def * (scalar:Double):Volume = {
    val a = this.lit * scalar
    Volume(a)
}
  def *= (scalar:Double):Unit  = {this.lit = this.lit * scalar}

  def / (scalar:Double):Volume = {
    val a = this.lit / scalar
    Volume(a)
}
  def /= (scalar:Double):Unit  = {this.lit = this.lit / scalar}

 //Getter functions that return in a variety of units
  def liters():Double = { 
    this.lit
  }
  def milliliters():Double = {
    val x = this.lit
    x * 1000 
  }
  def gallons():Double = {  
    val x = this.lit
    x * 3.78541
  }
  def quarts():Double = {  
    val x = this.lit
    x * 1.0567
  }
  def pints():Double = {  
    val x = this.lit
    x * 2.113376
  }
  def cups():Double = {  
    val x = this.lit
    x * 4.2268
  }
  def teaspoons():Double = {  
    val x = this.lit
    x * 202.88
  }
  def tablespoons():Double = { 
    val x = this.lit
    x * 67.628
  }
  override def toString():String = lit.toString
}

object Volume {
  //"Constructor" apply methods operating in liters
  def apply():Volume = { new Volume() }
  def apply(amt:Double):Volume = {  
    var a = new Volume()
    a.lit = amt
    a
  }

  //Alternative "static" methods to create volumes in other units
  def liters(amt:Double):Volume = { 
    val x = apply(amt)
    x
  } //identical to an apply method
  def milliliters(amt:Double):Volume = {  
    val x = apply(amt / 1000)
    x
  }
  def gallons(amt:Double):Volume = { 
    val x = apply(amt / 3.78541)
    x
  }
  def quarts(amt:Double):Volume = { 
    val x = apply(amt / 1.0567)
    x
  }
  def pints(amt:Double):Volume = { 
    val x = apply(amt / 2.113376)
    x
  }
  def cups(amt:Double):Volume = { 
    val x = apply(amt / 4.2268)
    x
  }
  def teaspoons(amt:Double):Volume = { 
    val x = apply(amt / 202.88)
    x
  }
  def tablespoons(amt:Double):Volume = { 
    val x = apply(amt / 67.628)
    x
  }

  /*def main(args:Array[String]):Unit = {
  
}
*/
}





