import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.lang.Math;
import java.awt.image.BufferedImage;
import java.io.File;
public class Ship extends Object
{
   Player ShipPlayer;
   int NumberofShips;
   double LaunchVelocity = 0;
   double Speed = 1;
   obj_Light Light;
   obj_Light RangeIndicator;
   PathManager CurrentPath;
   Team TeamReference;
   ShipController ShipControllerReference;
   
   int ShipBinding = -1;
   
   double DesiredMovementDirection = -1;
   double MoveDestinationx = -1;
   double MoveDestinationy = -1;
   Ship Target;
   //general stats
   final double rangemultiplyer = 3.5;
   final double StartingLaunchVelocity = 2;
   int MergeActionTimer = -1;
   final int MergeTimerCd = 45;
   final int HowLongMergeTakes = 180;
   Ship MergeTarget;
   int MergingTimer = -1;
   
   int SplitCdTimer = -1;
   final int SplitCd = 300;
   public Ship(MainManager main, int x, int y, KeyboardInput Keyboard, MouseInput Mouse, Team TeamReference, ShipController ShipControllerReference, int NumberofShips) {
       super(main, x, y, Keyboard, Mouse, "sprites/spr_shipred.png");
       Light = new obj_Light(main, x, y, Keyboard, Mouse, 0.25f, 1, 1, this);
       Light.scaleSprite( (((double)((double)NumberofShips / 15000)) / 2) + 0.5, (((double)((double)NumberofShips / 15000)) / 2) + 0.5, 1);
       RangeIndicator = new obj_Light(main, x, y, Keyboard, Mouse, 0.10f, 1, 1, this);
       RangeIndicator.scaleSprite( rangemultiplyer * ((((double)((double)NumberofShips / 15000)) / 4) + 0.75), rangemultiplyer * ((((double)((double)NumberofShips / 15000)) / 4) + 0.75), 1);
       this.TeamReference = TeamReference;
       this.ShipControllerReference = ShipControllerReference;
       TeamReference.TeamShips.add(this);
       main.ShipObjects.add(this);
       if (ShipControllerReference != null) ShipControllerReference.TeamShips.add(this);
       this.NumberofShips = NumberofShips;
       switch (TeamReference.TeamNumber) {
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
       reDrawHitbox(camerax, cameray);
       //split(270, 500);
   }
   public void createstep() {
       
   }
   public void setupstep() {
       reDrawHitbox(camerax, cameray);
       ClickedOnCheck();
       resetRotation();
       if (LaunchVelocity > 0) LaunchVelocity -= 0.01;
       if (MergeActionTimer > 0) MergeActionTimer--;
       if (MergingTimer > 0) MergingTimer--;
       if (SplitCdTimer > 0) SplitCdTimer--;
   }
   public void step() {
       Light.x = x;
       Light.y = y;
       RangeIndicator.x = x;
       RangeIndicator.y = y;
       
       PathActions();
       
       if (MergeActionTimer == 0) {
           MergeActions();
           MergeActionTimer = MergeTimerCd;
       }
       if (MergingTimer == 0) {
           new Ship(main, (int)x, (int)y, Keyboard, Mouse, TeamReference, ShipControllerReference, MergeTarget.NumberofShips + NumberofShips);
           MergeTarget.DeleteSelf();
           DeleteSelf();
           MergingTimer = -1;
       }
   }
   public void drawstep(Graphics g, Graphics2D g2) {
       setup(g, g2);
       drawself(g, g2);
       setAlpha(g2, 0.35f);
       setTeamColor(g);
       DrawHitBox(g, hitbox);
       
       setAlpha(g2, 1f);
       g.setColor(Color.WHITE);
       g2.drawString(Integer.toString(NumberofShips), (int)(camerax - spriteWidth / 2), (int)(cameray + spriteHeight / 2 + resize(10)));
       if (ShipBinding != -1) g2.drawString(Integer.toString(ShipBinding), (int)(camerax - spriteWidth / 2 - resize(20)), (int)(cameray + spriteHeight / 2 + resize(25)));
       
       //draw merging progress bar
       
       //draw selection circle
       if (ShipControllerReference != null) {
           setTeamColor(g);
           if (ShipControllerReference.ShipSelected == this) {
               g.drawOval((int)(camerax - spriteWidth), (int)(cameray - spriteHeight), (int)((double)spriteWidth * 2), (int)((double)spriteHeight * 2));
           }
       }
       if (MergingTimer > 0) {
           g.setColor(Color.WHITE);
           int mergebarstartx = (int)resize(camerax - 40);
           int mergebarendx = (int)resize(camerax + 10);
           int mergebarstarty = (int)resize(cameray - 60);
           int mergebarendy = (int)resize(cameray - 40);
           double percentagefull = 1.0 - (double)((double)MergingTimer / (double)HowLongMergeTakes);
           g.drawRect(mergebarstartx, mergebarstarty, mergebarendx - mergebarstartx, mergebarendy - mergebarstarty);
           g.fillRect(mergebarstartx, mergebarstarty, (int)(percentagefull * (double)(mergebarendx - mergebarstartx)), mergebarendy - mergebarstarty);
       }
   }
   public void DeleteSelf() {
       for (int i = 0; i <= TeamReference.TeamShips.size() - 1; i++) {
           if (TeamReference.TeamShips.get(i) == this) TeamReference.TeamShips.remove(i);
       }
       if (ShipControllerReference != null) {
           for (int i = 0; i <= ShipControllerReference.TeamShips.size() - 1; i++) {
               if (ShipControllerReference.TeamShips.get(i) == this) ShipControllerReference.TeamShips.remove(i);
           }
       }
       for (int i = 0; i <= main.ShipObjects.size() - 1; i++) {
           if (main.ShipObjects.get(i) == this) main.ShipObjects.remove(i);
       }
       if (Light != null) Light.DeleteSelf();
       if (RangeIndicator != null) RangeIndicator.DeleteSelf();
       if (ShipControllerReference.ShipSelected == this) ShipControllerReference.ShipSelected = null;
       DeleteSelfBase();    
   }
   public Ship split(int Rotation, int Scoutsize) {
       if ((Scoutsize < NumberofShips) && (Scoutsize >= 500)){
           Ship TempScout = new Ship(main, (int)x, (int)y, Keyboard, Mouse, TeamReference, ShipControllerReference, Scoutsize);
           TempScout.Rotation = Rotation;
           TempScout.LaunchVelocity = StartingLaunchVelocity;
           NumberofShips -= Scoutsize;
           Light.DeleteSelf();
           RangeIndicator.DeleteSelf();
           Light = new obj_Light(main, (int)x, (int)y, Keyboard, Mouse, 0.25f, 1, 1, this);
           Light.scaleSprite( (((double)((double)NumberofShips / 15000)) / 2) + 0.5, (((double)((double)NumberofShips / 15000)) / 2) + 0.5, 1);
           RangeIndicator = new obj_Light(main, (int)x, (int)y, Keyboard, Mouse, 0.10f, 1, 1, this);
           RangeIndicator.scaleSprite( rangemultiplyer * ((((double)((double)NumberofShips / 15000)) / 4) + 0.75), rangemultiplyer * ((((double)((double)NumberofShips / 15000)) / 4) + 0.75), 1);
           return TempScout;
       }
       return new Ship(main, (int)x, (int)y, Keyboard, Mouse, TeamReference, ShipControllerReference, Scoutsize);
   }
   public void PathPrep() {
       MoveDestinationx = CurrentPath.endx;//resize(CurrentPath.endx - main.Mapx);
       MoveDestinationy = CurrentPath.endy;//resize(CurrentPath.endy - main.Mapy);
       //DesiredMovementDirection = (int)CurrentPath.distancedirection;
       DesiredMovementDirection = point_directiondegrees(x, y, MoveDestinationx, MoveDestinationy);//point_directiondegrees(camerax, cameray, Destinationx, Destinationy);
   }
   public void PathActions() {
       if ((DesiredMovementDirection != -1) && (MoveDestinationx != -1) && (MoveDestinationy != -1)) {
           DesiredMovementDirection = point_directiondegrees(x, y, MoveDestinationx, MoveDestinationy);
           //DesiredMovementDirection = point_directiondegrees(camerax, cameray, Destinationx, Destinationy);
           if (Rotation == (int)DesiredMovementDirection) {
               //Move towards Point
               //int sin = (int)point_directiondegrees(camerax, cameray, Destinationx, Destinationy);
               x += ((lengthdir_x(Speed + LaunchVelocity, (double)Rotation)));
               y += ((lengthdir_y(Speed + LaunchVelocity, (double)Rotation)));
           } else {
               int bazinga = 0;
               int bazinga1 = 0;
               if (Rotation > (int)DesiredMovementDirection) {
                   bazinga = (int)(Rotation - DesiredMovementDirection);
                   bazinga1 = 360 - bazinga;
               } else {
                   bazinga1 = (int)(DesiredMovementDirection - Rotation);
                   bazinga = 360 - bazinga1;
               }
               
               if (bazinga1 > bazinga) {
                   Rotation--; //left
               } else {
                   Rotation++;
               }
               if (Rotation == 360) Rotation = 0;
               if (Rotation == -1) Rotation = 359;
           }
           if ((((int)x == (int)MoveDestinationx) && ((int)y == (int)MoveDestinationy)) || (hitbox.contains(resize(MoveDestinationx - main.Mapx), resize(MoveDestinationy - main.Mapy)))){
               if (distance(x, y, MoveDestinationx, MoveDestinationy) < 50) {
                   if (Target != null) {
                       Ship TempTarget = Target;
                       ResetPathWork();
                       if ((TempTarget.TeamReference == TeamReference) && (TempTarget.MergeTarget == null)){
                           //merge
                           if (TempTarget.Target == this) TempTarget.ResetPathWork();
                           ResetPathWork();
                           if ((ShipControllerReference.ShipSelected == this) || (ShipControllerReference.ShipSelected == TempTarget)) ShipControllerReference.ShipSelected = null;
                           MergeTarget = TempTarget;
                           TempTarget.MergeTarget = this;
                           MergingTimer = HowLongMergeTakes;
                           TempTarget.MergingTimer = HowLongMergeTakes;
                       } else {
                           //attack
                       }
                   } else {
                       ResetPathWork();
                   }
               }
           }
       }
   }
   public void MergeActions() {
       if ((Target != null) && (Target.TeamReference == TeamReference)){
           if ((Target.x != MoveDestinationx) || (Target.y != MoveDestinationy)) {
               if (CurrentPath != null) CurrentPath.DeleteSelf();
               String ColorFound = "white";
               switch (TeamReference.TeamNumber) {
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
               CurrentPath = new PathManager(main, 0, 0, Keyboard, Mouse, ColorFound, (int)x, (int)y, (int)Target.x, (int)Target.y);
               MoveDestinationx = Target.x;
               MoveDestinationy = Target.y;
               DesiredMovementDirection = point_directiondegrees(x, y, MoveDestinationx, MoveDestinationy);
               
           }
           
       }
   }
   public void ResetPathWork() {
       if (CurrentPath != null) CurrentPath.DeleteSelf();
       CurrentPath = null;
       DesiredMovementDirection = -1;
       MoveDestinationx = -1;
       MoveDestinationy = -1;
       MergingTimer = -1;
       MergeActionTimer = -1;
       if (Target != null) Target = null;
       if (MergeTarget != null) {
           MergeTarget.ResetPathWork();
           MergeTarget.MergeTarget = null;
           MergeTarget = null;
       }
   }
   public void setTeamColor(Graphics g) {
       switch (TeamReference.TeamNumber) {
           case 1:
                g.setColor(Color.RED);
                break;
           case 2:
                g.setColor(Color.YELLOW);
                break;
           case 3:
                g.setColor(Color.BLUE);
                break;
           case 4:
                g.setColor(Color.GREEN);
                break;
       }
   }
}
