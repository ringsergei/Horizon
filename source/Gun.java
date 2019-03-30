import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.imageio.*;
import java.io.*;


class Gun{
    public Player player;
    public int ShotSpeed, numberOfShot=0, scatter, damage, mag, bull_d;
    public Color shot_color;
    public Bullet[] shots;
    public Image img, blotImg;

    Timer strikeTimer = new Timer(0, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Strike();
        }
    });


    public void Strike(){
        if( numberOfShot<mag){
            shots[numberOfShot] = new Bullet(player.obstacles, player);
            shots[numberOfShot].isOnField = true;
            shots[numberOfShot].color = shot_color;
            shots[numberOfShot].blotImg = blotImg;
            shots[numberOfShot].movedToX = ((player.mouseX-scatter) + (int)(Math.random()*(scatter*2)));
            shots[numberOfShot].movedToY =  ((player.mouseY-scatter) + (int)(Math.random()*(scatter*2)));;
            shots[numberOfShot].x = (player.x+19)+25*Math.cos(Math.toRadians(player.rotation+90));
            shots[numberOfShot].y = (player.y+19)+25*Math.sin(Math.toRadians(player.rotation+90));
            shots[numberOfShot].rotation = player.rotation;
            shots[numberOfShot].bull_d = bull_d;
            shots[numberOfShot].speed = ShotSpeed;
            shots[numberOfShot].SetVariables();
            shots[numberOfShot].timerUpdate.start();
            numberOfShot++;
        }
    }

    public Gun(int ShotSpeed, int mag, int stimer, int scatter, int damage, String img_name, String blot_img_name, Color shot_color, int bull_d, Player player){

        this.player = player;
        this.ShotSpeed = ShotSpeed;
        this.mag = mag;
        strikeTimer.setDelay(stimer);
        this.scatter = scatter;
        this.damage = damage;
        this.shots = new Bullet[mag];
        this.shot_color = shot_color;
        this.bull_d = bull_d;
        try{
            img = ImageIO.read(new File("images//" + img_name));
            blotImg = ImageIO.read(new File("images//" + blot_img_name));
        }catch(Exception exp){System.out.println(exp);}
        

    }

    public void drawImage(Graphics g){
        g.drawImage(img, 40, 980, img.getWidth(null), img.getHeight(null), null);
    }

}