/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import DataStructures.LinkedList;
import DataStructures.Queue;
import UserInterface.MainUI;


public class StarshipSide {

    private int starshipInt;
    private String starshipName;
    private int characterCounter; // used to generate charcaters ID
    private LinkedList<Character> characters;
    private Queue<Character> topPriorityQueue;
    private Queue<Character> secondPriorityQueue;
    private Queue<Character> thirdPriorityQueue;
    private Queue<Character> supportQueue;
    private CharacterCreator characterCreator;
    private MainUI userInterface;
    private int winsQty;

    public StarshipSide(int starshipInt, String starshipName, MainUI userInterface) {
        this.starshipInt = starshipInt;
        this.starshipName = starshipName;
        this.characterCounter = 0;
        this.characters = new LinkedList<>();
        this.topPriorityQueue = new Queue<>();
        this.secondPriorityQueue = new Queue<>();
        this.thirdPriorityQueue = new Queue<>();
        this.supportQueue = new Queue<>();
        this.characterCreator = new CharacterCreator();
        this.createInitialCharacters();
        this.userInterface = userInterface;
        this.winsQty = 0;
    }

    public Character getNextFighter() {
        if (!topPriorityQueue.isEmpty()) {
            return getTopPriorityQueue().dispatch();
        } else if (!secondPriorityQueue.isEmpty()) {
            return getSecondPriorityQueue().dispatch();
        } else if (!thirdPriorityQueue.isEmpty()) {
            return getThirdPriorityQueue().dispatch();
        } else {
            return null;
        }
    }

    public void increaseStarvationCounters() {
        String charactersMovedToTopQueue = "New To Top: ";
        String charactersMovedToSecondQueue = "New To Second: ";
        for (int i = 0; i < getSecondPriorityQueue().getSize(); i++) {
            charactersMovedToTopQueue += modifyCharacterPriority(i, 2);

        }
        for (int i = 0; i < getThirdPriorityQueue().getSize(); i++) {
            charactersMovedToSecondQueue += modifyCharacterPriority(i, 3);

        }
        System.out.println("\n[ " + this.starshipName + " ]");
        System.out.println(charactersMovedToTopQueue);
        System.out.println(charactersMovedToSecondQueue);
        System.out.println("");
    }

    public String modifyCharacterPriority(int index, int queuePriorityLevel) {
        Queue<Character> queue = getQueueByPriorityLevel(queuePriorityLevel);
        Character character = queue.getElement(index);

        if (character.getStarvationCounter() == 8) {
            character.setPriorityLevel(queuePriorityLevel - 1);

            queue.removeElement(character);

            Queue<Character> newQueue = getQueueByPriorityLevel(queuePriorityLevel - 1);
            newQueue.add(character);

            character.setStarvationCounter(0);
            return character.getName() + " -> ";

        }
        character.increaseStarvationCounter();
        return "";

    }

    public void updateQueuesUI() {
        getUserInterface().changeQueueByPriorityLevelAndStarship(1, getstarshipInt(),
                getTopPriorityQueue().printQueue());

        getUserInterface().changeQueueByPriorityLevelAndStarship(2, getstarshipInt(),
                getSecondPriorityQueue().printQueue());

        getUserInterface().changeQueueByPriorityLevelAndStarship(3, getstarshipInt(),
                getThirdPriorityQueue().printQueue());

        getUserInterface().changeQueueByPriorityLevelAndStarship(4, getstarshipInt(),
                getSupportQueue().printQueue());
    }

    public Queue<Character> getQueueByPriorityLevel(int priorityLevel) {
        switch (priorityLevel) {
            case 1:
                return getTopPriorityQueue();
            case 2:
                return getSecondPriorityQueue();
            case 3:
                return getThirdPriorityQueue();
            default:
                return null;
        }
    }

    public void addCharacter(Character character) {
        // TODO - Refactor to Add random character to the starship
        if (character.isTopPriority()) {
            getTopPriorityQueue().add(character);
        } else if (character.getPriorityLevel() == 2) {
            getSecondPriorityQueue().add(character);
        } else if (character.getPriorityLevel() == 3) {
            getThirdPriorityQueue().add(character);
        }
    }

    private void createInitialCharacters() {
        switch (this.getstarshipInt()) {
            case 0 ->
                this.characters = this.characterCreator.createInitialStarwarCharacters();
            case 1 ->
                this.characters = this.characterCreator.createInitialStartrekCharacters();
        }
        this.characterCounter = 20;
        for (int i = 0; i < this.characters.getSize(); i++) {
            Character character = this.characters.getElement(i);
            this.addCharacter(character);
        }
        this.printQueues();
    }

    public void printQueues() {
        System.out.println("\n--------------------------------------------------------------------------");
        System.out.println("[ " + this.starshipName + " ]");
        System.out.println("Top Queue: " + this.getTopPriorityQueue().printQueue());
        System.out.println("Second Queue: " + this.getSecondPriorityQueue().printQueue());
        System.out.println("Third Queue: " + this.getThirdPriorityQueue().printQueue());
        System.out.println("Support Queue: " + this.getSupportQueue().printQueue());
        System.out.println("--------------------------------------------------------------------------\n\n");
    }

    public void createRandomCharacter() {
        Character randomCharacter = null;
        switch (this.getstarshipInt()) {
            case 0 ->
                randomCharacter = this.characterCreator.createRandomStarwarCharacter(++this.characterCounter);
            case 1 ->
                randomCharacter = this.characterCreator.createRandomStartrekCharacter(++this.characterCounter);
        }

        this.addCharacter(randomCharacter);
        String newCharacterString = "\n***New " + this.getstarshipName() + " Character***\n" + randomCharacter.toString();
        System.out.println(newCharacterString);

    }

    // Getters and Setters
    public int getstarshipInt() {
        return starshipInt;
    }

    public void setstarshipInt(int starshipInt) {
        this.starshipInt = starshipInt;
    }

    public String getstarshipName() {
        return starshipName;
    }

    public void setstarshipName(String starshipName) {
        this.starshipName = starshipName;
    }

    public LinkedList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(LinkedList<Character> characters) {
        this.characters = characters;
    }

    public Queue<Character> getTopPriorityQueue() {
        return topPriorityQueue;
    }

    public void setTopPriorityQueue(Queue<Character> topPriorityQueue) {
        this.topPriorityQueue = topPriorityQueue;
    }

    public Queue<Character> getSecondPriorityQueue() {
        return secondPriorityQueue;
    }

    public void setSecondPriorityQueue(Queue<Character> secondPriorityQueue) {
        this.secondPriorityQueue = secondPriorityQueue;
    }

    public Queue<Character> getThirdPriorityQueue() {
        return thirdPriorityQueue;
    }

    public void setThirdPriorityQueue(Queue<Character> thirdPriorityQueue) {
        this.thirdPriorityQueue = thirdPriorityQueue;
    }

    public Queue<Character> getSupportQueue() {
        return supportQueue;
    }

    public void setSupportQueue(Queue<Character> supportQueue) {
        this.supportQueue = supportQueue;
    }

    /**
     * @return the characterCounter
     */
    public int getCharacterCounter() {
        return characterCounter;
    }

    /**
     * @param characterCounter the characterCounter to set
     */
    public void setCharacterCounter(int characterCounter) {
        this.characterCounter = characterCounter;
    }

    /**
     * @return the characterCreator
     */
    public CharacterCreator getCharacterCreator() {
        return characterCreator;
    }

    /**
     * @param characterCreator the characterCreator to set
     */
    public void setCharacterCreator(CharacterCreator characterCreator) {
        this.characterCreator = characterCreator;
    }

    public MainUI getUserInterface() {
        return userInterface;
    }

    public int getWinsQty() {
        return winsQty;
    }

    public void setWinsQty(int winsQty) {
        this.winsQty = winsQty;
    }
}
