package com.subrutin.game.legendofjava.tile;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import com.subrutin.game.legendofjava.GamePanel;

public class TileManager {

    GamePanel gp;
    Tile[] tiles;
    
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[10];
        loadTiles();
    }
    
    public void loadTiles() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/002.png"));            
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
            g2.drawImage(tiles[0].image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
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
