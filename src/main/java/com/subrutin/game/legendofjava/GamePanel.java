package com.subrutin.game.legendofjava;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import java.awt.Color;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
    // screen settings
    final int ORIGINAL_TILE_SIZE = 16; // 16x16 tile
    final int SCALE = 3;
    final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48x48 tile
    final int MAX_SCREEN_COL = 16;
    final int MAX_SCREEN_ROW = 12;
    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;// 768 pixels
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;// 576 pixels

    KeyHandler keyHandler;
    Thread gameThread;

    // set players default position
    int playerX = 100;
    int playerY = 100;

    final int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new DimensionUIResource(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(java.awt.Color.black);
        this.setDoubleBuffered(true);
        keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // set fps in 60 frames per second
        double drawInterval = 1000000000 / FPS; // 0.01666 secnds
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
                System.out.println("Draw Count: " + drawCount);
                timer = 0;
                drawCount = 0;
            }
        }
    }

    public void update() {
        if (keyHandler.upPressed) {
            playerY -= 1;
        } else if (keyHandler.downPressed) {
            playerY += 1;
        } else if (keyHandler.leftPressed) {
            playerX -= 1;
        } else if (keyHandler.rightPressed) {
            playerX += 1;
        }
    }

    public void paintComponent(java.awt.Graphics g) {
        // every change you need call this method to update the screen
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, TILE_SIZE, TILE_SIZE);
        g2.dispose();
    }
}
