import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.imageio.*;
import java.io.*;

class Player{

    public int x=1920/2-25, y=1080/2-25, degrees=0, numberOfShot=0, hp=20;
    public boolean move_right, move_left, move_top, move_down;
    double deltaX, deltaY, rotation;
    int mouseX=0, mouseY=0;
    public Bullet[] shots = new Bullet[30];
    public Obstacles[] obstacles;
    public MachineGun[] machineGuns;
    public Hearts heart;
    public Ammunition ammunition;

    public void createBullet(){
      shots[numberOfShot] = new Bullet(obstacles);
      shots[numberOfShot].isOnField = true;
      shots[numberOfShot].color = new Color(218,101,111);
      shots[numberOfShot].movedToX = ((mouseX-70) + (int)(Math.random()*140));
      shots[numberOfShot].movedToY =  ((mouseY-70) + (int)(Math.random()*140));;
      shots[numberOfShot].x = (x+19)+25*Math.cos(Math.toRadians(rotation+90));
      shots[numberOfShot].y = (y+19)+25*Math.sin(Math.toRadians(rotation+90));
      shots[numberOfShot].SetVariables();
      shots[numberOfShot].timerUpdate.start();
      numberOfShot++;
    }

    
    Timer thingsTimer = new Timer(3000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if( hp<20 ){
                if( heart==null ){
                  heart = new Hearts(obstacles);
                }
            } 
            if( numberOfShot>0 ){
                if( ammunition==null ){
                  ammunition = new Ammunition(obstacles);
                }
            }   
        }
    });


    Timer strikeTimer = new Timer(100, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
            if( numberOfShot<30 )createBullet();
      }
    });

    Timer checkHits = new Timer(1, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          for( Bullet shot: shots ){
            if( shot != null ){
              if( shot.isHit ){
                  for( MachineGun gun: machineGuns ){
                    if( gun!=null ){
                        if( gun.hp!=0 ){
                            if(shot.x <= gun.x+40 && shot.x >= gun.x && shot.y >= gun.y && shot.y <= gun.y+40){
                                gun.hp -= 1;
                                shot.isOnField = false;
                                shot.isHit = false;
                            // shot.sound.obsSound("villian");
                            }
                            if( gun.hp==0 ){
                                gun.snooping.stop(); 
                                gun.timerStrike.stop();
                                gun.regeneration.start();
                            }
                        }
                        else if( gun.hp==0 ){
                            if(shot.x <= gun.x+40 && shot.x >= gun.x && shot.y >= gun.y && shot.y <= gun.y+40){
                                shot.isOnField=false;
                            }
                        }
                    }
                  }
              }
            }
          }
        }
      });

    Timer timerUpdate = new Timer(3, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

        deltaX = mouseX-(x+35);
        deltaY = mouseY-(y-5);
        rotation = (Math.atan2(deltaY, deltaX)) * 180 / 3.14159265;

            try{
                for( Obstacles obstacle : obstacles ){
                    if( obstacle != null ){
                        if( x==(obstacle.x+obstacle.width) && y+50>obstacle.y && y<(obstacle.height+obstacle.y)){
                            move_left=false;
                        }
                        else if( x+50==(obstacle.x) && y+50>obstacle.y && y<(obstacle.height+obstacle.y) ){
                            move_right=false;
                        }
                        else if( y+50==(obstacle.y) && x+50>obstacle.x && x<(obstacle.width+obstacle.x) ){
                            move_down=false;
                        }
                        else if( y==(obstacle.y+obstacle.height) && x+50>obstacle.x && x<(obstacle.width+obstacle.x) ){
                            move_top=false;
                        }
                        if( obstacle.isMoved == true ){
                            if( obstacle.orientation.equals("vertical") ){
                                if( y<obstacle.y && y+50>=obstacle.y && x+50>obstacle.x && x<(obstacle.x+obstacle.width) ){
                                    y-=2;
                                }
                                else if( y>obstacle.y && y<=obstacle.y+obstacle.height && x+50>obstacle.x && x<(obstacle.x+obstacle.width) ){
                                    y+=2;
                                }
                            }
                        }
                    }
                }
            }catch(Exception exp){}

            if( heart!=null ){
                if( (x+50)>=heart.x && x<(heart.x+50) && (y+50)>=heart.y && y<(heart.y+71)  ){
                    if( hp<19 ){
                      hp+=2;
                    }else hp+=1;
                    heart=null;
                }
            }
            if( ammunition!=null ){
                if( (x+50)>=ammunition.x && x<(ammunition.x+50) && (y+50)>=ammunition.y && y<(ammunition.y+50)  ){
                    if( numberOfShot>2 ){
                      numberOfShot-=3;
                    }else numberOfShot-=1;
                    ammunition=null;
                }
            }

            if( move_right==true ){
                x+=1;
            }
            else if( move_left==true ){
                x-=1;
            }
            else if( move_top==true ){
                y-=1;
            }
            else if( move_down==true ){
                y+=1;
            }

			}
		});

    public Player(Obstacles obstacles[], MachineGun machineGuns[]){
        this.obstacles = obstacles;
        this.machineGuns = machineGuns;
        checkHits.start();
        thingsTimer.start();
    }

    public void drawPanel(Graphics gr){
        for( int i = 0; i < hp; i++ ){
            gr.setColor(new Color(218,101,111));
            gr.fillRect(40+(i*20), 1030, 20, 8);
        }
        for( int i = hp; i < 20; i ++ ){
            gr.setColor(new Color(171,178,191));
            gr.fillRect(40+(i*20), 1030, 20, 8);
        }
        gr.setColor(new Color(171,178,191));
        gr.setFont(new Font("Arial", Font.BOLD, 50));
        gr.drawString((30-numberOfShot)+"", 40, 1010);
    }


    public void draw(Graphics g){
      Graphics2D g2d = (Graphics2D)g;
      AffineTransform old = g2d.getTransform();
      g2d.setColor(Color.WHITE);
      g2d.setColor(Color.BLACK);
      g2d.rotate(Math.toRadians(rotation+90), x+25, y+25);
      g2d.fillRect(x+35, y-5, 20, 40);
      g2d.setColor(new Color(0,138,246));
      g2d.fillOval(x, y, 50, 50);
      g2d.setTransform(old);

    }

}
