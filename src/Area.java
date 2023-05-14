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
	//Az elem egyedülálló azonosítója, id
	private int ID;
	//statikus számláló, ami egyrészt számon tartja az elemek számát,
	// de az ID létrehozásában használjuk, minden használatnál nõ egyel az értéke
	private static int numOfIDs = 0;

	//getted for ID
	public int getID() {
		return ID;
	}

	public List<Area> getConnectedAreas() {
		return connectedAreas;
	}

	public  void printConnectedAreas() {
		System.out.println("Areas connected are:");
		for(Area a : connectedAreas) System.out.println(a.toString());
	}

	public Area() {
		// TODO Auto-generated constructor stub
		connectedAreas = new ArrayList<Area>();
		ID = numOfIDs++;
	}
	//Az elemrõl elmozduló karakter eltávolítása az attributumból.
	public Boolean RemoveCharacter(PlayableCharacter character) {
        /*Scanner scanner = new Scanner(System.in);
		System.out.println("Can I remove? (Y/N)");
        if (scanner.next().charAt(0) == 'Y') {
        	return true;
        }
        else {
    		return false;
        }*/
		if (player==null) return false;
		if (player.getID() == character.getID()) {
			player = null;
			return true;
		}
		return false;
	}
	//Az elemre lépett játékos elmentését valósítja meg.
	//Mindig igazat add vissza, ahol kell logika, ott override van a specifikus osztalyon
	public Boolean AcceptCharacter(PlayableCharacter character) {
        /*Scanner scanner = new Scanner(System.in);
		System.out.println("Can I accept? (Y/N)");
        if (scanner.next().charAt(0) == 'Y') {
        	return true;
        }
        else {
    		return false;
        }*/
		player = character;
		player.a1 = this;
		return true;
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
	public String toString() {return "[Area]ID : " + ID;}

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
