package cs2.util

class Vec2 (var x:Double, var y:Double) {
  /** DO NOT MODIFY THE FOLLOWING TOSTRING METHOD **/
  override def toString():String = "("+x+","+y+")"
  
  //Methods for addition and subtraction of vectors
  def +  (other:Vec2):Vec2 = { 
    val a = this.x + other.x
    val b = this.y + other.y
    return Vec2(a,b)
    }
  def += (other:Vec2):Unit = { 
    this.x = this.x + other.x
    this.y = this.y + other.y
  }
  
  def -  (other:Vec2):Vec2 = { 
    val a = this.x - other.x
    val b = this.y - other.y
    return Vec2(a,b) 
  }
  def -= (other:Vec2):Unit = { 
    this.x = this.x - other.x
    this.y = this.y - other.y
  }

  //Methods for multiplication and division of vectors by a scalar (non-vector)
  def *  (scalar:Double):Vec2 = { 
    val a = this.x * scalar
    val b = this.y * scalar
    return Vec2(a,b)
    }
  def *= (scalar:Double):Unit = { 
    this.x = this.x * scalar
    this.y = this.y * scalar
  }

  def /  (scalar:Double):Vec2 = { 
    val a = this.x / scalar
    val b = this.y / scalar
    return Vec2(a,b)
  }
  def /= (scalar:Double):Unit = { 
    this.x = this.x / scalar
    this.y = this.y / scalar
  }

  //Methods to determine the length of a vector (magnitude and length should return the same value)
  def magnitude():Double = {math.sqrt(this.x*this.x + this.y + this.y)}
  def length():Double = {math.sqrt(this.x*this.x + this.y + this.y)}
  
  //Methods to calculate the dot product (same returns)
  def dot(other:Vec2):Double = { this.x * other.x + this.y * other.y }
  def **(other:Vec2):Double = { this.x * other.x + this.y * other.y }
  
  //Methods to determine the angle between 2 vectors (same returns)
  def angleBetween(other:Vec2):Double = { (math.acos(this.dot(other) /( (this.magnitude * other.magnitude))))}
  def <>(other:Vec2):Double = { (math.acos(this.dot(other) /( (this.magnitude * other.magnitude))))}

  //Methods to return a new vector that is in the same direction, but length 1 (same returns)
  def normalize():Vec2 = { 
    var a = this.magnitude
    this.x = this.x / a
    this.y = this.y / a
    this
  }
  def unary_~ : Vec2 = {
    this.x = this.x / this.magnitude
    this.y  = this.y / this.magnitude
    this
  }

  //A clone operator can be useful when making "deep" copies of objects
  override def clone():Vec2 = { 
    var cloney = this
    cloney 
  }
}

object Vec2 {
  //These apply methods can be used for constructing Vec2 instances without saying "new"
  /** DO NOT CHANGE THE FOLLOWING THREE APPLY METHODS**/
  def apply(myX:Double, myY:Double):Vec2 = { new Vec2(myX, myY) }
  def apply(toCopy:Vec2):Vec2 = { new Vec2(toCopy.x, toCopy.y) }
  def apply():Vec2 = { new Vec2(0, 0) }

  def main(args:Array[String]):Unit = {
    var j = apply(98.4736 , 29.4241)
    var k = apply(97.7431 , 30.2672)
    var speedv = apply(k.x - j.x, k.y - j.y)
    var speediv = speedv / 1.5
    var distchange = speediv * 2.5
    var fine = distchange + k
    println(fine)
  }
}
