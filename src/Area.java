import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/*
 * A pálya elemeit reprezentálja. A játékosok 
 * ezeken lépkednek és ezeken interakciókat hajtanak végbe.
 */
public class Area {
	
	//Az elemen elhelyezkedõ játékost tárolja.
	PlayableCharacter player;
	//A elemhez kapcsolodó elemeket tárolja.
	List<Area> connectedAreas;

	public List<Area> getConnectedAreas() {
		return connectedAreas;
	}

	public Area() {
		// TODO Auto-generated constructor stub
		connectedAreas = new ArrayList<Area>();
	}
	//Az elemrõl elmozduló karakter eltávolítása az attributumból.
	public Boolean RemoveCharacter(PlayableCharacter character) {
        Scanner scanner = new Scanner(System.in);
		System.out.println("Can I remove? (Y/N)");
        if (scanner.next().charAt(0) == 'Y') {
        	return true;
        }
        else {
    		return false;
        }
	}
	//Az elemre lépett játékos elmentését valósítja meg.
	public Boolean AcceptCharacter(PlayableCharacter character) {
        Scanner scanner = new Scanner(System.in);
		System.out.println("Can I accept? (Y/N)");
        if (scanner.next().charAt(0) == 'Y') {
        	return true;
        }
        else {
    		return false;
        }
	}

	/*
	 * Az elemhez egy újabb elem csatlakoztatása.
	 */
	void Connect(Area a){}
	
	/*
	 * Az elemhez csatlakozó egyik elem eltávolítása. 
	 */
	public void Disconnect(Area a1){}

	/*
	 * Az elem megjavítása
	 */
	void Fix(){}
	
	/*
	 * üres függvény, pipe írja felül
	 */
	void PlacePump(Pump p) {}

	/*
	 * Az elem tönkretétele (pl.: lyukasztás)
	 */
	void Break(){}
	
	/*
	 * konzolra írást segítõ fv
	 */
	public String toString() {return "[Area]";}

	/*
	 * Üres függvény, Pump írja felül
	 */
	public void SetInput(Area a) {
	}

	/*
	 * Üres függvény, Pump írja felül
	 */
	public void SetOutput(Area a) {
	}
	/*
	 * Az elembe beáramló víz fogadását valósítja meg.
	 */
	public void step() {
		// TODO Auto-generated method stub
	}

	/*
	* Megadja, hogy a mezõ ragadós állapotban van-e
	 */
	boolean Sticky(){
		return false;
	}

	/*
	* Megadja, hogy a mezõ csúszós állapotban van-e
	 */
	boolean Slippery(){
		return false;
	}

}
