
/**
 * Write a description of class Wizard here.
 * 
 * @author Sydney Tsin
 * @version (a version number or a date)
 */
import java.util.*;
class Wizard implements Character
{
    private int HP;
    private int wizardNum;
    private String element;
    private ArrayList<Spell> spellbook = new ArrayList<Spell>();
    private boolean deployed = false;
    private boolean alive = true;
    private int level;
    //number of each level monster beat to increase level
    private int lvlOne = 0;
    private int lvlTwo = 0;
    private int lvlThree = 0;
    private boolean levelUp = false;
    int damage = 0;
    String s1,s2,s3 = "";

    public Wizard(String e){
        HP = 10;
        level = 1;
        element = e;
    }

    public void assignSpells(){

        switch(wizardNum){
            case 0: s1 = "Firebolt";
            s2 = "Hellish Rebuke";
            s3 = "Fireball";
            break;
            case 1: s1 = "Cone of Cold";
            s2 = "Sleet Storm";
            s3 = "Blizzard";
            break;
            case 2: s1 = "Bestow Curse";
            s2 = "Blight";
            s3 = "Hallow";
            break;
            case 3: s1 = "Sacred Flame";
            s2 = "Moonbeam";
            s3 = "Holy Aura";
            break;
            default:
            s1 = "";
            s2 = "";
            s3 = "";
        }

        switch(level){
            case 1:
            for(int i = 0; i < 20; i++){
                spellbook.add(new Spell(1, s1));
            }
            break;
            case 2: 
            for(int i = 0; i < 3; i++){
                spellbook.add(new Spell(2, s2));
            }
            break;
            case 3: 
            for(int q = 0; q < 3; q++){
                spellbook.add(new Spell(2, s2));
            }
            for(int w = 0; w < 2; w++){
                spellbook.add(new Spell(3, s3));
            }
            break;
            case 4:
            for(int e = 0; e < 3; e++){
                spellbook.add(new Spell(2, s2));
            }
            for(int r = 0; r < 2; r++){
                spellbook.add(new Spell(3, s3));
            }
            break;
            case 5:
            for(int t = 0; t < 3; t++){
                spellbook.add(new Spell(2, s2));
            }
            for(int y = 0; y < 2; y++){
                spellbook.add(new Spell(3, s3));
            }
            break;
            default:
        }

    }
    public Spell getSpell(int index){
        Spell s = spellbook.get(index);
        return s;
    }

    public int attack(){
        Spell s = spellbook.get(0);
        damage = s.damage();
        spellbook.remove(s);
        return damage;
    }

    public boolean isDeployed(){
        return deployed;
    }

    public boolean isAlive(){
        return alive;
    }

    public void setDeployed(boolean b){
        deployed = b;
    }

    public void setAlive(boolean b){
        alive = b;
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

    public String getElement(){
        return element;
    }

    public void reset(){
        switch(level){
            case 1: HP = 10;
            break;
            case 2: HP = 18;
            break;
            case 3: HP = 25;
            break;
            case 4: HP = 34;
            break;
            case 5: HP = 46;
            break;
        }
        setAlive(true);
        spellbook.clear();
        assignSpells();
    }

    public boolean addDefeated(int l){
        switch(l){
            case 1: lvlOne++;
            break;
            case 2: lvlTwo++;
            break;
            case 3: lvlThree++;
            break;
        }
        if(lvlOne == 2){
            levelUp = true;
            lvlOne = 0;
            if(level < 5){
                level++;
                switch(level){
                    case 1: HP = 10;
                    break;
                    case 2: HP = 18;
                    break;
                    case 3: HP = 25;
                    break;
                    case 4: HP = 34;
                    break;
                    case 5: HP = 46;
                    break;
                }
            }
        }else if(lvlTwo == 1){
            levelUp = true;
            lvlTwo = 0;
            if(level < 5){
                level++;
                switch(level){
                    case 1: HP = 10;
                    break;
                    case 2: HP = 18;
                    break;
                    case 3: HP = 25;
                    break;
                    case 4: HP = 34;
                    break;
                    case 5: HP = 46;
                    break;
                }
            }
        }else if(lvlThree == 1){
            levelUp = true;
            lvlThree = 0;
            if(level < 5){
                level++;
                switch(level){
                    case 1: HP = 10;
                    break;
                    case 2: HP = 18;
                    break;
                    case 3: HP = 25;
                    break;
                    case 4: HP = 34;
                    break;
                    case 5: HP = 46;
                    break;
                }
            }
        }else{
            levelUp = false;
        }

        return levelUp;
    }

}

