import java.util.ArrayList;
import java.util.List;

/*
 * A csõrendszert reprezentálja, a játékosok ennek 
 * a mezõin lépkednek és a víz is ezen keresztül folyik.
 */
public class Map {

	/*
	 * A területrendszert felépítõ elemek halmaza.
	 */
    public List<Area> areas = new ArrayList<Area>();
    /*
     * A pályán lévõ játékosok listája.
     */
    public List<PlayableCharacter> playableCharacters = new ArrayList<PlayableCharacter>();

    //ID alapján visszaadja az Area-t
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

    //ID alapján visszaadja az Player-t
    public PlayableCharacter getPlayerbyID(int id){
        for (PlayableCharacter pc: playableCharacters) {
            if (pc.getID() == id) return pc;
        }
        return null;
    }

    /*
     * A rendszerhez egy újabb elem illesztése.
     */
    void AddArea(Area a){
        areas.add(a);
        System.out.println("->AddArea["+a.toString()+"]");
        System.out.println("<-AddArea["+a.toString()+"]");
    }
    /*
     * A rendszerhez egy újabb player illesztése.
     */
    void AddPlayer(PlayableCharacter pc){
        playableCharacters.add(pc);
        System.out.println("->AddPlayer["+pc.toString()+"]");
        System.out.println("<-AddPlayer["+pc.toString()+"]");
    }
    /*
     * A rendszerbõl az egyik elem elvétele.
     */
    void RemoveArea(Area a){

        areas.remove(a);
        System.out.println("->RemoveArea["+a.toString()+"]");
        System.out.println("<-RemoveArea["+a.toString()+"]");
    }
}
