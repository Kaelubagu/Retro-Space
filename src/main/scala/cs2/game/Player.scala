package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.event.Event
import scalafx.scene.input.KeyEvent
import scalafx.animation.AnimationTimer

/** The player representation for a simple game based on sprites. Handles all
 *  information regarding the player's positions, movements, and abilities.
 *
 *  @param avatar the image representing the player
 *  @param initPos the initial position of the '''center''' of the player
 *  @param bulletPic the image of the bullets fired by this player
 */
class Player(avatar:Image, var initPos:Vec2, private val bulletPic:Image) extends Sprite(avatar, initPos) {


  
  def moveUp():Unit = { 
    if(initPos.y > 0)
      initPos.y -= 8
    else
      initPos.y += 0
  }

  def moveDown():Unit = { 
    if(initPos.y < 750)
      initPos.y += 8
    else
      initPos.y += 0
  }

  def moveLeft():Unit = { 
    if(initPos.x > 0)
      initPos.x -= 8
    else
      initPos.x += 0
  }


  def moveRight():Unit = { //not taking it all the way to the end (900) because it looks unnatural if I did.
    if(initPos.x < 750) 
      initPos.x += 8
    else 
      initPos.x += 0 
    
  }

  override def clone():Player = {
    var cloneposition:Vec2 = new Vec2(this.pos.x, this.pos.y)
    var clonedPlayer = new Player(this.avatar, cloneposition, this.bulletPic) //not changing, just copying manually
    clonedPlayer
  }

  def shoot():Bullet = { 

    new Bullet(bulletPic, new Vec2(initPos.x, initPos.y - 30), Vec2(0,25))

  }
}

