/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UserInterface;

import Classes.Administrator;
import Classes.StarshipSide;
import Classes.ArtificialIntelligence;
import Classes.Stats;
import static Constants.Constants.STARTREK_INT;
import static Constants.Constants.STARTREK_STRING;
import static Constants.Constants.STARWAR_INT;
import static Constants.Constants.STARWAR_STRING;
import java.awt.Image;
import java.util.concurrent.Semaphore;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSpinner;


public class MainUI extends javax.swing.JFrame {

    private ArtificialIntelligence AI;
    private Administrator admin;
    private StarshipSide starWar;
    private StarshipSide starTrek;

    /**
     * Creates new form MainUI
     */
    public MainUI() {
        super("Starwars vs Startreck");
        initComponents();
        setLocationRelativeTo(null);

        changeCharacterImage(0, "src/Assets/StarWarsImages/starwar_logo.png");
        changeCharacterImage(1, "src/Assets/StartreckImages/starteckLogo.png");

        setStarWar(new StarshipSide(STARWAR_INT, STARWAR_STRING, this));
        setStarTrek(new StarshipSide(STARTREK_INT, STARTREK_STRING, this));

        Semaphore sync = new Semaphore(0);
        Semaphore readyAI = new Semaphore(0);
        int processingSpeedInMS = (int) this.speed.getValue() * 1000;
        setAI(new ArtificialIntelligence(sync, readyAI, processingSpeedInMS, getStarWar(), getStarTrek(), this));

        setAdmin(new Administrator(sync, readyAI, getAI(), processingSpeedInMS, getStarWar(), getStarTrek(), this));

        getAI().start();
        getAdmin().start();

    }

    public void changeWinsCounterByStarship(int starShipInt, int wins) {
        switch (starShipInt) {
            case 0 -> {
                winsCounterStarwars.setText(Integer.toString(wins));
            }
            case 1 -> {
                winsCounterStartreck.setText(Integer.toString(wins));
            }
            default -> {
            }
        }
    }

    public void changeQueueByPriorityLevelAndStarship(int priorityLevel, int starShipInt, String queueString) {
        switch (priorityLevel) {
            case 1 -> {
                changeTopPriorityQueueByStarShip(starShipInt, queueString);
            }
            case 2 -> {
                changeSecondPriorityQueueByStarship(starShipInt, queueString);
            }
            case 3 -> {
                changeThirdPriorityQueueByStarship(starShipInt, queueString);
            }
            case 4 -> {
                changeSupportPriorityQueueByStarship(starShipInt, queueString);
            }
            default -> {
            }
        }
    }

    public void changeTopPriorityQueueByStarShip(int starShipInt, String queueString) {
        switch (starShipInt) {
            case 0 -> {
                topQueueStarwars.setText(queueString);
            }
            case 1 -> {
                topQueueStartreck.setText(queueString);
            }
            default -> {
            }
        }
    }

    public void changeSecondPriorityQueueByStarship(int starShipInt, String queueString) {
        switch (starShipInt) {
            case 0 -> {
                secondQueueStarwars.setText(queueString);
            }
            case 1 -> {
                secondQueueStartreck.setText(queueString);
            }
            default -> {
            }
        }
    }

    public void changeThirdPriorityQueueByStarship(int starShipInt, String queueString) {
        switch (starShipInt) {
            case 0 -> {
                thirdQueueStarwars.setText(queueString);
            }
            case 1 -> {
                thirdQueueStartreck.setText(queueString);
            }
            default -> {
            }
        }
    }

    public void changeSupportPriorityQueueByStarship(int starShipInt, String queueString) {
        switch (starShipInt) {
            case 0 -> {
                supportQueueStarwars.setText(queueString);
            }
            case 1 -> {
                supportQueueStartreck.setText(queueString);
            }
            default -> {
            }
        }
    }

    public void changeCharacterStatsByStarship(int starShipInt, Stats characterStats, String characterID) {
        switch (starShipInt) {
            case 0 -> {
                characterIDStarwar.setText(characterID);
                chracterHPStarwar.setText(Integer.toString(characterStats.getHP()));
                chracterSTStarwar.setText(Integer.toString(characterStats.getStrength()));
                chracterAGStarwar.setText(Integer.toString(characterStats.getAgility()));
                chracterSPStarwar.setText(Integer.toString(characterStats.getSpeed()));
                chracterMGStarwar.setText(Integer.toString(characterStats.getMagic()));
                chracterMPStarwar.setText(Integer.toString(characterStats.getManaPoints()));

            }
            case 1 -> {
                characterIDStatreck.setText(characterID);
                chracterHPStartreck.setText(Integer.toString(characterStats.getHP()));
                chracterSTStartreck.setText(Integer.toString(characterStats.getStrength()));
                chracterAGStartreck.setText(Integer.toString(characterStats.getAgility()));
                chracterSPStartreck.setText(Integer.toString(characterStats.getSpeed()));
                chracterMGStartreck.setText(Integer.toString(characterStats.getMagic()));
                chracterMPStartreck.setText(Integer.toString(characterStats.getManaPoints()));
            }
            default -> {
            }
        }
    }

    public void changeAIStatus(String status) {
        statusAI.setText(status);
    }

    public void changeCharacterImage(int starShipInt, String characterImagePath) {
        try {
            ImageIcon characterResizedImage = resizeIcon(new ImageIcon(characterImagePath),
                    getCharacterImageByStarship(starShipInt).getWidth(),
                    getCharacterImageByStarship(starShipInt).getHeight());

            getCharacterImageByStarship(starShipInt).setIcon(characterResizedImage);
        } catch (Exception e) {
        }

    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    private JLabel getCharacterImageByStarship(int starShipInt) {
        return switch (starShipInt) {
            case 0 ->
                characterImgStarwar;
            case 1 ->
                characterImgStartreck;
            default ->
                null;
        };
    }

    public void setStartreckWinner() {
        this.StartreckWinner.setText("Winner!");
    }

    public void setStarwarWinner() {
        this.StarwarWinner.setText("Winner!");
    }

    public void clearStarWarWinner() {
        this.StartreckWinner.setText("");
    }

    public void clearStarTrekWinner() {
        this.StarwarWinner.setText("");
    }

    public void changeResult(String result) {
        this.result.setText(result);
    }

    //Getters and Setters
    public ArtificialIntelligence getAI() {
        return AI;
    }

    public void setAI(ArtificialIntelligence AI) {
        this.AI = AI;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public StarshipSide getStarWar() {
        return starWar;
    }

    public void setStarWar(StarshipSide starWar) {
        this.starWar = starWar;
    }

    public StarshipSide getStarTrek() {
        return starTrek;
    }

    public void setStarTrek(StarshipSide starTrek) {
        this.starTrek = starTrek;
    }

    public JSpinner getUISpeedSpinner() {
        return this.speed;
    }

    public void changeRound(int roundInt) {
        this.round.setText(Integer.toString(roundInt));
    }

    public void changeBattleType(String battleType) {
        this.battleType.setText(battleType);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        generalPanel = new javax.swing.JPanel();
        StartreckPanel = new javax.swing.JPanel();
        StartreckPanel_title = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        chracterSPStartreck = new javax.swing.JLabel();
        chracterAGStartreck_Label = new javax.swing.JLabel();
        chracterMPStartreck_Label = new javax.swing.JLabel();
        characterIDStatreck = new javax.swing.JLabel();
        chracterMPStartreck = new javax.swing.JLabel();
        characterIDStarteck_Label = new javax.swing.JLabel();
        chracterSPStartreck_Label = new javax.swing.JLabel();
        chracterHPStartreck = new javax.swing.JLabel();
        chracterHPStartreck_Label = new javax.swing.JLabel();
        chracterMGStartreck = new javax.swing.JLabel();
        chracterSTStartreck = new javax.swing.JLabel();
        chracterSTStartreck_Label = new javax.swing.JLabel();
        characterImgStartreck = new javax.swing.JLabel();
        chracterAGStartreck = new javax.swing.JLabel();
        chracterMGStartreck_Label = new javax.swing.JLabel();
        StartreckWinner = new javax.swing.JLabel();
        secondQueueStartreck_label = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        secondQueueStartreck = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        topQueueStartreck = new javax.swing.JTextArea();
        topQueueStarttreck_label = new javax.swing.JLabel();
        supportQueueStartreck_label = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        thirdQueueStartreck = new javax.swing.JTextArea();
        thirdQueueStartreck_label = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        supportQueueStartreck = new javax.swing.JTextArea();
        winsStartreck_label = new javax.swing.JLabel();
        winsCounterStartreck = new javax.swing.JLabel();
        round = new javax.swing.JLabel();
        battleType = new javax.swing.JLabel();
        speed = new javax.swing.JSpinner();
        speed_Label = new javax.swing.JLabel();
        result = new javax.swing.JLabel();
        StarwarsPanel = new javax.swing.JPanel();
        secondQueueStarwars_label = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        characterIDStarwar_Label = new javax.swing.JLabel();
        characterIDStarwar = new javax.swing.JLabel();
        characterImgStarwar = new javax.swing.JLabel();
        chracterHPStarwar_Label = new javax.swing.JLabel();
        chracterHPStarwar = new javax.swing.JLabel();
        chracterAGStarwar = new javax.swing.JLabel();
        chracterAGStarwar_Label = new javax.swing.JLabel();
        chracterSTStarwar = new javax.swing.JLabel();
        chracterSTStarwar_Label = new javax.swing.JLabel();
        chracterSPStarwar = new javax.swing.JLabel();
        chracterSPStarwar_Label = new javax.swing.JLabel();
        chracterMGStarwar = new javax.swing.JLabel();
        chracterMGStarwar_Label = new javax.swing.JLabel();
        chracterMPStarwar_Label = new javax.swing.JLabel();
        chracterMPStarwar = new javax.swing.JLabel();
        StarwarWinner = new javax.swing.JLabel();
        StarwarsPanel_title = new javax.swing.JLabel();
        topQueueStarwars_label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        secondQueueStarwars = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        topQueueStarwars = new javax.swing.JTextArea();
        thirdQueueStarwars_label = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        thirdQueueStarwars = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        supportQueueStarwars = new javax.swing.JTextArea();
        supportQueueStarwars_label = new javax.swing.JLabel();
        winsStarwars_label = new javax.swing.JLabel();
        winsCounterStarwars = new javax.swing.JLabel();
        result_label = new javax.swing.JLabel();
        round_label = new javax.swing.JLabel();
        battleType_label = new javax.swing.JLabel();
        statusAI = new javax.swing.JLabel();
        AI_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        generalPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        StartreckPanel.setBackground(new java.awt.Color(255, 255, 255));
        StartreckPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        StartreckPanel_title.setBackground(new java.awt.Color(0, 0, 0));
        StartreckPanel_title.setFont(new java.awt.Font("Microsoft YaHei", 1, 36)); // NOI18N
        StartreckPanel_title.setForeground(new java.awt.Color(51, 51, 51));
        StartreckPanel_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/StartreckImages/startreck (1).png"))); // NOI18N
        StartreckPanel.add(StartreckPanel_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 280, 60));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chracterSPStartreck.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterSPStartreck.setForeground(new java.awt.Color(190, 190, 0));
        chracterSPStartreck.setText("0");
        jPanel2.add(chracterSPStartreck, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 54, -1));

        chracterAGStartreck_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterAGStartreck_Label.setForeground(new java.awt.Color(255, 153, 0));
        chracterAGStartreck_Label.setText("AG:");
        jPanel2.add(chracterAGStartreck_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 29, -1));

        chracterMPStartreck_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterMPStartreck_Label.setForeground(new java.awt.Color(51, 153, 255));
        chracterMPStartreck_Label.setText("MP:");
        jPanel2.add(chracterMPStartreck_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, -1, -1));

        characterIDStatreck.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        characterIDStatreck.setForeground(java.awt.Color.white);
        characterIDStatreck.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel2.add(characterIDStatreck, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 143, 20));

        chracterMPStartreck.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterMPStartreck.setForeground(new java.awt.Color(51, 153, 255));
        chracterMPStartreck.setText("0");
        jPanel2.add(chracterMPStartreck, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 54, -1));

        characterIDStarteck_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        characterIDStarteck_Label.setForeground(java.awt.Color.white);
        characterIDStarteck_Label.setText("ID:");
        jPanel2.add(characterIDStarteck_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 41, -1));

        chracterSPStartreck_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterSPStartreck_Label.setForeground(new java.awt.Color(190, 190, 0));
        chracterSPStartreck_Label.setText("SP:");
        jPanel2.add(chracterSPStartreck_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 29, -1));

        chracterHPStartreck.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterHPStartreck.setForeground(new java.awt.Color(153, 204, 0));
        chracterHPStartreck.setText("0");
        jPanel2.add(chracterHPStartreck, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 54, 20));

        chracterHPStartreck_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterHPStartreck_Label.setForeground(new java.awt.Color(153, 204, 0));
        chracterHPStartreck_Label.setText("HP:");
        jPanel2.add(chracterHPStartreck_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 29, 20));

        chracterMGStartreck.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterMGStartreck.setForeground(new java.awt.Color(204, 102, 255));
        chracterMGStartreck.setText("0");
        jPanel2.add(chracterMGStartreck, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 54, 20));

        chracterSTStartreck.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterSTStartreck.setForeground(new java.awt.Color(255, 51, 51));
        chracterSTStartreck.setText("0");
        jPanel2.add(chracterSTStartreck, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 54, 20));

        chracterSTStartreck_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterSTStartreck_Label.setForeground(new java.awt.Color(255, 51, 51));
        chracterSTStartreck_Label.setText("ST:");
        jPanel2.add(chracterSTStartreck_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 29, -1));

        characterImgStartreck.setForeground(java.awt.Color.white);
        characterImgStartreck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/StartreckImages/starteckLogo.png"))); // NOI18N
        characterImgStartreck.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel2.add(characterImgStartreck, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 200, 220));

        chracterAGStartreck.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterAGStartreck.setForeground(new java.awt.Color(255, 153, 0));
        chracterAGStartreck.setText("0");
        jPanel2.add(chracterAGStartreck, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 54, 20));

        chracterMGStartreck_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterMGStartreck_Label.setForeground(new java.awt.Color(204, 102, 255));
        chracterMGStartreck_Label.setText("MG:");
        jPanel2.add(chracterMGStartreck_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 35, 20));

        StartreckWinner.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        StartreckWinner.setForeground(new java.awt.Color(102, 204, 0));
        jPanel2.add(StartreckWinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 100, 30));

        StartreckPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 230, 400));

        secondQueueStartreck_label.setBackground(new java.awt.Color(0, 0, 0));
        secondQueueStartreck_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        secondQueueStartreck_label.setForeground(new java.awt.Color(51, 51, 51));
        secondQueueStartreck_label.setText("Second Priority:");
        StartreckPanel.add(secondQueueStartreck_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 310, 130, 30));

        secondQueueStartreck.setColumns(20);
        secondQueueStartreck.setRows(5);
        secondQueueStartreck.setBorder(null);
        jScrollPane6.setViewportView(secondQueueStartreck);

        StartreckPanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 260, 50));

        topQueueStartreck.setColumns(20);
        topQueueStartreck.setRows(5);
        topQueueStartreck.setBorder(null);
        jScrollPane5.setViewportView(topQueueStartreck);

        StartreckPanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 260, 50));

        topQueueStarttreck_label.setBackground(new java.awt.Color(0, 0, 0));
        topQueueStarttreck_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        topQueueStarttreck_label.setForeground(new java.awt.Color(51, 51, 51));
        topQueueStarttreck_label.setText("Top Priority:");
        StartreckPanel.add(topQueueStarttreck_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 110, 30));

        supportQueueStartreck_label.setBackground(new java.awt.Color(0, 0, 0));
        supportQueueStartreck_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        supportQueueStartreck_label.setForeground(new java.awt.Color(51, 51, 51));
        supportQueueStartreck_label.setText("Supports:");
        StartreckPanel.add(supportQueueStartreck_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 490, 80, 30));

        thirdQueueStartreck.setColumns(20);
        thirdQueueStartreck.setRows(5);
        thirdQueueStartreck.setBorder(null);
        jScrollPane7.setViewportView(thirdQueueStartreck);

        StartreckPanel.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 430, 260, 50));

        thirdQueueStartreck_label.setBackground(new java.awt.Color(0, 0, 0));
        thirdQueueStartreck_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        thirdQueueStartreck_label.setForeground(new java.awt.Color(51, 51, 51));
        thirdQueueStartreck_label.setText("Third Priority:");
        StartreckPanel.add(thirdQueueStartreck_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 400, 110, 30));

        supportQueueStartreck.setColumns(20);
        supportQueueStartreck.setRows(5);
        supportQueueStartreck.setBorder(null);
        jScrollPane8.setViewportView(supportQueueStartreck);

        StartreckPanel.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 520, 260, 50));

        winsStartreck_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        winsStartreck_label.setForeground(new java.awt.Color(51, 51, 51));
        winsStartreck_label.setText("Wins:");
        StartreckPanel.add(winsStartreck_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 70, 30));

        winsCounterStartreck.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        winsCounterStartreck.setForeground(new java.awt.Color(51, 51, 51));
        winsCounterStartreck.setText("0");
        StartreckPanel.add(winsCounterStartreck, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 70, 30));

        round.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        round.setForeground(new java.awt.Color(51, 51, 51));
        StartreckPanel.add(round, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 120, 30));

        battleType.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        battleType.setForeground(new java.awt.Color(51, 51, 51));
        StartreckPanel.add(battleType, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 190, 30));

        speed.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        speed.setModel(new javax.swing.SpinnerNumberModel(10, 1, 20, 1));
        speed.setToolTipText("");
        speed.setBorder(null);
        speed.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        speed.setName(""); // NOI18N
        StartreckPanel.add(speed, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 50, 30));

        speed_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        speed_Label.setForeground(new java.awt.Color(51, 51, 51));
        speed_Label.setText("Speed(s):");
        StartreckPanel.add(speed_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 130, 30));

        result.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        result.setForeground(new java.awt.Color(51, 51, 51));
        StartreckPanel.add(result, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 150, 30));

        generalPanel.add(StartreckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 590, 670));

        StarwarsPanel.setBackground(new java.awt.Color(153, 153, 153));
        StarwarsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        secondQueueStarwars_label.setBackground(new java.awt.Color(0, 0, 0));
        secondQueueStarwars_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        secondQueueStarwars_label.setForeground(new java.awt.Color(255, 255, 255));
        secondQueueStarwars_label.setText("Second Priority:");
        StarwarsPanel.add(secondQueueStarwars_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 110, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        characterIDStarwar_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        characterIDStarwar_Label.setForeground(new java.awt.Color(153, 153, 153));
        characterIDStarwar_Label.setText("ID:");
        jPanel1.add(characterIDStarwar_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 41, -1));

        characterIDStarwar.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        characterIDStarwar.setForeground(new java.awt.Color(153, 153, 153));
        characterIDStarwar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 0), 1, true));
        jPanel1.add(characterIDStarwar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 143, 23));

        characterImgStarwar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/StarWarsImages/starwar_logo.png"))); // NOI18N
        characterImgStarwar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 0), 1, true));
        jPanel1.add(characterImgStarwar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 200, 220));

        chracterHPStarwar_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterHPStarwar_Label.setForeground(new java.awt.Color(153, 204, 0));
        chracterHPStarwar_Label.setText("HP:");
        jPanel1.add(chracterHPStarwar_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 29, -1));

        chracterHPStarwar.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterHPStarwar.setForeground(new java.awt.Color(153, 204, 0));
        chracterHPStarwar.setText("0");
        jPanel1.add(chracterHPStarwar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 54, 20));

        chracterAGStarwar.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterAGStarwar.setForeground(new java.awt.Color(255, 153, 0));
        chracterAGStarwar.setText("0");
        jPanel1.add(chracterAGStarwar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 54, 20));

        chracterAGStarwar_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterAGStarwar_Label.setForeground(new java.awt.Color(255, 153, 0));
        chracterAGStarwar_Label.setText("AG:");
        jPanel1.add(chracterAGStarwar_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 29, -1));

        chracterSTStarwar.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterSTStarwar.setForeground(new java.awt.Color(255, 51, 51));
        chracterSTStarwar.setText("0");
        jPanel1.add(chracterSTStarwar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 54, -1));

        chracterSTStarwar_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterSTStarwar_Label.setForeground(new java.awt.Color(255, 51, 51));
        chracterSTStarwar_Label.setText("ST:");
        jPanel1.add(chracterSTStarwar_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 29, -1));

        chracterSPStarwar.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterSPStarwar.setForeground(new java.awt.Color(190, 190, 0));
        chracterSPStarwar.setText("0");
        jPanel1.add(chracterSPStarwar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 54, -1));

        chracterSPStarwar_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterSPStarwar_Label.setForeground(new java.awt.Color(190, 190, 0));
        chracterSPStarwar_Label.setText("SP:");
        jPanel1.add(chracterSPStarwar_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 29, -1));

        chracterMGStarwar.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterMGStarwar.setForeground(new java.awt.Color(204, 102, 255));
        chracterMGStarwar.setText("0");
        jPanel1.add(chracterMGStarwar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 54, -1));

        chracterMGStarwar_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterMGStarwar_Label.setForeground(new java.awt.Color(204, 102, 255));
        chracterMGStarwar_Label.setText("MG:");
        jPanel1.add(chracterMGStarwar_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 35, -1));

        chracterMPStarwar_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterMPStarwar_Label.setForeground(new java.awt.Color(51, 153, 255));
        chracterMPStarwar_Label.setText("MP:");
        jPanel1.add(chracterMPStarwar_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 29, -1));

        chracterMPStarwar.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        chracterMPStarwar.setForeground(new java.awt.Color(51, 153, 255));
        chracterMPStarwar.setText("0");
        jPanel1.add(chracterMPStarwar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 54, 20));

        StarwarWinner.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        StarwarWinner.setForeground(new java.awt.Color(102, 204, 0));
        jPanel1.add(StarwarWinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 130, 30));

        StarwarsPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 230, 400));

        StarwarsPanel_title.setBackground(new java.awt.Color(0, 0, 0));
        StarwarsPanel_title.setFont(new java.awt.Font("Microsoft YaHei", 1, 36)); // NOI18N
        StarwarsPanel_title.setForeground(new java.awt.Color(255, 255, 255));
        StarwarsPanel_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/StarWarsImages/logo-starwars.png"))); // NOI18N
        StarwarsPanel.add(StarwarsPanel_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 230, 110));

        topQueueStarwars_label.setBackground(new java.awt.Color(0, 0, 0));
        topQueueStarwars_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        topQueueStarwars_label.setForeground(new java.awt.Color(255, 255, 255));
        topQueueStarwars_label.setText("Top Priority:");
        StarwarsPanel.add(topQueueStarwars_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 110, 30));

        secondQueueStarwars.setColumns(20);
        secondQueueStarwars.setRows(5);
        secondQueueStarwars.setBorder(null);
        jScrollPane1.setViewportView(secondQueueStarwars);

        StarwarsPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 260, 50));

        topQueueStarwars.setColumns(20);
        topQueueStarwars.setRows(5);
        topQueueStarwars.setBorder(null);
        jScrollPane2.setViewportView(topQueueStarwars);

        StarwarsPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 260, 50));

        thirdQueueStarwars_label.setBackground(new java.awt.Color(0, 0, 0));
        thirdQueueStarwars_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        thirdQueueStarwars_label.setForeground(new java.awt.Color(255, 255, 255));
        thirdQueueStarwars_label.setText("Third Priority:");
        StarwarsPanel.add(thirdQueueStarwars_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 110, 30));

        thirdQueueStarwars.setColumns(20);
        thirdQueueStarwars.setRows(5);
        thirdQueueStarwars.setBorder(null);
        jScrollPane3.setViewportView(thirdQueueStarwars);

        StarwarsPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 260, 50));

        supportQueueStarwars.setColumns(20);
        supportQueueStarwars.setRows(5);
        supportQueueStarwars.setBorder(null);
        jScrollPane4.setViewportView(supportQueueStarwars);

        StarwarsPanel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 260, 50));

        supportQueueStarwars_label.setBackground(new java.awt.Color(0, 0, 0));
        supportQueueStarwars_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        supportQueueStarwars_label.setForeground(new java.awt.Color(255, 255, 255));
        supportQueueStarwars_label.setText("Supports:");
        StarwarsPanel.add(supportQueueStarwars_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 110, 30));

        winsStarwars_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        winsStarwars_label.setForeground(new java.awt.Color(51, 51, 51));
        winsStarwars_label.setText("Wins:");
        StarwarsPanel.add(winsStarwars_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 70, 30));

        winsCounterStarwars.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        winsCounterStarwars.setForeground(new java.awt.Color(51, 51, 51));
        winsCounterStarwars.setText("0");
        StarwarsPanel.add(winsCounterStarwars, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 70, 30));

        result_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        result_label.setForeground(new java.awt.Color(51, 51, 51));
        result_label.setText("Result:");
        StarwarsPanel.add(result_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 90, 30));

        round_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        round_label.setForeground(new java.awt.Color(51, 51, 51));
        round_label.setText("Round:");
        StarwarsPanel.add(round_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 90, 30));

        battleType_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        battleType_label.setForeground(new java.awt.Color(51, 51, 51));
        battleType_label.setText("Battle Type:");
        StarwarsPanel.add(battleType_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 150, 30));

        statusAI.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        statusAI.setForeground(new java.awt.Color(51, 51, 51));
        statusAI.setText("Waiting");
        StarwarsPanel.add(statusAI, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 190, 30));

        AI_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        AI_label.setForeground(new java.awt.Color(51, 51, 51));
        AI_label.setText("AI:");
        StarwarsPanel.add(AI_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 40, 30));

        generalPanel.add(StarwarsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 670));

        getContentPane().add(generalPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AI_label;
    private javax.swing.JPanel StartreckPanel;
    private javax.swing.JLabel StartreckPanel_title;
    private javax.swing.JLabel StartreckWinner;
    private javax.swing.JLabel StarwarWinner;
    private javax.swing.JPanel StarwarsPanel;
    private javax.swing.JLabel StarwarsPanel_title;
    private javax.swing.JLabel battleType;
    private javax.swing.JLabel battleType_label;
    private javax.swing.JLabel characterIDStarteck_Label;
    private javax.swing.JLabel characterIDStarwar;
    private javax.swing.JLabel characterIDStarwar_Label;
    private javax.swing.JLabel characterIDStatreck;
    private javax.swing.JLabel characterImgStartreck;
    private javax.swing.JLabel characterImgStarwar;
    private javax.swing.JLabel chracterAGStartreck;
    private javax.swing.JLabel chracterAGStartreck_Label;
    private javax.swing.JLabel chracterAGStarwar;
    private javax.swing.JLabel chracterAGStarwar_Label;
    private javax.swing.JLabel chracterHPStartreck;
    private javax.swing.JLabel chracterHPStartreck_Label;
    private javax.swing.JLabel chracterHPStarwar;
    private javax.swing.JLabel chracterHPStarwar_Label;
    private javax.swing.JLabel chracterMGStartreck;
    private javax.swing.JLabel chracterMGStartreck_Label;
    private javax.swing.JLabel chracterMGStarwar;
    private javax.swing.JLabel chracterMGStarwar_Label;
    private javax.swing.JLabel chracterMPStartreck;
    private javax.swing.JLabel chracterMPStartreck_Label;
    private javax.swing.JLabel chracterMPStarwar;
    private javax.swing.JLabel chracterMPStarwar_Label;
    private javax.swing.JLabel chracterSPStartreck;
    private javax.swing.JLabel chracterSPStartreck_Label;
    private javax.swing.JLabel chracterSPStarwar;
    private javax.swing.JLabel chracterSPStarwar_Label;
    private javax.swing.JLabel chracterSTStartreck;
    private javax.swing.JLabel chracterSTStartreck_Label;
    private javax.swing.JLabel chracterSTStarwar;
    private javax.swing.JLabel chracterSTStarwar_Label;
    private javax.swing.JPanel generalPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel result;
    private javax.swing.JLabel result_label;
    private javax.swing.JLabel round;
    private javax.swing.JLabel round_label;
    private javax.swing.JTextArea secondQueueStartreck;
    private javax.swing.JLabel secondQueueStartreck_label;
    private javax.swing.JTextArea secondQueueStarwars;
    private javax.swing.JLabel secondQueueStarwars_label;
    private javax.swing.JSpinner speed;
    private javax.swing.JLabel speed_Label;
    private javax.swing.JLabel statusAI;
    private javax.swing.JTextArea supportQueueStartreck;
    private javax.swing.JLabel supportQueueStartreck_label;
    private javax.swing.JTextArea supportQueueStarwars;
    private javax.swing.JLabel supportQueueStarwars_label;
    private javax.swing.JTextArea thirdQueueStartreck;
    private javax.swing.JLabel thirdQueueStartreck_label;
    private javax.swing.JTextArea thirdQueueStarwars;
    private javax.swing.JLabel thirdQueueStarwars_label;
    private javax.swing.JTextArea topQueueStartreck;
    private javax.swing.JLabel topQueueStarttreck_label;
    private javax.swing.JTextArea topQueueStarwars;
    private javax.swing.JLabel topQueueStarwars_label;
    private javax.swing.JLabel winsCounterStartreck;
    private javax.swing.JLabel winsCounterStarwars;
    private javax.swing.JLabel winsStartreck_label;
    private javax.swing.JLabel winsStarwars_label;
    // End of variables declaration//GEN-END:variables
}
