/*
 * A forrás mezõt reprezentálja. A hozzá csatlakoztatott elemekbe víz áramlik.
 */
public class Fountain extends Area{
	/*
	 * Az elemen lévõ csövet továbbítja a szerelõnek aki fel akarja venni azt.
	 */
    public Pipe PickupPipe() {
        System.out.println("+New Pipe Created");
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

	/*
	 * konzolra írást segítõ fv
	 */
    @Override
    public String toString() {return "[Fountain]";}
}
