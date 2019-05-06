import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.lang.Math;
import java.awt.image.BufferedImage;
import java.io.File;
public class PathPoint extends Object
{
   //int alphadirection;
   PathManager PathManage;
   public PathPoint(MainManager main, int x, int y, KeyboardInput Keyboard, MouseInput Mouse, String color, PathManager PathManage) {
       super(main, x, y, Keyboard, Mouse, "sprites/spr_pathpointred.png");
       PathManage.PathPoints.add(this);
       switch (color.toLowerCase()) {
           case "red":
                sprite = "sprites/spr_pathpointred.png";
                setSprite();
                break;
           case "yellow":
                sprite = "sprites/spr_pathpointyellow.png";
                setSprite();
                break;
           case "blue":
                sprite = "sprites/spr_pathpointblue.png";
                setSprite();
                break;
           case "green":
                sprite = "sprites/spr_pathpointgreen.png";
                setSprite();
                break;
       }
       this.scaleSprite(0.35, 0.35, 1);
       this.PathManage = PathManage;
   }
   public void createstep() {
   }
   public void setupstep() {
       
   }
   public void step() {
   }
   public void drawstep(Graphics g, Graphics2D g2) {
       setup(g, g2);
       drawself(g, g2);
   }
   public void DeleteSelf() {
       for (int i = 0; i <= PathManage.PathPoints.size() - 1; i++) {
           if (PathManage.PathPoints.get(i) == this) PathManage.PathPoints.remove(i);
       }
       DeleteSelfBase();
   }
}
