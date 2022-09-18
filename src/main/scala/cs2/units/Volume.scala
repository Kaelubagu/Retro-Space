package cs2.units

class Volume() {
  //Field - the volume stored in LITERS
  private var lit:Double = 0.0

  //Basic math operators to add, subtract, and scale volumes
  def + (other:Volume):Volume = {
    this.lit = this.lit + other.lit 
    return Volume(this.lit)}
  
  def += (other:Volume):Unit  = {this.lit = this.lit + other.lit }

  def - (other:Volume):Volume = {
    this.lit - other.lit
    Volume(this.lit)
  }
  def -= (other:Volume):Unit  = {this.lit = this.lit - other.lit}

  def * (scalar:Double):Volume = {
    this.lit * scalar
    Volume(this.lit)
}
  def *= (scalar:Double):Unit  = {this.lit = this.lit * scalar}

  def / (scalar:Double):Volume = {
    this.lit / scalar
    Volume(this.lit)
}
  def /= (scalar:Double):Unit  = {this.lit = this.lit / scalar}

 //Getter functions that return in a variety of units
  def liters():Double = { lit }
  def milliliters():Double = { lit * 1000 }
  def gallons():Double = { lit / 3.785 }
  def quarts():Double = { lit * 1.057 }
  def pints():Double = { lit * 2.113 }
  def cups():Double = { lit * 4.227 }
  def teaspoons():Double = { lit * 202.9  }
  def tablespoons():Double = { lit * 67.628 }
}

object Volume {
  //"Constructor" apply methods operating in liters
  def apply():Volume = { new Volume }
  def apply(amt:Double):Volume = { new Volume() }

  //Alternative "static" methods to create volumes in other units
  def liters(amt:Double):Volume = { new apply() } //identical to an apply method
  def milliliters(amt:Double):Volume = { liters * 1000 }
  def gallons(amt:Double):Volume = { lit / 3.785 }
  def quarts(amt:Double):Volume = { lit * 1.057 }
  def pints(amt:Double):Volume = { lit * 2.113 }
  def cups(amt:Double):Volume = { lit * 4.227 }
  def teaspoons(amt:Double):Volume = { lit * 202.9 }
  def tablespoons(amt:Double):Volume = { lit * 67.628 }
}

