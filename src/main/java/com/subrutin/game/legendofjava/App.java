package com.subrutin.game.legendofjava;

import javax.swing.JFrame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JFrame windows = new JFrame();
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setResizable(false);
        windows.setTitle("Legend of Java");

        GamePanel gamePanel = new GamePanel();
        windows.add(gamePanel);

        windows.pack();
        windows.setLocationRelativeTo(null);
        windows.setVisible(true);

        gamePanel.startGameThread();
    }
}
