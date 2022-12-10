package cs2.game

import scalafx.application.JFXApp
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import scalafx.event.Event
import scalafx.scene.input.MouseEvent
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
import scalafx.scene.effect.Effect
import scalafx.animation.AnimationTimer
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.text.Font
import scalafx.scene.text.Text
import scalafx.scene.text.TextAlignment
import scalafx.scene.canvas.GraphicsContext
import cs2.util.Vec2
import scala.collection.mutable.Buffer
import scala.collection.mutable.Set

object SpaceGameApp extends JFXApp {

  val player = new Player(Sprites.spaceCraft, new Vec2(375, 650), Sprites.playerBullet)
  val playerBullet = new Bullet(Sprites.playerBullet, new Vec2(player.initPos.x, player.initPos.y + 100),Vec2(0, 2.5))
  var enemySwarm = new EnemySwarm(8,4)

  var keysTrackedSet = Set[KeyCode]()
  var BulletRB = Buffer[Bullet]()
  var BulletBuffer = Buffer[Bullet]()
  var enemySwarmBuffer = Buffer[Enemy]()
  

  stage = new JFXApp.PrimaryStage {
    title = "RETRO SPACE!"
    scene = new Scene(800, 800) {
      val canvas = new Canvas(800, 800)
      content = canvas
      val a = canvas.graphicsContext2D

      var Score:Int = 0
      var Lives:Int = 3
      var Time: Int = 60
      var count:Int = 0
      var livesDP = false
      var timeDP = false
      var startScreen = true
      var showPauseScreen = false
      var showDeadScreen = false
      var beginGame = false
      var scoreDP = false

      canvas.requestFocus()

      canvas.onKeyPressed = (e: KeyEvent) => {
        if (e.code == KeyCode.ENTER) {
          beginGame = true
          scoreDP = true
          livesDP = true
          timeDP = true
          showPauseScreen = false
          showDeadScreen = false
          startScreen = false
        }

        if (e.code == KeyCode.G) {
          beginGame = true
          scoreDP = true
          livesDP = true
          timeDP = true
          enemySwarm = new EnemySwarm(6, 3)
          showDeadScreen = false
          startScreen = false
          showPauseScreen = false
          Time = 60
          Lives = 3
          killcounter = 0
          bulletinterception = 0
        }
        
        if (e.code == KeyCode.Escape) {
          showPauseScreen = true
          beginGame = false
          showDeadScreen = false
          startScreen = false
          livesDP = false
          scoreDP = false
          timeDP = false
        }

        
        //WASD functionality
        if (e.code == KeyCode.Left || e.code == KeyCode.A) {
          keysTrackedSet += KeyCode.Left
        }

        if (e.code == KeyCode.Right || e.code == KeyCode.D) {
          keysTrackedSet += KeyCode.Right
        }
        if (e.code == KeyCode.Up || e.code == KeyCode.W) {
          keysTrackedSet += KeyCode.Up
        }
        if (e.code == KeyCode.Down || e.code == KeyCode.S) {
          keysTrackedSet += KeyCode.Down
        }
        //shooting functionality
        if (e.code == KeyCode.Space) {
          keysTrackedSet += KeyCode.Space
        }
      }

      canvas.onKeyReleased = (e: KeyEvent) => {
        if (e.code == KeyCode.Left || e.code == KeyCode.A) {
          keysTrackedSet -= KeyCode.Left
        }
        if (e.code == KeyCode.Right || e.code == KeyCode.D) {
          keysTrackedSet -= KeyCode.Right
        }
        if (e.code == KeyCode.Up || e.code == KeyCode.W) {
          keysTrackedSet -= KeyCode.Up
        }
        if (e.code == KeyCode.Down || e.code == KeyCode.S) {
          keysTrackedSet -= KeyCode.Down
        }
        if (e.code == KeyCode.Space) {
          keysTrackedSet -= KeyCode.Space
        }
      }


        var slowdown = 0
        var enemyslowdown = 0
        var killcounter = 0
        var bulletinterception = 0
        val timer = AnimationTimer(t => {

        if (startScreen == true) {
            //font stuff
            val path = getClass().getResource("/fonts/Silkscreen-Bold.ttf").toString 
            val Silkscreen = Font.loadFont(path,40)
            a.setFont(Fonts.Silkscreen) 
            a.textAlign = TextAlignment.Center 
            a.setFill(Color.Black) 
            //Title
            val spaceTitle = a.fillText("RETRO SPACE!", canvas.getWidth() / 2 , 400)
            val beginWords = a.fillText("Press ENTER to start", canvas.getWidth() / 2 , 750)
        }
        


        if (beginGame) {
            a.drawImage(Sprites.space, 0, 0)//background
            a.setFont(Fonts.Silkscreen)
            a.setFill(Color.Black)
            a.fillText("Player Score: "+Score.toString(), canvas.height.value -400, 780) //score
            a.setFont(Fonts.Silkscreen)
            a.setFill(Color.White)
            a.fillText("Lives: " + Lives.toString(), canvas.height.value -670, 50) //lives
            a.setFont(Fonts.Silkscreen);a.setFill(Color.White);a.fillText(Time.toString(), canvas.height.value - 50, 50) //time
            player.display(a)
            enemySwarm.display(a)
            enemySwarm.swarmMove()
            enemyslowdown += 1
            count+=1
           
            if (keysTrackedSet.contains(KeyCode.Left)) {
            player.moveLeft()
            }
            if (keysTrackedSet.contains(KeyCode.Right)) {
            player.moveRight()
            }
            if (keysTrackedSet.contains(KeyCode.Up)) {
            player.moveUp()
            }
            if (keysTrackedSet.contains(KeyCode.Down)) {
            player.moveDown()
            }

            if (keysTrackedSet.contains(KeyCode.Space)) {
                slowdown += 1
                if (slowdown > 0) {
                    slowdown = -15
                    BulletBuffer += player.shoot()
            }
          } 
        }

        if (showPauseScreen) {
            a.setFill(Color.Black)
            a.fillRect(200, 200, 400, 400)
            a.setFont(Fonts.Silkscreen)
            a.setFill(Color.White) 
            a.setStroke(Color.White)
            a.fillText("Anythin' Wrong?", canvas.width.value / 2, canvas.height.value - 500, 800);
            a.setFont(Fonts.Silkscreen)
            a.fillText("Press Enter to Continue", canvas.width.value / 2, 500, 500)

        }

         if (Lives == 0 || Time == 0) {
            showPauseScreen = false
            beginGame = false
            showDeadScreen = true
            livesDP = false
            scoreDP = false
            timeDP = false
            startScreen = false
        }

        if (showDeadScreen) {
            a.setFill(Color.Black)
            a.fillRect(0,0, 800,800)
            a.setFont(Fonts.Silkscreen)
            a.setFill(Color.White)
            a.fillText("You Stink, Loser", 400, 400)
            a.setFont(Fonts.Silkscreen)
            a.fillText("Press G to Begin a New Game", canvas.width.value / 2, 450 , 500)
        }   

        if(count > 0){
            count = -60
            Time -= 1
        }
        for (Bullet <- BulletBuffer) {
            Bullet.display(a)
            Bullet.timeStep()
        }

        if (enemyslowdown > 0) {
            enemyslowdown = -45
            BulletBuffer += enemySwarm.arrayshoot()
        }


        for (i <- 0 until BulletBuffer.length) {
          if (BulletBuffer(i).initPos.y > 830 || BulletBuffer(i).initPos.y < -30)
            BulletRB += BulletBuffer(i)

          val enemybp = Sprites.enemyBullet 
          if ( player.intersection(BulletBuffer(i)) && BulletBuffer(i).pic != Sprites.playerBullet) {
            player.moveTo(Vec2(375, 650))
            Lives -= 1 
            BulletRB += BulletBuffer(i)
          }
        }

        BulletBuffer --= BulletRB
        for (i <- 0 until BulletBuffer.length) {
          if (enemySwarm.bulletHitEnemy(BulletBuffer(i))) {
            BulletRB += BulletBuffer(i)
            killcounter+=1
           
          }
          if (enemySwarm.playerBumpEnemy(player)) {
            Lives -= 1
            player.moveTo(Vec2(375, 650))
          }

          for (j <- 0 until BulletBuffer.length) {
            if (i != j) {
              if ((BulletBuffer(i).intersection(BulletBuffer(j)))) {
                bulletinterception += 1 
                BulletRB += BulletBuffer(i)
                BulletRB += BulletBuffer(j)
              }
            }
          }

        }

        BulletBuffer --= BulletRB

        if (enemySwarm.isEmpty()) {

          enemySwarm = new EnemySwarm(6, 3)
          enemySwarm.display(a)
        }

         Score = killcounter*100 + bulletinterception*25 

      })

      timer.start()
      canvas.requestFocus()

    }

  }

}

//Object classes to hold things in place

object Fonts {
    val path = getClass().getResource("/fonts/Silkscreen-Bold.ttf").toString 
    val Silkscreen = Font.loadFont(path,40)
}

object Sprites {
    val spaceCraftPath = getClass().getResource("/images/spacecraft.png")
    val spaceCraft = new Image(spaceCraftPath.toString())

    val playerBulletPath = getClass().getResource("/images/bullet.png")
    val playerBullet = new Image(playerBulletPath.toString())

    val enemyBulletPath = getClass().getResource("/images/enemybullet.png")
    val enemyBullet = new Image(enemyBulletPath.toString())

    val enemyImgPath = getClass().getResource("/images/enemy.png")
    val enemyImg = new Image(enemyImgPath.toString())

    val BackgroundPath = getClass().getResource("/images/Background.jpg")
    val space = new Image(BackgroundPath.toString())
  
}
