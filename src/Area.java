import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/*
 * A p�lya elemeit reprezent�lja. A j�t�kosok 
 * ezeken l�pkednek �s ezeken interakci�kat hajtanak v�gbe.
 */
public class Area {
	
	//Az elemen elhelyezked� j�t�kost t�rolja.
	PlayableCharacter player;
	//A elemhez kapcsolod� elemeket t�rolja.
	List<Area> connectedAreas;
	//Az elem egyed�l�ll� azonos�t�ja, id
	private int ID;
	//statikus sz�ml�l�, ami egyr�szt sz�mon tartja az elemek sz�m�t,
	// de az ID l�trehoz�s�ban haszn�ljuk, minden haszn�latn�l n� egyel az �rt�ke
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
	//Az elemr�l elmozdul� karakter elt�vol�t�sa az attributumb�l.
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
	//Az elemre l�pett j�t�kos elment�s�t val�s�tja meg.
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
	 * Az elemhez egy �jabb elem csatlakoztat�sa.
	 */
	void Connect(Area a){}
	
	/*
	 * Az elemhez csatlakoz� egyik elem elt�vol�t�sa. 
	 */
	public void Disconnect(Area a1){}

	/*
	 * Az elem megjav�t�sa
	 */
	void Fix(){}
	
	/*
	 * �res f�ggv�ny, pipe �rja fel�l
	 */
	void PlacePump(Pump p) {}

	/*
	 * Az elem t�nkret�tele (pl.: lyukaszt�s)
	 */
	void Break(){}
	
	/*
	 * konzolra �r�st seg�t� fv
	 */
	public String toString() {return "[Area]ID : " + ID;}

	/*
	 * �res f�ggv�ny, Pump �rja fel�l
	 */
	public void SetInput(Area a) {
	}

	/*
	 * �res f�ggv�ny, Pump �rja fel�l
	 */
	public void SetOutput(Area a) {
	}
	/*
	 * Az elembe be�raml� v�z fogad�s�t val�s�tja meg.
	 */
	public void step() {
		// TODO Auto-generated method stub
	}

	/*
	* Megadja, hogy a mez� ragad�s �llapotban van-e
	 */
	boolean Sticky(){
		return false;
	}

	/*
	* be�ll�tja a mez� cs�sz�s id�z�t�j�t, ameddig cs�sz�s �llapotban marad
	 */
	public void setSlipperyTimer(){}

	/*
	* Megadja, hogy a mez� cs�sz�s �llapotban van-e
	 */
	boolean Slippery(){
		return false;
	}

	/*
	 * be�ll�tja a mez� ragad�s id�z�t�j�t, ameddig ragad�s �llapotban marad
	 */
	public void setStickyTimer() {}
}
