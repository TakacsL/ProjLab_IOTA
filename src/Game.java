import java.util.ArrayList;
import java.util.List;

public final class Game {

    private static Game INSTANCE;

    private Game() {}
    public List<Map> maps;

    public static Game getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Game();
        }
        return INSTANCE;
    }

    public void StartGame(){

    }


    public void EndGame(){

    }

    public void EndTurn(){

        System.out.println("->EndTurn[m]");

        System.out.println("For all Area");
        System.out.println("->Step[]");
        System.out.println("<-Step[]");

        System.out.println("For all PlayableCharacter");
        System.out.println("->Step[]");
        System.out.println("<-Step[]");


        System.out.println("<-EndTurn[m]");
    }

    public void CreateInitialMap(){

        Map m = new Map();
        maps = new ArrayList<Map>();
        maps.add(m);

        System.out.println("->CreateInitialMap[]");

        Fountain f = new Fountain();
        Cistern c = new Cistern();
        Pipe p1 = new Pipe();
        Pipe p2 = new Pipe();

        m.AddArea(f);
        m.AddArea(c);
        m.AddArea(p1);
        m.AddArea(p2);

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
