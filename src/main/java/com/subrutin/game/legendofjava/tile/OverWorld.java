package com.subrutin.game.legendofjava.tile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OverWorld extends Tile {
    private final int tileRow;
    private final int tileColumn;
    private int tileWidth = 16;
    private int tileHeight = 16;

    public OverWorld(int tileRow, int tileColumn, int tileWidth, int tileHeight) {
        this.tileRow = tileRow;
        this.tileColumn = tileColumn;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        loadTile();
    }
    
    
    public OverWorld(int tileRow, int tileColumn) {
        this.tileRow = tileRow;
        this.tileColumn = tileColumn;
        loadTile();
    }
    
    private void loadTile() {
        try {
            int x = tileColumn * tileWidth;
            int y = tileRow * tileHeight;
            
            image = ImageIO.read(getClass().getResourceAsStream("/tiles-sheet/Overworld.png"))
                    .getSubimage(x, y, tileWidth, tileHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
