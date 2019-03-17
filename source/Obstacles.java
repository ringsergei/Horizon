import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

class Obstacles{
    public int width, height, x, y;
    public boolean isMoved=false, moveDown=true;
    public String orientation;

    Timer move = new Timer(10, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if( orientation=="vertical" )
             if( moveDown==true ){
                 y+=1;
                 if( y+height>=1050 ){
                    moveDown=false;
                    y-=1;
                 }
             }
             else{
                 if( y>=30 ){
                     y-=1;
                 }
                 else{
                     moveDown=true;
                     y+=1;
                 }
             }
      }
    });

    public void setValue(String name, String value){
        if( name=="x" ){
            x = Integer.parseInt(value);
        }
        else if( name=="y" ){
            y = Integer.parseInt(value);
        }
        else if( name=="width" ){
            width = Integer.parseInt(value);
        }
        else if( name=="height" ){
            height = Integer.parseInt(value);
        }
    }


    public void draw(Graphics gr){
        gr.setColor(new Color(40,44,52));
        gr.fillRect(x, y, width, height);
    }

}
