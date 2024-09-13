
/**
 * Write a description of class Spell here.
 * 
 * @author Sydney Tsin
 * @version (a version number or a date)
 */
import java.lang.Math.*;

class Spell
{
    private int maxDamage, minDamage;
    private int level;
    private String element;
    int damage = 0;
    int range = 0;
    
    public Spell(int l, String e){
        level = l;
        element = e;
        switch(level){
            case 1: maxDamage = 6;
            minDamage = 1;
            break;
            case 2: maxDamage = 12;
            minDamage = 2;
            break;
            case 3: maxDamage = 18;
            minDamage = 6;
            break;
        }
    }
    
    public int damage(){
        range = (maxDamage - minDamage) +1;
        damage = (int)Math.floor(Math.random() * range + minDamage);
        return damage;
    }
    
    public String getElement(){
        return element;
    }
    
}
