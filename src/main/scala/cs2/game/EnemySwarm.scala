package cs2.game

import scalafx.scene.canvas.GraphicsContext
import scala.util.Random
import scalafx.scene.image.Image
import cs2.util.Vec2
import scala.collection.mutable.Buffer


/** contains the control and logic to present a coordinated set of Enemy objects.
 *  For now, this class generates a "grid" of enemy objects centered near the
 *  top of the screen.
 *
 *  @param nRows - number of rows of enemy objects (4 rows of 8 enemies)
 *  @param nCols - number of columns of enemy objects
 */
class EnemySwarm(private val nRows:Int, private val nCols:Int){

  val enemyPath = getClass().getResource("/images/enemy.png") //make a sprite list later 
  val enemyImg  = new Image(enemyPath.toString)
  val bulletPath = getClass().getResource("/images/bullet.png") //seriously make a sprite list
  val bulletImg  = new Image(bulletPath.toString)

  var enemySwarmBuffer = Buffer[Enemy]()
  

  for(a <- 1 to nRows; j <- 1 to nCols) {
    enemySwarmBuffer += new Enemy(enemyImg, new Vec2(100*a - 25,75*j), bulletImg)
   }
   


  def display(g:GraphicsContext):Unit = {
   enemySwarmBuffer.foreach(_.display(g))
      
   
  }


  def arrayshoot():Bullet = { 
    enemySwarmBuffer((math.random * enemySwarmBuffer.length).toInt).shoot()
  }
  




  def EShit(s:Sprite):Boolean = {
    val bulletPath = getClass().getResource("/images/bullet.png")
    val bulletImg  = new Image(bulletPath.toString)
    val playerAva = getClass().getResource("/images/Sprite.png")
    val playerImg = new Image(playerAva.toString)
    val playerbulletpic = bulletImg
    

    var enemyRB = Buffer[Enemy]() 

    var attack = false
      for(Enemy <- enemySwarmBuffer) {
        if(Enemy.intersection(s)){
          enemyRB += Enemy
          attack = true 
        }
      }
      enemySwarmBuffer --= enemyRB
      attack
    
  }

  def enemyBump(s:Sprite):Boolean = {
    var Bump = false
    for(Enemy <- enemySwarmBuffer) {
      if(Enemy.intersection(s))
        Bump = true
    }
    Bump

  }
  def isEmpty():Boolean = {
    var empty = false
    if(enemySwarmBuffer.length == -1) {
      empty = true
    }
    empty
  }
}
