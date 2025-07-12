package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenheight = maxScreenRow * tileSize;

public GamePanel(){
    this.setPreferredSize(new Dimension(screenWidth,screenheight));
    this.setBackground(Color.BLACK);
    this.setDoubleBuffered(true);
}
    }

