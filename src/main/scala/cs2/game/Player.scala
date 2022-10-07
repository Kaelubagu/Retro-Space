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


  


  def moveLeft():Unit = { 
    if(initPos.x > 100)
      initPos.x -= 20
    else
      initPos.x += 0
  }

  /** moves the player sprite one "step" to the right (see note above)
   *
   *  @return none/Unit
   */
  def moveRight():Unit = { //not taking it all the way to the end (900) because it looks unnatural if I did.
    if(initPos.x < 750) 
      initPos.x += 20
    else 
      initPos.x += 0 
    
  }

  /** creates a new Bullet instance beginning from the player, with an
   *  appropriate velocity
   *
   *  @return Bullet - the newly created Bullet object that was fired
   */
  def shoot():Bullet = { 

    new Bullet(bulletPic, new Vec2(initPos.x, initPos.y - 30), Vec2(0,25))

  }
}

