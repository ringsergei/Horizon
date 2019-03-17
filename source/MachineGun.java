import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.imageio.*;
import java.io.*;

class MachineGun{
    public int damageRadius, x, y, numberOfShot=0, hp=10;
    public String orientation;
    public double deltaX, deltaY, rotation;
    public Player player;
    public Bullet[] shots = new Bullet[100];
    public Obstacles[] obstacles;
    public int numberOfHit=19;

    Timer regeneration = new Timer(20000, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
          checkHits.start(); 
          snooping.start(); 
          hp=10;
          regeneration.stop();
      }
    });

    Timer checkHits = new Timer(1, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        for( Bullet shot: shots ){
          if( shot != null ){
            if( shot.isHit ){
                if( player.hp!=0 ){
                  if(shot.x <= player.x+50 && shot.x >= player.x && shot.y >= player.y && shot.y <= player.y+50){
                      shot.isOnField = false;
                      shot.isHit = false;
                      player.hp -= 1;
                      //shot.sound.obsSound("villian");
                  }
                }
            }
          }
        }
      }
    });

    Timer timerStrike = new Timer(250, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
          for( int i = 0; i <=1; i++ ){
              shots[numberOfShot+i] = new Bullet(obstacles);
              shots[numberOfShot+i].isOnField = true;
              shots[numberOfShot+i].color = new Color(69,107,72);
              if( orientation.equals("vertical") ){
                shots[numberOfShot+i].x = (x+15)+20*Math.cos(Math.toRadians(rotation-70+(i*130)));
                shots[numberOfShot+i].y = (y+15)+20*Math.sin(Math.toRadians(rotation-70+(i*130)));
              }
              else if( orientation.equals("horizontal") ){
                shots[numberOfShot+i].x = (x+15)+20*Math.cos(Math.toRadians(rotation-70+(i*130)));
                shots[numberOfShot+i].y = (y+15)+20*Math.sin(Math.toRadians(rotation-70+(i*130)));
              }
              shots[numberOfShot+i].movedToX = player.x+15+(i*10);
              shots[numberOfShot+i].movedToY = player.y+15+(i*10);
              shots[numberOfShot+i].SetVariables();
              shots[numberOfShot+i].timerUpdate.start();
          }
          numberOfShot+=2;
          if( numberOfShot>99 ){
              numberOfShot=0;
          }
      }
    });

    Timer snooping = new Timer(1, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
          if( orientation.equals("vertical") )
                if( x > damageRadius ){
                    if( player.x+50>damageRadius ){
                        deltaX = player.x-x;
                        deltaY = player.y-y;
                        rotation = (Math.atan2(deltaY, deltaX)) * 180 / 3.14159265;
                        timerStrike.start();
                    }
                    else{
                      timerStrike.stop();
                    }
                }
                else if( x < damageRadius ){
                      if( player.x<damageRadius ){
                          deltaX = player.x-x;
                          deltaY = player.y-y;
                          rotation = (Math.atan2(deltaY, deltaX)) * 180 / 3.14159265;
                          timerStrike.start();
                      }
                      else{
                        timerStrike.stop();
                      }
                }
          if( orientation.equals("horizontal") ){
              if( y > damageRadius ){
                  if( player.y+50>damageRadius ){
                      deltaX = player.x-x;
                      deltaY = player.y-y;
                      rotation = (Math.atan2(deltaY, deltaX)) * 180 / 3.14159265;
                      timerStrike.start();
                  }
                  else{
                    timerStrike.stop();
                  }
              }
              else if( y < damageRadius ){
                    if( player.y<damageRadius ){
                        deltaX = player.x-x;
                        deltaY = player.y-y;
                        rotation = (Math.atan2(deltaY, deltaX)) * 180 / 3.14159265;
                        timerStrike.start();
                    }
                    else{
                      timerStrike.stop();
                    }
              }
          }
      }
    });

    public MachineGun(){
    }

    public void setValue(String name, String value){
        if( name=="x" ){
            x = Integer.parseInt(value);
        }
        else if( name=="y" ){
            y = Integer.parseInt(value);
        }
        else if( name=="damageRadius" ){
            damageRadius = Integer.parseInt(value);
        }
        else if( name=="orientation" ){
            orientation = value;
        }
    }

    public void drawPanel(Graphics gr){
      for( int i = 0; i < hp; i++ ){
          gr.setColor(new Color(218,101,111));
          if( orientation.equals("vertical") ){
            if( damageRadius>=x ){
              gr.fillRect(x-10, (y-30)+(i*10), 8, 10);
            }else gr.fillRect(x+30, (y-30)+(i*10), 8, 10);;
          }
      }
      for( int i = hp; i < 10; i ++ ){
          gr.setColor(new Color(171,178,191));
          if( orientation.equals("vertical") ){
            if( damageRadius>=x ){
              gr.fillRect(x-10, (y-30)+(i*10), 8, 10);
            }else gr.fillRect(x+30, (y-30)+(i*10), 8, 10);;
          }
      }
    }

    public void draw(Graphics gr){
        try{
            for( int i = 0; i < 100; i++ ){
                shots[i].draw(gr);
            }
        }catch(Exception exp){}
        Graphics2D g2d = (Graphics2D)gr;
        AffineTransform old = g2d.getTransform();
        g2d.rotate(Math.toRadians(rotation+180), x+20, y+20);
        g2d.setColor(new Color(69,107,72));
        g2d.fillRect(x-10, y+30, 30, 10);
        g2d.fillRect(x-10, y+1, 30, 10);
        g2d.setColor(new Color(33,37,43));
        g2d.fillOval(x, y, 40, 40);
        g2d.setTransform(old);
    }
}
