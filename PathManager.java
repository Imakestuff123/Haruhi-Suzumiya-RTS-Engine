import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.lang.Math;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
public class PathManager extends Object
{
   int startx;
   int starty;
   int endx;
   int endy;
   //double theoreticalxsize = resizex((int)0.5);
   //double theoreticalysize = resizey((int)0.5);
   double theoreticalxsize = 0.5 * main.Windowmultiplyer;
   double theoreticalysize = 0.5 * main.Windowmultiplyer;
   double Arrowdiagnal = 16 * main.Windowmultiplyer;//distance(0, 0, (int)(theoreticalxsize * 32), (int)(theoreticalysize * 32));
   ArrayList <PathArrow> PathArrows = new ArrayList<PathArrow>();
   ArrayList <PathPoint> PathPoints = new ArrayList<PathPoint>();
   double distancedirection;
   public PathManager(MainManager main, int x, int y, KeyboardInput Keyboard, MouseInput Mouse, String color, int startx, int starty, int endx, int endy) 
   {
       super(main, x, y, Keyboard, Mouse, "sprites/spr_patharrowred.png");
       this.startx = startx;
       this.starty = starty;
       this.endx = endx;
       this.endy = endy;
       new PathPoint(main, startx, starty, Keyboard, Mouse, color, this);
       new PathPoint(main, endx, endy, Keyboard, Mouse, color, this);
       double distance = distance(startx, starty, endx, endy);
       distancedirection = variablepoint_directiondegrees(startx, starty, endx, endy);
       for (int i = 0 + (int)Arrowdiagnal; i <= distance ; i += Arrowdiagnal) {
           PathArrow kk = new PathArrow(main, (int)(startx + (lengthdir_x(theoreticalxsize * 32, distancedirection) * (i / Arrowdiagnal))), (int)(starty + (lengthdir_y(theoreticalysize * 32, distancedirection) * (i / Arrowdiagnal) )), Keyboard, Mouse, color, this, (int)distancedirection);
       }
   }
   public void createstep() {
       
   }
   public void setupstep() {
       
   }
   public void step() {
       
   }
   public void drawstep(Graphics g, Graphics2D g2) {
       setup(g, g2);
   }
   public void DeleteSelf() {
       for (int i = 0; i <= PathArrows.size() - 1; i++) {
           PathArrows.get(i).DeleteSelf();           
           i--;
       }
       for (int i = 0; i <= PathPoints.size() - 1; i++) {
           PathPoints.get(i).DeleteSelf();           
           i--;
       }
       DeleteSelfBase();
   }
}
