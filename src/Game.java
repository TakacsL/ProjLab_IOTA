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
    public void StartGame(){}

    //A játék befejezése.
    public void EndGame(){}

    //A kör befejezése, az elemek léptetése.
    public void EndTurn(){

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
    public void CreateInitialMap(){

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

        Repairman r1 = new Repairman();
        Repairman r2 = new Repairman();
        Saboteur s1 = new Saboteur();
        Saboteur s2 = new Saboteur();




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
