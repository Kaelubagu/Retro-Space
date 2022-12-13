package cs2.game

import cs2.util.Vec2
import scala.collection.mutable.Buffer
import scala.collection.mutable.Set
import scala.collection.mutable.Stack

class GameState() {

    var player = new Player(Sprites.spaceCraft, new Vec2(375, 650), Sprites.playerBullet)
    var playerBullet = new Bullet(Sprites.playerBullet, new Vec2(player.initPos.x, player.initPos.y + 100),Vec2(0, 2.5))
    var enemySwarm = new EnemySwarm(6,3)
    var BulletBuffer = Buffer[Bullet]()
    var livesDP = false 
    var scoreDP = false
    var timeDP = false 
    var Score:Int = 0
    var Time:Int = 60
    var Lives:Int = 3
    var count:Int = 0
    var killcounter = 0
    var bulletinterception = 0

    def deepycopy():GameState = {
    var clonestate = new GameState()

        clonestate.player = this.player.clone()
        clonestate.enemySwarm.enemySwarmBuffer = enemySwarm.cloneBuffer()
        clonestate.BulletBuffer = Buffer[Bullet]()
          for(Bullet <- this.BulletBuffer) {
                var ABullet = Bullet.clone()
                clonestate.BulletBuffer += ABullet
          }
        
            clonestate.Lives = this.Lives
            clonestate.Time  = this.Time
            clonestate.Score = this.Score 
            clonestate.count = this.count
            clonestate.killcounter = this.killcounter
            clonestate.bulletinterception = this.bulletinterception

        return clonestate
    }


}

