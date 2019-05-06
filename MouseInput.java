import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;
public class MouseInput implements MouseListener, MouseMotionListener{  
    MainManager main;
    JFrame gameFrame;
    int MouseClickLeft;
    int MouseClickRight;
    int MouseDownLeft;
    int MouseDownRight;
    int JustClickedLeft;
    int JustClickedRight;
    int MouseInside;
    int Mousex;
    int Mousey;
    Object MouseTouching;
    //int Mousegamex = main.Mapx + (int)((double)Mousex / main.Windowmultiplyer);
    //int Mousegamey = main.Mapy + (int)((double)Mousey / main.Windowmultiplyer);
    PathManager asdtest;
    public MouseInput(MainManager main, JFrame gameFrame) {  
        this.main = main;
        gameFrame.addMouseListener(this);  
        gameFrame.addMouseMotionListener(this);
    }  
    public void mouseClicked(MouseEvent e) {  
        if (SwingUtilities.isLeftMouseButton(e) == true) {
            MouseClickLeft = 1;
            JustClickedLeft = 1;
        }
        if (SwingUtilities.isRightMouseButton(e) == true) {
            MouseClickRight = 1;
            JustClickedRight = 1;
        }
        
        Mousex = e.getX();
        Mousey = e.getY();
        
        //Pathtest();
        //hitboxtest();
    }
    public void mouseEntered(MouseEvent e) {  
        MouseInside = 1;
        Mousex = e.getX();
        Mousey = e.getY();
    }  
    public void mouseExited(MouseEvent e) {  
        MouseInside = 0;
        Mousex = e.getX();
        Mousey = e.getY();
    }  
    public void mousePressed(MouseEvent e) {  
        if (SwingUtilities.isLeftMouseButton(e) == true) MouseDownLeft = 1;
        if (SwingUtilities.isRightMouseButton(e) == true) MouseDownRight = 1;
        Mousex = e.getX();
        Mousey = e.getY();
    }  
    public void mouseReleased(MouseEvent e) {  
        if (SwingUtilities.isLeftMouseButton(e) == true) MouseDownLeft = 0;
        if (SwingUtilities.isRightMouseButton(e) == true) MouseDownRight = 0;
        Mousex = e.getX();
        Mousey = e.getY();
    }  
    public void mouseMoved(MouseEvent e)
    {
        Mousex = e.getX();
        Mousey = e.getY();
    }
    public void mouseDragged(MouseEvent e) 
    {
        Mousex = e.getX();
        Mousey = e.getY();
    }
    //mouse test functions
    public void Pathtest() {
        if (asdtest != null) asdtest.DeleteSelf();
        asdtest = new PathManager(main, 0, 0, main.Keyboard, this, "green", 200, 200, main.Mapx + (int)((double)Mousex / main.Windowmultiplyer), main.Mapy + (int)((double)Mousey / main.Windowmultiplyer));
    }
    public void hitboxtest() {
        for (int i = 0; i <= main.Objects.size() - 1; i++) {
            Object TargetObject = main.Objects.get(i);
            if (TargetObject.hitbox != null) {
                if (TargetObject.hitbox.contains(Mousex, Mousey)) System.out.println("hit");
            }
        }
    }
}  