
package Constants;

import Classes.Stats;


public class Constants {

        // Odds
        public static final int DRAW_RATE = 27;
        public static final int NON_COMBAT_RATE = 33;
        public static final int WIN_RATE = 40;

        public static final int QUALITY_ABILITIES_CHANCE = 60;
        public static final int QUALITY_HP_CHANCE = 70;
        public static final int QUALITY_STRENGTH_CHANCE = 50;
        public static final int QUALITY_AGILITY_CHANCE = 40;

        public static final int OUT_OF_SUPPORT_CHANCE = 40;
        public static final int NEW_CHARACTER_CHANCE = 80;

        // STARWAR
        public static final int STARWAR_INT = 0;
        public static final String STARWAR_STRING = "STARWAR";
        public static final String[] STARWAR_CHARACTER_NAMES = {
                        "Ahsoka Tano",
                        "Bobbafet",
                        "C3P0",
                        "Chewbacca",
                        "Darth Vader",
                        "Dooku",
                        "Ewok",
                        "General Grievous",
                        "Han Solo",
                        "Janjo Fett",
                        "Luke Skywalker",
                        "jarjar",
                        "Darth Maul",
                        "Obi-Wan Kenobi",
                        "Padme Amidala",
                        "Palpatine",
                        "Qui-Gon Jinn",
                        "r2d2",
                        "stormtrooper",
                        "Yoda"
        };
        public static final String[] STARWAR_CHARACTER_IMAGES = {
                        "src/Assets/StarwarsImages/ahsoka.jpg",
                        "src/Assets/StarwarsImages/bobbafet.jpg",
                        "src/Assets/StarwarsImages/c3po.jpg",
                        "src/Assets/StarwarsImages/chewbaca.jpg",
                        "src/Assets/StarwarsImages/dartvader.jpg",
                        "src/Assets/StarwarsImages/dooku.jpg",
                        "src/Assets/StarwarsImages/ewok.jpg",
                        "src/Assets/StarwarsImages/grievus.jpg",
                        "src/Assets/StarwarsImages/hansolo.jpg",
                        "src/Assets/StarwarsImages/jango.jpg",
                        "src/Assets/StarwarsImages/luke.jpg",
                        "src/Assets/StarwarsImages/jarjar.jpg",
                        "src/Assets/StarwarsImages/maul.jpg",
                        "src/Assets/StarwarsImages/obiwan.jpg",
                        "src/Assets/StarwarsImages/padme.jpg",
                        "src/Assets/StarwarsImages/palpatine.jpg",
                        "src/Assets/StarwarsImages/Quigon.jpg",
                        "src/Assets/StarwarsImages/r2d2.jpg",
                        "src/Assets/StarwarsImages/stormtrooper.jpeg",
                        "src/Assets/StarwarsImages/yoda.jpg"
        };

        // Cartoon Network
        public static final int STARTREK_INT = 1;
        public static final String STARTREK_STRING = "Startrek";
        public static final String[] STARTREK_CHARACTER_NAMES = {
                        "Benjamin Sisko",
                        "Beverly Crusher",
                        "Charles “Trip”",
                        "Data",
                        "Geordi La Forge",
                        "Hikaru Sulu",
                        "James T Kirk",
                        "Jean-Luc Picard",
                        "Julian Bashir",
                        "Kathryn Janeway",
                        "Kira Nerys",
                        "Leonard “Bones”",
                        "Miles O’ Brien",
                        "Montgomery“ Scotty”",
                        "Nyota Uhura",
                        "Odo",
                        "Seven of Nine",
                        "Spock",
                        "William Riker",
                        "Worf"
        };

        public static final String[] STARTREK_CHARACTER_IMAGES = {
                        "src/Assets/StartreckImages/BenjaminSisko.png",
                        "src/Assets/StartreckImages/BeverlyCrusher.png",
                        "src/Assets/StartreckImages/Charles“Trip”.png",
                        "src/Assets/StartreckImages/Data.png",
                        "src/Assets/StartreckImages/GeordiLaForge.png",
                        "src/Assets/StartreckImages/HikaruSulu.png",
                        "src/Assets/StartreckImages/JamesTKirk.png",
                        "src/Assets/StartreckImages/Jean-LucPicard.png",
                        "src/Assets/StartreckImages/JulianBashir.png",
                        "src/Assets/StartreckImages/KathrynJaneway.png",
                        "src/Assets/StartreckImages/KiraNerys.png",
                        "src/Assets/StartreckImages/Leonard“Bones”.png",
                        "src/Assets/StartreckImages/MilesO’Brien.png",
                        "src/Assets/StartreckImages/Montgomery“Scotty”.png",
                        "src/Assets/StartreckImages/NyotaUhura.png",
                        "src/Assets/StartreckImages/Odo.png",
                        "src/Assets/StartreckImages/SevenofNine.png",
                        "src/Assets/StartreckImages/Spock.png",
                        "src/Assets/StartreckImages/WilliamRiker.png",
                        "src/Assets/StartreckImages/Worf.png"
        };

        // Stats when Null
        public static final Stats ZERO_STATS = new Stats();

}
