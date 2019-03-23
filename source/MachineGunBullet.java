import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.imageio.*;
import java.io.*;

class MachineGunBullet{
    public double x, y;
    public double movedToX, movedToY, progressiveX, progressiveY;
    public boolean isOnField=false, isHit=true;
    public Obstacles[] obstacles;
    public Color color;
    public Sound sound = new Sound();

    public MachineGunBullet(Obstacles obstacles[]){
        this.obstacles = obstacles;
        //sound.shotSound();
    }

    public void SetVariables(){

          if( Math.abs(movedToX-x)>Math.abs(movedToY-y) ){
              if( (movedToX-x<0) ) progressiveX=-1;
              else if( (movedToX-x>0) ) progressiveX=1;
              if( movedToY-y<0 ) progressiveY=-Math.abs((movedToY-y)/(movedToX-x));
              else progressiveY=Math.abs((movedToY-y)/(movedToX-x));
          }
          else{
              if( (movedToY-y<0) ) progressiveY=-1;
              else if( (movedToY-y>0) ) progressiveY=1;
              if( movedToX-x<0 ) progressiveX=-Math.abs((movedToX-x)/(movedToY-y));
              else progressiveX=Math.abs((movedToX-x)/(movedToY-y));
          }


    }

    Timer timerUpdate = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        try{
            for( Obstacles obstacle : obstacles ){
                if( isHit==true ){
                    if( (x-7) >= obstacle.x && (x+7)<=(obstacle.width+obstacle.x) && (y+7)>=obstacle.y && (y+7)<=(obstacle.height+obstacle.y) ){
                        isOnField=false;
                       // sound.obsSound("wall");
                        timerUpdate.stop();
                    }
                }
            }
        }catch(Exception exp){}
        y+=progressiveY*5;
        x+=progressiveX*5;
      }

    });


    public void draw(Graphics g){
        if( x>1950|x<-30|y>1110|y<-30 ){
            isOnField=false;
            timerUpdate.stop();
        }
        else if(isOnField==true){
            g.setColor(color);
            g.fillOval((int)x, (int)y, 12, 12);
        }
    }

}