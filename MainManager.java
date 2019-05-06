import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
public class MainManager extends JPanel
{
    ArrayList <Object> Objects = new ArrayList<Object>();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int Windowxsize = screenSize.width;
    final int expectedwindowx = 1920;
    //1920
    //1600
    final int Windowysize = screenSize.height;
    final int expectedwindowy = 1080;
    //1080
    //1072
    
    double Windowmultiplyer = (double)Windowxsize / (double)expectedwindowx;
    double Windowmultiplyery = (double)Windowysize / (double)expectedwindowy;
    int Screenx = Object.staticresizeint(15, Windowmultiplyer);
    int Screeny = Object.staticresizeint(5, Windowmultiplyer);
    int Screenxlength = Object.staticresizeint(900, Windowmultiplyer);
    int Screenylength = Object.staticresizeint(900, Windowmultiplyer);
    int Screenxend, Screenyend;
    int Mapx = 0;
    int Mapy = 0;
    int Mapxsize = 3000;
    int Mapysize = 3000;
    float alpha;
    KeyboardInput Keyboard;
    MouseInput Mouse;
    obj_SideBarControl Sidebar;
    Camera Camera;
    int Sidebarx = Object.staticresizeint(1600, Windowmultiplyer);
    ArrayList <obj_Light> LightObjects = new ArrayList<obj_Light>();
    ArrayList <Ship> ShipObjects = new ArrayList<Ship>();
    Team[] TeamArray;
    ShipController ShipControllerReference;
    public MainManager(JFrame gameFrame) {
        CreateWindow(gameFrame);
        Keyboard = new KeyboardInput(this, gameFrame);
        Mouse = new MouseInput(this, gameFrame);
        
        Screenxend = Screenx + Screenxlength;
        Screenyend = Screeny + Screenylength;
        
        
        
        Gamecreatestep();
    }
    private void CreateWindow(JFrame gameFrame) {
        gameFrame.setSize(Windowxsize, Windowysize);
        gameFrame.setUndecorated(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(this);
        gameFrame.setVisible(true);
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }
    public void Gamecreatestep() {
        createTeams();
        
        ShipControllerReference = new ShipController(this, 0, 0, Keyboard, Mouse, TeamArray[0]);
        Sidebar = new obj_SideBarControl(this, -1, -1, Keyboard, Mouse);
        Camera = new Camera(this, Keyboard, Mouse, obj_SideBarControl.class.cast(Sidebar));
        new Ship(this, 1500, 1000, Keyboard, Mouse, TeamArray[0], ShipControllerReference, 15000);
        new Ship(this, 1500, 800, Keyboard, Mouse, TeamArray[0], ShipControllerReference, 15000);
        new Ship(this, 1500, 600, Keyboard, Mouse, TeamArray[0], ShipControllerReference, 15000);
        
        new Ship(this, 1800, 1000, Keyboard, Mouse, TeamArray[1], null, 15000);
        
    }
    public void startGame() {
        
    }
    public void Managerstep() {
        
    }
    public void step() {
        Managersetupstep();
        for (int i = 0; i <= Objects.size() - 1; i++) (Objects.get(i)).setupstep();
        for (int i = 0; i <= Objects.size() - 1; i++) (Objects.get(i)).step();
        Camera.CameraStep();
        Managerstep();
    }
    public void Managersetupstep() {
        if (Mouse.MouseClickLeft == 1) {
            if (Mouse.JustClickedLeft == 0) {
                Mouse.MouseClickLeft = 0;
            }
            if (Mouse.JustClickedLeft == 1) {
                Mouse.JustClickedLeft = 0;
            }
        }
        if (Mouse.MouseClickRight == 1) {
            if (Mouse.JustClickedRight == 0) {
                Mouse.MouseClickRight = 0;
            }
            if (Mouse.JustClickedRight == 1) {
                Mouse.JustClickedRight = 0;
            }
        }
        if (Keyboard.keycpressed == 1) {
            if (Keyboard.keycjustpressed == 0) {
                Keyboard.keycpressed = 0;
            }
            if (Keyboard.keycjustpressed == 1) {
                Keyboard.keycjustpressed = 0;
            }
        }
        if (Keyboard.keyctrlpressed == 1) {
            if (Keyboard.keyctrljustpressed == 0) {
                Keyboard.keyctrlpressed = 0;
            }
            if (Keyboard.keyctrljustpressed == 1) {
                Keyboard.keyctrljustpressed = 0;
            }
        }
        if (Keyboard.key0pressed == 1) {
            if (Keyboard.key0justpressed == 0) {
                Keyboard.key0pressed = 0;
            }
            if (Keyboard.key0justpressed == 1) {
                Keyboard.key0justpressed = 0;
            }
        }
        if (Keyboard.key1pressed == 1) {
            if (Keyboard.key1justpressed == 0) {
                Keyboard.key1pressed = 0;
            }
            if (Keyboard.key1justpressed == 1) {
                Keyboard.key1justpressed = 0;
            }
        }
        if (Keyboard.key2pressed == 1) {
            if (Keyboard.key2justpressed == 0) {
                Keyboard.key2pressed = 0;
            }
            if (Keyboard.key2justpressed == 1) {
                Keyboard.key2justpressed = 0;
            }
        }
        if (Keyboard.key3pressed == 1) {
            if (Keyboard.key3justpressed == 0) {
                Keyboard.key3pressed = 0;
            }
            if (Keyboard.key3justpressed == 1) {
                Keyboard.key3justpressed = 0;
            }
        }
        if (Keyboard.key4pressed == 1) {
            if (Keyboard.key4justpressed == 0) {
                Keyboard.key4pressed = 0;
            }
            if (Keyboard.key4justpressed == 1) {
                Keyboard.key4justpressed = 0;
            }
        }
        if (Keyboard.key5pressed == 1) {
            if (Keyboard.key5justpressed == 0) {
                Keyboard.key5pressed = 0;
            }
            if (Keyboard.key5justpressed == 1) {
                Keyboard.key5justpressed = 0;
            }
        }
        if (Keyboard.key6pressed == 1) {
            if (Keyboard.key6justpressed == 0) {
                Keyboard.key6pressed = 0;
            }
            if (Keyboard.key6justpressed == 1) {
                Keyboard.key6justpressed = 0;
            }
        }
        if (Keyboard.key7pressed == 1) {
            if (Keyboard.key7justpressed == 0) {
                Keyboard.key7pressed = 0;
            }
            if (Keyboard.key7justpressed == 1) {
                Keyboard.key7justpressed = 0;
            }
        }
        if (Keyboard.key8pressed == 1) {
            if (Keyboard.key8justpressed == 0) {
                Keyboard.key8pressed = 0;
            }
            if (Keyboard.key8justpressed == 1) {
                Keyboard.key8justpressed = 0;
            }
        }
        if (Keyboard.key9pressed == 1) {
            if (Keyboard.key9justpressed == 0) {
                Keyboard.key9pressed = 0;
            }
            if (Keyboard.key9justpressed == 1) {
                Keyboard.key9justpressed = 0;
            }
        }
        if (Keyboard.key0pressed == 1) Keyboard.keynumbermasterpressed = 0;
        if (Keyboard.key1pressed == 1) Keyboard.keynumbermasterpressed = 1;
        if (Keyboard.key2pressed == 1) Keyboard.keynumbermasterpressed = 2;
        if (Keyboard.key3pressed == 1) Keyboard.keynumbermasterpressed = 3;
        if (Keyboard.key4pressed == 1) Keyboard.keynumbermasterpressed = 4;
        if (Keyboard.key5pressed == 1) Keyboard.keynumbermasterpressed = 5;
        if (Keyboard.key6pressed == 1) Keyboard.keynumbermasterpressed = 6;
        if (Keyboard.key7pressed == 1) Keyboard.keynumbermasterpressed = 7;
        if (Keyboard.key8pressed == 1) Keyboard.keynumbermasterpressed = 8;
        if (Keyboard.key9pressed == 1) Keyboard.keynumbermasterpressed = 9;
        
        if ((Keyboard.key0pressed == 0) && (Keyboard.keynumbermasterpressed == 0)) Keyboard.keynumbermasterpressed = -1;
        if ((Keyboard.key1pressed == 0) && (Keyboard.keynumbermasterpressed == 1)) Keyboard.keynumbermasterpressed = -1;
        if ((Keyboard.key2pressed == 0) && (Keyboard.keynumbermasterpressed == 2)) Keyboard.keynumbermasterpressed = -1;
        if ((Keyboard.key3pressed == 0) && (Keyboard.keynumbermasterpressed == 3)) Keyboard.keynumbermasterpressed = -1;
        if ((Keyboard.key4pressed == 0) && (Keyboard.keynumbermasterpressed == 4)) Keyboard.keynumbermasterpressed = -1;
        if ((Keyboard.key5pressed == 0) && (Keyboard.keynumbermasterpressed == 5)) Keyboard.keynumbermasterpressed = -1;
        if ((Keyboard.key6pressed == 0) && (Keyboard.keynumbermasterpressed == 6)) Keyboard.keynumbermasterpressed = -1;
        if ((Keyboard.key7pressed == 0) && (Keyboard.keynumbermasterpressed == 7)) Keyboard.keynumbermasterpressed = -1;
        if ((Keyboard.key8pressed == 0) && (Keyboard.keynumbermasterpressed == 8)) Keyboard.keynumbermasterpressed = -1;
        if ((Keyboard.key9pressed == 0) && (Keyboard.keynumbermasterpressed == 9)) Keyboard.keynumbermasterpressed = -1;
        //if (Keyboard.keyctrlpressed == 1) System.out.println("1");
        //if (Keyboard.keyctrldown == 1) System.out.println("down");
    }
    public void drawObjects(Graphics g, Graphics2D g2) {
        for (int i = 0; i <= Objects.size() - 1; i++){
            if ((Objects.get(i).hidden == 0) && (Objects.get(i).camerahidden == 0)) (Objects.get(i)).drawstep(g, g2);
        }
        Drawsetup(g, g2);
    }
    public void gameLoop()
    {
        while (true) {
            
            repaint();
            step();
            //See if mouse is inside the screen
            //System.out.println(Object.isbetween(Mouse.Mousex, Mouse.Mousey, Screenx, Screeny, Screenxlength, Screenylength));
            try 
            {
                Thread.sleep(17);
                //Thread.sleep(1000/30);
            }
            catch (Exception e) 
            {
                
            }
        }
    }
    
    public void paintComponent(Graphics g) {
        //whats being painted
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Windowxsize, Windowysize);
        
        drawObjects(g, g2);
        //Rectangle
        g.setColor(Color.PINK);
        g.drawRect(Screenx, Screeny, Screenxlength, Screenylength);
        //mini lines
        setAlpha(0.25f, g2);
        g.setColor(Color.PINK);
        
        for (int i = Screenx; i <= Screenxend; i += Object.staticresize(20, Windowmultiplyer)) {
            g.drawLine(i, Screeny, i, Screenyend);
        }
        for (int i = Screeny; i <= Screenyend; i += Object.staticresize(20, Windowmultiplyer)) {
            g.drawLine(Screenx, i, Screenxend, i);
        }
        setAlpha(1, g2);
        g.setColor(Color.PINK);
        //Bigger map lines
        for (int i = 0; i <= 3000; i += 300) {
            if ((Mapx <= i) && (i <= Mapx + 900)) g.drawLine(Object.staticresizeint(i - Mapx + Screenx, Windowmultiplyer), Screeny, Object.staticresizeint(i - Mapx + Screenx, Windowmultiplyer), Screenyend);
        }
        for (int i = 0; i <= 3000; i += 300) {
            if ((Mapy <= i) && (i <= Mapy + 900)) g.drawLine(Screenx, Object.staticresizeint(i - Mapy + Screeny, Windowmultiplyer), Screenxend, Object.staticresizeint(i - Mapy + Screeny, Windowmultiplyer));
        }
        
    }
    void Drawsetup(Graphics g, Graphics2D g2) {
        setAlpha(1.0f, g2);
    }
    void setAlpha(float alphavalue, Graphics2D g2) {
        alpha = alphavalue;
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
        g2.setComposite(ac);
    }
    public void drawtriangle(int x1, int y1, int x2, int y2, Graphics g) {
        int bazinga = x1 + ((x2 - x1) / 2);
        g.drawLine(x1, y1, x2, y1);
        g.drawLine(x1, y1, bazinga, y2);
        g.drawLine(bazinga, y2, x2, y1);
    }
    public void createTeams() {
        TeamArray = new Team[4];
        for (int i = 1; i <= 4; i++) {
            Team Currentteam = new Team(this);
            Currentteam.TeamNumber = i;
            TeamArray[i - 1] = Currentteam;
        }
    }
    Image scaleImage(double xsize, double ysize, String spritepath) {
       try {
           BufferedImage bimg = ImageIO.read(new File (spritepath));
           int width = bimg.getWidth();
           int height = bimg.getHeight();
           xsize = xsize * Windowmultiplyer;
           ysize = ysize * Windowmultiplyer;
           Image Imagefile = Toolkit.getDefaultToolkit().getImage(spritepath);
           Image newImage = bimg.getScaledInstance((int)((double)width * xsize), (int)((double)height * ysize), Image.SCALE_DEFAULT);
           return newImage;
       } catch (Exception e) {
            
       }
       return Toolkit.getDefaultToolkit().getImage(spritepath);
    }
    public static void main() { 
        System.out.print('\u000C');
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame gameFrame = new JFrame("Game Name");
                final MainManager gamePanel = new MainManager(gameFrame);
                Thread loop = new Thread() {
                    public void run() {
                        gamePanel.gameLoop();
                    }
                };
                loop.start();
            }
        });
        
    }
}
