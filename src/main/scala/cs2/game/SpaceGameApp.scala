package cs2.game

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import scalafx.scene.text.Text
import scalafx.scene.text.Font
import scalafx.scene.text.TextAlignment
import scalafx.animation.AnimationTimer
import cs2.game.Sprite
import cs2.util.Vec2
import scalafx.scene.image.Image
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode



/** main object that initiates the execution of the game, including construction
 *  of the window.
 *  Will create the stage, scene, and canvas to draw upon. Will likely contain or
 *  refer to an AnimationTimer to control the flow of the game.
 */
object SpaceGameApp extends JFXApp{
    stage = new JFXApp.PrimaryStage {
        title = "Retro Space!"
        scene = new Scene (900,900) {
            val canvas = new Canvas(900,900)
            content = canvas
            val a = canvas.graphicsContext2D
            
            fill = Color.Black //background
            
            val path = getClass().getResource("/fonts/Silkscreen-Bold.ttf").toString //font stuff
            val Silkscreen = Font.loadFont(path,40)
            a.setFont(Silkscreen) 
            a.textAlign = TextAlignment.Center 


            a.setFill(Color.White) //background
            val spaceTitle = a.fillText("RETRO SPACE!", canvas.getWidth() / 2 , 60)

            //Sprites

            val bulletPath = getClass().getResource("/images/bullet.png")
            val bulletImg  = new Image(bulletPath.toString)
            var BulletList = List[Bullet]()

            var enemyBulletList = List[Bullet]()

            val playerAva = getClass().getResource("/images/Sprite.png")
            val playerImg = new Image(playerAva.toString)
            val player    = new Player(playerImg, new Vec2(425,700), bulletImg)

            val enemyPath = getClass().getResource("/images/enemy.png")
            val enemyImg  = new Image(enemyPath.toString)
            val enemy     = new Enemy(enemyImg, new Vec2(80,80), bulletImg)

            val enemySwarm = new EnemySwarm(8,4)
            val enemySwarmList = List[Enemy]()
            
            
            

            //AnimationTimer & KeyEvents
            canvas.onKeyPressed = (e:KeyEvent) => { //event with only key pressed, only one at a time can exit.
                if(e.code == KeyCode.Left){ 
                    a.setFill(Color.Black)
                    a.fillRect(0,400, 900, 900)
                    player.moveLeft()
                }
                if(e.code == KeyCode.Right){ 
                    a.setFill(Color.Black)
                    a.fillRect(0,400, 900, 900)
                    player.moveRight()
                }
                if(e.code == KeyCode.Space){ 
                    BulletList ::= player.shoot()
                }

            }

            val timer = AnimationTimer (t => {
                
                a.setFill(Color.Black)
                a.fillRect(0,0,900,900)
                player.display(a)
                enemySwarm.display(a)
                enemyBulletList ::= enemySwarm.arrayshoot()
                


                for(Bullet <- BulletList){
                    Bullet.display(a)
                    Bullet.timeStep()
                }

                for(Bullet <- enemyBulletList){
                    Bullet.display(a)
                    Bullet.timeStep()
                
        
                    
                }
            
            })
            
            timer.start()
            canvas.requestFocus()
         



        }
       

    }
}
  