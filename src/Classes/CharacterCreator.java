/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Constants.Constants;
import static Constants.Constants.STARTREK_CHARACTER_IMAGES;
import static Constants.Constants.STARTREK_CHARACTER_NAMES;
import static Constants.Constants.STARTREK_INT;
import static Constants.Constants.STARWAR_CHARACTER_IMAGES;
import static Constants.Constants.STARWAR_CHARACTER_NAMES;
import static Constants.Constants.STARWAR_INT;
import static Constants.Constants.QUALITY_ABILITIES_CHANCE;
import static Constants.Constants.QUALITY_AGILITY_CHANCE;
import static Constants.Constants.QUALITY_HP_CHANCE;
import static Constants.Constants.QUALITY_STRENGTH_CHANCE;
import DataStructures.LinkedList;
import Utils.Functions;


public class CharacterCreator {

    private Character createCharacter(int starshipInt, int nextCharacterInt, String name, String image) {
        String starship = Integer.toString(starshipInt);
        String characterInt = Integer.toString(nextCharacterInt);
        String nameNoBlanks = name.replace(" ", "");
        String id = starship + "-" + nameNoBlanks + "-" + characterInt;
        Stats stats = this.getStats();
        int priority = this.calculatePriority();
        return new Character(id, name, priority, stats, image);
    }

    private Stats getStats() {
        return StatsCreator.createStats();
    }

    // We could use Weigthed Factors
    private int calculatePriority() {

        int skillsQuality = Functions.checkSuccess(QUALITY_ABILITIES_CHANCE);
        int hpQuality = Functions.checkSuccess(QUALITY_HP_CHANCE);
        int strengthQuality = Functions.checkSuccess(QUALITY_STRENGTH_CHANCE);
        int agilityQuality = Functions.checkSuccess(QUALITY_AGILITY_CHANCE);

        int overall = skillsQuality + hpQuality + strengthQuality + agilityQuality;
        int[] priorities = {3, 3, 2, 2, 1};
        int characterPriority = priorities[overall];

        return characterPriority;
    }

    public Character createRandomStarwarCharacter(int nextCharacterInt) {
        Character character;
        String[] characterNames = STARWAR_CHARACTER_NAMES;
        String[] characterImages = STARWAR_CHARACTER_IMAGES;
        int randomInt = Functions.getRandomInt(0, 19);
        String characterName = characterNames[randomInt];
        String characterImage = characterImages[randomInt];
        character = createCharacter(STARWAR_INT, nextCharacterInt, characterName, characterImage);
        return character;
    }
    
    public Character createRandomStartrekCharacter(int nextCharacterInt) {
        Character character;
        String[] characterNames = STARTREK_CHARACTER_NAMES;
        String[] characterImages = STARTREK_CHARACTER_IMAGES;
        int randomInt = Functions.getRandomInt(0, 19);
        String characterName = characterNames[randomInt];
        String characterImage = characterImages[randomInt];
        character = this.createCharacter(STARTREK_INT , nextCharacterInt, characterName, characterImage);
        return character;
    }
    
    public LinkedList<Character> createInitialStarwarCharacters(){
        LinkedList<Character> charactersList = new LinkedList<>();
        String[] characterNames = STARWAR_CHARACTER_NAMES;
        String[] characterImages = STARWAR_CHARACTER_IMAGES;
        Character character = null;
        String characterName = null;
        String characterImage = null;
        for (int i = 0; i < characterNames.length; i++) {
            characterName = characterNames[i];
            characterImage = characterImages[i];
            character = createCharacter(STARWAR_INT, i+1, characterName, characterImage);
            charactersList.addLast(character);
        }
        return charactersList;
    }
    
    public LinkedList<Character> createInitialStartrekCharacters(){
        LinkedList<Character> charactersList = new LinkedList<>();
        String[] characterNames = STARTREK_CHARACTER_NAMES;
        String[] characterImages = STARTREK_CHARACTER_IMAGES;
        Character character = null;
        String characterName = null;
        String characterImage = null;
        for (int i = 0; i < characterNames.length; i++) {
            characterName = characterNames[i];
            characterImage = characterImages[i];
            character = createCharacter(STARTREK_INT, i+1, characterName, characterImage);
            charactersList.addLast(character);
        }
        return charactersList;
    }
    

}
