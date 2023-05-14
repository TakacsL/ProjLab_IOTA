/**
Cs� mez�. A k�t v�ge forr�shoz, cisztern�hoz, vagy pump�hoz van k�tve. 
K�t �llapota van, lyukas, vagy m�k�d�k�pes. Egy karakter tart�zkodhat 
rajta egyszerre. A bemenet, �s kimenet cs�vek k�z�tt v�z folyhat, 
ha nem lyukas a cs�. A cs�vek mozgathat�k, b�rmelyik v�g�t le lehet csatlakoztatni, �s elvinni m�shova
*/
public class Pipe extends Area{
	/*
	 * maxim�lis mennyis�g� v�z, amit t�rolni tud, eg�sz sz�mban
	 */
    private int maxCapacity;
    /*
     * jelenlegi v�zszint, eg�sz sz�mban
     */
    private int waterLevel;
    /*
     * �llapot t�rol�
     */
    private boolean broken;

    /*
    * Foltoz�s ut�n a cs� egy ideig nem romolhat el
     */
    private int brokenTimer;

    /*
    * marad�k id�, ameddig m�g ragad�s
     */
    private int stickyTimer;

    /*
    * marad�k id�, ameddig m�g cs�sz�s
     */
    private int slipperyTimer;

    /*
     * Az elemhez egy �jabb elem csatlakoztat�sa.
     */
    @Override
    void Connect(Area a){
        System.out.println("->Pipe.Connect["+a.toString()+"]");
        if(connectedAreas.size()>=2){
            System.out.println("Pipe cannot connect to more than 2 areas, returning");
            return;
        }

        connectedAreas.add(a);
        System.out.println("<-Pipe.Connect["+a.toString()+"]");
    }

    /*
     * A lyukas cs� megjav�t�sa.
     */
    @Override
    void Fix(){
        System.out.println("->Pipe.Fix[]");
        broken = false;
        brokenTimer = 3;
        System.out.println("<-Pipe.Fix[]");
    }

    /*
     * A cs� kilyukaszt�sa.
     */
    @Override
    void Break() {
        if (brokenTimer == 0) {
            System.out.println("->Pipe.Break[]");
            broken = true;
            System.out.println("<-Pipe.Break[]");
        }
        else System.out.println("The pipe cannot be broken for " + brokenTimer + " turns");
    }
    
    /*
     * A cs� �llapot�nak lek�rdez�se.
     */
    public Boolean isBroken() {return broken;}
    
	/*
	 * konzolra �r�st seg�t� fv
	 */
    @Override
	public String toString() {return "[Pipe]ID : " + getID() + (broken ? ", broken" : ", not broken");}

    /*
    * be�ll�tja a cs� ragad�s id�z�t�j�t, ameddig ragad�s �llapotban marad
     */
    public void setStickyTimer(){
        stickyTimer = 3;
    }

    /*
    * Megadja mennyi ideig ragad�s m�g a cs�
     */
    public int getStickyTimer(){
        return stickyTimer;
    }

    /*
    * be�ll�tja a cs� cs�sz�s id�z�t�j�t, ameddig cs�sz�s �llapotban marad
     */
    @Override
    public void setSlipperyTimer(){
        slipperyTimer = 3;
    }

    /*
    * Megadja mennyi ideig cs�sz�s m�g a cs�
     */
    public int getSlipperyTimer(){
        return slipperyTimer;
    }

    /*
    * Megadja, hogy a cs� cs�sz�s-e
     */
    @Override
    public boolean Slippery(){
        if(getSlipperyTimer() == 0) return false;
        else return true;
    }

    /*
    * Megadja, hogy a cs� ragad�s-e
     */
    @Override
    public boolean Sticky(){
        if (getStickyTimer() == 0) return false;
        else return true;
    }
}
