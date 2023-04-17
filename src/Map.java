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
    public List<PlayableCharacter> playableCharacters;

	/*
	 * A rendszerhez egy �jabb elem illeszt�se.
	 */
    void AddArea(Area a){

        areas.add(a);
        System.out.println("->AddArea["+a.toString()+"]");
        System.out.println("<-AddArea["+a.toString()+"]");
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
