import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

class Blot{
    public int x, y;
    public BufferedImage blotImg;
    double rotation;

    public Blot(BufferedImage blotImg){
        this.blotImg = blotImg;
    }


    public void draw(Graphics gr){
        Graphics2D g2d = (Graphics2D)gr;
        AffineTransform old = g2d.getTransform();
        g2d.rotate(Math.toRadians(rotation), x+8, y+15);
        g2d.drawImage(blotImg, x, y, 16,  30, null);
        g2d.setTransform(old);
    }
}