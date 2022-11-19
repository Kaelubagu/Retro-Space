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
import scala.collection.mutable.Buffer
import scala.collection.mutable.Set



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
            var BulletBuffer = Buffer[Bullet]()

            var enemyBulletBuffer = Buffer[Bullet]()

            val playerAva = getClass().getResource("/images/Sprite.png")
            val playerImg = new Image(playerAva.toString)
            val player    = new Player(playerImg, new Vec2(425,700), bulletImg)

            val enemyPath = getClass().getResource("/images/enemy.png")
            val enemyImg  = new Image(enemyPath.toString)
            val enemy     = new Enemy(enemyImg, new Vec2(80,80), bulletImg)

            var enemySwarm = new EnemySwarm(8,4)

            var TrackedKeys = Set[KeyCode]()
            
            var removeBulBuf = Buffer[Bullet]()
            
            var numBullets = 0

            //AnimationTimer & KeyEvents
            canvas.onKeyPressed = (e:KeyEvent) => { //event with only key pressed, only one at a time can exit.
                if(e.code == KeyCode.Left){ 
                    a.setFill(Color.Black)
                    a.fillRect(0,400, 900, 900)
                    TrackedKeys += KeyCode.Left
                    player.moveLeft()
                }
                if(e.code == KeyCode.Right){ 
                    a.setFill(Color.Black)
                    a.fillRect(0,400, 900, 900)
                    TrackedKeys += KeyCode.Right
                    player.moveRight()
                }
                if(e.code == KeyCode.Up){ 
                    a.setFill(Color.Black)
                    a.fillRect(0,400, 900, 900)
                    TrackedKeys += KeyCode.Up
                    player.moveUp()
                }
                if(e.code == KeyCode.Down){ 
                    a.setFill(Color.Black)
                    a.fillRect(0,400, 900, 900)
                    TrackedKeys += KeyCode.Down
                    player.moveDown()
                }
                if(e.code == KeyCode.Space){ 
                    if(Pslowlypls >= 0) {
                        numBullets += 1
                        Pslowlypls = -30
                        BulletBuffer += player.shoot()
                        println(numBullets)
                    }
                    
                }
            

            canvas.onKeyReleased = (e:KeyEvent) => {
                if(e.code == KeyCode.Left){
                    TrackedKeys -= KeyCode.Left
                }
                if(e.code == KeyCode.Right){
                    TrackedKeys -= KeyCode.Right
                }
                if(e.code == KeyCode.Up){
                    TrackedKeys -= KeyCode.Up
                }
                if(e.code == KeyCode.Down){
                    TrackedKeys -= KeyCode.Down
                }
            }

            }

            var Pslowlypls = 0
            var Eslowlypls = 0

            val timer = AnimationTimer (t => {
                Pslowlypls += 1
                Eslowlypls += 1

                if(TrackedKeys.contains(KeyCode.Left)){
                    player.moveLeft()
                }
                if(TrackedKeys.contains(KeyCode.Right)){
                    player.moveRight()
                }
                if(TrackedKeys.contains(KeyCode.Up)){
                    player.moveUp()
                }
                if(TrackedKeys.contains(KeyCode.Down)){
                    player.moveDown()
                }

                //println(enemySwarm.enemyBump(player))

            
                if(enemySwarm.enemyBump(player)) { //moves player back once bumps enemy
                        player.moveTo(Vec2(425,700))
                }
            

                

                for( i <- 0 until BulletBuffer.length) {
                    if(BulletBuffer(i).initPos.y > 950 || BulletBuffer(i).initPos.y < -50)
                    removeBulBuf += BulletBuffer(i)
                }
                
                BulletBuffer --= removeBulBuf //for above function (removes all the ones to the remove list)
                for( i <- 0 until enemyBulletBuffer.length){
                    if(enemyBulletBuffer(i).initPos.y > 950 || enemyBulletBuffer(i).initPos.y < -50)
                    removeBulBuf += enemyBulletBuffer(i)
                }
                
                for( i <- 0 until BulletBuffer.length) {
                    if(enemySwarm.EShit(BulletBuffer(i)))
                        removeBulBuf += BulletBuffer(i)
                        //println(enemySwarm.EShit(BulletBuffer(i)))
                }

                for( i <- 0 until enemyBulletBuffer.length) {
                    if(player.intersection(enemyBulletBuffer(i)))
                        player.moveTo(Vec2(425,700))
                        removeBulBuf += enemyBulletBuffer(i)
                }

                enemyBulletBuffer --= removeBulBuf

                


                a.setFill(Color.Black)
                a.fillRect(0,0,900,900)
                player.display(a)
                enemySwarm.display(a)
                if(Eslowlypls >= 0) {
                    Eslowlypls = -20
                    enemyBulletBuffer += enemySwarm.arrayshoot()
                }
                


                for(Bullet <- enemyBulletBuffer){
                    Bullet.display(a)
                    Bullet.timeStep()
                }

                for(Bullet <- BulletBuffer){
                    Bullet.display(a)
                    Bullet.timeStep()
                }

                if(enemySwarm.isEmpty()) {
                    enemySwarm = new EnemySwarm(8,4)
                    enemySwarm.display(a)
                }
                
            })
            timer.start()
            canvas.requestFocus()
        }
       

    }
}
  