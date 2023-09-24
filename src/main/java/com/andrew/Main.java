package com.andrew;

import com.andrew.service.Game;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            game.setVisible(true);
        });
    }
}
