/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;


public class BattleType {

    private int type; // 0: Normal Battle, 1:Resistance battle, 2: Agility Battle
    private String typeString;
    private float[] weights; // weights[0]:HP  weights[1]:strength weights[2]:agility weights[3]:speed weights[4]:magic weights[5]:manaPoints

    public BattleType(int type) {
        this.type = type;
        this.weights = this.chooseWeightsByType();
        this.typeString = this.chooseTypeString();
    }

    private float[] chooseWeightsByType() {
        float[] typeBattleArray = {1, 1, 1, 1, 1, 1};
        switch (this.type) {
            case 0 -> {
                float[] normalBattleArray = {16.67f, 16.67f, 16.67f, 16.67f, 16.67f, 16.67f};
                typeBattleArray = normalBattleArray;
            }
            case 1 -> {
                float[] resistanceBattleArray = {25f, 25f, 10f, 10f, 10f, 20f};
                typeBattleArray = resistanceBattleArray;
            }
            case 2 -> {
                float[] agilityBattleArray = {10f, 10f, 25f, 25f, 20f, 10f};
                typeBattleArray = agilityBattleArray;
            }
        }
        return typeBattleArray;

    }
    
    private String chooseTypeString () {
        String typeBattleString = "";
        switch (this.type) {
            case 0 -> {
                String normalBattleString = "Normal";
                typeBattleString = normalBattleString;
            }
            case 1 -> {
                String resistanceBattleString = "Resistance";
                typeBattleString = resistanceBattleString;
            }
            case 2 -> {
                String agilityBattleString = "Agility";
                typeBattleString = agilityBattleString;
            }

        }
        return typeBattleString;

    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the weights
     */
    public float[] getWeights() {
        return weights;
    }

    /**
     * @param weights the weights to set
     */
    public void setWeights(float[] weights) {
        this.weights = weights;
    }

    /**
     * @return the typeString
     */
    public String getTypeString() {
        return typeString;
    }

    /**
     * @param typeString the typeString to set
     */
    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

}
