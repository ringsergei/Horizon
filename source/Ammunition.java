import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.imageio.*;
import java.io.*;

class Ammunition{
    public int x, y;
    public Obstacles[] obstacles;
    public Image ammunitionImg;

    public Ammunition(Obstacles obstacles[]){
        this.obstacles=obstacles;
        try{
            ammunitionImg = ImageIO.read(new File("images//ammunition.png"));
        }catch(Exception exp){}
        ammunitionGeneration();
    }

    public void ammunitionGeneration(){
        x = (30 + (int)(Math.random()*1890));
        y = (30 + (int)(Math.random()*1050));
        for(Obstacles obs: obstacles){
            if( obs!= null){
                if(  (x+50) >= obs.x && x<=(obs.x+obs.width) && (y+50)>=obs.y && y<=(obs.y+obs.height) ){
                    ammunitionGeneration();
                }
            }
        }
    }

    public void draw(Graphics gr){
        gr.drawImage(ammunitionImg, x, y, 50,  50, null);
    }
}