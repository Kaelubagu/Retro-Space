package cs2.game

import scalafx.scene.canvas.GraphicsContext
import scala.util.Random
import scalafx.scene.image.Image
import cs2.util.Vec2
import scala.collection.mutable.Buffer



class EnemySwarm(private val nRows:Int, private val nCols:Int){


//make a sprite list later 

  val enemybulletPath = getClass().getResource("/images/enemybullet.png") //seriously make a sprite list
  val enemybulletImg  = new Image(enemybulletPath.toString)
  var enemySwarmBuffer = Buffer[Enemy]()


  
  for(a <- 1 to nRows; j <- 1 to nCols) { // enemySwarm grid of enemies
    enemySwarmBuffer += new Enemy(Sprites.enemyImg, new Vec2(100*a ,75*j), enemybulletImg)
   }

  def display(g:GraphicsContext):Unit = {
   enemySwarmBuffer.foreach(_.display(g))
  }

  def arrayshoot():Bullet = { //shooting should be random 
    enemySwarmBuffer((math.random * enemySwarmBuffer.length).toInt).shoot()
  }

  def bulletHitEnemy(s:Sprite):Boolean = { //when bullet intersects enemy
    val playerBulletPic = Sprites.playerBullet
    val enemyBulletImg = Sprites.enemyBullet
    var enemyRB = Buffer[Enemy]() 
    var attack = false
      for(Enemy <- enemySwarmBuffer){
        if((Enemy.intersection(s)) && (s.picture == playerBulletPic)){
          enemyRB += Enemy
          attack = true 
        }
        else if (s.picture == enemybulletImg)
          attack = false
      }
      enemySwarmBuffer --= enemyRB
      attack
  }
  
  def playerBumpEnemy(s:Sprite):Boolean = {
    val spaceCraft = Sprites.spaceCraft
    var Bump = false
    for(Enemy <- enemySwarmBuffer) {
      if((Enemy.intersection(s)) && (s.picture == spaceCraft))
        Bump = true
    }
    Bump
  }

  def swarmMove():Unit = {
    enemySwarmBuffer.foreach(_.move())
  }

  def cloneBuffer(): Buffer[Enemy] = {
    var cloneBuffer = Buffer[Enemy]()
      for(Enemy <- enemySwarmBuffer) {
        var AEnemy = Enemy.clone()
        cloneBuffer += AEnemy
      }
      cloneBuffer
  }

  def isEmpty():Boolean = {
    var empty = false
    if(enemySwarmBuffer.length == 0) {
      empty = true
    }
    empty
  }


}
