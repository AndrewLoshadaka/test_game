package com.andrew;
import com.andrew.entity.Monster;
import com.andrew.entity.Player;
import com.andrew.service.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final int playerX = 50;
    private final int playerY = 110;
    private final int monsterX = 250;
    private final int monsterY = 100;
    private final int playerSpeed = 5;
    private int playerBulletX, playerBulletY;
    private int monsterBulletX, monsterBulletY;
    private int countRound = 0;
    private int countHealPlayer = 0;
    private Timer timer;
    private boolean playerBulletVisible = false;
    private boolean monsterBulletVisible = false;
    private final Player player;
    private final Monster monster;

    private boolean isAttackInProgress = false;




    public GamePanel(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
        setFocusable(true);
        addKeyListener(this);
        setBackground(Color.black);
        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlayers(g);
        drawBullets(g);
    }

    private void drawPlayers(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, 50, 50);
        g.setColor(Color.blue);
        g.fillRect(monsterX, monsterY, 70, 70);

        g.setColor(Color.GREEN);
        int playerHealthBarWidth = (int) ((double) player.getHealth() / player.getMaxHealth() * 50);
        g.fillRect(playerX, playerY - 10, playerHealthBarWidth, 5);

        g.setColor(Color.RED);
        int monsterHealthBarWidth = (int) ((double) monster.getHealth() / monster.getMaxHealth() * 70);
        g.fillRect(monsterX, monsterY - 10, monsterHealthBarWidth, 5);
    }

    private void drawBullets(Graphics g) {
        if(countRound % 2 != 0){
            g.setColor(Color.yellow);
            g.fillRect(playerBulletX, playerBulletY, 10, 10);
        }

        else{
            g.setColor(Color.yellow);
            g.fillRect(monsterBulletX, monsterBulletY, 10, 10);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveBullets();
        checkCollisions();
        repaint();

        if (isGameOver()) {
            showGameOverDialog();
        }
    }

    private void moveBullets() {
        if (playerBulletVisible) {
            playerBulletX += playerSpeed;
            if (playerBulletX > getWidth()) {
                playerBulletVisible = false;
            }
        }
        if (monsterBulletVisible) {
            monsterBulletX -= playerSpeed;
            if (monsterBulletX < 0) {
                monsterBulletVisible = false;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE && !isAttackInProgress) {
            playerBulletX = playerX + 50;
            playerBulletY = playerY + 20;
            monsterBulletX = monsterX - 10;
            monsterBulletY = monsterY + 20;
            playerBulletVisible = true;
            monsterBulletVisible = true;
            countRound++;
            isAttackInProgress = true;
        }
    }

    private void checkCollisions() {
        if (playerBulletVisible && playerBulletX < monsterX + 70 &&
                playerBulletX + 10 > monsterX &&
                playerBulletY < monsterY + 70 &&
                playerBulletY + 10 > monsterY && countRound % 2 != 0) {

            player.attack(monster);
            playerBulletX = -10;
            playerBulletVisible = false;
            isAttackInProgress = false;
        }

        if (monsterBulletVisible && monsterBulletX < playerX + 50 &&
                monsterBulletX + 10 > playerX &&
                monsterBulletY < playerY + 50 &&
                monsterBulletY + 10 > playerY && countRound % 2 == 0) {

            monster.attack(player);
            if(countHealPlayer < 4){
                player.heal();
                countHealPlayer++;
            }
            monsterBulletX = -10;
            monsterBulletVisible = false;
            isAttackInProgress = false;
        }
    }

    private boolean isGameOver() {
        return player.getHealth() <= 0 || monster.getHealth() <= 0;
    }

    private void showGameOverDialog() {
        String message = "";

        if (player.getHealth() <= 0) {
            message = "Монстр выиграл!";
        } if(monster.getHealth() <= 0){
            message = "Игрок выиграл!";
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                message + " Начать новую игру?",
                "Игра окончена",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            player.setHealth(10000);
            monster.setHealth(10000);
            Window window = SwingUtilities.getWindowAncestor(this);
            window.dispose();
            Game game = new Game();
            game.setVisible(true);
        } else {
            System.exit(0);
        }
    }



    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
