package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.keyH = kh;
    }

public void setDefaultValues(){

         x  = 100;
         y =  100;
         speed = 4;

}
public  void update(){

    if (keyH.upPressed) {
        y -= speed;
    }
    if (keyH.downPressed) {
        y += speed;
    }
    if (keyH.rightPressed) {
        x += speed;
    }

    if (keyH.leftPressed) {
        x -= speed;
    }

}
public  void Draw(Graphics2D g2){

    g2.setColor(Color.white);
    g2.fillRect(x, y, gp.tileSize, gp.tileSize);
}

}
