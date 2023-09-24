package com.andrew.service;

import com.andrew.entity.Monster;
import com.andrew.entity.Player;

import java.util.Scanner;

public class GameService {
    public void start() {
        cleanScreen();
        Player player = new Player(20, 15, 100, 5, 15);
        Monster monster = new Monster(18, 12, 80, 8, 20);
        int step = 0;
        int countHealPlayer = 0;

        while (player.getHealth() >= 0 || monster.getHealth() >= 0) {
            Scanner scanner = new Scanner(System.in);
            if (player.getHealth() == 0) {
                winMonster();
                return;
            }
            if (monster.getHealth() == 0) {
                winPlayer();
                return;
            }
            System.out.print("Enter command (1): ");
            int command = scanner.nextInt();
            if (command == 1) {

                if (step % 2 == 0) {
                    System.out.println("Player attacks Monster!");
                    player.attack(monster);

                    if(countHealPlayer < 4){
                        player.heal();
                        countHealPlayer++;
                    }
                } else {
                    System.out.println("Monster attacks Player!");
                    monster.attack(player);
                }

                cleanScreen();
                getPicture();
            }
            System.out.println("Player health: " + player.getHealth() + "           Monster health: " + monster.getHealth());

            step++;
        }
    }

    public void getPicture(){
        System.out.println(" 0           /------\\");
        System.out.println("/|\\          |(.)   |--|");
        System.out.println("/ \\          \\______/  |----8");
    }

    public void winMonster(){
        System.out.println("*       *    ****    *     *    ****   *****   *****   ****         *               *   ***   *     *");
        System.out.println("* *   * *   *    *   * *   *   *         *     *       *   *         *             *     *    * *   *");
        System.out.println("*   *   *   *    *   *  *  *    **       *     ***     ****           *     *     *      *    *  *  *");
        System.out.println("*       *   *    *   *   * *       *     *     *       **              *   * *   *       *    *   * *");
        System.out.println("*       *    ****    *     *   ****      *     *****   *  **            ***   ***       ***   *     *");
    }

    public void winPlayer(){
        System.out.println("****    *           *       *   *   *****   ****         *               *   ***   *     *");
        System.out.println("*   *   *          * *       *  *   *       *   *         *             *     *    * *   *");
        System.out.println("****    *         *****       ***   ***     ****           *     *     *      *    *  *  *");
        System.out.println("*       *        *     *       *    *       **              *   * *   *       *    *   * *");
        System.out.println("*       *****   *       *     **    *****   *  **            ***   ***       ***   *     *");
    }

    public void cleanScreen(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
