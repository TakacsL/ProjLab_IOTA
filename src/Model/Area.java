package Model;

import Controller.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * A pálya elemeit reprezentálja. A játékosok
 * ezeken lépkednek és ezeken interakciókat hajtanak végbe.
 */
public class Area {

    /**
     * Az elemen elhelyezkedõ játékost tárolja.
     */
    List<PlayableCharacter> players;

    int x, y;
    /**
     * A elemhez kapcsolodó elemeket tárolja.
     */
    List<Area> connectedAreas;

    /**
     * Az elem egyedülálló azonosítója, id
     */
    private int ID;

    /**
     * statikus számláló, ami egyrészt számon tartja az elemek számát,
     * de az ID létrehozásában használjuk, minden használatnál nõ egyel az értéke
     */
    private static int numOfIDs = 0;

    /**
     * water units which can flow to adjacent areas
     */
    private int waterLevel = 0;

    /**
     * *  Getter for WaterUnits
     **/
    public int getWaterLevel() {
        return waterLevel;
    }

    /**
     * *  Function, which increases waterUnits by 1
     **/
    public void addWaterLevel(Area AreaFrom) {
    }

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
     * * állapot tároló
     **/
    private boolean broken;

    /**
     *
     * @return broken state
     */
    public boolean isBroken() {
        return broken;
    }

    /**
     *
     * @param newBroken set broken state
     */
    public void setBroken(boolean newBroken) {
        broken = newBroken;
    }

    /**
     * Model.Area ahonnan fogad vizet
     */
    protected Area input;
    /**
     * Model.Area ahová ad vizet
     */
    protected Area output;

    /**
     *
     * @return This area ID
     */
    public int getID() {
        return ID;
    }

    /**
     *
     * @param id set area ID
     */
    public void setID(int id) {
        this.ID = id;
    }

    /**
     *
     * @return areas connected to this area
     */
    public List<Area> getConnectedAreas() {
        return connectedAreas;
    }

    /*
    public void printConnectedAreas() {
        System.out.println("Areas connected are:");
        for (Area a : connectedAreas) System.out.println(a.toString());
    }*/

    /**
     * Constructs the Area object
     * initialises connected areas and players collections
     */
    public Area() {
        // TODO Auto-generated constructor stub
        connectedAreas = new ArrayList<Area>();
        players = new ArrayList<PlayableCharacter>();
        ID = numOfIDs++;
    }

    /**
     * Az elemrõl elmozduló karakter eltávolítása az attributumból.
     * @param character Character to remove from this area
     * @return true if the character was removed, false if not
     */
    public Boolean RemoveCharacter(PlayableCharacter character) {
        /*Scanner scanner = new Scanner(System.in);
		System.out.println("Can I remove? (Y/N)");
        if (scanner.next().charAt(0) == 'Y') {
        	return true;
        }
        else {
    		return false;
        }*/
        if (players.contains(character)) {
            players.remove(character);
            return true;
        }
        return false;
    }

    /**
     * Az elemre lépett játékos elmentését valósítja meg.
     * Mindig igazat add vissza, ahol kell logika, ott override van a specifikus osztalyon
     * @param character place this character on this area
     * @return
     */
    public Boolean AcceptCharacter(PlayableCharacter character) {
        /*Scanner scanner = new Scanner(System.in);
		System.out.println("Can I accept? (Y/N)");
        if (scanner.next().charAt(0) == 'Y') {
        	return true;
        }
        else {
    		return false;
        }*/
        players.add(character);
        character.a1 = this;
        Game.getInstance().getWindow().drawComponents();
        return true;
    }

    /**
     * Az elemhez egy újabb elem csatlakoztatása.
     */
    public void Connect(Area a) {
        System.out.println("->" + toString() + ".Connect(" + a + ")");
        a.Connect(this);
        if (a.connectedAreas.contains(this))
            connectedAreas.add(a);
        System.out.println("<-" + toString() + ".Connect(" + a + ")");
    }

    /**
     * Az elemhez csatlakozó egyik elem eltávolítása.
     */
    public void Disconnect(Area a) {
        System.out.println("->" + toString() + ".Disconnect(" + a + ")");
        connectedAreas.remove(a);
        a.connectedAreas.remove(this);
        System.out.println("->" + toString() + ".Disconnect(" + a + ")");
    }

    /**
     * Az elem megjavítása
     */
    void Fix() {
    }

    /**
     * üres függvény, pipe írja felül
     */
    void PlacePump(Pump p) {
    }

    Pump PickupPump() {
        return null;
    }

    ;

    /**
     * Az elem tönkretétele (pl.: lyukasztás)
     */
    void Break() {
    }

    /**
     * konzolra írást segítõ fv
     */
    public String toString() {
        return "[Model.Area]ID : " + ID;
    }

    /**
     *
     * @param a Set the area's input area
     */
    public void SetInput(Area a) {
        if (input == a) return;
        System.out.println("->" + toString() + ".SetInput(" + a + ")");
        if ((a != null && !connectedAreas.contains(a)) || a == input) return;
        input = a;
        if (input != null) input.SetOutput(null);
        if (a != null) a.SetOutput(this);
        System.out.println("<-" + toString() + ".SetInput(" + a + ")");
    }

    /**
     *
     * @param a Set the area's input area
     */
    public void SetOutput(Area a) {
        if (output == a) return;
        System.out.println("->" + toString() + ".SetOutput(" + a + ")");
        output = a;
        if ((a != null && !connectedAreas.contains(a)) || a == output) return;
        if (output != null) output.SetInput(null);
        if (a != null) a.SetInput(this);
        System.out.println("<-" + toString() + ".SetOutput(" + a + ")");
    }

    /**
     * Az elembe beáramló víz fogadását valósítja meg.
     */

    public void step() {
        // TODO Auto-generated method stub
    }

    /**
     * Megadja, hogy a mezõ ragadós állapotban van-e
     */
    boolean Sticky() {
        return false;
    }

    /**
     * beállítja a mezõ csúszós idõzítõjét, ameddig csúszós állapotban marad
     */
    public void setSlipperyTimer() {
    }

    /**
     * Megadja, hogy a mezõ csúszós állapotban van-e
     */
    boolean Slippery() {
        return false;
    }

    /**
     *
     * @return Retruns the list of characters standing on this area
     */
    public List<PlayableCharacter> getPlayers() {
        return players;
    }

    /**
     *
     * @param player Adds the player to the players collection
     */
    public void addPlayer(PlayableCharacter player) {
        players.add(player);
    }

    /**
     *
     * @return Nothing to save in the base class
     */
    public String SavableState() {
        return "";
    }

    /**
     * A játékos lekéri az állítható be- és kimeneti opciókat
     */
    public List<Area> getConfigureOptions() {
        return new ArrayList<>();
    }

    /**
     * beállítja a mezõ ragadós idõzítõjét, ameddig ragadós állapotban marad
     */
    public void setStickyTimer() {
    }

    /**
     * Creates a component based on this Area's state
     * @return the component representing this object
     */
    public JComponent draw() {
        String label = "<html>Field Id: " + getID();
        if (isBroken()) label += "<br>Broken";
        if (Slippery()) label += "<br>Slippery";
        if (Sticky()) label += "<br>Sticky";
        label += "</html>";

        JButton component = new JButton(label);
        component.setBounds(x, y, 66, 66);
        component.setBorder(BorderFactory.createEmptyBorder());
        component.setVerticalTextPosition(SwingConstants.CENTER);
        component.setHorizontalTextPosition(SwingConstants.CENTER);
        component.setForeground(Color.RED);

        return component;
    }

    /**
     * Set the coordinates on the Window for this area
     * @param x coordinate
     * @param y coordinate
     */
    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
