package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // width and height of tile
    final int maxScreenCol = 16; // number of col tile
    final int maxScreenRow = 12;// number of row tile
    final int screenWidth = maxScreenCol * tileSize;
    final int screenheight = maxScreenRow * tileSize;

    int FPS = 60;
    Thread gameThread;
    // game player position

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    KeyHandler keyH = new KeyHandler();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenheight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.requestFocusInWindow();
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    // we can also use delta METHOD iam lazy
    /* public void run() {
        long timer = 0;
        int drawCount = 0;
        long lastTime = System.nanoTime();
        long curentTime;
        double drawInterval = 1000000000 / FPS; // 20 // drawInterval = nanoseconds per frame // 16,666,666.66
                                                // nanoseconds per frame
                                                // 0.016666666 second perframe is delay
        double nextDrawTime = System.nanoTime() + drawInterval; // nextDrawTime = 100 + 20 2nd frame 140 + 20
        while (gameThread != null) {
            curentTime = System.nanoTime();
            timer = timer + (curentTime - lastTime);
            lastTime = curentTime;
            update(); // 5 unit time consumtion so 100 + 5 = 105
            repaint();
            drawCount++;
            if (timer > 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }

            try { // here delay happen
                double remainingTime = nextDrawTime - System.nanoTime(); // 120 - 105 = 15
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime); // 105 current time sleep for 15 unit 105 + 15 = 120
                nextDrawTime += drawInterval; // otherwise it overlaps //120 + 20 = 140

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    } */
public void run() {
    double drawInterval = 1000000000 / FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    int drawCount = 0;
    
    while (gameThread != null) {
        currentTime = System.nanoTime();
        delta += (currentTime - lastTime) / drawInterval;
        timer += (currentTime - lastTime);
        lastTime = currentTime;
        
        if (delta >= 1) {
            update();
            repaint();
            delta--;
            drawCount++;
        }
        
        if (timer >= 1000000000) {
            System.out.println("FPS:" + drawCount);
            drawCount = 0;
            timer = 0;
        }
        
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

    public void update() {

        if (keyH.upPressed == true) {
            playerY -= playerSpeed;
        }
        if (keyH.downPressed == true) {
            playerY += playerSpeed;
        }
        if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }

        if (keyH.leftPressed == true) {
            playerX -= playerSpeed;
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
