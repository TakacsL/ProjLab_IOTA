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
        broken = true;
        System.out.println("<-Pipe.Fix[]");
    }

    /*
     * A csõ kilyukasztása.
     */
    @Override
    void Break() {
        System.out.println("->Pipe.Break[]");
        broken = false;
        System.out.println("<-Pipe.Break[]");
    }
    
    /*
     * A csõ állapotának lekérdezése.
     */
    public Boolean isBroken() {return broken;}
    
	/*
	 * konzolra írást segítõ fv
	 */
    @Override
	public String toString() {return "[Pipe]"; }
}
