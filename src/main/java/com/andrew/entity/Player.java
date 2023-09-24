package com.andrew.entity;

public class Player extends Creature{
    private final int maxHealth;
    public Player(int attack, int defence, int health, int minDamage, int maxDamage) {
        super(attack, defence, health, minDamage, maxDamage);
        maxHealth = health;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }




}
