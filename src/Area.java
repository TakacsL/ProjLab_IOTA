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

    /*
     * Area ahonnan fogad vizet
     */
    protected Area input;
    /*
     * Area ahová ad vizet
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
        return true;
    }

    /*
     * Az elemhez egy újabb elem csatlakoztatása.
     */
    void Connect(Area a) {
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
        return "[Area]ID : " + ID;
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
     * Megadja, hogy a mezõ csúszós állapotban van-e
     */
    boolean Slippery() {
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
