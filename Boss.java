
/**
 * Write a description of class Boss here.
 * 
 * @author Sydney Tsin
 * @version (a version number or a date)
 */
import java.lang.Math;
class Boss extends Monster
{
    public Boss(){
        super(10);
        level = 10;
        HP = 70;
        vul = null;
        res = null;
    }
    
    public int attack(){
        int range = (20 - 6) + 1;
        int damage = (int)(Math.random() * range) + 6;
       
        return damage;
    }
}
