 package com.subrutin.game.legendofjava.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.subrutin.game.legendofjava.GamePanel;
import com.subrutin.game.legendofjava.KeyHandler;

public class Player extends BaseEntity {

    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    public final int SCREEN_X;
    public final int SCREEN_Y;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
        direction ="down";
        SCREEN_X  = gamePanel.screenWidth / 2 - gamePanel.TILE_SIZE / 2;
        SCREEN_Y = gamePanel.screenHeight / 2 - gamePanel.TILE_SIZE / 2;
    }

    public void setDefaultValues(){
        worldX = gamePanel.TILE_SIZE * 23;
        worldY = gamePanel.TILE_SIZE * 21;
        speed = 4;
    }

    public void getPlayerImage(){
        //up1 get with imageio to src/main/resources/zelda-like/up_1.png
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/zelda-like/up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/zelda-like/up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/zelda-like/down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/zelda-like/down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/zelda-like/left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/zelda-like/left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/zelda-like/right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/zelda-like/right_2.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void update(){
        //if dont press WSDA then dont update
        if (!keyHandler.upPressed && !keyHandler.downPressed && !keyHandler.leftPressed && !keyHandler.rightPressed) {
            return;
        }
        if (keyHandler.upPressed) {
            direction = "up";
            worldY -= speed;
        } else if (keyHandler.downPressed) {
            direction = "down";
            worldY += speed;
        } else if (keyHandler.leftPressed) {
            direction = "left";
            worldX -= speed;
        } else if (keyHandler.rightPressed) {
            direction = "right";
            worldX += speed;
        }
        spriteCounter++;
        if (spriteCounter > 10) {
            if(spriteNum == 1) {
                spriteNum = 2;
            } else {
                spriteNum = 1;
            }
            spriteCounter = 0;
        } 
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        //make the sprite walking, by change the image by switch case
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, SCREEN_X, SCREEN_Y, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
    }
}
