package Model;

import Controller.Game;

import java.util.ArrayList;
import java.util.List;


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
     ** állapot tároló
     **/
    private boolean broken;

    public boolean isBroken() {return broken;}

    public void setBroken(boolean newBroken) {broken = newBroken;}

    /*
     * Model.Area ahonnan fogad vizet
     */
    protected Area input;
    /*
     * Model.Area ahová ad vizet
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
        if (player == null) return false;
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
        Game.getInstance().getWindow().drawComponents();
        return true;
    }

    /*
     * Az elemhez egy újabb elem csatlakoztatása.
     */
    public void Connect(Area a) {
        System.out.println("->" + toString() + ".Connect(" + a + ")");
        connectedAreas.add(a);
        a.connectedAreas.add(this);
        System.out.println("<-" + toString() + ".Connect(" + a + ")");
    }

    /*
     * Az elemhez csatlakozó egyik elem eltávolítása.
     */
    public void Disconnect(Area a) {
        System.out.println("->" + toString() + ".Disconnect(" + a + ")");
        connectedAreas.remove(a);
        a.connectedAreas.remove(this);
        System.out.println("->" + toString() + ".Disconnect(" + a + ")");
    }

    /*
     * Az elem megjavítása
     */
    void Fix() {
    }

    /*
     * üres függvény, pipe írja felül
     */
    void PlacePump(Pump p) {
    }

    Pump PickupPump() {
        return null;
    };

    /*
     * Az elem tönkretétele (pl.: lyukasztás)
     */
    void Break() {
    }

    /*
     * konzolra írást segítõ fv
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
     * Az elembe beáramló víz fogadását valósítja meg.
     */
    public void step() {
        // TODO Auto-generated method stub
    }

    /*
     * Megadja, hogy a mezõ ragadós állapotban van-e
     */
    boolean Sticky() {
        return false;
    }

	/*
	* beállítja a mezõ csúszós idõzítõjét, ameddig csúszós állapotban marad
	 */
	public void setSlipperyTimer(){}

	/*
	* Megadja, hogy a mezõ csúszós állapotban van-e
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

    //A játékos lekéri az állítható be- és kimeneti opciókat
    public List<Area> getConfigureOptions() {
        return new ArrayList<>();
    }

    /*
     * beállítja a mezõ ragadós idõzítõjét, ameddig ragadós állapotban marad
     */
    public void setStickyTimer() {}
}
