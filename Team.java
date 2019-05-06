import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
public class Team
{
    int TeamNumber;
    MainManager main;
    /**
     * 1 - red
     * 2 - yellow
     * 3 - blue
     * 4 - white
     */
    ArrayList <Ship> TeamShips = new ArrayList<Ship>();
    public Team(MainManager main) {
        this.main = main;
    }
    
}