package com.subrutin.game.legendofjava.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.subrutin.game.legendofjava.GamePanel;

public class TileManager {

    GamePanel gp;
    Tile[] tiles;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadTiles();
        buildMap();
    }
    
    public void loadTiles() {
        try {
            // Example: Loading the first tile (0,0) from Overworld.png
            // tiles[0] = new OverWorld(0, 0);
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/002.png"));
            tiles[0].collision = true;

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/032.png"));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/019.png"));
            tiles[2].collision = true;
            
            //tree
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/016.png"));
            tiles[3].collision = true;
            
            //sand
            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/017.png"));
            tiles[4].collision = true;  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildMap() {
        //load from src/main/resources/map/map01.text
        String line = "";
        try {
            InputStream is = getClass().getResourceAsStream("/map/world01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                line = br.readLine();
                String[] tokens = line.split(" ");
                for (int i = 0; i < tokens.length; i++) {
                    mapTileNum[col][row] = Integer.parseInt(tokens[i]);
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                } 

            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.TILE_SIZE;
            int worldY = worldRow * gp.TILE_SIZE;
            int screenX = worldX - gp.player.worldX + gp.player.SCREEN_X;   
            int screenY = worldY - gp.player.worldY + gp.player.SCREEN_Y;
            if(worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.SCREEN_X
            && worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.SCREEN_X
            && worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.SCREEN_Y
            && worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.SCREEN_Y) {
                g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
            }
            
            worldCol++;
            // x += gp.TILE_SIZE;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                // x = 0;
                worldRow++;
                // y += gp.TILE_SIZE;
            }
        }
    }
}
