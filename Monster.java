
/**
 * Write a description of class Monster here.
 * 
 * @author Sydney Tsin
 * @version (a version number or a date)
 */
import java.lang.Math.*;
class Monster implements Character
{
    protected int HP;
    protected int monsterNum;
    protected boolean deployed = false;
    protected boolean alive = true;
    protected int level;
    protected String vul, res;
    protected boolean vulKnown, resKnown = false;

    public Monster(int l){
        level = l;
        switch(level){
            case 1: vul = "Fire";
            res = "Ice";
            HP = 6;
            break;
            case 2: vul = "Ice";
            res = "Necrotic";
            HP = 12;
            break;
            case 3: vul = "Radiant";
            res = "Fire";
            HP = 21;
            break;
            default:
        }
    }

    public int attack(){
        int damage = 0;
        int range = 0;
        int min = 0;
        //calculating damage range based on level of Monster
        switch(level){
            case 1: range = (3 - 1) + 1;
            min = 1;
            break;
            case 2: range = (5-1)+1;
            min = 1;
            break;
            case 3: range = (10-2)+1;
            min = 2;
            break;
            default:
        }

        damage = (int)(Math.random() * range) + min;
        return damage;
    }

    public void reset(){
        switch(level){
            case 1: HP = 6;
            break;
            case 2: HP = 12;
            break;
            case 3: HP = 21;
            break;
        }
        setAlive(true);
    }

    public boolean isDeployed(){
        return deployed;
    }

    public void setDeployed(boolean d){
        deployed = d;
    }

    public boolean isAlive(){
        return alive;
    }

    public void setAlive(boolean a){
        alive = a; 
    }

    public int getHP(){
        return HP;
    }

    public void setHP(int h){
        HP = h;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int l){
        level = 1;
    }

    public int getMonsterNum(){
        return monsterNum;
    }

    public void setMonsterNum(int n){
        monsterNum = n;
    }

    public void setVulKnown(boolean v){
        vulKnown = v;
    }

    public void setResKnown(boolean r){
        resKnown = r;
    }

    public boolean getResKnown(){
        return resKnown;
    }

    public boolean getVulKnown(){
        return vulKnown;
    }

    public String getVul(){
        return vul;
    }

    public String getRes(){
        return res;
    }

    public void setVul(String v){
        vul = v;
    }
}
