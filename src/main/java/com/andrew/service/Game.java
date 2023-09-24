package com.andrew.service;

import com.andrew.GamePanel;
import com.andrew.entity.Monster;
import com.andrew.entity.Player;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Game extends JFrame{

    private final int WIDTH = 400;
    private final int HEIGHT = 300;
    private JTextField playerAttackField;
    private JTextField playerDefenseField;
    private JTextField playerHealthField;
    private JTextField playerMinDamage;
    private JTextField playerMaxDamage;

    private JTextField monsterAttackField;
    private JTextField monsterDefenseField;
    private JTextField monsterHealthField;
    private JTextField monsterMinDamage;
    private JTextField monsterMaxDamage;

    private Player player;
    private Monster monster;

    public Game() {
        player = null;
        monster = null;
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        Label labelPlayer = new Label("PLAYER");
        labelPlayer.setBounds(140, 30, 90, 20);
        Label labelMonster = new Label("MONSTER");
        labelMonster.setBounds(265, 30, 90, 20);

        Label[] labels = new Label[5];
        labels[0] = new Label("Attack");
        labels[1] = new Label("Defences");
        labels[2] = new Label("Health");
        labels[3] = new Label("Min Damage");
        labels[4] = new Label("Max Damage");

        playerAttackField = new JTextField();
        playerAttackField.setBounds(140, 50, 90, 25);
        playerDefenseField = new JTextField();
        playerDefenseField.setBounds(140, 80, 90, 25);
        playerHealthField = new JTextField();
        playerHealthField.setBounds(140, 110, 90, 25);
        playerMinDamage = new JTextField();
        playerMinDamage.setBounds(140, 140, 90, 25);
        playerMaxDamage = new JTextField();
        playerMaxDamage.setBounds(140, 170, 90, 25);


        monsterAttackField = new JTextField();
        monsterAttackField.setBounds(265, 50, 90, 25);
        monsterDefenseField = new JTextField();
        monsterDefenseField.setBounds(265, 80, 90, 25);
        monsterHealthField = new JTextField();
        monsterHealthField.setBounds(265, 110, 90, 25);
        monsterMinDamage = new JTextField();
        monsterMinDamage.setBounds(265, 140, 90, 25);
        monsterMaxDamage = new JTextField();
        monsterMaxDamage.setBounds(265, 170, 90, 25);

        for(int i = 0; i < 5; i++){
            labels[i].setBounds(20, 50 + i * 30, 90, 25);
            inputPanel.add(labels[i]);
        }

        inputPanel.add(labelPlayer);
        inputPanel.add(labelMonster);
        inputPanel.add(playerAttackField);
        inputPanel.add(playerDefenseField);
        inputPanel.add(playerHealthField);
        inputPanel.add(playerMinDamage);
        inputPanel.add(playerMaxDamage);


        inputPanel.add(monsterAttackField);
        inputPanel.add(monsterDefenseField);
        inputPanel.add(monsterHealthField);
        inputPanel.add(monsterMinDamage);
        inputPanel.add(monsterMaxDamage);


        JButton startButton = getjButton(inputPanel);

        inputPanel.add(startButton);

        add(inputPanel);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int windowWidth = getWidth();
        int windowHeight = getHeight();

        int x = (screenWidth - windowWidth) / 2;
        int y = (screenHeight - windowHeight) / 2;

        setLocation(x, y);
    }

    public JButton getjButton(JPanel inputPanel) {
        JButton startButton = new JButton("Start Game");
        startButton.setBackground(Color.GREEN);
        startButton.setBounds(90, 200, 200, 40);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isNumber(playerAttackField.getText()) && isNumber(playerDefenseField.getText()) && isNumber(playerHealthField.getText())
                        && isNumber(playerMinDamage.getText()) && isNumber(playerMaxDamage.getText())
                        && isNumber(monsterAttackField.getText()) && isNumber(monsterDefenseField.getText()) && isNumber(monsterHealthField.getText())
                        && isNumber(monsterMinDamage.getText()) && isNumber(monsterMaxDamage.getText())){

                    if(isGoodAttackAndDefences(playerAttackField.getText()) && isGoodAttackAndDefences(playerDefenseField.getText()) &&
                            isGoodAttackAndDefences(monsterAttackField.getText()) && isGoodAttackAndDefences(monsterDefenseField.getText())){

                        if(Integer.parseInt(playerHealthField.getText()) > 0 && Integer.parseInt(monsterHealthField.getText()) > 0){
                            if(Integer.parseInt(playerMinDamage.getText()) < Integer.parseInt(playerMaxDamage.getText()) &&
                                    Integer.parseInt(monsterMinDamage.getText()) < Integer.parseInt(monsterMaxDamage.getText())) {
                                int playerAttack = Integer.parseInt(playerAttackField.getText());
                                int playerDefense = Integer.parseInt(playerDefenseField.getText());
                                int playerHealth = Integer.parseInt(playerHealthField.getText());
                                int playerMinD = Integer.parseInt(playerMinDamage.getText());
                                int playerMaxD = Integer.parseInt(playerMaxDamage.getText());
                                player = new Player(playerAttack, playerDefense, playerHealth, playerMinD, playerMaxD);

                                int monsterAttack = Integer.parseInt(monsterAttackField.getText());
                                int monsterDefense = Integer.parseInt(monsterDefenseField.getText());
                                int monsterHealth = Integer.parseInt(monsterHealthField.getText());
                                int monsterMin = Integer.parseInt(monsterMinDamage.getText());
                                int monsterMax = Integer.parseInt(monsterMaxDamage.getText());
                                monster = new Monster(monsterAttack, monsterDefense, monsterHealth, monsterMin, monsterMax);


                                setVisible(true);
                                GamePanel gamePanel = new GamePanel(player, monster);
                                add(gamePanel);
                                inputPanel.setVisible(false);
                            } else {
                                JOptionPane.showMessageDialog(null, "Min Damage должен быть меньше Max Damage!", "Ошибка", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Health может быть строго больше 0!", "Ошибка", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Attack и Defence может быть строго от 1 до 30!", "Ошибка", JOptionPane.WARNING_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Где-то введено не число!", "Ошибка", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        return startButton;
    }


    private boolean isNumber(String text){
        try {
            int number = Integer.parseInt(text);
            return true;
        } catch (NumberFormatException ignored){}

        return false;
    }

    private boolean isGoodAttackAndDefences(String number){
        return Integer.parseInt(number) >= 1 && Integer.parseInt(number) <= 30;
    }
}
