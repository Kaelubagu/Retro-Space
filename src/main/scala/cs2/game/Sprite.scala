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
    
    var attack = false

    val whatWidth = this.img.width.value
    val aheight = s.img.height.value
    val whatHeight = this.img.height.value
    val awidth = s.img.width.value
    val timg = this.img

    if((this.pos.x + whatWidth > s.pos.x && this.pos.x < s.pos.x + awidth) && (this.pos.y + whatHeight > s.pos.y && this.pos.y < s.pos.y + aheight) && (s.img != timg))
      attack = true
    
    attack
  }
  
  
}


