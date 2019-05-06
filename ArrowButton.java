import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.lang.Math;
import java.awt.image.BufferedImage;
import java.io.File;
public class ArrowButton extends Object
{
   int spritewidth;
   int spriteheight;
   obj_SideBarControl SideBarReference;
   public ArrowButton(MainManager main, int x, int y, KeyboardInput Keyboard, MouseInput Mouse, obj_SideBarControl SideBarReference, int Rotation) {
       super(main, x, y, Keyboard, Mouse, "spritepath here");
       switch (main.ShipControllerReference.TeamReference.TeamNumber) {
           case 1:
                sprite = "sprites/spr_shipred.png";
                setSprite();
                break;
           case 2:
                sprite = "sprites/spr_shipyellow.png";
                setSprite();
                break;
           case 3:
                sprite = "sprites/spr_shipblue.png";
                setSprite();
                break;
           case 4:
                sprite = "sprites/spr_shipgreen.png";
                setSprite();
                break;
       }
       scaleSprite(2, 2, 1);
       this.SideBarReference = SideBarReference;
       this.Rotation = Rotation;
       
   }
   public void createstep() {
       
   }
   public void setupstep() {
       ClickedOnCheck();
       resetRotation();
       if (Rotation == 90) reDrawHitbox(SideBarReference.Sidebarx + resize(250), resize(15) + resize(110));
       if (Rotation == 270) reDrawHitbox(SideBarReference.Sidebarx + resize(250), resize(15) + resize(30));
   }
   public void step() {
       if (ClickedOn == 1) {
           if ((Rotation == 90) && (SideBarReference.AmountofShipsSelected - 20 >= 500)) SideBarReference.AmountofShipsSelected -= 20;
           if ((Rotation == 270) && (SideBarReference.AmountofShipsSelected + 20 < 15000)) SideBarReference.AmountofShipsSelected += 20;
           //System.out.println("test");
       }
       //System.out.println(hitbox.getX());
       //System.out.println(hitbox.getWidth());
       //System.out.println(Rotation);
   }
   public void drawstep(Graphics g, Graphics2D g2) {
       setup(g, g2);
       
       if (Rotation == 90) drawself(g, g2, SideBarReference.Sidebarx + (int)resize(250), (int)(resize(15) + resize(110)));
       if (Rotation == 270) drawself(g, g2, SideBarReference.Sidebarx + (int)resize(250), (int)(resize(15) + resize(30)));
       //g.setColor(Color.WHITE);
       //DrawHitBox(g, hitbox);
   }
   public void DeleteSelf() {
       DeleteSelfBase();
   }
}
