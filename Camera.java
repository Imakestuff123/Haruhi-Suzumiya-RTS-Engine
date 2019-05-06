import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
public class Camera
{
    //map camera 
    //Main camera
    MainManager main;
    KeyboardInput Keyboard;
    obj_SideBarControl Sidebar;
    final int cameraSpeed = 15;
    final int shiftCameraSpeed = 15;
    int movex = 0;  
    int movey = 0;
    public Camera(MainManager main, KeyboardInput Keyboard, MouseInput Mouse, obj_SideBarControl Sidebar) {
        this.main = main;
        this.Keyboard = Keyboard;
        this.Sidebar = Sidebar;
    }
    public void CameraStep() {
        movex = Keyboard.keyddown - Keyboard.keyadown;
        movey = Keyboard.keysdown - Keyboard.keywdown;
        double SynValuex = main.Windowmultiplyer;
        double SynValuey = main.Windowmultiplyer;
        //if ((Sidebar.rectx + (SynValuex * movex) <= Sidebar.Mapxlength - Sidebar.rectwidth) && (Sidebar.rectx + (SynValuex * movex) >= 0)) Sidebar.rectx += (double)(SynValuex * movex);
        //if ((Sidebar.recty + (SynValuey * movey) <= Sidebar.Mapylength - Sidebar.rectheight) && (Sidebar.recty + (SynValuey * movey) >= 0)) Sidebar.recty += (double)(SynValuey * movey);
        SynValuex = (cameraSpeed + (shiftCameraSpeed * Keyboard.keyleftshiftdown)) * movex; //* main.Windowmultiplyer;
        SynValuey = (cameraSpeed + (shiftCameraSpeed * Keyboard.keyleftshiftdown)) * movey; //* main.Windowmultiplyer;
        if (main.Mapx + SynValuex <= 0) main.Mapx = 0;
        if (main.Mapx + SynValuex >= 2100) main.Mapx = 2100;
        if (main.Mapy + SynValuey <= 0) main.Mapy = 0;
        if (main.Mapy + SynValuey >= 2100) main.Mapy = 2100;
        if ((main.Mapx + (SynValuex) <= 2100) && (main.Mapx + (SynValuex) >= 0)) main.Mapx += SynValuex;
        if ((main.Mapy + (SynValuey) <= 2100) && (main.Mapy + (SynValuey) >= 0)) main.Mapy += SynValuey;
        displaySpritesonCamera();
    }
    public void displaySpritesonCamera() {
        int regionxstart = main.Mapx;
        int regionystart = main.Mapy;
        int regionxend = main.Mapx + 900;
        int regionyend = main.Mapy + 900;
        for (int i = 0; i <= main.Objects.size() - 1; i++) {
            Object currentobject = main.Objects.get(i);
            if (currentobject != null) {
                double objx = currentobject.x;
                double objy = currentobject.y;
                if ((objx != -1) && (objy != -1)) {
                    if (((objx >= regionxstart) && (objx <= regionxend)) && ((objy >= regionystart) && (objy <= regionyend))) {
                        currentobject.camerahidden = 0;
                        currentobject.camerax = Object.staticresize(objx - regionxstart, main.Windowmultiplyer);
                        currentobject.cameray = Object.staticresize(objy - regionystart, main.Windowmultiplyer);
                    } else {
                        currentobject.camerahidden = 1;
                    }
                }
            }
        }
        
    }
}
