/*
 * A ciszterna mezõt reprezentálja. 
 * Csöveket kreál, amelyeket a rajta 
 * álló szerelõk fel tudnak venni. 
 * Az ide érkezõ víz a szerelõ csapatnak ad pontot. 
 */
public class Cistern extends Area {
	
	
	/*
	 * Az elemen lévõ pumpát továbbítja a szerelõnek aki fel akarja venni azt.
	 */
	public Pump PickupPump() {
		return new Pump();
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
	
	/*
	 * konzolra írást segítõ fv
	 */
	@Override
	public String toString() {return "[cistern]";}
	
}
