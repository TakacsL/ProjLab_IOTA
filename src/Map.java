import java.util.ArrayList;
import java.util.List;

/*
 * A cs�rendszert reprezent�lja, a j�t�kosok ennek 
 * a mez�in l�pkednek �s a v�z is ezen kereszt�l folyik.
 */
public class Map {

	/*
	 * A ter�letrendszert fel�p�t� elemek halmaza.
	 */
    public List<Area> areas = new ArrayList<Area>();
    /*
     * A p�ly�n l�v� j�t�kosok list�ja.
     */
    public List<PlayableCharacter> playableCharacters = new ArrayList<PlayableCharacter>();

    //ID alapj�n visszaadja az Area-t
    public Area getAreabyID(int id){
        for (Area a: areas) {
            if (a.getID() == id) return a;
        }
        return null;
    }

    public void printAreas(){
        System.out.println("Available areas are:");
        for (Area a: areas) {
            System.out.println(a.toString());
            for(Area ca: a.connectedAreas)
                System.out.println("\tis connected to" + ca);
        }
    }

    public void printPlayers(){
        System.out.println("Available players are:");
        for (PlayableCharacter pc: playableCharacters) {
            System.out.println(pc.toString());
        }
    }

    //ID alapj�n visszaadja az Player-t
    public PlayableCharacter getPlayerbyID(int id){
        for (PlayableCharacter pc: playableCharacters) {
            if (pc.getID() == id) return pc;
        }
        return null;
    }

    /*
     * A rendszerhez egy �jabb elem illeszt�se.
     */
    void AddArea(Area a){
        areas.add(a);
        System.out.println("->AddArea["+a.toString()+"]");
        System.out.println("<-AddArea["+a.toString()+"]");
    }
    /*
     * A rendszerhez egy �jabb player illeszt�se.
     */
    void AddPlayer(PlayableCharacter pc){
        playableCharacters.add(pc);
        System.out.println("->AddPlayer["+pc.toString()+"]");
        System.out.println("<-AddPlayer["+pc.toString()+"]");
    }
    /*
     * A rendszerb�l az egyik elem elv�tele.
     */
    void RemoveArea(Area a){

        areas.remove(a);
        System.out.println("->RemoveArea["+a.toString()+"]");
        System.out.println("<-RemoveArea["+a.toString()+"]");
    }
}
