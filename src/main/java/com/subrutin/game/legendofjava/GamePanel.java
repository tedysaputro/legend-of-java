package com.subrutin.game.legendofjava;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import com.subrutin.game.legendofjava.entity.Player;
import com.subrutin.game.legendofjava.tile.TileManager;

import java.awt.Color;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
    // screen settings
    public final int ORIGINAL_TILE_SIZE = 16; // 16x16 tile
    final int SCALE = 3;
    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48x48 tile
    public final int MAX_SCREEN_COL = 16;
    public final int MAX_SCREEN_ROW = 12;
    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;// 768 pixels
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;// 576 pixels

    KeyHandler keyHandler;
    Thread gameThread;
    Player player;
    TileManager tileManager;

    // set players default position
    int playerX = 100;
    int playerY = 100;

    final int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new DimensionUIResource(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(java.awt.Color.WHITE);
        this.setDoubleBuffered(true);
        keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        player = new Player(this, keyHandler);
        tileManager = new TileManager(this);
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
        player.update();
    }

    public void paintComponent(java.awt.Graphics g) {
        // every change you need call this method to update the screen
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
