import java.util.List;


/**
Csõ mezõ. A két vége forráshoz, ciszternához, vagy pumpához van kötve.
Két állapota van, lyukas, vagy mûködõképes. Egy karakter tartózkodhat
rajta egyszerre. A bemenet, és kimenet csövek között víz folyhat,
ha nem lyukas a csõ. A csövek mozgathatók, bármelyik végét le lehet csatlakoztatni, és elvinni máshova
*/
public class Pipe extends Area{
	/*
	 * maximális mennyiségû víz, amit tárolni tud, egész számban
	 */
    private int maxCapacity;
    /*
     * jelenlegi vízszint, egész számban
     */
    private int waterLevel;
    /*
     * állapot tároló
     */
    private boolean broken;

    /*
    * Foltozás után a csõ egy ideig nem romolhat el
     */
    private int brokenTimer;

    /*
    * maradék idõ, ameddig még ragadós
     */
    private int stickyTimer;

    /*
    * maradék idõ, ameddig még csúszós
     */
    private int slipperyTimer;

    /*
     * Az elemhez egy újabb elem csatlakoztatása.
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
     * A lyukas csõ megjavítása.
     */
    @Override
    void Fix(){
        System.out.println("->Pipe.Fix[]");
        broken = false;
        brokenTimer = 3;
        System.out.println("<-Pipe.Fix[]");
    }

    /*
     * A csõ kilyukasztása.
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
     * A csõ állapotának lekérdezése.
     */
    public Boolean isBroken() {return broken;}

	/*
	 * konzolra írást segítõ fv
	 */
    @Override
	public String toString() {return "[Pipe]ID : " + getID();}

    /*
     * Pumpa mezõ lehelyezése a csõre
     * Repairman hívja meg
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
    * beállítja a csõ ragadós idõzítõjét, ameddig ragadós állapotban marad
     */
    public void setStickyTimer(){
        stickyTimer = 3;
    }

    public void setStickyTimer(int timer) {
        stickyTimer = timer;
    }

    /*
    * Megadja mennyi ideig ragadós még a csõ
     */
    public int getStickyTimer(){
        return stickyTimer;
    }

    /*
    * beállítja a csõ csúszós idõzítõjét, ameddig csúszós állapotban marad
     */
    public void setSlipperyTimer(){
        slipperyTimer = 3;
    }

    public void setSlipperyTimer(int timer) {
        slipperyTimer = timer;
    }

    /*
    * Megadja mennyi ideig csúszós még a csõ
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
    * Megadja, hogy a csõ csúszós-e
     */
    @Override
    public boolean Slippery(){
        if(getSlipperyTimer() == 0) return false;
        else return true;
    }

    /*
    * Megadja, hogy a csõ ragadós-e
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
