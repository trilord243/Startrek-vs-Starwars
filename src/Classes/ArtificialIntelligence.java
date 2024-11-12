/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import static Constants.Constants.DRAW_RATE;
import static Constants.Constants.NON_COMBAT_RATE;
import static Constants.Constants.WIN_RATE;

import java.util.Random;
import java.util.concurrent.Semaphore;

import DataStructures.LinkedList;
import UserInterface.MainUI;
import static java.lang.Thread.sleep;


public class ArtificialIntelligence extends Thread {

    private Character firstFighter;
    private Character secondFighter;
    private int winRate;
    private int drawRate;
    private int nonCombatRate;
    private LinkedList<Character> winners;
    private Battle battleOcurring;
    private Semaphore synchronization;
    private Semaphore readyAI;
    private MainUI userInterface;
    private int processingSpeedInMS;
    private StarshipSide starWars;
    private StarshipSide starTrek;
    private int round = 1;

    public ArtificialIntelligence(Semaphore synchronization, Semaphore readyAI, int processingSpeedInMS,
            StarshipSide starWars, StarshipSide starTrek, MainUI userInterface) {
        this.firstFighter = null;
        this.secondFighter = null;
        this.winRate = WIN_RATE;
        this.drawRate = DRAW_RATE;
        this.nonCombatRate = NON_COMBAT_RATE;
        this.winners = new LinkedList<>();
        this.battleOcurring = null;
        this.synchronization = synchronization;
        this.userInterface = userInterface;
        this.readyAI = readyAI;
        this.processingSpeedInMS = processingSpeedInMS;
        this.starWars = starWars;
        this.starTrek = starTrek;
    }

    @Override
    public void run() {
        while (true) {
            try {
                getSynchronization().acquire();
                this.updateProcesingSpeedFromSpinner();

                getUserInterface().changeAIStatus("Processing...");
                getUserInterface().changeBattleType(this.getBattleOcurring().getBattleType().getTypeString());
                sleep(getProcessingSpeedInMS());

                chooseWinner();

                getUserInterface().changeAIStatus("Done!");
                sleep(200);

                updateUIValues(); // UI Updates should be done by Admin

                getReadyAI().release();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void chooseWinner() {
        // TODO - Modify User Interface to show the winner
        if (getBattleOcurring() != null) {
            Random random = new Random();
            int randomResult = random.nextInt(1, 100);

            if (randomResult <= getDrawRate()) {
                getBattleOcurring().setResult(1);
                handleDraw();
            } else if (randomResult > getDrawRate() && randomResult <= getDrawRate() + getNonCombatRate()) {
                getBattleOcurring().setResult(2);
                handleNoCombat();
            } else if (randomResult > getDrawRate() + getNonCombatRate()
                    && randomResult <= getDrawRate() + getNonCombatRate() + getWinRate()) {
                determineWinner(random);
            }
            System.out.println("-------" + "Round-" + getRound() + "-------");
            System.out.println(getBattleOcurring().toString() + "\n");
            this.getUserInterface().changeResult(getBattleOcurring().getResultString());

        }
    }

    public void determineWinner(Random random) {
        if (getFirstFighter() != null && getSecondFighter() != null) {

            int pickedWinner = this.pickWinner(random);
            if (pickedWinner == 1) {
                getWinners().addLast(getFirstFighter());

                getstarWars().setWinsQty(getstarWars().getWinsQty() + 1);
                getUserInterface().changeWinsCounterByStarship(getstarWars().getstarshipInt(),
                        getstarWars().getWinsQty());

                this.getUserInterface().setStarwarWinner();
                getBattleOcurring().setResult(0);
                getBattleOcurring().setWinner(getFirstFighter());

            } else {
                getWinners().addLast(getSecondFighter());

                getstarTrek().setWinsQty(getstarTrek().getWinsQty() + 1);
                getUserInterface().changeWinsCounterByStarship(getstarTrek().getstarshipInt(),
                        getstarTrek().getWinsQty());

                this.getUserInterface().setStartreckWinner();
                getBattleOcurring().setResult(0);
                getBattleOcurring().setWinner(getSecondFighter());
            }
        }
    }

    public int pickWinner(Random random) {
        float[] typeBattleArray = this.getBattleOcurring().getBattleType().getWeights();

        float[] StarwarFighterStats = this.getFirstFighter().statsToArray();
        float StarwarFighterOverall = 0;

        float[] StartreckFighterStats = this.getSecondFighter().statsToArray();
        float StartreckFighterOverall = 0;

        for (int i = 0; i < typeBattleArray.length; i++) {
            StarwarFighterOverall += typeBattleArray[i] + StarwarFighterStats[i];
            StartreckFighterOverall += typeBattleArray[i] + StartreckFighterStats[i];
        }

        int winner;
        if (StarwarFighterOverall > StartreckFighterOverall) {
            winner = 1;
        } else if (StarwarFighterOverall < StartreckFighterOverall) {
            winner = 2;
        } else {
            winner = random.nextInt(1, 3);
        }
        return winner;
    }

    public void handleDraw() {
        if (getFirstFighter() != null && getSecondFighter() != null) {
            getBattleOcurring().setResult(1);
        }
    }

    public void handleNoCombat() {
        if (getFirstFighter() != null && getSecondFighter() != null) {
            getBattleOcurring().setResult(2);
        }
    }

    public void updateUIValues() {
        getstarWars().updateQueuesUI();
        getstarTrek().updateQueuesUI();

    }

    public void updateProcesingSpeedFromSpinner() {
        int newSpeed = (int) this.getUserInterface().getUISpeedSpinner().getValue() * 1000;
        this.setProcessingSpeedInMS(newSpeed);
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

    public int getWinRate() {
        return winRate;
    }

    public void setWinRate(int winRate) {
        this.winRate = winRate;
    }

    public int getDrawRate() {
        return drawRate;
    }

    public void setDrawRate(int drawRate) {
        this.drawRate = drawRate;
    }

    public int getNonCombatRate() {
        return nonCombatRate;
    }

    public void setNonCombatRate(int nonCombatRate) {
        this.nonCombatRate = nonCombatRate;
    }

    public LinkedList<Character> getWinners() {
        return winners;
    }

    public void setWinners(LinkedList<Character> winners) {
        this.winners = winners;
    }

    public Battle getBattleOcurring() {
        return battleOcurring;
    }

    public void setBattleOcurring(Battle battleOcurring) {
        this.battleOcurring = battleOcurring;
    }

    public Semaphore getSynchronization() {
        return synchronization;
    }

    public MainUI getUserInterface() {
        return userInterface;
    }

    public Semaphore getReadyAI() {
        return readyAI;
    }

    public int getProcessingSpeedInMS() {
        return processingSpeedInMS;
    }

    public void setProcessingSpeedInMS(int processingSpeedInMS) {
        this.processingSpeedInMS = processingSpeedInMS;
    }

    public StarshipSide getstarWars() {
        return starWars;
    }

    public StarshipSide getstarTrek() {
        return starTrek;
    }

    /**
     * @return the round
     */
    public int getRound() {
        return round;
    }

    /**
     * @param round the round to set
     */
    public void setRound(int round) {
        this.round = round;
    }
}
