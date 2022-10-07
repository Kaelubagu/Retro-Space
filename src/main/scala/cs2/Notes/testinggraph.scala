package cs2.Notes

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import scalafx.scene.input.MouseEvent

/*
object AnotherWindow extends JFXApp {
    stage = new JFXApp.PrimaryStage {
        title = "Wow nice window bro, where'd you get it."
        scene = new Scene(900,800){
            val canvas = new Canvas (900,800)
            content = canvas
            val a = canvas.graphicsContext2D


            a.strokeText("this is pretty cool", 200, 100)
        }

    }
}
*/
object NewerWindow extends JFXApp {
    stage = new JFXApp.PrimaryStage {
        title = "Wow"
        scene = new Scene(900,900) {
            val canvas = new Canvas (900,900)
            content = canvas 
            val a = canvas.graphicsContext2D
            
            a.setFill(Color.DeepPink)
            a.fillRect(90,90,200,200)
            a.setFill(Color.Aqua)
            a.setStroke(Color.Bisque)
            a.strokeRect(90,90,100,90)
            a.fillRect(50,50,100,100)
            a.setStroke(Color.Black)
            a.setLineWidth(2)
            a.strokeLine(90,90,100,500)

            
            

        }
    }
}



//companion objects:
