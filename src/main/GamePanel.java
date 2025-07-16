package main;

import javax.swing.*;
import java.awt.*;

public  class GamePanel extends JPanel implements  Runnable{
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenheight = maxScreenRow * tileSize;
    Thread  gameThread;


public   GamePanel(){
    this.setPreferredSize(new Dimension(screenWidth,screenheight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
}

public  void startGameThread(){
    gameThread = new Thread(this);
    gameThread.start();
}
    @Override
    public void run() {
        while (gameThread != null) {
            //update character postion
            //draw the screan with updated postion

        }
    }
public void update(){


    }
public void paintComponent(Graphics g){


    }
}

