
/**
 * Write a description of class Battle here.
 * 
 * @author Sydney Tsin 
 * @version Febuary 2023
 **/
import java.lang.Math;
import java.util.ArrayList;
import java.awt.*;
class Battle 
{
    //at end of battle clear spells array and add all new spells based on level of wizard
    int level = 1;
    private int numMon = 0;
    private boolean turn = true; //Player starts first, true if it is the players turn
    private boolean endBattle = false;
    private boolean defeated = false;
    private boolean finalBattle = false;
    private boolean ss = false;
    int target = 0;
    Monster[] m123 = new Monster[3];
    Monster[] m456 = new Monster[3];
    Monster[] m789 = new Monster[3];
    Player p;
    Wizard activeW;
    int pHP;
    int damage;
    int oneM;
    int twoM;
    int threeM;

    public Battle(Player p, int l){
        this.p = p;
        level = l;
        activeW = p.getWizard(0);
        pHP = activeW.getHP();
    }

    public void setLevel(int l){
        level = 1;
    }

    public int deployMonsters(){
        numMon = 3;
        m123[0] = new Monster(1);
        m123[1] = new Monster(1);
        m123[2] = new Monster(1);
        
        m456[0] = new Monster(2);
        m456[1] = new Monster(1);
        m456[2] = new Monster(2);
        
        m789[0] = new Monster(3);
        m789[1] = new Monster(2);
        m789[2] = new Monster(3);
        
        return numMon;
    }

    /**public void countMonsters(){
        for(int i = 0; i < m.size(); i++){
            if(m.get(i).getLevel() == 1){
                oneM++;
            }else if(m.get(i).getLevel() == 2){
                twoM++;
            }else if(m.get(i).getLevel() == 3){
                threeM++;
            }
        }
    }
    **/

    public int getOne(){
        return oneM;
    }

    public int getTwo(){
        return twoM;
    }

    public int getThree(){
        return threeM;
    }

    public void setTurn(boolean t){
        turn = t;
    }

    public boolean getTurn(){
        return turn;
    }

    public void setFinalBattle(boolean b){
        finalBattle = b;
    }

    public boolean getFinalBattle(){
        return finalBattle;
    }

    public Wizard getActiveW(){
        return activeW;
    }


}
