package cs2.Notes
import scalafx.Includes._ //makes event error go away.
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import scalafx.scene.input.MouseEvent

/*
syntax:
object NAME_OF_OBJECT extends JFXApp {
    stage = new JFXApp.PrimaryStage { //finish this
        title 
    }
}
*/
//boiler plate code:

object FirstWindow extends JFXApp {
    stage = new JFXApp.PrimaryStage { //our stage to make the window
        title = "lmaoooo"
        scene = new Scene(600,400) {
            val canvas = new Canvas (600,400)
            content = canvas
            val g = canvas.graphicsContext2D
            
            
            g.fillRect(200,50,300,200) //origin is top left hand corner. positive y goes down-not up. Angles go clockwise!!!!
            g.fillOval(200,40,300,1)
            g.setStroke(Color.DodgerBlue) //set colors before strokes/fills to work.
            g.setLineWidth(30)
            g.setFill(Color.rgb(255,25,0))
            
            def cap(c:Double):Int = {
                if(c < 0) 0
                else if(c > 255) 255
                else c.toInt
            }
            
            
            for( x <- 0 to 600) {//to -- goes to 600
                g.setStroke(Color.rgb(0, 255, (x * 255 / 600))) 
                g.strokeLine(x, 0, x, 400)
            }

            g.setStroke(Color.Black) //make a function that draws a circle. specify: center and radisu
            g.setLineWidth(1)
            //fractal
            def drawCirc(x:Double, y:Double, r:Double):Unit = {
                g.strokeOval(x-r, y-r, r*2, r*2)
                if(r > 2) {
                    drawCirc(x-r, y, r/2)
                    drawCirc(x+r, y, r/2)
                    drawCirc(x, y-r, r/2)
                    drawCirc(x, y+r, r/10)
                }
           
            }
            
            //draw lines event code
            drawCirc(300,200, 100)
            g.setFill(Color.White)
            g.fillRect(0,0, 600, 400)
            canvas.onMouseDragged = (e:MouseEvent) => {
                g.strokeLine(300,200, e.x, e.y)
            }
            
            drawCirc(300,200, 100)
        }
        

      
//color.rgb looks like a method in a companion object
    
    }
}

object main {

}

/*other functions: //look at slides
    .fillRect(X,Y,WIDTH,HEIGHT)
    .strokeRect(..)
    .fillOval(X,Y,WIDTH,HEIGHT)
    .strokeOval(..)
    .fillText(Text, X,Y)
    .strokeText(..)
    .strokeLine(EMD1X,END1Y,END2X,END2Y)
modify
    .setFill(Color)
    .
*/
