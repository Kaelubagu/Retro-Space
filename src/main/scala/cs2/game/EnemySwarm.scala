package cs2.game

import scalafx.scene.canvas.GraphicsContext
import scala.util.Random
import scalafx.scene.image.Image
import cs2.util.Vec2


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

  var enemySwarmList = List[Enemy]()

  for(a <- 1 to nRows; j <- 1 to nCols) {
    enemySwarmList ::= new Enemy(enemyImg, new Vec2(180*a - 115,75*j), bulletImg)
   }
   


  def display(g:GraphicsContext):Unit = {
   enemySwarmList.foreach(_.display(g))
      
   
  }

  /** overridden method of ShootsBullets. Creates a single, new bullet instance
   *  originating from a random enemy in the swarm. (Not a bullet from every
   *  object, just a single from a random enemy)
   *
   *  @return Bullet - the newly created Bullet object fired from the swarm
   */
  def arrayshoot():Bullet = { 
    enemySwarmList((math.random * enemySwarmList.length).toInt).shoot()
  }

}
