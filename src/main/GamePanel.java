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

    // we can also use delta METHOD 
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


public void run(){
double drawInterval = (double) 1000000000 /FPS; //16,666,666.666.  1 second = 60 frames
/* 
So between each frame:
Frame 1 → wait 16.67 million nanoseconds → Frame 2                                              
Frame 2 → wait 16.67 million nanoseconds → Frame 3
Frame 3 → wait 16.67 million nanoseconds → Frame 4 ... Frame 60; 
Higher FPS = shorter wait time = smoother animation
Lower FPS = longer wait time = choppier animation
*/
double delta = 0;
long lastTime = System.nanoTime();
long currentTime;
long timer = 0;
long drawCount = 0;


while (gameThread != null) {
currentTime = System.nanoTime();
delta += (currentTime - lastTime) / drawInterval;
timer += (currentTime - lastTime);
lastTime = currentTime;
if (delta >= 1) {                                 // "Did 16.67 million nanoseconds pass?" 
  /*
  Think of it like cooking:
Recipe needs 30 minutes
Check: "Has 30 minutes passed?" = "Is time_passed/30_minutes >= 1?"
If 15 minutes passed: 15/30 = 0.5 → Not ready
If 30 minutes passed: 30/30 = 1.0 → Ready
The "1" means "one complete frame interval has elapsed!" 
  // 8ms passed (half the time needed)
delta = 8,000,000 / 16,666,667 = 0.48
if (delta >= 1) // FALSE - not ready yet

// 16.67ms passed (exactly the time needed)  
delta = 16,666,667 / 16,666,667 = 1.0
if (delta >= 1) // TRUE - exactly time for 1 frame!

// 33ms passed (double the time needed)
delta = 33,000,000 / 16,666,667 = 2.0  
if (delta >= 1) // TRUE - time for 2 frames!
  
  */          
                                            
     update();                                   // YES! Do the frame update
     repaint();                                // Show the frame  
     delta --;   
     drawCount ++;                            // Reset and start counting again
}
if (timer >= 1000000000){
    System.out.println("FPS" + drawCount);
    drawCount = 0;  // Reset counter!
    timer = 0;      // Reset timer!
}
}
} 
/* while(gameThread != null) {
Loop 1:

Time 0ms: update() runs → Game logic updates (maybe takes 2ms)
Time 2ms: repaint() runs → Screen draws (maybe takes 3ms)
Time 5ms: Thread.sleep(16) → Program STOPS and waits
Time 21ms: Sleep ends, back to loop start

Loop 2:

Time 21ms: update() runs → Game logic updates again
Time 23ms: repaint() runs → Screen draws again
Time 26ms: Thread.sleep(16) → Program STOPS again
Time 42ms: Sleep ends, back to loop start

Loop 3:

Time 42ms: Next cycle starts..
    update();
    repaint();
    try {
        Thread.sleep((long) 16);
    } catch (InterruptedException e) {
        e.printStackTrace();
    } // Sleep for 16ms
}
} */
    public void update() {
        if (keyH.upPressed) {
            playerY -= playerSpeed;
        }
        if (keyH.downPressed) {
            playerY += playerSpeed;
        }
        if (keyH.rightPressed) {
            playerX += playerSpeed;
        }

        if (keyH.leftPressed) {
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
