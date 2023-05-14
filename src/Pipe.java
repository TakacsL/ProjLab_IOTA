import java.util.List;


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
	public String toString() {return "[Pipe]ID : " + getID();}

    /*
     * Pumpa mez� lehelyez�se a cs�re
     * Repairman h�vja meg
     */
    @Override
    void PlacePump(Pump p) {
        System.out.println("->" + toString() + ".PlacePump(" + p + ")");
        Pipe newPipe = new Pipe();
        Game.getInstance().map.AddArea(newPipe);
        Game.getInstance().map.AddArea(p);
        p.Connect(this);
        p.Connect(newPipe);
        newPipe.SetInput(this.input);
        p.SetInput(newPipe);
        this.SetInput(p);
        System.out.println("<-" + toString() + ".PlacePump(" + p + ")");
    }


    /*
    * be�ll�tja a cs� ragad�s id�z�t�j�t, ameddig ragad�s �llapotban marad
     */
    public void setStickyTimer(){
        stickyTimer = 3;
    }

    public void setStickyTimer(int timer) {
        stickyTimer = timer;
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
    public void setSlipperyTimer(){
        slipperyTimer = 3;
    }

    public void setSlipperyTimer(int timer) {
        slipperyTimer = timer;
    }

    /*
    * Megadja mennyi ideig cs�sz�s m�g a cs�
     */
    public int getSlipperyTimer(){
        return slipperyTimer;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public void setBrokenTimer(int brokenTimer) {
        this.brokenTimer = brokenTimer;
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

    public String SavableState() {
        String res = "areaType:Pipe,areaId:" + getID() + ",";
        if (player != null) res += "playerId:" + player.getID() + ",";
        if (slipperyTimer > 0) res += "slipperyTimer:" + slipperyTimer + ",";
        if (stickyTimer > 0) res += "stickyTimer:" + stickyTimer + ",";
        if (brokenTimer > 0) res += "brokenTimer:" + brokenTimer + ",";
        if (broken) res += "broken:" + true + ",";
        if (maxCapacity > 0) res += "maxCapacity:" + maxCapacity + ",";
        if (waterLevel > 0) res += "waterLevel:" + waterLevel + ",";
        for (Area area : connectedAreas) {
            res += "connectedAreaId:" + area.getID() + ",";
        }
        return res;
    }
}
