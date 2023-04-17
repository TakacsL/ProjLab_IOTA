import java.util.ArrayList;

/*
 *   A j�t�k ir�ny�t�s��rt felel�s oszt�ly. Mag�ba foglalja a 
 * 	 k�r�k v�g�n a l�ptet�st, ami minden olyan akci�t
 *   lefuttat, ami a j�t�kosokon k�v�l t�rt�nik. 
 *   A j�t�k elej�n kialak�tja a p�lya alap�ll�s�t
 */
import java.util.List;

public final class Game {

	//singleton m�k�d�s megval�s�t�sa
    private static Game INSTANCE;		

    private Game() {}
    public Map map;	//a j�t�khoz tartoz� ter�letrendszert tartalmaza.

  //singleton m�k�d�s megval�s�t�sa
    public static Game getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Game();
        }
        return INSTANCE;
    }

    //A j�t�k ind�t�sa.
    public void StartGame(){}

    //A j�t�k befejez�se.
    public void EndGame(){}

    //A k�r befejez�se, az elemek l�ptet�se.
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

    //A p�lya alap�ll�s�nak kialak�t�sa
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
