/*
 * A forrás mezõt reprezentálja. A hozzá csatlakoztatott elemekbe víz áramlik.
 */
public class Fountain extends Area{

	/*
	 * Az elemen lévõ csövet továbbítja a szerelõnek aki fel akarja venni azt.
	 */
    public Pipe PickupPipe() {
        return new Pipe();
    }

    /*
	 * Az elemhez egy újabb elem csatlakoztatása.
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
	 * konzolra írást segítõ fv
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
