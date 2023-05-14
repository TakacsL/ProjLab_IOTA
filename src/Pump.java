/**
Pumpa mezõ. Több bekötött csöve lehet, ezek közül minden 
pillanatban egy bemenõ, és egy kimenõ csöve van. Ezt a két 
csövet minden játékos tudja állítani, aki rajta van a pumpa
mezõn. Véletlen idõközönként elromolhat. Vagy a játék elején 
fix helyen vannak, vagy vízvezeték szerelõ karakterek helyezhetik le.
 */

public class Pump extends Area{
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
     * Az elemhez egy újabb elem csatlakoztatása.
     */
    @Override
    void Connect(Area a){
        System.out.println("->Pump.Connect["+a.toString()+"]");
        connectedAreas.add(a);
        System.out.println("<-Pump.Connect["+a.toString()+"]");
    }

	/*
	 * konzolra írást segítõ fv
	 */
    @Override
    public String toString() {return "[Pump]ID : " + getID();}

    /*
     * A hibás pumpa megjavítása.
     */
    @Override
    void Fix(){
        System.out.println("->Pump.Fix[]");
        broken = true;
        System.out.println("<-Pump.Fix[]");
    }

    /*
     * 	 * Az elem lyukasztása
     */
    @Override
    void Break() {
        System.out.println("->Pump.Break[]");
        broken = false;
        System.out.println("<-Pump.Break[]");
    }


    /*
     * A pumpa kimenetének kiválasztása.
     */
    @Override
    public void SetOutput(Area a) {
        System.out.println("->Pump.SetOutput[Area]");
        if(connectedAreas.contains(a)){
            output = a;
        }
        System.out.println("<-Pump.SetOutput[Area]");
    }

    public String SavableState() {
        String res = "areaType:Pump,areaId:" + getID() + ",";
        if (player != null) res += "playerId:" + player.getID() + ",";
        if (broken) res += "broken:" + true + ",";
        if (maxCapacity > 0) res += "maxCapacity:" + maxCapacity + ",";
        if (waterLevel > 0) res += "waterLevel:" + waterLevel + ",";
        for (Area area : connectedAreas) {
            res += "connectedAreaId:" + area.getID() + ",";
        }
        return res;
    }

    public Pump() {
        super();
        System.out.println("Create " + this.toString() + ": " + getID());
    }

}
