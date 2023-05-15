import java.util.List;

/**
Pumpa mez�. T�bb bek�t�tt cs�ve lehet, ezek k�z�l minden 
pillanatban egy bemen�, �s egy kimen� cs�ve van. Ezt a k�t 
cs�vet minden j�t�kos tudja �ll�tani, aki rajta van a pumpa
mez�n. V�letlen id�k�z�nk�nt elromolhat. Vagy a j�t�k elej�n 
fix helyen vannak, vagy v�zvezet�k szerel� karakterek helyezhetik le.
 */

public class Pump extends Area{
    /*
     * Az elemhez egy �jabb elem csatlakoztat�sa.
     */
    @Override
    void Connect(Area a){
        if(connectedAreas.contains(a)) return;
        System.out.println("->Pump.Connect["+a.toString()+"]");
        connectedAreas.add(a);
        a.connectedAreas.add(this);
        System.out.println("<-Pump.Connect["+a.toString()+"]");
    }

	/*
	 * konzolra �r�st seg�t� fv
	 */
    @Override
    public String toString() {return "[Pump]ID : " + getID() + (getWaterLevel() > 0 ? ", hasWater" : ", has no water");}

    /*
     * A hib�s pumpa megjav�t�sa.
     */
    @Override
    void Fix(){
        System.out.println("->Pump.Fix[]");
        setBroken(false);
        System.out.println("<-Pump.Fix[]");
    }

    /*
     * 	 * Az elem lyukaszt�sa
     */
    @Override
    void Break() {
        System.out.println("->Pump.Break[]");
        setBroken(true);
        System.out.println("<-Pump.Break[]");
    }


    /*
     * A pumpa kimenet�nek kiv�laszt�sa.
     */
    @Override
    public void SetOutput(Area a) {
        System.out.println("->Pump.SetOutput(" + a + ")");
        if(connectedAreas.contains(a)){
            output = a;
        }
        System.out.println("<-Pump.SetOutput(" + a + ")");
    }

    public String SavableState() {
        String res = "areaType:Pump,areaId:" + getID() + ",";
        if (player != null) res += "playerId:" + player.getID() + ",";
        if (isBroken()) res += "broken:" + true + ",";
        if (maxCapacity > 0) res += "maxCapacity:" + maxCapacity + ",";
        if (getWaterLevel() > 0) res += "waterLevel:" + getWaterLevel() + ",";
        for (Area area : connectedAreas) {
            res += "connectedAreaId:" + area.getID() + ",";
        }
        return res;
    }

    //A j�t�kos lek�ri az �ll�that� be- �s kimeneti opci�kat
    public List<Area> getConfigureOptions() {
        return getConnectedAreas();
    }

    public Pump() {
        super();
        System.out.println("Create " + this + ": " + getID());
    }

    /**
     * If empty, nothhing happens
     * else decrease waterlevel
     * if broken, sabouteur ponits incr
     * else add water to output
     */
    @Override
    public void step(){
        if (getWaterLevel() > 0) {
            this.setWaterLevel(this.getWaterLevel() - 1);
            if (isBroken()) {
                Game.saboteurPoints++;
                return;
            }
            output.addWaterLevel(this);
        }
    }

    /**
     * AddWaterLevel override, specific for this type of area
     * If not full, and input ID is same as caller, incr. WaterLevel
     */
    @Override
    public void addWaterLevel(Area AreaFrom){
        if (this.getWaterLevel() < this.maxCapacity && AreaFrom.getID() == input.getID()) this.setWaterLevel(this.getWaterLevel() + 1);
    }

}
