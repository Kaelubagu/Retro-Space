package cs2.Notes

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import scalafx.scene.input.MouseEvent
import scalafx.event.Event
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
import scalafx.animation.AnimationTimer

object animation extends JFXApp {
    stage = new JFXApp.PrimaryStage {
        title = "New Window."
        scene = new Scene (900,900) {
            val canvas = new Canvas(900,900)
            content = canvas
            val a = canvas.graphicsContext2D
            /*
            a.setFill(Color.White)
            canvas.onMouseDragged = (e:MouseEvent) => {
            a.strokeLine(450,450, e.x, e.y) 
            }
            
            canvas.onKeyPressed = (e:KeyEvent) => { //event with only key pressed, only one at a time can exit.
                if(e.code == KeyCode.Space){ //only if space is pressed
                a.setFill(Color.White)
                a.fillRect(0,0, 900, 900)
                }
                if(e.code == KeyCode.R){ //only if r is pressed == red background.
                a.setFill(Color.Red)
                a.fillRect(0,0, 900, 900)
                }
            }
            */
            //animation
            var xvel:Double = 10 //velocity
            var xpos:Double = 450
            val timer = AnimationTimer (t => {
                a.setFill(Color.White) //replaces old circle with white
                a.fillRect(0,0, 900, 900)
                a.setFill(Color.BlueViolet)
                a.fillOval(xpos,200, 100, 100)
                if(xpos + 100 >= 900 || xpos < 0) {
                    xvel = -xvel
                }
                xpos += xvel
            })
            timer.start() //starts animation

            canvas.requestFocus() //make sure to have this for keypress


        }

    }
}

//animation


