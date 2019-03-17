import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.imageio.*;
import java.io.*;

class Hearts{
    public int x, y;
    public Obstacles[] obstacles;
    public Image heartImg;

    public Hearts(Obstacles obstacles[]){
        this.obstacles=obstacles;
        try{
          heartImg = ImageIO.read(new File("images//heart.png"));
        }catch(Exception exp){}
        heartsGeneration();
    }

    public void heartsGeneration(){
        x = (30 + (int)(Math.random()*1890));
        y = (30 + (int)(Math.random()*1050));
        for(Obstacles obs: obstacles){
            if( obs!= null){
                if(  (x+50) >= obs.x && x<=(obs.x+obs.width) && (y+71)>=obs.y && y<=(obs.y+obs.height) ){
                    heartsGeneration();
                }
            }
        }
    }

    public void draw(Graphics gr){
        gr.drawImage(heartImg, x, y, 50,  71, null);
    }
}