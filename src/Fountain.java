/*
 * A forr�s mez�t reprezent�lja. A hozz� csatlakoztatott elemekbe v�z �ramlik.
 */
public class Fountain extends Area{

	/*
	 * Az elemen l�v� cs�vet tov�bb�tja a szerel�nek aki fel akarja venni azt.
	 */
    public Pipe PickupPipe() {
        return new Pipe();
    }

    /*
	 * Az elemhez egy �jabb elem csatlakoztat�sa.
     */
    @Override
    void Connect(Area a){
        System.out.println("->" + toString() + ".Connect["+a.toString()+"]");
        connectedAreas.add(a);
        System.out.println("<-" + toString() + ".Connect["+a.toString()+"]");
    }

    /**
     * Adds a water unit for each adjacent area
     */
    @Override
    public void step(){
        for (Area a : connectedAreas)
            a.addWaterLevel(this);
    }

    /**
     * AddWaterLevel override, specific for this type of area
     * Has no use this class
     */
    @Override
    public void addWaterLevel(Area AreaFrom){}

	/*
	 * konzolra �r�st seg�t� fv
	 */
    @Override
    public String toString() {return "[Fountain] ID : " + getID();}

    public String SavableState() {
        String res = "areaType:Fountain,areaId:" + getID() + ",";
        if (player != null) res += "playerId:" + player.getID() + ",";
        for (Area area : connectedAreas) {
            res += "connectedAreaId:" + area.getID() + ",";
        }
        return res;
    }

    public Fountain() {
        super();
        System.out.println("Create " + this.toString());
    }
}
