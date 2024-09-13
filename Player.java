
/**
 * Write a description of class Player here.
 * 
 * @author Sydney Tsin
 * @version (a version number or a date)
 */
import java.util.*;
class Player
{
    private int level = 1;
    private boolean turn = true;
    ArrayList<Wizard> wiz = new ArrayList<Wizard>();

    //creating each Wizard
    Wizard one = new Wizard("Fire");
    Wizard two = new Wizard("Ice");
    Wizard three = new Wizard("Necrotic");
    Wizard four = new Wizard("Radiant");

    public Player(){

        //adding wizards to Player's wizard array and assigning spells to each wizard
        switch(level){
            case 1: one.assignSpells();
            wiz.add(one);
            break;
            case 2: two.assignSpells();
            wiz.add(two);
            break;
            case 3: three.assignSpells();
            wiz.add(three);
            break;
            case 4: four.assignSpells();
            wiz.add(four);
            default:
        }

    }

    public void addWizard(int l){
        switch(level){
            case 1: one.assignSpells();
            wiz.add(one);
            break;
            case 2: two.assignSpells();
            wiz.add(two);
            break;
            case 3: three.assignSpells();
            wiz.add(three);
            break;
            case 4: four.assignSpells();
            wiz.add(four);
            default:
        }
    }

    public ArrayList<Wizard> getWizards(){
        return wiz;
    }

    public Wizard getWizard(int num){
        return wiz.get(num);
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int l){
        level = l;
    }

    public void setTurn(boolean b){
        turn = b;
    }

    public boolean getTurn(){
        return turn;
    }

  
}
