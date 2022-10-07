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


  def move (direction:Vec2):Unit = { 
    pos.x += direction.x
    pos.y += direction.y
  }
  

  def moveTo (location:Vec2):Unit = { 
    pos.x = location.x
    pos.x = location.y
  }
  

  def display (g:GraphicsContext):Unit = { 
    g.drawImage(img, pos.x, pos.y)
  }
  
}
