import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.lang.Math;
import java.awt.image.BufferedImage;
import java.io.File;
public class obj_Light extends Object
{
   float lightalpha;
   int spritewidth;
   int spriteheight;
   Ship ShipParent;
   public obj_Light(MainManager main, int x, int y, KeyboardInput Keyboard, MouseInput Mouse, float lightalpha, double xsize, double ysize, Ship ShipParent) {
       //sprites/spr_light2.png
       super(main, x, y, Keyboard, Mouse, "sprites/spr_light2.png");
       main.LightObjects.add(this);
       this.lightalpha = lightalpha;
       this.xsize = xsize;
       this.ysize = ysize;
       this.ShipParent = ShipParent;
       sprite = "sprites/spr_light2.png";
       setSprite();
   }
   public void createstep() {
       //hidden = 1;
   }
   public void setupstep() {
       reDrawHitbox(camerax, cameray);
   }
   public void step() {
       
   }
   public void drawstep(Graphics g, Graphics2D g2) {
       setup(g, g2);
       //DrawHitBox(g, hitbox);
       alpha = lightalpha;
       drawself(g, g2);
       g.setColor(Color.PINK);
   }
   public void DeleteSelf() {
       for (int i = 0; i <= main.LightObjects.size() - 1; i++) {
           if (main.LightObjects.get(i) == this) main.LightObjects.remove(i);
       }
       ShipParent.Light = null;
       DeleteSelfBase();
   }
}

