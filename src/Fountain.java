/*
 * A forr�s mez�t reprezent�lja. A hozz� csatlakoztatott elemekbe v�z �ramlik.
 */
public class Fountain extends Area{
	/*
	 * Az elemen l�v� cs�vet tov�bb�tja a szerel�nek aki fel akarja venni azt.
	 */
    public Pipe PickupPipe() {
        System.out.println("+New Pipe Created");
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

	/*
	 * konzolra �r�st seg�t� fv
	 */
    @Override
    public String toString() {return "[Fountain]";}
}
