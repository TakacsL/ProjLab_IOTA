package Model;

import Controller.Game;

import java.util.ArrayList;
import java.util.List;


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

    /**
     * water units which can flow to adjacent areas
     */
    private int waterLevel = 0;

    /**
     **  Getter for WaterUnits
     **/
    public int getWaterLevel() {
        return waterLevel;
    }

    /**
     **  Function, which increases waterUnits by 1
     **/
    public void addWaterLevel(Area AreaFrom){}

    /**
     * max num of Units an Model.Area can contain, wont be used for cistern and fountain
     */
    public int maxCapacity = 10;

    /**
     * setter for MayCapacity and WaterLevel for exceptional use-cases
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    /**
     ** �llapot t�rol�
     **/
    private boolean broken;

    public boolean isBroken() {return broken;}

    public void setBroken(boolean newBroken) {broken = newBroken;}

    /*
     * Model.Area ahonnan fogad vizet
     */
    protected Area input;
    /*
     * Model.Area ahov� ad vizet
     */
    protected Area output;

    //getted for ID
    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public List<Area> getConnectedAreas() {
        return connectedAreas;
    }

    public void printConnectedAreas() {
        System.out.println("Areas connected are:");
        for (Area a : connectedAreas) System.out.println(a.toString());
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
        if (player == null) return false;
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
        Game.getInstance().getWindow().drawComponents();
        return true;
    }

    /*
     * Az elemhez egy �jabb elem csatlakoztat�sa.
     */
    public void Connect(Area a) {
        System.out.println("->" + toString() + ".Connect(" + a + ")");
        connectedAreas.add(a);
        a.connectedAreas.add(this);
        System.out.println("<-" + toString() + ".Connect(" + a + ")");
    }

    /*
     * Az elemhez csatlakoz� egyik elem elt�vol�t�sa.
     */
    public void Disconnect(Area a) {
        System.out.println("->" + toString() + ".Disconnect(" + a + ")");
        connectedAreas.remove(a);
        a.connectedAreas.remove(this);
        System.out.println("->" + toString() + ".Disconnect(" + a + ")");
    }

    /*
     * Az elem megjav�t�sa
     */
    void Fix() {
    }

    /*
     * �res f�ggv�ny, pipe �rja fel�l
     */
    void PlacePump(Pump p) {
    }

    Pump PickupPump() {
        return null;
    };

    /*
     * Az elem t�nkret�tele (pl.: lyukaszt�s)
     */
    void Break() {
    }

    /*
     * konzolra �r�st seg�t� fv
     */
    public String toString() {
        return "[Model.Area]ID : " + ID;
    }

    public void SetInput(Area a) {
        if(input == a) return;
        System.out.println("->" + toString() + ".SetInput("+ a +")");
        if ((a != null && !connectedAreas.contains(a)) || a == input) return;
        input = a;
        if (input != null) input.SetOutput(null);
        if (a != null) a.SetOutput(this);
        System.out.println("<-" + toString() + ".SetInput("+ a +")");
    }

    public void SetOutput(Area a) {
        if(output == a) return;
        System.out.println("->" + toString() + ".SetOutput("+ a +")");
        output = a;
        if ((a != null && !connectedAreas.contains(a)) || a == output) return;
        if (output != null) output.SetInput(null);
        if (a != null) a.SetInput(this);
        System.out.println("<-" + toString() + ".SetOutput("+ a +")");
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
    boolean Sticky() {
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

    public PlayableCharacter getPlayer() {
        return player;
    }

    public void setPlayer(PlayableCharacter player) {
        this.player = player;
    }

    public String SavableState() {
        return "";
    }

    //A j�t�kos lek�ri az �ll�that� be- �s kimeneti opci�kat
    public List<Area> getConfigureOptions() {
        return new ArrayList<>();
    }

    /*
     * be�ll�tja a mez� ragad�s id�z�t�j�t, ameddig ragad�s �llapotban marad
     */
    public void setStickyTimer() {}
}
