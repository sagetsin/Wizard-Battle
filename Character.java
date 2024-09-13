
/**
 * Write a description of interface Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 interface Character
{
    public int attack();
    public boolean isDeployed();
    public boolean isAlive();
    public int getHP();
    public void setHP(int h);
    public int getLevel();
    public void setLevel(int l);
    public void reset();
}
