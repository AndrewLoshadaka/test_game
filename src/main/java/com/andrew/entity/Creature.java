package com.andrew.entity;

import java.util.Random;

public abstract class Creature {
    private int attack;
    private int defence;
    private int health;
    private int minDamage;
    private int maxDamage;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public Creature(int attack, int defence, int health, int minDamage, int maxDamage) {
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void attack(Creature targetCreature){
        int modifyAttack = Math.abs(attack - targetCreature.defence) + 1;
        int damage = 0;
        Random rand = new Random();

        if(modifyAttack > 0){
            for(int i = 0; i < modifyAttack; i++){
                int value = rand.nextInt(6) + 1;
                if(value >= 5) {
                    damage += rand.nextInt(maxDamage - minDamage + 1) + minDamage;
                    break;
                }
            }
        }

        targetCreature.getDamage(damage);
    }

    public void getDamage(int damage){
        health -= damage;
        if(health < 0)
            health = 0;
    }

    public void heal(){
        int maxHealth = getMaxHealth();
        int pointHealing = (int) (maxHealth * 0.3);

        if(getHealth() + pointHealing > maxHealth){
            setHealth(maxHealth);
        } else {
            int newHealth = getHealth() + pointHealing;
            setHealth(newHealth);
        }
    }



    public abstract int getMaxHealth();

}
