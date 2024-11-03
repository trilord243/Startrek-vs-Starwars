/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;


public class Stats {

    private int HP;
    private int strength;
    private int agility;
    private int speed;
    private int magic;
    private int manaPoints;

    public Stats() {
        this.HP = 0;
        this.strength = 0;
        this.agility = 0;
        this.speed = 0;
        this.magic = 0;
        this.manaPoints = 0;
    }

    public Stats(int HP, int strength, int agility, int speed, int magic, int manaPoints) {
        this.HP = HP;
        this.strength = strength;
        this.agility = agility;
        this.speed = speed;
        this.magic = magic;
        this.manaPoints = manaPoints;
    }

    // Getters and Setters
    public int getHP() {
        return HP;
    }

    public void setHp(int HP) {
        this.HP = HP;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getManaPoints() {
        return manaPoints;
    }

    public void setManaPoints(int manaPoints) {
        this.manaPoints = manaPoints;
    }

}
