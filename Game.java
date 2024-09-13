
/**
 * Write a description of class Game here.
 * 
 * @author Sydney Tsin
 * @version (a version number or a date)
 */
import java.awt.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Math.*;
import java.util.*;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JOptionPane;
public class Game extends JApplet implements KeyListener
{
    int x = 0;
    int level = 1;
    int spaceCounter = 0;
    boolean s = true;
    Font title = new Font("Elephant",Font.BOLD,40); 
    Font instructions = new Font("Courier",Font.BOLD, 25);
    Font HPbar = new Font("Dialog", Font.PLAIN, 20);
    Player p = new Player();

    boolean wizardSpawn = false;
    boolean m1Spawn = false;
    boolean m2Spawn = false;
    boolean m3Spawn = false;
    boolean mBossSpawn = false;
    boolean battleStart = false;
    boolean battleEnd = false;
    boolean levelUp = false;
    boolean forefit = false;
    boolean switchWizy = false;
    boolean bossBattle = false;
    boolean allDefeated = false;
    boolean useSS = false;
    boolean lvlOneVulKnown = false;
    boolean lvlOneResKnown = false;
    boolean lvlTwoVulKnown = false;
    boolean lvlTwoResKnown = false;

    Wizard w1;
    Wizard w2;
    Wizard w3;
    Wizard w4;

    Battle b = new Battle(p, level);
    int n = b.deployMonsters();
    Wizard activeW = p.wiz.get(0);
    Monster targetM = b.m123[0];
    Monster activeM = b.m123[0];
    Monster secondM = b.m123[1];
    Monster thirdM = b.m123[2];
    Boss boss = new Boss();

    //setting up images for wizards and monsters
    Image ww1;
    Image ww2;
    Image ww3;
    Image ww4;
    Image activeWizy;
    Image mm1;
    Image mm2;
    Image mm3;
    Image mmBoss;
    Image activeMon1;
    Image activeMon2;
    Image activeMon3;

    //image for background
    Image bg;

    AudioClip a;
    AudioClip endMusic;
    AudioClip victory;
    javax.swing.Timer r;
    double t;
    double T;
    int move;

    public void init()
    {
        addKeyListener(this);
        setFocusable(true);

        ww1 = getImage(getCodeBase(), "fireW.png");
        ww2 = getImage(getCodeBase(), "iceW.png");
        ww3 = getImage(getCodeBase(), "necroW.png");
        ww4 = getImage(getCodeBase(), "radW.png");
        mm1 = getImage(getCodeBase(), "lvl1.png");
        mm2 = getImage(getCodeBase(), "lvl2.png");
        mm3 = getImage(getCodeBase(), "lvl3.png");
        mmBoss = getImage(getCodeBase(), "boss.png");
        bg = getImage(getCodeBase(), "background.jpg");
        a = getAudioClip(getCodeBase(), "battleMusic.mid");
        endMusic = getAudioClip(getCodeBase(), "gameOver.mid");
        victory = getAudioClip(getCodeBase(), "victory.mid");

    }

    public void background(Graphics g){
        //background image
        g.drawImage(bg,0,0,1800,1000, this); 

        //drawing active wizard
        if(wizardSpawn && !bossBattle){
            g.drawImage(activeWizy,50,240,300,300, this); 
            g.setFont(HPbar);
            g.setColor(Color.black);
            g.drawString("Lvl " + activeW.getLevel(), 165, 230);
            if(activeW.getHP() >= 0)
                g.drawString(activeW.getHP() + "", 60, 555);
            else{
                g.drawString("0", 60, 555);
            }
            g.setColor(Color.red);
            int HPlength = activeW.getHP() * 5;
            g.fillRect(90, 540, HPlength, 10);
        }

        //settting up all characters for boss battle
        if(bossBattle){
            g.setColor(Color.black);
            g.setFont(instructions);
            g.drawString("Boss Battle", 70, 75);
            g.setFont(HPbar);
            g.drawString("Current Wizard Attacking: " + activeW.getElement() + " Wizard", 600, 75);

            //drawing all four wizards on the field
            g.drawImage(ww1,50,335,200,200, this); 
            g.setFont(HPbar);
            g.setColor(Color.black);
            g.drawString("Lvl " + w1.getLevel(), 110, 325);
            if(activeW.getHP() >= 0)
                g.drawString(w1.getHP() + "", 60, 540);
            else{
                g.drawString("0", 60, 540);
            }
            g.setColor(Color.red);
            int W1HPlength = w1.getHP() * 2;
            g.fillRect(90, 525, W1HPlength, 10);

            g.drawImage(ww2,250,335,200,200, this); 
            g.setFont(HPbar);
            g.setColor(Color.black);
            g.drawString("Lvl " + w2.getLevel(), 310, 325);
            if(activeW.getHP() >= 0)
                g.drawString(w2.getHP() + "", 260, 540);
            else{
                g.drawString("0", 260, 540);
            }
            g.setColor(Color.red);
            int W2HPlength = w2.getHP() * 2;
            g.fillRect(290, 525, W2HPlength, 10);

            g.drawImage(ww3,450,335,200,200, this); 
            g.setFont(HPbar);
            g.setColor(Color.black);
            g.drawString("Lvl " + w3.getLevel(), 510, 325);
            if(w3.getHP() >= 0)
                g.drawString(w3.getHP() + "", 460, 540);
            else{
                g.drawString("0", 460, 540);
            }
            g.setColor(Color.red);
            int W3HPlength = w3.getHP() * 2;
            g.fillRect(490, 525, W3HPlength, 10);

            g.drawImage(ww4,650,335,200,200, this); 
            g.setFont(HPbar);
            g.setColor(Color.black);
            g.drawString("Lvl " + w4.getLevel(), 710, 325);
            if(w4.getHP() >= 0)
                g.drawString(w4.getHP() + "", 660, 540);
            else{
                g.drawString("0", 660, 540);
            }
            g.setColor(Color.red);
            int W4HPlength = w4.getHP() * 2;
            g.fillRect(690, 525, W4HPlength, 10);

            //drawing the boss
            g.drawImage(mmBoss, 1000, 150, 640, 490, this);
            g.setFont(HPbar);
            g.setColor(Color.black);
            if(boss.getHP() >= 0)
                g.drawString(boss.getHP() + "", 1575, 555);
            else{
                g.drawString("0", 1575, 555);
            }
            g.setColor(Color.red);
            int BossHPlength = boss.getHP() * 2;
            g.fillRect(1605, 540, BossHPlength, 10);

        }

        //drawing monsters
        if(m1Spawn && level <= 9){
            g.drawImage(activeMon1, 1100, 350 ,200,200, this);
            g.setFont(HPbar);
            g.setColor(Color.red);
            g.drawString("Lvl " + activeM.getLevel(), 1180, 350);
            if(activeM.getHP() >= 0){
                g.setColor(Color.black);
                g.drawString(activeM.getHP() + "", 1100, 545);
            }
            else{
                g.setColor(Color.black);
                g.drawString("0", 1100, 545);
            }
            g.setColor(Color.red);
            int MHPlength = activeM.getHP() * 5;
            g.fillRect(1145, 530, MHPlength, 10);
        }
        if(m2Spawn && (level == 2 || level == 3 || level == 5 || level == 6 || level == 8 || level == 9)){
            g.drawImage(activeMon2, 1300, 350 ,200,200, this);
            g.setFont(HPbar);
            g.setColor(Color.black);
            g.drawString("Lvl " + secondM.getLevel(), 1380, 350);
            if(secondM.getHP() >= 0)
                g.drawString(secondM.getHP() + "", 1300, 545);
            else{
                g.drawString("0", 1300, 545);
            }
            g.setColor(Color.red);
            int MHPlength = secondM.getHP() * 5;
            g.fillRect(1345, 530, MHPlength, 10);
        }
        if(m3Spawn && (level == 3 || level == 6 || level == 9)){
            g.drawImage(activeMon3, 1500, 350 ,200,200, this);
            g.setFont(HPbar);
            g.setColor(Color.black);
            g.drawString("Lvl " + thirdM.getLevel(), 1580, 350);
            if(thirdM.getHP() >= 0)
                g.drawString(thirdM.getHP() + "", 1500, 545);
            else{
                g.drawString("0", 1500, 545);
            }
            g.setColor(Color.red);
            int MHPlength = thirdM.getHP() * 5;
            g.fillRect(1545, 530, MHPlength, 10);
        }

        //drawing level number at top
        if(battleStart && level < 10){
            g.setColor(Color.black);
            g.setFont(instructions);
            g.drawString("Level " + level, 70, 75);
        }
    }

    public void paint(Graphics g)
    {
        background(g);

        if(s == true){
            start(g, spaceCounter);
        }
        if(s == false){
            if(forefit){
                quitGame(g);
            }else{
                levels(g,x);

            }

        }
    }

    public void keyPressed(KeyEvent e)
    {
        x = e.getKeyCode();
        if(x == 32){
            spaceCounter++;
            if(switchWizy){
                spaceCounter = 2;

            }else if(battleStart){
                spaceCounter = 1;
                if(levelUp){
                    spaceCounter = 1;
                    levelUp = false;
                }
            }

            if(spaceCounter == 4){
                s = false;
                spaceCounter = 0;
            }
            if(forefit){
                spaceCounter = 0;
                spaceCounter++;
            }

        }
        if(x == 56){
            forefit = true;
        }

        if(switchWizy == true){
            if(x == 70){
                activeW = w1;
                activeWizy = ww1;
                switchWizy = false;
            } else if(x == 73){
                activeW = w2;
                activeWizy = ww2;
                switchWizy = false;
            }else if(x == 78){
                activeW = w3;
                activeWizy = ww3;
                switchWizy = false;
            }else if(x == 82){
                activeW = w4;
                activeWizy = ww4;
                switchWizy = false;
            }
        }

        if(useSS == true){
            if(x == 70){
                activeM.setVul("Fire");
            } else if(x == 73){
                activeM.setVul("Ice");
            }else if(x == 78){
                activeM.setVul("Necrotic");
            }else if(x == 82){
                activeM.setVul("Radiant");
            }
        }

        repaint();
    }

    public void start(Graphics g, int space){
        g.setColor(Color.white);
        g.setFont(title);
        if(spaceCounter == 0)
            JOptionPane.showMessageDialog(null, "Welcome to Wizard Battle!");
        g.drawString("Welcome to Wizard Battle! [space to continue]", 100, 100);

        if(spaceCounter == 1){
            a.loop();
            g.setColor(Color.black);
            g.drawString("Here are the rules:", 100, 150);
            g.setFont(instructions);
            g.drawString("You will unlock a wizard on levels 1, 2, 3, and 4.", 100, 175);
            g.drawString("You will be able to deploy them to fight monsters.", 100, 200);
            g.drawString("You can only deploy one Wizard at a time.", 100, 225);
            g.drawString("You can move on to the next level once all monsters are defeated.", 100, 250);
            g.drawString("If your active Wizard dies you can switch to a living Wizard.", 100, 275);
            g.drawString("If all Wizards die, the game ends and you lose.", 100, 300);
            g.drawString("[space to continue]", 100, 400);
        }
        if(spaceCounter == 2){
            background(g);
            g.setColor(Color.black);
            g.setFont(instructions);
            g.drawString("Now here is your first Wizard, its element type is Fire [space to continue]", 100,100);
            //spawn wizard here
            activeWizy = ww1;
            g.drawImage(activeWizy,50,240,300,300, this); 
            wizardSpawn = true;
        }
        if(spaceCounter == 3){
            background(g);
            g.setColor(Color.black);
            g.setFont(instructions);
            g.drawString("Monsters have vulnerabilties and resistances,", 100, 100);
            g.drawString("Once you discover a resistance or vulnerability of a certain level Monster, you will remember it.", 100,125);
            g.drawString("Monsters vulnerable to a certain type will take double damage to that", 100, 150);
            g.drawString("element and monsters resistant to a certain type will take half damage.", 100, 175);
            g.drawString("So be careful which Wizard you are attacking with. [space to continue]", 100, 200);
            activeMon1 = mm1;
            g.drawImage(activeMon1, 1100, 350 ,200,200, this);
            m1Spawn = true;
        }
        if(spaceCounter == 4){
            background(g);

            s = false;
            spaceCounter = 0;
        }

    }

    public void levels(Graphics g, int key){
        if(level <= 4){
            p.setLevel(level);
            switch(p.getLevel()){
                case 1:
                w1 = p.wiz.get(0);
                break;
                case 2:
                w2 = p.wiz.get(1);
                break;
                case 3:
                w3 = p.wiz.get(2);
                break;
                case 4:
                w4 = p.wiz.get(3);
                break;
            }
        }
        if(level == 10){
            if(spaceCounter == 0){
                g.setColor(Color.white);
                g.setFont(title);
                g.drawString("Start Boss Battle!", 755, 200);
                battleStart = true;
                JOptionPane.showMessageDialog(null, "Press space to continue");
            }
            if(spaceCounter == 1){
                background(g);
                g.setFont(instructions);
                g.setColor(Color.decode("#7c2310"));
                if(useSS == false){
                    g.drawString("0 - Attack",100, 750);
                    g.drawString("1 - Skip a turn",100, 775);
                    g.drawString("2 - Forefit",100, 800);
                    g.drawString("3 - Use Special Skill",100, 825);
                }else{
                    g.drawString("0 - Attack",100, 750);
                    g.drawString("1 - Skip a turn",100, 775);
                    g.drawString("2 - Forefit",100, 800);
                }

                switch(key){
                    case 48: background(g);
                    int wAttacks = p.wiz.size();
                    int wDamage = 0;
                    int newBossHP = 0;
                    for(int i = 0; i < wAttacks ; i++){
                        g.setColor(Color.black);
                        g.setFont(instructions);
                        wDamage = activeW.attack();
                        g.drawString(wDamage + " " + activeW.getElement() + " damage", 750, 200);
                        newBossHP = targetM.getHP() - wDamage;
                        targetM.setHP(newBossHP); 
                        JOptionPane.showMessageDialog(null, "Press space to continue");
                        background(g);
                        if(activeW.equals(w1)){
                            activeW = w2;
                        }else if(activeW.equals(w2)){
                            activeW = w3;
                        }else if(activeW.equals(w3)){
                            activeW = w4;
                        }else{
                            i = 5;
                        }
                        if(targetM.getHP() <= 0){
                            battleStart = false;
                            battleEnd = true;
                            background(g);
                            targetM.setAlive(false);
                            quitGame(g);
                        }
                    }
                    if(activeW.equals(w4)){
                        activeW = w1;
                        monsterAttack(g);
                    }
                    spaceCounter = 1;
                    break;
                    case 49: background(g);
                    monsterAttack(g);
                    spaceCounter = 0;
                    break;
                    case 50: background(g);
                    g.setFont(title);
                    g.setColor(Color.black);
                    g.drawString("Boo! You forefit.", 700, 200);
                    JOptionPane.showMessageDialog(null, "Press space to continue");
                    if(spaceCounter == 1){
                        forefit = true;
                        quitGame(g);
                    }
                    break;
                    case 51: background(g);
                    useSS = true;
                    g.setFont(instructions);
                    g.setColor(Color.black);
                    g.drawString("Choose one element for the Boss to be Vulnerable to", 350, 200);
                    g.setColor(Color.decode("#7c2310"));
                    if(w1.isAlive())
                        g.drawString("F - Fire Wizard",100, 750);
                    if(w2.isAlive())
                        g.drawString("I - Ice Wizard",100, 775);
                    if(w3.isAlive())
                        g.drawString("N - Necromancy Wizard",100, 800);
                    if(w4.isAlive())
                        g.drawString("R - Radiant Wizard",100, 825);
                    spaceCounter = 1;
                    break;
                }
            }

        }else if(level > 0 && level < 10 ){

            if(spaceCounter == 0 && switchWizy == false){
                g.setColor(Color.white);
                g.setFont(title);
                g.drawString("Start Battle!", 755, 100);
                g.drawString("Level " + level, 805, 200);
                if(level == 2){
                    g.setFont(instructions);
                    g.drawString("Unlocked: Ice Wizard", 745, 300);
                    w1.reset();
                    w2.reset();
                }else if(level == 3){
                    g.setFont(instructions);
                    g.drawString("Unlocked: Necrotic Wizard", 745, 300);
                    w1.reset();
                    w2.reset();
                    w3.reset();
                }else if(level == 4){
                    g.setFont(instructions);
                    g.drawString("Unlocked: Radiant Wizard", 745, 300);
                    w1.reset();
                    w2.reset();
                    w3.reset();
                    w4.reset();
                }

                battleStart = true;
                spaceCounter = 1;
                JOptionPane.showMessageDialog(null, "Press space to continue");

                String v = "You have not revealed this Monster's Vulnerability yet.";
                String r = "You have not revealed this Monster's Resistance yet.";
                String v2 = "You have not revealed this Monster's Vulnerability yet.";
                String r2 = "You have not revealed this Monster's Resistance yet.";
                if(level == 1 || level == 2 || level == 3 || level == 4 || level == 7){
                    if(targetM.getVulKnown()){
                        v = "Vulnerability of Level " + targetM.getLevel() + " Monster: " + targetM.getVul();
                    }
                    if(targetM.getResKnown()){
                        r = "Resistance of Level " + targetM.getLevel() + " Monster: " + targetM.getRes();
                    }
                    JOptionPane.showMessageDialog(null, v + "\n" + r);
                }else{
                    if(targetM.getVulKnown()){
                        v = "Vulnerability of Level " + targetM.getLevel() + " Monster: " + targetM.getVul();
                    }
                    if(targetM.getResKnown()){
                        r = "Resistance of Level " + targetM.getLevel() + " Monster: " + targetM.getRes();
                    }
                    if(level == 5 || level == 6){
                        if(lvlOneVulKnown){
                            v2 = "Vulnerability of Level " + secondM.getLevel() + " Monster: " + secondM.getVul();
                        }
                        if(lvlOneResKnown){
                            r2 = "Resistance of Level " + secondM.getLevel() + " Monster: " + secondM.getRes();
                        }
                    }
                    if(level == 8 || level == 9){
                        if(lvlTwoVulKnown){
                            v2 = "Vulnerability of Level " + secondM.getLevel() + " Monster: " + secondM.getVul();
                        }
                        if(lvlTwoResKnown){
                            r2 = "Resistance of Level " + secondM.getLevel() + " Monster: " + secondM.getRes();
                        }
                    }

                    JOptionPane.showMessageDialog(null, v + "\n" + r + "\n" + v2 + "\n" + r2);
                }

                spaceCounter = 1;

            }
            if(spaceCounter == 1){
                background(g);
                g.setFont(instructions);
                g.setColor(Color.decode("#7c2310"));
                g.drawString("0 - Attack",100, 750);
                g.drawString("1 - Swap Wizard",100, 775);
                g.drawString("2 - Check vulnerability",100, 800);
                g.drawString("3 - Check resistance",100, 825);
                g.drawString("4 - Skip a turn",100, 850);
                g.drawString("5 - Forfeit",100, 875);
                spaceCounter = 1;
                switch(key){
                    case 48: background(g);
                    g.setFont(instructions);
                    g.setColor(Color.black);
                    int damage = activeW.attack();
                    if(targetM.getVul() == activeW.getElement()){
                        damage *= 2;
                    }else if(targetM.getRes() == activeW.getElement()){
                        damage /= 2;
                        if(damage == 0){
                            damage = 1;
                        }
                    }else{
                        damage = damage;
                    }
                    g.drawString(damage + " " + activeW.getElement() + " damage", 1000, 200);
                    int newMHP = targetM.getHP() - damage;
                    targetM.setHP(newMHP);
                    JOptionPane.showMessageDialog(null, "Press space to continue");
                    if(targetM.getHP() <= 0){
                        targetM.setAlive(false);
                        levelUp = activeW.addDefeated(targetM.getLevel());
                        if(levelUp){
                            if(spaceCounter == 1){
                                if(activeW.getLevel() < 5){
                                    background(g);
                                    g.setColor(Color.black);
                                    g.setFont(instructions);
                                    g.drawString("Your " + activeW.getElement() + " Wizard leveled up to level " + activeW.getLevel(), 550, 200);
                                    levelUp = false;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Press space to continue");
                        }

                        switch(level){
                            case 1: allDefeated = true;
                            break;
                            case 2: 
                            if(secondM.getHP() > 0){
                                targetM = secondM;
                                allDefeated = false;
                            }else{
                                allDefeated = true;
                            }
                            break;
                            case 3:
                            if(secondM.getHP() > 0){
                                targetM = secondM;
                                allDefeated = false;
                            }else if(thirdM.getHP() > 0){
                                targetM = thirdM;
                                allDefeated = false;
                            }
                            else{
                                allDefeated = true;
                            }
                            break;
                            case 4: allDefeated = true;
                            break;
                            case 5: 
                            if(secondM.getHP() > 0){
                                targetM = secondM;
                                allDefeated = false;
                            }else{
                                allDefeated = true;
                            }
                            break;
                            case 6:
                            if(secondM.getHP() > 0){
                                targetM = secondM;
                                allDefeated = false;
                            }else if(thirdM.getHP() > 0){
                                targetM = thirdM;
                                allDefeated = false;
                            }
                            else{
                                allDefeated = true;
                            }
                            break;
                            case 7: allDefeated = true;
                            break;
                            case 8: 
                            if(secondM.getHP() > 0){
                                targetM = secondM;
                                allDefeated = false;
                            }else{
                                allDefeated = true;
                            }
                            break;
                            case 9:
                            if(secondM.getHP() > 0){
                                targetM = secondM;
                                allDefeated = false;
                            }else if(thirdM.getHP() > 0){
                                targetM = thirdM;
                                allDefeated = false;
                            }
                            else{
                                allDefeated = true;
                            }
                            break;

                        }

                        if(allDefeated == true){
                            battleStart = false;
                            battleEnd = true;
                            background(g);
                            if(spaceCounter == 1){
                                background(g);
                                g.setFont(title);
                                g.setColor(Color.black);
                                g.drawString("Congratulations on beating these difficult enemies!", 400, 200);
                                g.drawString("On to the next battle!", 560, 300);
                                level++;
                                p.setLevel(level);                                
                                p.addWizard(level);
                                battleStart = false;
                                spaceCounter = -1;
                                if(level == 2){
                                    activeM = b.m123[0];
                                    secondM = b.m123[1];
                                    thirdM = b.m123[2];
                                    targetM = activeM;

                                    activeM.reset();
                                    secondM.reset();
                                    thirdM.reset();
                                    w1.reset();

                                    activeMon1 = mm1;
                                    activeMon2 = mm1;
                                    activeMon3 = mm1;

                                    m1Spawn = true;
                                    m2Spawn = true;
                                    m3Spawn = false;
                                }else if(level == 3){
                                    activeM = b.m123[0];
                                    secondM = b.m123[1];
                                    thirdM = b.m123[2];
                                    targetM = activeM;

                                    activeM.reset();
                                    secondM.reset();
                                    thirdM.reset();
                                    w1.reset();
                                    w2.reset();

                                    activeMon1 = mm1;
                                    activeMon2 = mm1;
                                    activeMon3 = mm1;

                                    m1Spawn = true;
                                    m2Spawn = true;
                                    m3Spawn = true;
                                }else if(level == 4){
                                    activeM = b.m456[0];
                                    secondM = b.m456[1];
                                    thirdM = b.m456[2];
                                    targetM = activeM;

                                    activeM.reset();
                                    secondM.reset();
                                    thirdM.reset();
                                    w1.reset();
                                    w2.reset();
                                    w3.reset();

                                    activeMon1 = mm2;
                                    activeMon2 = mm1;
                                    activeMon3 = mm2;

                                    m1Spawn = true;
                                    m2Spawn = false;
                                    m3Spawn = false;
                                }else if(level == 5){
                                    activeM = b.m456[0];
                                    secondM = b.m456[1];
                                    thirdM = b.m456[2];
                                    targetM = activeM;

                                    activeM.reset();
                                    secondM.reset();
                                    thirdM.reset();
                                    w1.reset();
                                    w2.reset();
                                    w3.reset();
                                    w4.reset();

                                    activeMon1 = mm2;
                                    activeMon2 = mm1;
                                    activeMon3 = mm2;

                                    m1Spawn = true;
                                    m2Spawn = true;
                                    m3Spawn = false;
                                }else if(level == 6){
                                    activeM = b.m456[0];
                                    secondM = b.m456[1];
                                    thirdM = b.m456[2];
                                    targetM = activeM;

                                    activeM.reset();
                                    secondM.reset();
                                    thirdM.reset();
                                    w1.reset();
                                    w2.reset();
                                    w3.reset();
                                    w4.reset();

                                    activeMon1 = mm2;
                                    activeMon2 = mm1;
                                    activeMon3 = mm2;

                                    m1Spawn = true;
                                    m2Spawn = true;
                                    m3Spawn = true;
                                }else if(level == 7){
                                    activeM = b.m789[0];
                                    secondM = b.m789[1];
                                    thirdM = b.m789[2];
                                    targetM = activeM;

                                    activeM.reset();
                                    secondM.reset();
                                    thirdM.reset();
                                    w1.reset();
                                    w2.reset();
                                    w3.reset();
                                    w4.reset();

                                    activeMon1 = mm3;
                                    activeMon2 = mm2;
                                    activeMon3 = mm3;

                                    m1Spawn = true;
                                    m2Spawn = false;
                                    m3Spawn = false;
                                }else if(level == 8){
                                    activeM = b.m789[0];
                                    secondM = b.m789[1];
                                    thirdM = b.m789[2];
                                    targetM = activeM;

                                    activeM.reset();
                                    secondM.reset();
                                    thirdM.reset();
                                    w1.reset();
                                    w2.reset();
                                    w3.reset();
                                    w4.reset();

                                    activeMon1 = mm3;
                                    activeMon2 = mm2;
                                    activeMon3 = mm3;

                                    m1Spawn = true;
                                    m2Spawn = true;
                                    m3Spawn = false;
                                }else if(level == 9){
                                    activeM = b.m789[0];
                                    secondM = b.m789[1];
                                    thirdM = b.m789[2];
                                    targetM = activeM;

                                    activeM.reset();
                                    secondM.reset();
                                    thirdM.reset();
                                    w1.reset();
                                    w2.reset();
                                    w3.reset();
                                    w4.reset();

                                    activeMon1 = mm3;
                                    activeMon2 = mm2;
                                    activeMon3 = mm3;

                                    m1Spawn = true;
                                    m2Spawn = true;
                                    m3Spawn = true;
                                }else if(level == 10){
                                    w1.reset();
                                    w2.reset();
                                    w3.reset();
                                    w4.reset();
                                    activeM = boss;
                                    targetM = boss;
                                    activeM.setDeployed(true);
                                    activeM.setAlive(true);
                                    activeM.reset();
                                    activeMon1 = mmBoss;
                                    activeW = w1;
                                    bossBattle = true;
                                    spaceCounter = -1;
                                }
                                allDefeated = false;
                            }
                            JOptionPane.showMessageDialog(null, "Press space to continue");
                        }
                    }else{
                        monsterAttack(g);
                    }
                    break;
                    case 49: background(g);
                    spaceCounter = 0;
                    if(p.wiz.size() <= 1){
                        JOptionPane.showMessageDialog(null,"You do not have any other Wizards to switch to");
                        spaceCounter = 1;
                        switchWizy = false;
                    }else{
                        background(g);
                        switchWizy = true;
                        g.setFont(instructions);
                        g.setColor(Color.decode("#7c2310"));
                        int wsize = p.wiz.size();
                        switch(wsize){
                            case 2:
                            g.drawString("F - Fire Wizard",100, 750);
                            g.drawString("I - Ice Wizard",100, 775);
                            spaceCounter = 1;
                            break;
                            case 3:
                            if(w1.isAlive())
                                g.drawString("F - Fire Wizard",100, 750);
                            if(w2.isAlive())
                                g.drawString("I - Ice Wizard",100, 775);
                            if(w3.isAlive())
                                g.drawString("N - Necromancy Wizard",100, 800);
                            spaceCounter = 1;
                            break;
                            case 4:
                            if(w1.isAlive())
                                g.drawString("F - Fire Wizard",100, 750);
                            if(w2.isAlive())
                                g.drawString("I - Ice Wizard",100, 775);
                            if(w3.isAlive())
                                g.drawString("N - Necromancy Wizard",100, 800);
                            if(w4.isAlive())
                                g.drawString("R - Radiant Wizard",100, 825);
                            spaceCounter = 1;
                            break;
                        }
                        JOptionPane.showMessageDialog(null, "Press space to continue");
                    }

                    break;
                    case 50:background(g);
                    battleStart = true;
                    background(g);
                    g.setColor(Color.black);
                    g.setFont(instructions);
                    targetM.setVulKnown(true);
                    if(targetM.getLevel() == 1){
                        lvlOneVulKnown = true;
                    }else if(targetM.getLevel() == 2){
                        lvlTwoVulKnown = true;
                    }
                    g.drawString("This monster is vulnerable to " + targetM.getVul(), 1000, 200);
                    JOptionPane.showMessageDialog(null, "Press space to continue");
                    monsterAttack(g);
                    break;
                    case 51:background(g);
                    battleStart = true;
                    background(g);
                    g.setColor(Color.black);
                    g.setFont(instructions);
                    targetM.setResKnown(true);
                    if(targetM.getLevel() == 1){
                        lvlOneResKnown = true;
                    }else if(targetM.getLevel() == 2){
                        lvlTwoResKnown = true;
                    }
                    g.drawString("This monster is resistant to " + targetM.getRes(), 1000, 200);
                    JOptionPane.showMessageDialog(null, "Press space to continue");
                    monsterAttack(g);
                    break;
                    case 52:background(g);
                    monsterAttack(g);
                    spaceCounter = 1;
                    break;
                    case 53:background(g);
                    g.setFont(title);
                    g.setColor(Color.black);
                    g.drawString("Boo! You forefit.", 700, 200);
                    JOptionPane.showMessageDialog(null, "Press space to continue");
                    if(spaceCounter == 1){
                        forefit = true;
                        quitGame(g);
                    }
                    break;
                    default:
                }
            }

        }

    }

    public void quitGame(Graphics g){
        battleStart = false;
        battleEnd = true;
        if(level == 10){
            activeWizy = null;
            activeMon1 = null;
            activeMon2 = null;
            activeMon3 = null;
            m1Spawn = false;
            m2Spawn = false;
            m3Spawn = false;
            wizardSpawn = false;
            bossBattle = false;
            background(g);
            g.setFont(title);
            g.setColor(Color.blue);
            g.drawString("You beat the game! Now get out.", 540, 300);
            a.stop();
            victory.play();
        }else{
            a.stop();
            endMusic.play();
        }
        if(forefit){
            activeWizy = null;
            activeMon1 = null;
            activeMon2 = null;
            activeMon3 = null;
            m1Spawn = false;
            m2Spawn = false;
            m3Spawn = false;
            wizardSpawn = false;
            background(g);
            g.setFont(title);
            g.setColor(Color.blue);
            g.drawString("Bye loser...", 755, 300);
            battleStart = false;
            battleEnd = true;
            forefit = true;
            spaceCounter = -500;
            a.stop();
            endMusic.play();
        }

        removeKeyListener(this);
        System.exit(0);
    }

    public void monsterAttack(Graphics g){
        int mDamage = 0;
        mDamage = activeM.attack();
        int newPHP = activeW.getHP() - mDamage;
        activeW.setHP(newPHP);
        background(g);
        g.setFont(instructions);
        g.setColor(Color.red);
        if(bossBattle){
            g.drawString(mDamage + " damage on " + activeW.getElement() + " Wizard!", 300, 250);
        }else{
            g.drawString(mDamage + " damage!", 300, 500);
        }
        if(activeW.getHP() <= 0){
            boolean allAlive = true;
            int aliveCounter = 0;
            int deadCounter = 0;
            for(int i = 0; i < p.wiz.size() ;i++ ){
                if(p.wiz.get(i).getHP() > 0){
                    aliveCounter++;
                }else{
                    deadCounter++;
                }
            }

            if(deadCounter == p.wiz.size()){
                allAlive = false;
                deadCounter = 0;
                aliveCounter = 0;
            }else{
                allAlive = true;
                deadCounter = 0;
                aliveCounter = 0;
            }
            if(allAlive == false){
                activeWizy = null;
                activeMon1 = null;
                activeMon2 = null;
                activeMon3 = null;
                m1Spawn = false;
                m2Spawn = false;
                m3Spawn = false;
                wizardSpawn = false;
                background(g);
                g.setFont(title);
                g.setColor(Color.red);
                g.drawString("GAME OVER", 755, 200);
                g.drawString("Wow. You actually died. Kinda sad...", 600, 300);
                g.drawString("Umm well, get out ig", 675, 400);
                battleStart = false;
                battleEnd = true;
                quitGame(g);
            }
            else if(level > 1 && activeM != boss && allAlive == true){
                background(g);
                g.setColor(Color.red);
                g.setFont(instructions);
                g.drawString("Uh Oh...Your " + activeW.getElement() + " Wizard has died", 450, 200);
                g.drawString("Your dead Wizards will be revived in the next level", 500, 250);
                g.drawString("Please choose a Wizard to switch to.", 525, 300);
                activeW.setAlive(false);
                activeW.setDeployed(false);
                switchWizy = true;
                g.setFont(instructions);
                g.setColor(Color.decode("#7c2310"));
                int wsize = p.wiz.size();
                switch(wsize){
                    case 2:
                    if(w1.isAlive())
                        g.drawString("F - Fire Wizard",100, 750);
                    if(w2.isAlive())
                        g.drawString("I - Ice Wizard",100, 775);
                    spaceCounter = 1;
                    break;
                    case 3:
                    if(w1.isAlive())
                        g.drawString("F - Fire Wizard",100, 750);
                    if(w2.isAlive())
                        g.drawString("I - Ice Wizard",100, 775);
                    if(w3.isAlive())
                        g.drawString("N - Necromancy Wizard",100, 800);
                    spaceCounter = 1;
                    break;
                    case 4:
                    if(w1.isAlive())
                        g.drawString("F - Fire Wizard",100, 750);
                    if(w2.isAlive())
                        g.drawString("I - Ice Wizard",100, 775);
                    if(w3.isAlive())
                        g.drawString("N - Necromancy Wizard",100, 800);
                    if(w4.isAlive())
                        g.drawString("R - Radiant Wizard",100, 825);
                    spaceCounter = 1;
                    break;
                }

                JOptionPane.showMessageDialog(null, "Press space to continue");
            }
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}
}
