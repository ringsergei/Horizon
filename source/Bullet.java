import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.imageio.*;
import java.io.*;

class Bullet{
    public Gun gun;
    public int bull_d, speed;
    public int numberOfBlot=0;
    public double x, y, rotation;;
    public double movedToX, movedToY, progressiveX, progressiveY;
    public boolean isOnField=false, isHit=true;
    public Obstacles[] obstacles;
    public Player player;
    public Blot[] blots = new Blot[100];
    public Image blotImg;
    public Color color;
    public Sound sound = new Sound();

    public Bullet(Obstacles obstacles[], Player player){
        this.obstacles = obstacles;
        this.player = player;
        //sound.shotSound();
    }

    public void CheckCollision(){
        for( Obstacles obstacle : obstacles ){
            if( obstacle!=null ){
                if( isHit==true ){
                    if( (int)x==obstacle.x && y>=obstacle.y && y<= (obstacle.y+obstacle.height) ){
                        
                        isOnField=false;
                        blots[numberOfBlot] = new Blot(blotImg);
                        blots[numberOfBlot].x = obstacle.x;
                        blots[numberOfBlot].y = (int)y;
                        blots[numberOfBlot].rotation = 180;
                        //System.out.println((int)rotation+180);
                        numberOfBlot++;
                        // sound.obsSound("wall");*/
                        timerUpdate.stop();
                    }
                    else if( (int)x==(obstacle.x+obstacle.width) && y>=obstacle.y && y<= (obstacle.y+obstacle.height) ){
                        isOnField=false;
                        blots[numberOfBlot] = new Blot(blotImg);
                        blots[numberOfBlot].x = (obstacle.x+obstacle.width-16);
                        blots[numberOfBlot].y = (int)y;
                        blots[numberOfBlot].rotation = 0;
                        //System.out.println((int)rotation+180);
                        numberOfBlot++;
                        // sound.obsSound("wall");*/
                        timerUpdate.stop();
                    }
                    else if( (int)y==obstacle.y && x>=obstacle.x && x<=(obstacle.x+obstacle.width) ){
                        isOnField=false;
                        blots[numberOfBlot] = new Blot(blotImg);
                        blots[numberOfBlot].x = (int)x;
                        blots[numberOfBlot].y = obstacle.y-7;
                        blots[numberOfBlot].rotation = -90;
                        //System.out.println((int)rotation+180);
                        numberOfBlot++;
                        // sound.obsSound("wall");*/
                        timerUpdate.stop(); 
                    }
                    else if( (int)y==(obstacle.y + obstacle.height) && x>=obstacle.x && x<=(obstacle.x+obstacle.width) ){
                        isOnField=false;
                        blots[numberOfBlot] = new Blot(blotImg);
                        blots[numberOfBlot].x = (int)x;
                        blots[numberOfBlot].y = (obstacle.y+obstacle.height-23);
                        blots[numberOfBlot].rotation = 90;
                        //System.out.println((int)rotation+180);
                        numberOfBlot++;
                        // sound.obsSound("wall");*/
                        timerUpdate.stop(); 
                    }
                }
            }
        }
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
        for( int i = 0; i < speed; i++ ){
            y+=progressiveY;
            x+=progressiveX;
            CheckCollision();
        }
      }

    });


    public void draw(Graphics g){
        if( x>1950|x<-30|y>1110|y<-30 ){
            isOnField=false;
            timerUpdate.stop();
        }
        else if(isOnField==true){
            g.setColor(color);
            g.fillOval((int)x, (int)y, bull_d, bull_d);
        }
        for( Blot blot : blots ){
            if( blot!=null )blot.draw(g);
        }
    }

}
