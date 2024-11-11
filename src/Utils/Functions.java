/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author david
 */
public class Functions {
    
    public static int getRandom(){
        return (int) Math.random();
    }
    
    public static int getRandomInt(int lower, int upper){
        return (int) (Math.random()*(upper-lower))+lower;  
    }
    
    public static String castIntToString(int num){
        return Integer.toString(num);
    }
    
    public static int checkSuccess(int probability){
        int randomInt = Functions.getRandomInt(0,100);
        int value = (randomInt <= probability) ? 1 : 0;
        return value;
    }
}
