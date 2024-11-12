/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.Random;


public class Battle {

    private Character firstFighter;
    private Character secondFighter;
    private int result; // 0 = Win, 1 = Draw, 2 = Not happened
    private Character winner;
    private BattleType battleType;

    public Battle(Character firstFighter, Character secondFighter) {
        this.firstFighter = firstFighter;
        this.secondFighter = secondFighter;
        this.result = -1;
        this.winner = null;
        this.battleType = this.chooseRandomBattleType();
    }
    
    public String toString(){
        String firstName = (this.getFirstFighter() == null) ?  "-" :this.getFirstFighter().getName();
        String secondName = (this.getSecondFighter() == null) ?  "-" :this.getSecondFighter().getName();
        
        String resultString=this.getResultString();
        if(this.getResult() == 0){
            resultString +=": " + this.winner.getName();
        }
        String fight = "F1: " + firstName + "  vs  "+ " F2: "+ secondName;
        return fight +"\n" + resultString;
        
    }
    
    private BattleType chooseRandomBattleType(){
        Random random = new Random();
        int n = random.nextInt(0, 3);
        return  new BattleType(n);
    }
    
    public String getResultString(){
        String resultString="";
        
        switch(this.getResult()){
            case -1 ->
                resultString = "---";
            case 0 ->
                resultString = "Winner"  ;
            case 1 ->
                resultString = "Draw";
            case 2 ->
                resultString = "No Combat";
        }
        return resultString;
    }

    // Getters and Setters
    public Character getFirstFighter() {
        return firstFighter;
    }

    public void setFirstFighter(Character firstFighter) {
        this.firstFighter = firstFighter;
    }

    public Character getSecondFighter() {
        return secondFighter;
    }

    public void setSecondFighter(Character secondFighter) {
        this.secondFighter = secondFighter;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Character getWinner() {
        return winner;
    }

    public void setWinner(Character winner) {
        this.winner = winner;
    }

    /**
     * @return the battleType
     */
    public BattleType getBattleType() {
        return battleType;
    }

    /**
     * @param battleType the battleType to set
     */
    public void setBattleType(BattleType battleType) {
        this.battleType = battleType;
    }
}
