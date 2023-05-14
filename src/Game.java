import java.util.ArrayList;

/*
 *   A játék irányításáért felelõs osztály. Magába foglalja a 
 * 	 körök végén a léptetést, ami minden olyan akciót
 *   lefuttat, ami a játékosokon kívül történik. 
 *   A játék elején kialakítja a pálya alapállását
 */
import java.util.List;

public final class Game {

	//singleton mûködés megvalósítása
    private static Game INSTANCE;


    //válzotó ami alapján tudjuk követni a játék jelenlegi állapotát
    private boolean GameRunning = false;

    //getter for Gamerunning
    public boolean isGameRunning() {
        return GameRunning;
    }
    private Game() {}
    public Map map;	//a játékhoz tartozó területrendszert tartalmaza.

  //singleton mûködés megvalósítása
    public static Game getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Game();
        }
        return INSTANCE;
    }

    //A játék indítása.
    public void StartGame(){
        GameRunning = true;
        getInstance().CreateInitialMap();
    }

    //A játék befejezése.
    public void EndGame(){
        GameRunning = false;
    }

    //A kör befejezése, az elemek léptetése.
    public void EndTurn(){
        if (!getInstance().GameRunning) return;
        System.out.println("->EndTurn[]");

        System.out.println("For all Area");
        System.out.println("->Step[]");
        for (Area a : map.areas) a.step();
        System.out.println("<-Step[]");

        System.out.println("For all PlayableCharacter");
        System.out.println("->Step[]");
        System.out.println("<-Step[]");

        System.out.println("<-EndTurn[m]");
    }

    //A pálya alapállásának kialakítása
    private void CreateInitialMap(){
        if (!getInstance().GameRunning) return;

        map = new Map();

        System.out.println("->CreateInitialMap[]");

        Fountain f = new Fountain();
        Cistern c = new Cistern();
        Pipe p1 = new Pipe();
        Pipe p2 = new Pipe();

        map.AddArea(f);
        map.AddArea(c);
        map.AddArea(p1);
        map.AddArea(p2);

        Repairman r1 = new Repairman(f);
        Repairman r2 = new Repairman(c);
        Saboteur s1 = new Saboteur(p1);
        Saboteur s2 = new Saboteur(p2);

        map.AddPlayer(r1);
        map.AddPlayer(r2);
        map.AddPlayer(s1);
        map.AddPlayer(s2);

        f.Connect(p1);
        p1.Connect(p2);
        p2.Connect(c);

        System.out.println("-->CreatePlayableCharacter[r1: Repairman]");
        System.out.println("-->CreatePlayableCharacter[r2: Repairman]");

        System.out.println("-->CreatePlayableCharacter[s1: Saboteur]");
        System.out.println("-->CreatePlayableCharacter[s2: Saboteur]");

        System.out.println("<-CreateInitialMap[]");


    }
}
