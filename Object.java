import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.lang.Math;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
public abstract class Object extends JPanel
{
   MainManager main;
   String sprite;
   double x;
   double y;
   double camerax;
   double cameray;
   double xsize;
   double ysize;
   KeyboardInput Keyboard;
   MouseInput Mouse;
   float alpha = 1.0f;
   int hidden;
   int camerahidden;
   Image spriteImage;
   int Rotation;
   Rectangle hitbox;
   int ClickedOn = 0;
   int ClickedOnRight = 0;
   int spriteWidth;
   int spriteHeight;
   
   public Object(MainManager main, int xxx, int yyy, KeyboardInput Keyboard, MouseInput Mouse, String spriteReference) {
       this.main = main;
       main.Objects.add(this);
       this.x = xxx;
       this.y = yyy;
       xsize = 1;
       ysize = 1;
       this.Keyboard = Keyboard;
       this.Mouse = Mouse;
       this.sprite = spriteReference;
       
       
       //sprite = "sprites/spr_shipred.png";
       //sprite = "sprites/spr_light2.png";
       
       hidden = 0;
       camerahidden = 0;
       Rotation = 0;
       //System.out.println(sprite);
       //setSprite();
       //spriteImage = scaleSprite(1, 1, 1);
       //remember to write super in the Objects constrtucturos
       createstep();
   }
   void setSprite() {
       try {
           BufferedImage bimg = ImageIO.read(new File(this.sprite));
           spriteWidth = bimg.getWidth();
           spriteHeight = bimg.getHeight();
       } catch (Exception e) {
           System.out.println(this.sprite);
           System.out.println(e);
       }
       spriteImage = Toolkit.getDefaultToolkit().getImage(this.sprite);
       spriteImage = scaleSprite(xsize, ysize, 1);
       //System.out.println(spriteWidth);
       //System.out.println(spriteHeight);
   }
   int drawself(Graphics g, Graphics2D g2) {
       if (!(sprite.equals("-1"))) {
           
           //backup should be put before or after setalpha
           
           setAlpha(g2, alpha);
           AffineTransform backup = g2.getTransform();
           //bimg = ImageIO.read(new File(sprite));
           //int imagecenterx = (int)(resize((int)(spriteWidth / 2)) * (double)xsize);
           //int imagecentery = (int)(resize((int)(spriteHeight / 2)) * (double)ysize);
           
           double imagecenterx = (((spriteWidth / 2)) * (double)xsize);
           double imagecentery = (((spriteHeight / 2)) * (double)ysize);
           
           int Rotationtransfer = Rotation;
           if ((Rotationtransfer > 180) && (Rotationtransfer <= 360)) Rotationtransfer = (360 - Rotationtransfer) * -1;
           AffineTransform a = AffineTransform.getRotateInstance(Math.toRadians(Rotationtransfer), camerax, cameray);
           g2.setTransform(a);
           g2.drawImage(spriteImage, (int)(camerax - imagecenterx), (int)(cameray - imagecentery), this);
           g2.setTransform(backup);
           return 1;
       }
       return -1;
   }
   int drawself(Graphics g, Graphics2D g2, int xx, int yy) {
       if (!(sprite.equals("-1"))) {
           //backup should be put before or after setalpha
           
           setAlpha(g2, alpha);
           AffineTransform backup = g2.getTransform();
           //bimg = ImageIO.read(new File(sprite));
           //int imagecenterx = (int)(resize((int)(spriteWidth / 2)) * (double)xsize);
           //int imagecentery = (int)(resize((int)(spriteHeight / 2)) * (double)ysize);
           
           double imagecenterx = (((spriteWidth / 2)) * (double)xsize);
           double imagecentery = (((spriteHeight / 2)) * (double)ysize);
           
           int Rotationtransfer = Rotation;
           if ((Rotationtransfer > 180) && (Rotationtransfer <= 360)) Rotationtransfer = (360 - Rotationtransfer) * -1;
           AffineTransform a = AffineTransform.getRotateInstance(Math.toRadians(Rotationtransfer), xx, yy);
           g2.setTransform(a);
           g2.drawImage(spriteImage, (int)(xx - imagecenterx), (int)(yy - imagecentery), this);
           g2.setTransform(backup);
           return 1;
       }
       return -1;
   }
   void drawsprite(Graphics g, Graphics2D g2, String spritepath, double xx, double yy, double Rotationvalue, double xxsize, double yysize, float alphavalue) {
       
       //back up should be put before or after setAlpha
       AffineTransform backup = g2.getTransform();
       Image Imagefile = main.scaleImage(xxsize, yysize, spritepath);
       setAlpha(g2, alphavalue);
       //BufferedImage TempBimg = ImageIO.read(new File(spritepath));
       //int imagecenterxx = (int)(resize((int)(TempspriteWidth / 2)) * (double)xxsize);
       //int imagecenteryy = (int)(resize((int)(TempspriteHeight / 2)) * (double)yysize);
       
       double imagecenterxx = (((spriteWidth / 2)) * (double)xxsize);
       double imagecenteryy = (((spriteHeight / 2)) * (double)yysize);
       
       AffineTransform a = AffineTransform.getRotateInstance(Math.toRadians(Rotationvalue), xx, yy);
       g2.setTransform(a);
       g2.drawImage(Imagefile, (int)(xx - imagecenterxx), (int)(yy - imagecenteryy), this);
       g2.setTransform(backup);

   }
   static double lengthdir_x(double xx, double direction) {
       double Rotationtransfer = direction;
       if ((Rotationtransfer > 180) && (Rotationtransfer <= 360)) Rotationtransfer = (360 - Rotationtransfer) * -1;
       Rotationtransfer = Math.toRadians(Rotationtransfer);
       return (Math.cos(Rotationtransfer) * xx);
   }
   static double lengthdir_y(double yy, double direction) {
       double Rotationtransfer = direction;
       if ((Rotationtransfer > 180) && (Rotationtransfer <= 360)) Rotationtransfer = (360 - Rotationtransfer) * -1;
       Rotationtransfer = Math.toRadians(Rotationtransfer);
       return (Math.sin(Rotationtransfer) * yy);
   }
   void setAlpha(Graphics2D g2, float alpha) {
       AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
       g2.setComposite(ac);
       this.alpha = alpha;
   }
   static int closestDivisible(int number, int divisibleby) {
       if (number % divisibleby == 0) return number;
       int q, n1, n2;
       q = number / divisibleby;
       //first possible closet number
       n1 = divisibleby * q;
       if (number * divisibleby > 0) {
           n2 = divisibleby * (q + 1);
       } else {
           n2 = divisibleby * (q - 1);
       }
       if (Math.abs(number - n1) < Math.abs(number - n2)) return n1;
       return n2;
    }
   
   Image scaleSprite(double xsize, double ysize, int type) {
       //don't remove this or iot messes up rotation
       this.xsize = xsize;
       this.ysize = ysize;
       
       //BufferedImage bimg = ImageIO.read(new File(sprite));
       int width = spriteWidth;
       int height = spriteHeight;
   
       xsize = xsize * main.Windowmultiplyer;
       ysize = ysize * main.Windowmultiplyer;
       Image newImage;
       //ratio
       if (type == 1) {
           newImage = spriteImage.getScaledInstance((int)((double)width * xsize), (int)((double)height * ysize), Image.SCALE_DEFAULT);
           spriteImage = newImage;
           return newImage;
       }
       if (type == 0) {
           newImage = spriteImage.getScaledInstance((int)xsize, (int)ysize, Image.SCALE_DEFAULT);
           spriteImage = newImage;
           return newImage;
       }
    
   //exact width and height
       this.xsize = xsize;
       this.ysize = ysize;
       return Toolkit.getDefaultToolkit().getImage(sprite);
   }
   void DrawHitBox(Graphics g, Rectangle r) {
       try {
           BufferedImage bimg = ImageIO.read(new File(sprite));
           int spritewidth = spriteWidth;
           int spriteheight = spriteHeight;
           g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
       } catch (Exception e) {
           
       }
   }
   
   void DeleteSelfBase() {
       for (int i = 0; i <= main.Objects.size() - 1; i++) {
           if (main.Objects.get(i) == this) main.Objects.remove(i);
       }
   }
   static boolean isbetween(double Inputx, double Inputy, double x1, double y1, double x2, double y2) {
       if ((Inputx >= x1) && (Inputx <= x2)) {
           if ((Inputy >= y1) && (Inputy <= y2)) {
               return true;
           }
           return false;
       } 
       return false;
   }
   static double staticresize(double value, double multiplyer) {
      return ((double)value * multiplyer);
   }
   static int staticresizeint(double value, double multiplyer) {
      return (int)((double)value * multiplyer);
   }
   double resize(double value) {
       return (value * main.Windowmultiplyer);
   }
   int resizeint(double value) {
       return (int)((double)value * main.Windowmultiplyer);
   }
   void displayposition(Graphics2D g2) {
       g2.drawString(Integer.toString((int)y), (int)camerax, (int)cameray);
       g2.drawString(Integer.toString((int)x), (int)camerax, (int)cameray + 20);
   }
   abstract public void DeleteSelf();
   abstract public void createstep();
   abstract public void step();
   abstract public void drawstep(Graphics g, Graphics2D g2);
   abstract public void setupstep();
   public void setup(Graphics g, Graphics2D g2) {
       setAlpha(g2, alpha);
   }
   static double point_directiondegrees(double x1, double y1, double x2, double y2) {
       double bazinga = Math.toDegrees(Math.atan2((double)y2 - (double)y1, (double)x2 - (double)x1));
       if (bazinga < 0) bazinga = 360 + bazinga;
       return bazinga;
   }
   static double point_directionradians(double x1, double y1, double x2, double y2) {
       return Math.atan2((double)y2 - (double)y1, (double)x2 - (double)x1);
   }
   double variablepoint_directiondegrees(double x1, double y1, double x2, double y2) {
       x1 = resize(x1);
       y1 = resize(y1);
       x2 = resize(x2);
       y2 = resize(y2);
       double bazinga = Math.toDegrees(Math.atan2((double)y2 - (double)y1, (double)x2 - (double)x1));
       if (bazinga < 0) bazinga = 360 + bazinga;
       return bazinga;
   }
   static double distance(double x1, double y1, double x2, double y2) {
       return Math.sqrt((double)(x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
   }
   double variabledistance(double x1, double y1, double x2, double y2) {
       x1 = resize(x1);
       y1 = resize(y1);
       x2 = resize(x2);
       y2 = resize(y2);
       return Math.sqrt((double)(x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
   }
   void ClickedOnCheck() {
       if (hitbox != null) {
           if (Mouse.MouseClickLeft == 1) {
               if (hitbox.contains(Mouse.Mousex, Mouse.Mousey)) {
                   ClickedOn = 1;
               } else {
                   ClickedOn = 0;
               }
           } else {
               ClickedOn = 0;
           }
           if (Mouse.MouseClickRight == 1) {
               if (hitbox.contains(Mouse.Mousex, Mouse.Mousey)) {
                   ClickedOnRight = 1;
               } else {
                   ClickedOnRight = 0;
               }
           } else {
               ClickedOnRight = 0;
           }
       }
   }
   void reDrawHitbox(double xMid, double yMid) {
       try {
           double spritewidth = spriteWidth * xsize;
           double spriteheight = spriteHeight * ysize;
           hitbox = new Rectangle((int)(xMid - (spritewidth / 2)), (int)(yMid - (spriteheight / 2)), (int)spritewidth, (int)spriteheight);
       } catch (Exception e) {
           
       }
   }
   void resetRotation() {
       if (Rotation == 360) Rotation = 0;
       if (Rotation == -1) Rotation = 359;
    }
}
