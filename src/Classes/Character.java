/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;


public class Character {

    private String ID;
    private String name;
    private int priorityLevel;
    private Stats stats;
    private int starvationCounter;
    private String image;

    public Character(String id, String name, int priorityLevel, Stats stats, String image) {
        this.ID = id;
        this.name = name;
        this.priorityLevel = priorityLevel;
        this.stats = stats;
        this.starvationCounter = 1;
        this.image = image;
    }

    public void increaseStarvationCounter() {
        if (!isTopPriority() && getStarvationCounter() < 8) {
            this.starvationCounter++;
        }
    }

    public boolean isTopPriority() {
        return getPriorityLevel() == 1;
    }

    @Override
    public String toString() {
        return "ID: " + this.ID + "\n"
                + "Name: " + this.name + "\n"
                + "Priority: " + this.priorityLevel + "\n"
                + "Image: " + this.image + "\n";
    }

    public float[] statsToArray() {
        float HP = (float) this.getStats().getHP();
        float strength = (float) this.getStats().getStrength();
        float agility = (float) this.getStats().getAgility();
        float speed = (float) this.getStats().getSpeed();
        float magic = (float) this.getStats().getMagic();
        float manaPoints = (float) this.getStats().getManaPoints();

        float[] statsArray = { HP, strength, agility, speed, magic, manaPoints };
        return statsArray;
    }

    // Getters and Setters
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public int getStarvationCounter() {
        return starvationCounter;
    }

    public void setStarvationCounter(int starvationCounter) {
        this.starvationCounter = starvationCounter;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
