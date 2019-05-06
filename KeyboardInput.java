import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyboardInput implements KeyListener
{
    MainManager main;
    JFrame gameFrame;
    int keyadown;
    int keywdown;
    int keyddown;
    int keysdown;   
    int keyqdown;
    
    int keynumbermasterdown = -1;
    int key0down;
    int key1down;
    int key2down;
    int key3down;
    int key4down;
    int key5down;
    int key6down;
    int key7down;
    int key8down;
    int key9down;
    
    int keyaltdown;
    int keyleftshiftdown;
    
    int keynumbermasterpressed = -1;
    int key0pressed;
    int key0justpressed;
    int key1pressed;
    int key1justpressed;
    int key2pressed;
    int key2justpressed;
    int key3pressed;
    int key3justpressed;
    int key4pressed;
    int key4justpressed;
    int key5pressed;
    int key5justpressed;
    int key6pressed;
    int key6justpressed;
    int key7pressed;
    int key7justpressed;
    int key8pressed;
    int key8justpressed;
    int key9pressed;
    int key9justpressed;
    
    int keyctrldown;
    
    int keycpressed;
    int keycjustpressed;
    
    int keyctrlpressed;
    int keyctrljustpressed;
    public KeyboardInput(MainManager main, JFrame gameFrame) {
        this.main = main;
        gameFrame.addKeyListener(this);
    }
    public void keyTyped(KeyEvent e) {
        String kys = String.valueOf(e.getKeyChar());
        /*switch (kys.toLowerCase()) {
            case "a":
                keyadown = 1;
                break;
            case "w":
                keywdown = 1;
                break;
            case "d":
                keyddown = 1;
                break;
            case "s":
                keysdown = 1;
                break;
        }*/
    }
    public void keyPressed(KeyEvent e) {
        switch (KeyEvent.getKeyText(e.getKeyCode()).toLowerCase()) {
            case "a":
                keyadown = 1;
                break;
            case "w":
                keywdown = 1;
                break;
            case "d":
                keyddown = 1;
                break;
            case "s":
                keysdown = 1;
                break;
            case "c":
                keycpressed = 1;
                keycjustpressed = 1;
                break;
            case "q":
                keyqdown = 1;
                break;
        }
        switch (KeyEvent.getKeyText(e.getKeyCode())) {
            case "0":
                key0pressed = 1;
                key0justpressed = 1;
                key0down = 1;    
                break;
            case "1":
                key1pressed = 1;
                key1justpressed = 1;
                key1down = 1;    
                break;
            case "2":
                key2pressed = 1;
                key2justpressed = 1;
                key2down = 1;    
                break;
            case "3":
                key3pressed = 1;
                key3justpressed = 1;
                key3down = 1;    
                break;
            case "4":
                key4pressed = 1;
                key4justpressed = 1;
                key4down = 1;    
                break;
            case "5":
                key5pressed = 1;
                key5justpressed = 1;
                key5down = 1;    
                break;
            case "6":
                key6pressed = 1;
                key6justpressed = 1;
                key6down = 1;    
                break;
            case "7":
                key7pressed = 1;
                key7justpressed = 1;
                key7down = 1;    
                break;
            case "8":
                key8pressed = 1;
                key8justpressed = 1;
                key8down = 1;    
                break;
            case "9":
                key9pressed = 1;
                key9justpressed = 1;
                key9down = 1;    
                break;
        }
        if (key0down == 1) keynumbermasterdown = 0;
        if (key1down == 1) keynumbermasterdown = 1;
        if (key2down == 1) keynumbermasterdown = 2;
        if (key3down == 1) keynumbermasterdown = 3;
        if (key4down == 1) keynumbermasterdown = 4;
        if (key5down == 1) keynumbermasterdown = 5;
        if (key6down == 1) keynumbermasterdown = 6;
        if (key7down == 1) keynumbermasterdown = 7;
        if (key8down == 1) keynumbermasterdown = 8;
        if (key9down == 1) keynumbermasterdown = 9;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_CONTROL:
                keyctrldown = 1;
                keyctrlpressed = 1;
                keyctrljustpressed = 1;
                break;
            case KeyEvent.VK_ALT:
                keyaltdown = 1;
                break;
            case KeyEvent.VK_SHIFT:
                if (e.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT) keyleftshiftdown = 1;
                break;
        }
        //System.out.println(Integer.toString(keynumbermasterdown));
    }
    public void keyReleased(KeyEvent e) {
        //System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
        switch (KeyEvent.getKeyText(e.getKeyCode()).toLowerCase()) {
            case "a":
                keyadown = 0;
                break;
            case "w":
                keywdown = 0;
                break;
            case "d":
                keyddown = 0;
                break;
            case "s":
                keysdown = 0;
                break;
            case "q":
                keyqdown = 0;
                break;
        }
        switch (KeyEvent.getKeyText(e.getKeyCode())) {
            case "0":
                key0down = 0;    
                if (keynumbermasterdown == 0) keynumbermasterdown = -1;
                break;
            case "1":
                key1down = 0;    
                if (keynumbermasterdown == 1) keynumbermasterdown = -1;
                break;
            case "2":
                key2down = 0;    
                if (keynumbermasterdown == 2) keynumbermasterdown = -1;
                break;
            case "3":
                key3down = 0;    
                if (keynumbermasterdown == 3) keynumbermasterdown = -1;
                break;
            case "4":
                key4down = 0;    
                if (keynumbermasterdown == 4) keynumbermasterdown = -1;
                break;
            case "5":
                key5down = 0;    
                if (keynumbermasterdown == 5) keynumbermasterdown = -1;
                break;
            case "6":
                key6down = 0;    
                if (keynumbermasterdown == 6) keynumbermasterdown = -1;
                break;
            case "7":
                key7down = 0;    
                if (keynumbermasterdown == 7) keynumbermasterdown = -1;
                break;
            case "8":
                key8down = 0;    
                if (keynumbermasterdown == 8) keynumbermasterdown = -1;
                break;
            case "9":
                key9down = 0; 
                if (keynumbermasterdown == 9) keynumbermasterdown = -1;
                break;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_CONTROL:
                keyctrldown = 0;
                break;
            case KeyEvent.VK_ALT:
                keyaltdown = 0;
                break;
            case KeyEvent.VK_SHIFT:
                if (e.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT) keyleftshiftdown = 0;
                break;
        }
    }
}
