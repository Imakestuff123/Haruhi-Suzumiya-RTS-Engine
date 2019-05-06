import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.lang.Math;
import java.awt.image.BufferedImage;
import java.io.File;
public class BlankObject extends Object
{
   int spritewidth;
   int spriteheight;
   public BlankObject(MainManager main, int x, int y, KeyboardInput Keyboard, MouseInput Mouse) {
       super(main, x, y, Keyboard, Mouse, "spritepath here");
       sprite = "spritepath";
       setSprite();
   }
   public void createstep() {
       
   }
   public void setupstep() {
       //TIMERS COUNTING DOWN GO HERE
       ClickedOnCheck();
       resetRotation();
       reDrawHitbox(camerax, cameray);
   }
   public void step() {
       
   }
   public void drawstep(Graphics g, Graphics2D g2) {
       setup(g, g2);
       drawself(g, g2);
   }
   public void DeleteSelf() {
       DeleteSelfBase();
   }
}
