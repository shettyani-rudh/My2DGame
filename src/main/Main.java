package main;
import javax.swing.*;
//import entity.Player;
    public class Main {
        public static void main(String[] args) {
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("2d Adventure");
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            GamePanel gamePanel = new GamePanel();
            //Player player = new Player(gamePanel,gamePanel.getKeyHandler());
            window.add(gamePanel);
            window.pack();
            gamePanel.startGameThread();
     

        }
    }

