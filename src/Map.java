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
    public List<PlayableCharacter> playableCharacters;

	/*
	 * A rendszerhez egy újabb elem illesztése.
	 */
    void AddArea(Area a){

        areas.add(a);
        System.out.println("->AddArea["+a.toString()+"]");
        System.out.println("<-AddArea["+a.toString()+"]");
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
