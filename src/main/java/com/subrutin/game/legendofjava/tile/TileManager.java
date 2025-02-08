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
        mapTileNum = new int[gp.MAX_SCREEN_COL][gp.MAX_SCREEN_ROW];
        loadTiles();
        buildMap();
    }
    
    public void loadTiles() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/002.png"));
            tiles[0].collision = true;
            
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/032.png"));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/004.png"));
            tiles[2].collision = true;
            
                        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildMap() {
        //load from src/main/resources/map/map01.text
        String line = "";
        try {
            InputStream is = getClass().getResourceAsStream("/map/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {
                line = br.readLine();
                String[] tokens = line.split(" ");
                for (int i = 0; i < tokens.length; i++) {
                    mapTileNum[col][row] = Integer.parseInt(tokens[i]);
                    col++;
                }
                if(col == gp.MAX_SCREEN_COL) {
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
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tiles[tileNum].image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);

            col++;
            x += gp.TILE_SIZE;
            if (col == gp.MAX_SCREEN_COL) {
                col = 0;
                x = 0;
                row++;
                y += gp.TILE_SIZE;
            }
        }
    }
}
