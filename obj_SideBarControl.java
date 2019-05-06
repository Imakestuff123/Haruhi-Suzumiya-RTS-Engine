import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.lang.Math;
public class obj_SideBarControl extends Object
{
   int Sidebarx = resizeint(1600);
   int Sidebarxend = resizeint(1900);
   int Sidebarxlength = Sidebarxend - Sidebarx;
   int Mapx, Mapxlength, Mapxend, Mapy, Mapylength, Mapyend;
   public double rectx, recty;
   int rectwidth, rectheight;
   int AmountofShipsSelected = 500;
   public obj_SideBarControl(MainManager main, int x, int y, KeyboardInput Keyboard, MouseInput Mouse) {
       super(main, x, y, Keyboard, Mouse, "");
       Mapx = Sidebarx + resizeint(10);
       Mapxlength = resizeint(200);
       Mapxend = Mapx + Mapxlength;
       Mapy = resizeint(15);
       Mapylength = resizeint(200);
       Mapyend = Mapy + Mapylength;
       rectx = 0;
       recty = 0;
       x = -1;
       y = -1;
       rectwidth = resizeint(60);
       rectheight = resizeint(60);
   }
   public void createstep() {
       ArrowButton reference = new ArrowButton(main, (int)x, (int)y, Keyboard, Mouse, this, 90);
       reference = new ArrowButton(main, (int)x, (int)y, Keyboard, Mouse, this, 270);
   }
   public void setupstep() {
       
   }
   public void step() {
       rectx = main.Mapx / 15;
       recty = main.Mapy / 15;
   }
   public void drawstep(Graphics g, Graphics2D g2) {
       setup(g, g2);
       g.setColor(Color.WHITE);
       g2.drawString(Integer.toString(AmountofShipsSelected), (int)(Sidebarx + resize(215)), (int)(resize(15) + resize(75)));
       g.setColor(Color.GREEN);
       g.drawRect(Sidebarx, 0, Sidebarxlength, resizeint(600));
       g.setColor(Color.WHITE);
       g2.drawString("Max Map", Sidebarx + resizeint(12), 10);
       g.setColor(Color.PINK);
       g.drawRect(Mapx, Mapy, Mapxlength, Mapylength);
       for (double i = Mapx; i <= Mapxend; i += (double)((double)Mapxlength / 10.0)) {
           g.drawLine((int)i, Mapy, (int)i, Mapyend); 
       }
       for (double i = Mapy; i <= Mapyend; i += (double)((double)Mapylength / 10.0)) {
           g.drawLine(Mapx, (int)i, Mapxend, (int)i);
       }
       g.setColor(Color.WHITE);
       g.drawRect(Mapx + (int)rectx, Mapy + (int)recty, rectwidth, rectheight);
       
       g.setColor(Color.PINK);
       g2.drawString("Screenx: " + Integer.toString(main.Mapx), Mapx + resizeint(10), Mapyend + resizeint(20));
       g2.drawString("Screeny: " + Integer.toString(main.Mapy), Mapx + resizeint(10), Mapyend + resizeint(35));
       g2.drawString("MouseScreenx: " + Integer.toString(Mouse.Mousex), Mapx + resizeint(10), Mapyend + resizeint(50));
       g2.drawString("MouseScreeny: " + Integer.toString(Mouse.Mousey), Mapx + resizeint(10), Mapyend + resizeint(65));
       if (((Mouse.Mousex >= main.Screenx) && (Mouse.Mousex <= main.Screenx + main.Screenxlength)) && ((Mouse.Mousey >= main.Screeny) && (Mouse.Mousey <= main.Screeny + main.Screenylength))) {
           //in confines of map
           g2.drawString("MouseGamex: " + Integer.toString(main.Mapx + (int)((double)Mouse.Mousex / main.Windowmultiplyer)), Mapx + resizeint(10), Mapyend + resizeint(80));
           g2.drawString("MouseGamey: " + Integer.toString(main.Mapy + (int)((double)Mouse.Mousey / main.Windowmultiplyer)), Mapx + resizeint(10), Mapyend + resizeint(95));
       } else {
           g2.drawString("MouseGamex: " + "N/A", Mapx + resizeint(10), Mapyend + resizeint(80));
           g2.drawString("MouseGamey: " + "N/A", Mapx + resizeint(10), Mapyend + resizeint(95));
       }
   }
   public void DeleteSelf() {
       DeleteSelfBase();
   }
}
