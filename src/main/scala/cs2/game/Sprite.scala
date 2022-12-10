package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

/** A graphical sprite object used for gaming or other visual displays
 *  
 *  @constructor create a new sprite based on the given image and initial location
 *  @param img the image used to display this sprite
 *  @param pos the initial position of the '''center''' of the sprite in 2D space
 */
abstract class Sprite (protected val img:Image, protected var pos:Vec2) {
  
  val width = img.getWidth()
  val height = img.getHeight()
  val picture = img

  def move (direction:Vec2):Unit = { 
    pos.x += direction.x
    pos.y += direction.y
  }
  

  def moveTo (location:Vec2):Unit = { 
    pos.x = location.x
    pos.y = location.y
  }
  

  def display (g:GraphicsContext):Unit = { 
    g.drawImage(img, pos.x, pos.y)
  }



  def intersection(s:Sprite):Boolean = {

    val lowerx = pos.x
    val higherx = pos.x + width
    val lowery = pos.y
    val highery = pos.y + height
    
    val dosLowerx = s.pos.x
    val dosHigherx = s.pos.x + s.width
    val dosLowery = s.pos.y
    val dosHighery = s.pos.y + s.height

    if((dosLowerx < higherx) && (dosHigherx > lowerx) && (dosLowery < highery) && (dosHighery > lowery)){
     true
    } 
    else {
     false
    }
  }
  
  
}



