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
import scala.collection.mutable.Stack

object SpaceGameApp extends JFXApp {


  
  var gs = new GameState()
  var gsStack = new Stack[GameState]
  var keysTrackedSet = Set[KeyCode]()
  var BulletRB = Buffer[Bullet]()
  

  stage = new JFXApp.PrimaryStage {
    title = "RETRO SPACE!"
    scene = new Scene(800, 800) {
      val canvas = new Canvas(800, 800)
      content = canvas
      val a = canvas.graphicsContext2D

      var startScreen = true
      var showPauseScreen = false
      var showDeadScreen = false
      var beginGame = false


      canvas.requestFocus()

      canvas.onKeyPressed = (e: KeyEvent) => {
        if (e.code == KeyCode.ENTER) {
          beginGame = true
          gs.scoreDP = true
          gs.livesDP = true
          gs.timeDP = true
          showPauseScreen = false
          showDeadScreen = false
          startScreen = false
        }

        if (e.code == KeyCode.G) {
          timeRO = false
          beginGame = true
          gs.scoreDP = true
          gs.livesDP = true
          gs.timeDP = true
          gs.enemySwarm = new EnemySwarm(6, 3)
          showDeadScreen = false
          startScreen = false
          showPauseScreen = false
          gs.Time = 60
          gs.Lives = 3
          gs.killcounter = 0
          gs.bulletinterception = 0
          rewindCounter = 0
        }
        
        if (e.code == KeyCode.Escape) {
          showPauseScreen = true
          beginGame = false
          showDeadScreen = false
          startScreen = false
          gs.livesDP = false
          gs.scoreDP = false
          gs.timeDP = false
        }


        
        //WASD functionality

        if (e.code == KeyCode.R) { //reverse
          keysTrackedSet += KeyCode.R
        }
        
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
        if (e.code == KeyCode.R ) {
          keysTrackedSet -= KeyCode.R
        }
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
        var rewindCounter = 0
        var timeRO = false
        val timer = AnimationTimer(t => {

        if (startScreen == true) {
            //font stuff
            val path = getClass().getResource("/fonts/Silkscreen-Bold.ttf").toString 
            val Silkscreen = Font.loadFont(path,40)
            a.setFont(Fonts.Silkscreen) 
            a.textAlign = TextAlignment.Center 
            a.setFill(Color.Black) 
            //Title
            a.drawImage(Sprites.gameLogo, 202, 200)//logo
            val beginWords = a.fillText("Press ENTER to start", canvas.getWidth() / 2 , 750)


        }

        if(!keysTrackedSet.contains(KeyCode.R)) {
          
        

        if (beginGame) {
            a.drawImage(Sprites.space, 0, 0)//background
            a.setFont(Fonts.Silkscreen)
            a.setFill(Color.Black)
            a.fillText("Player Score: "+gs.Score.toString(), canvas.height.value -400, 780) //score
            a.setFont(Fonts.Silkscreen)
            a.setFill(Color.White)
            a.fillText("Lives: " + gs.Lives.toString(), canvas.height.value -670, 50) //lives
            a.setFont(Fonts.Silkscreen);a.setFill(Color.White);a.fillText(gs.Time.toString(), canvas.height.value - 50, 50) //time
            a.setFill(Color.White);a.fillRect(20, 80, rewindCounter / 5, 30)
            a.setFont(Fonts.Silkscreen);a.setFill(Color.Black);a.fillText("(r) Rewind", 130, 108, 200)
            gs.player.display(a)
            gs.enemySwarm.display(a)
            gs.enemySwarm.swarmMove()
            gs.count+=1
            enemyslowdown += 1

            if(rewindCounter >= 0 ) {
              if(rewindCounter <= 3598) { // small delay by 2 frames
                rewindCounter += 1
              }
              if(rewindCounter == 3599) {// small delay by 1 frame
                rewindCounter = 3599
              }
            }
           
            if (keysTrackedSet.contains(KeyCode.Left)) {
            gs.player.moveLeft()
            }
            if (keysTrackedSet.contains(KeyCode.Right)) {
            gs.player.moveRight()
            }
            if (keysTrackedSet.contains(KeyCode.Up)) {
            gs.player.moveUp()
            }
            if (keysTrackedSet.contains(KeyCode.Down)) {
            gs.player.moveDown()
            }

            if (keysTrackedSet.contains(KeyCode.Space)) {
                slowdown += 1
                if (slowdown > 0) {
                    slowdown = -15
                    gs.BulletBuffer += gs.player.shoot()

            
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

        if (gs.Lives == 0) { // death
            timeRO == false
            showPauseScreen = false
            beginGame = false
            showDeadScreen = true
            gs.livesDP = false
            gs.scoreDP = false
            gs.timeDP = false
            startScreen = false
            rewindCounter = 0
        }

        if (gs.Time == 0) { //time up screen
            timeRO = true
            showPauseScreen = false
            beginGame = false
            showDeadScreen = false
            gs.livesDP = false
            gs.scoreDP = false
            gs.timeDP = false
            startScreen = false
            rewindCounter = 0

        }

        if (timeRO) {
            a.setFill(Color.White)
            a.fillRect(0,0, 800,800)
            a.setFont(Fonts.Silkscreen)
            a.setFill(Color.Black)
            a.fillText("Time Up, Loser", 400, 400)
            a.setFont(Fonts.Silkscreen)
            a.fillText("Press G to Begin a New Game", canvas.width.value / 2, 450 , 500)

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

        if (gs.count > 0){
            gs.count = -60
            gs.Time -= 1
        }
        
        for (Bullet <- gs.BulletBuffer) {
            Bullet.display(a)
            Bullet.timeStep()
        }

        if (enemyslowdown > 0) {
            enemyslowdown = -45
            gs.BulletBuffer += gs.enemySwarm.arrayshoot()
        }


        for (i <- 0 until gs.BulletBuffer.length) {
          if (gs.BulletBuffer(i).initPos.y > 850 || gs.BulletBuffer(i).initPos.y < -30)
            BulletRB += gs.BulletBuffer(i)

          val enemybp = Sprites.enemyBullet 
          if ( gs.player.intersection(gs.BulletBuffer(i)) && gs.BulletBuffer(i).pic != Sprites.playerBullet) {
            gs.player.moveTo(Vec2(375, 650))
            gs.Lives -= 1 
            BulletRB += gs.BulletBuffer(i)
          }
        }

        gs.BulletBuffer --= BulletRB
        for (i <- 0 until gs.BulletBuffer.length) {
          if (gs.enemySwarm.bulletHitEnemy(gs.BulletBuffer(i))) {
            BulletRB += gs.BulletBuffer(i)
            gs.killcounter += 1
           
          }
          if (gs.enemySwarm.playerBumpEnemy(gs.player)) {
            gs.Lives -= 1
            gs.player.moveTo(Vec2(375, 650))
          }

          for (j <- 0 until gs.BulletBuffer.length) {
            if (i != j) {
              if ((gs.BulletBuffer(i).intersection(gs.BulletBuffer(j)))) {
                gs.bulletinterception += 1 
                BulletRB += gs.BulletBuffer(i)
                BulletRB += gs.BulletBuffer(j)
              }
            }
          }

        }

        gs.Score = gs.killcounter * 300 + gs.bulletinterception * 10 //score counter

        gs.BulletBuffer --= BulletRB

        gsStack.push(gs.deepycopy())

        if (gs.enemySwarm.isEmpty()) { 
          gs.enemySwarm = new EnemySwarm(6, 3)
          gs.enemySwarm.display(a)
        }

      } else {
          if(gs.Time < 60 && rewindCounter > 0) {
            if(rewindCounter > 1) {
              rewindCounter -= 1
            }
            if(rewindCounter == 1) {
              rewindCounter = 0
            }
            gs = gsStack.pop()

            a.drawImage(Sprites.space, 0, 0)//background
            a.setFont(Fonts.Silkscreen)
            a.setFill(Color.Black)
            a.fillText("Player Score: "+gs.Score.toString(), canvas.height.value -400, 780) //score
            a.setFont(Fonts.Silkscreen)
            a.setFill(Color.White)
            a.fillText("Lives: " + gs.Lives.toString(), canvas.height.value -670, 50) //lives
            a.setFont(Fonts.Silkscreen);a.setFill(Color.White);a.fillText(gs.Time.toString(), canvas.height.value - 50, 50) //time
            a.setFont(Fonts.Silkscreen);a.setFill(Color.White);a.fillText("REWIND!!!", 400, 400, 200);a.setFill(Color.Red);a.fillRect(20, 80, rewindCounter / 5, 30)

            gs.player.display(a)
            gs.enemySwarm.display(a)
            for(bullet <- gs.BulletBuffer) {
              bullet.display(a)
            }
        }

      }

         

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

    val gameLogoPath = getClass().getResource("/images/Logo.gif")
    val gameLogo = new Image(gameLogoPath.toString())

    val playerBulletPath = getClass().getResource("/images/bullet.png")
    val playerBullet = new Image(playerBulletPath.toString())

    val enemyBulletPath = getClass().getResource("/images/enemybullet.png")
    val enemyBullet = new Image(enemyBulletPath.toString())

    val enemyImgPath = getClass().getResource("/images/enemy.png")
    val enemyImg = new Image(enemyImgPath.toString())

    val BackgroundPath = getClass().getResource("/images/Background.jpg")
    val space = new Image(BackgroundPath.toString())
}
