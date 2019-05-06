import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
public class ShipController extends Object
{
   ArrayList <Ship> TeamShips = new ArrayList<Ship>();
   Player ShipPlayer;
   Team TeamReference;
   Ship ShipSelected;
   //PathManager CurrentPath;
   Ship[] Shipbindingarray = new Ship[10];
   public ShipController(MainManager main, int x, int y, KeyboardInput Keyboard, MouseInput Mouse, Team TeamReference) {
       super(main, x, y, Keyboard, Mouse, "");
       this.TeamReference = TeamReference;
   }
   public void createstep() {
   }
   public void setupstep() {
       
   }
   public void step() {
       if (ShipSelected != null) {
           if (ShipSelected.MergingTimer != -1) ShipSelected = null;
           if (ShipSelected.LaunchVelocity > 0) ShipSelected = null;
       }
       if ((Mouse.MouseClickLeft == 1) && (Object.isbetween(Mouse.Mousex, Mouse.Mousey, main.Screenx, main.Screeny, main.Screenxlength, main.Screenylength))){
           ShipSelected = null;
           for (int i = 0; i <= TeamShips.size() - 1; i++) {
               Ship CurrentShip = TeamShips.get(i);
               if (CurrentShip.ClickedOn == 1) {
                   ShipSelected = TeamShips.get(i);
               }
           }
       }
       if ((Mouse.MouseClickRight == 1) && (Object.isbetween(Mouse.Mousex, Mouse.Mousey, main.Screenx, main.Screeny, main.Screenxlength, main.Screenylength))) {
           //Reset everything pertaining
           Ship Target = null;
           for (int i = 0; i <= main.ShipObjects.size() - 1; i++) {
               Ship CurrentShip = main.ShipObjects.get(i);
               if (CurrentShip.ClickedOnRight == 1) {
                   Target = main.ShipObjects.get(i);
               }
           }
           if (ShipSelected != null) {
               ShipSelected.ResetPathWork();
               if (Target == null) {
                   //if Nothing is clicked, then a path will be created from there
                   if (ShipSelected.CurrentPath != null) ShipSelected.CurrentPath.DeleteSelf();
                   String ColorFound = "white";
                   switch (ShipSelected.TeamReference.TeamNumber) {
                       case 1:
                            ColorFound = "red";
                            break;
                       case 2:
                            ColorFound = "yellow";
                            break;
                       case 3:
                            ColorFound = "blue";
                            break;
                       case 4:
                            ColorFound = "green";
                            break;
                   }
                   if (Keyboard.keyqdown == 1) {
                       if (((main.Sidebar.AmountofShipsSelected < ShipSelected.NumberofShips) && (main.Sidebar.AmountofShipsSelected >= 500)) && (ShipSelected.SplitCdTimer <= 0)){
                           Ship createdShip = ShipSelected.split((int)variablepoint_directiondegrees(ShipSelected.x, ShipSelected.y, main.Mapx + ((double)Mouse.Mousex / main.Windowmultiplyer), main.Mapy + (((double)Mouse.Mousey / main.Windowmultiplyer))), main.Sidebar.AmountofShipsSelected);
                           createdShip.CurrentPath = new PathManager(main, 0, 0, Keyboard, Mouse, ColorFound, (int)createdShip.x, (int)createdShip.y,  main.Mapx + (int)((double)Mouse.Mousex / main.Windowmultiplyer), main.Mapy + (int)((double)Mouse.Mousey / main.Windowmultiplyer));
                           createdShip.PathPrep();
                           ShipSelected.SplitCdTimer = ShipSelected.SplitCd;
                           main.Sidebar.AmountofShipsSelected = 500;
                       }
                   }
                   if (Keyboard.keyqdown != 1) {
                       //regular path generation
                       ShipSelected.CurrentPath = new PathManager(main, 0, 0, Keyboard, Mouse, ColorFound, (int)ShipSelected.x, (int)ShipSelected.y,  main.Mapx + (int)((double)Mouse.Mousex / main.Windowmultiplyer), main.Mapy + (int)((double)Mouse.Mousey / main.Windowmultiplyer));
                       ShipSelected.PathPrep();
                    }
               } else {
                   //if target is a ship
                   if ((Target.TeamReference == TeamReference) && (Target.ShipControllerReference == this)) {
                       //merge
                       ShipSelected.MergeActionTimer = 30;
                       ShipSelected.Target = Target;
                       if (ShipSelected.CurrentPath != null) ShipSelected.CurrentPath.DeleteSelf();
                       String ColorFound = "white";
                       switch (ShipSelected.TeamReference.TeamNumber) {
                           case 1:
                                ColorFound = "red";
                                break;
                           case 2:
                                ColorFound = "yellow";
                                break;
                           case 3:
                                ColorFound = "blue";
                                break;
                           case 4:
                                ColorFound = "green";
                                break;
                       }
                       ShipSelected.CurrentPath = new PathManager(main, 0, 0, Keyboard, Mouse, ColorFound, (int)ShipSelected.x, (int)ShipSelected.y,  (int)Target.x, (int)Target.y);
                       ShipSelected.PathPrep();
                   } else {
                       //attack
                       ShipSelected.Target = Target;
                   }
               }
               
            }
       }
       if ((Keyboard.keycpressed == 1) && (Keyboard.keyctrldown != 1)){
           if ((ShipSelected != null) && (ShipSelected.CurrentPath != null)) {
               ShipSelected.ResetPathWork();
           }
       }
       //Binding ships
       if (ShipSelected != null) {
           if (Keyboard.keyctrldown == 1) {
               if (Keyboard.keycpressed == 1) {
                   if (ShipSelected.ShipBinding != -1) {
                       Shipbindingarray[ShipSelected.ShipBinding] = null;
                       ShipSelected.ShipBinding = -1;
                   }
               }
               if (Keyboard.keynumbermasterpressed != -1) {
                   if (ShipSelected.ShipBinding != -1)Shipbindingarray[ShipSelected.ShipBinding] = null;
                   ShipSelected.ShipBinding = Keyboard.keynumbermasterpressed;
                   if (Shipbindingarray[Keyboard.keynumbermasterpressed] != null) {
                       Shipbindingarray[Keyboard.keynumbermasterpressed].ShipBinding = -1;
                   }
                   Shipbindingarray[Keyboard.keynumbermasterpressed] = ShipSelected;
               }
           } else {
               if (Keyboard.keynumbermasterpressed != -1) {
                    if (Shipbindingarray[Keyboard.keynumbermasterpressed] != null) {
                        ShipSelected = Shipbindingarray[Keyboard.keynumbermasterpressed];
                        if (Keyboard.keyleftshiftdown == 1) { 
                            main.Mapx = (int)ShipSelected.x - 450;
                            main.Mapy = (int)ShipSelected.y - 450;
                            if (ShipSelected.x - 450 > 2100) main.Mapx = 2100;
                            if (ShipSelected.x - 450 < 0) main.Mapx = 0;
                            if (ShipSelected.y - 450 > 2100) main.Mapy = 2100;
                            if (ShipSelected.y - 450 < 0) main.Mapy = 0;
                        } else {
                        }
                        
                    }
               }
           }
       }
   }
   public void drawstep(Graphics g, Graphics2D g2) {
       setup(g, g2);
       drawself(g, g2);
   }

   public void DeleteSelf() {
       for (int i = 0; i <= TeamShips.size() - 1; i++) {
           TeamShips.get(i).DeleteSelf();
       }
       main.ShipControllerReference = null;
       DeleteSelfBase();
   }

}
    