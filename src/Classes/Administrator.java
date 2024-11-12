/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import static Constants.Constants.NEW_CHARACTER_CHANCE;
import static Constants.Constants.OUT_OF_SUPPORT_CHANCE;
import static Constants.Constants.ZERO_STATS;

import java.util.Random;
import java.util.concurrent.Semaphore;

import UserInterface.MainUI;


public class Administrator extends Thread {

    private StarshipSide starWars;
    private StarshipSide starTrek;
    private int cyclesCounter;
    private int newCharacterChance;
    private Semaphore synchronization;
    private Semaphore readyAI;
    private ArtificialIntelligence AI;
    private MainUI userInterface;
    private int processingSpeedInMS;
    private int outOfSupportChance;

    public Administrator(Semaphore synchronization, Semaphore readyAI, ArtificialIntelligence AI,
            int processingSpeedInMS, StarshipSide starWars, StarshipSide starTrek,
            MainUI userInterface) {
        this.starWars = starWars;
        this.starTrek = starTrek;
        this.cyclesCounter = 1;
        this.newCharacterChance = NEW_CHARACTER_CHANCE;
        this.outOfSupportChance = OUT_OF_SUPPORT_CHANCE;
        this.synchronization = synchronization;
        this.AI = AI;
        this.userInterface = userInterface;
        this.readyAI = readyAI;
        this.processingSpeedInMS = processingSpeedInMS;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.updateProcesingSpeedFromSpinner();

                this.resetInterface();
                sleep(100);

                updateUIValues();

                this.getUserInterface().changeRound(this.cyclesCounter);
                chooseFighters();
                updateUIValues();

                if (getAI().getBattleOcurring() == null) {
                    System.out.println("No hay peleadores disponibles");
                    sleep(this.getProcessingSpeedInMS());
                    continue;
                }

                getAI().setRound(cyclesCounter);

                getSynchronization().release();

                getReadyAI().acquire();

                avoidStarvation();
                updateUIValues();

                handleBattleResults(getAI().getBattleOcurring());
                updateUIValues();

                Random random = new Random();
                askForSupport(random);

                this.evaluateIfNewCharacters(random);

                cyclesCounter++;
                updateUIValues();
                printBothStarshipQueues();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void resetInterface() {
        getUserInterface().changeAIStatus("Waiting");
        getUserInterface().changeBattleType("");
        getUserInterface().changeResult("");
        this.getUserInterface().clearStarWarWinner();
        this.getUserInterface().clearStarTrekWinner();
    }

    public void chooseFighters() {
        Character firstFighter = getstarWars().getNextFighter();
        getAI().setFirstFighter(firstFighter);
        Character secondFighter = getstarTrek().getNextFighter();
        getAI().setSecondFighter(secondFighter);

        if (firstFighter != null && secondFighter != null) {
            updateCharactersUI(firstFighter, secondFighter);
            Battle battle = new Battle(firstFighter, secondFighter);
            getAI().setBattleOcurring(battle);
        } else {
            updateCharactersUI(firstFighter, secondFighter);
            getAI().setBattleOcurring(null);
        }
        getAI(); // ???

    }

    public void avoidStarvation() {
        getstarWars().increaseStarvationCounters();
        getstarTrek().increaseStarvationCounters();
    }

    public void evaluateIfNewCharacters(Random random) {
        if ((cyclesCounter % 2 == 0) && (random.nextInt(1, 100) < newCharacterChance)) {
            createNewCharacters();
            updateUIValues();
        }
    }

    public void updateUIValues() {
        getstarWars().updateQueuesUI();
        getstarTrek().updateQueuesUI();

    }

    public void printBothStarshipQueues() {
        getstarWars().printQueues();
        getstarTrek().printQueues();
    }

    public void updateCharactersUI(Character firstFighter, Character secondFighter) {
        if (firstFighter != null && secondFighter != null) {
            getUserInterface().changeCharacterStatsByStarship(getstarWars().getstarshipInt(), firstFighter.getStats(),
                    firstFighter.getID());
            getUserInterface().changeCharacterStatsByStarship(getstarTrek().getstarshipInt(),
                    secondFighter.getStats(),
                    secondFighter.getID());

            getUserInterface().changeCharacterImage(getstarWars().getstarshipInt(), firstFighter.getImage());
            getUserInterface().changeCharacterImage(getstarTrek().getstarshipInt(), secondFighter.getImage());
        } else {
            getUserInterface().changeCharacterStatsByStarship(getstarWars().getstarshipInt(), ZERO_STATS, null);
            getUserInterface().changeCharacterStatsByStarship(getstarTrek().getstarshipInt(), ZERO_STATS, null);

            getUserInterface().changeCharacterImage(getstarWars().getstarshipInt(),
                    "src/Assets/StarWarImages/starwar_logo.png");
            getUserInterface().changeCharacterImage(getstarTrek().getstarshipInt(),
                    "src/Assets/StartreckImages/starteckLogo.png");
        }

    }

    public StarshipSide getStarShipByStarship(int starShipInt) {
        return switch (starShipInt) {
            case 0 ->
                getstarWars();
            case 1 ->
                getstarTrek();
            default ->
                null;
        };
    }

    public void handleBattleResults(Battle battleOcurring) {
        int result = battleOcurring.getResult();
        switch (result) {
            case 0 ->
                handleWin(battleOcurring);
            case 1 ->
                handleDraw(battleOcurring);
            case 2 ->
                handleNoCombat(battleOcurring);

        }

    }

    public void createNewCharacters() {
        this.getstarWars().createRandomCharacter();
        this.getstarTrek().createRandomCharacter();
    }

    public void handleWin(Battle battleOcurring) {
        // Add winner to winner list should be done by this method
    }

    public void handleDraw(Battle battleOcurring) {

        Character StarwarFighter = battleOcurring.getFirstFighter();
        Character StarTreckFighter = battleOcurring.getSecondFighter();
        if (StarwarFighter != null && StarTreckFighter != null) {

            StarwarFighter.setPriorityLevel(1);
            this.getstarWars().getTopPriorityQueue().add(StarwarFighter);

            StarTreckFighter.setPriorityLevel(1);
            this.getstarTrek().getTopPriorityQueue().add(StarTreckFighter);

        }
    }

    public void handleNoCombat(Battle battleOcurring) {
        Character StarwarFighter = battleOcurring.getFirstFighter();
        Character StarTreckFighter = battleOcurring.getSecondFighter();
        if (StarwarFighter != null && StarTreckFighter != null) {
            this.getstarWars().getSupportQueue().add(StarwarFighter);

            this.getstarTrek().getSupportQueue().add(StarTreckFighter);
        }
    }

    public void askForSupport(Random random) {
        int randomInt = random.nextInt(1, 100);
        Character StarwarFighter = this.getstarWars().getSupportQueue().dispatch();
        Character StarTreckFighter = this.getstarTrek().getSupportQueue().dispatch();

        if (StarwarFighter == null || StarTreckFighter == null) {
            return;
        }
        if (randomInt <= this.getOutOfSupportChance()) {
            StarwarFighter.setPriorityLevel(1);
            this.getstarWars().getTopPriorityQueue().add(StarwarFighter);
            StarTreckFighter.setPriorityLevel(1);
            this.getstarTrek().getTopPriorityQueue().add(StarTreckFighter);
            System.out.println("\nOut of Starwars support Queue--->" + StarwarFighter.getID());
            System.out.println("\nOut of StarTreck support Queue--->" + StarTreckFighter.getID());
            System.out.println("");
        } else {
            this.getstarWars().getSupportQueue().add(StarwarFighter);
            this.getstarTrek().getSupportQueue().add(StarTreckFighter);
            System.out.println("\nBack to Starwars support Queue--->" + StarwarFighter.getID());
            System.out.println("\nBack to StarTreck support Queue--->" + StarTreckFighter.getID());
            System.out.println("");
        }
    }

    public void updateProcesingSpeedFromSpinner() {
        int newSpeed = (int) this.getUserInterface().getUISpeedSpinner().getValue() * 1000;
        this.setProcessingSpeedInMS(newSpeed);
    }

    // Getters and Setters
    public StarshipSide getstarWars() {
        return starWars;
    }

    public void setstarWars(StarshipSide starWars) {
        this.starWars = starWars;
    }

    public StarshipSide getstarTrek() {
        return starTrek;
    }

    public void setstarTrek(StarshipSide starTrek) {
        this.starTrek = starTrek;
    }

    public int getCyclesCounter() {
        return cyclesCounter;
    }

    public void setCyclesCounter(int cyclesCounter) {
        this.cyclesCounter = cyclesCounter;
    }

    public int getNewCharacterChance() {
        return newCharacterChance;
    }

    public void setNewCharacterChance(int newCharacterChance) {
        this.newCharacterChance = newCharacterChance;
    }

    public Semaphore getSynchronization() {
        return synchronization;
    }

    public ArtificialIntelligence getAI() {
        return AI;
    }

    public void setAI(ArtificialIntelligence AI) {
        this.AI = AI;
    }

    public MainUI getUserInterface() {
        return userInterface;
    }

    public Semaphore getReadyAI() {
        return readyAI;
    }

    /**
     * @return the workingSpeed
     */
    public int getProcessingSpeedInMS() {
        return processingSpeedInMS;
    }

    /**
     * @param processingSpeedInMS the workingSpeed to set
     */
    public void setProcessingSpeedInMS(int processingSpeedInMS) {
        this.processingSpeedInMS = processingSpeedInMS;
    }

    /**
     * @return the outOfSupportChance
     */
    public int getOutOfSupportChance() {
        return outOfSupportChance;
    }

    /**
     * @param outOfSupportChance the outOfSupportChance to set
     */
    public void setOutOfSupportChance(int outOfSupportChance) {
        this.outOfSupportChance = outOfSupportChance;
    }
}
