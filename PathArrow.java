import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.lang.Math;
import java.awt.image.BufferedImage;
import java.io.File;
public class PathArrow extends Object
{
   PathManager PathManage;
   //int alphadirection;
   public PathArrow(MainManager main, int x, int y, KeyboardInput Keyboard, MouseInput Mouse, String color, PathManager PathManage, int Rotation) {
       super(main, x, y, Keyboard, Mouse, "sprites/spr_patharrowred.png");
       PathManage.PathArrows.add(this);
       this.Rotation = Rotation;
       switch (color.toLowerCase()) {
           case "red":
                sprite = "sprites/spr_patharrowred.png";
                setSprite();
                break;
           case "yellow":
                sprite = "sprites/spr_patharrowyellow.png";
                setSprite();
                break;
           case "blue":
                sprite = "sprites/spr_patharrowblue.png";
                setSprite();
                break;
           case "green":
                sprite = "sprites/spr_patharrowgreen.png";
                setSprite();
                break;
       }
       this.scaleSprite(0.5, 0.5, 1);
       this.PathManage = PathManage;
   }
   public void createstep() {
       //alphadirection = -1;
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
       for (int i = 0; i <= PathManage.PathArrows.size() - 1; i++) {
           if (PathManage.PathArrows.get(i) == this) PathManage.PathArrows.remove(i);
       }
       DeleteSelfBase();
   }
}