/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Utils.Functions;


public class StatsCreator {

    public static Stats createStats() {
    int HP = Functions.getRandomInt(100, 200);
    int strength = Functions.getRandomInt(100, 200);
    int agility = Functions.getRandomInt(100, 200);
    int speed = Functions.getRandomInt(100, 200);
    int magic = Functions.getRandomInt(100, 200);
    int manaPoints = Functions.getRandomInt(100, 200);
    return new Stats(HP,strength,agility,speed,magic,manaPoints);
}

}
