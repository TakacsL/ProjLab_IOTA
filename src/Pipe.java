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
        broken = true;
        System.out.println("<-Pipe.Fix[]");
    }

    /*
     * A cs� kilyukaszt�sa.
     */
    @Override
    void Break() {
        System.out.println("->Pipe.Break[]");
        broken = false;
        System.out.println("<-Pipe.Break[]");
    }
    
    /*
     * A cs� �llapot�nak lek�rdez�se.
     */
    public Boolean isBroken() {return broken;}
    
	/*
	 * konzolra �r�st seg�t� fv
	 */
    @Override
	public String toString() {return "[Pipe]"; }
}
