package Controller;

import Model.*;
import View.Window;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Singleton class
 *
 * Holds all necessary game logic
 */

public final class Game {

    /**
     * singleton mûködés megvalósítása
     */

    private static Game INSTANCE;

    /**
     * public static nums of points for each teams
     */
    public static int saboteurPoints = 0;
    public static int repairmanPoints = 0;

    /**
     * Window variable, stored to gain access to view
     */

    private Window window;

    /**
     *
     * @return the window associated with this game
     */
    public Window getWindow() {
        return window;
    }


    /**
     * változó ami alapján tudjuk követni a játék jelenlegi állapotát
     */
    private boolean GameRunning = false;

    /**
     *
     * @return the game running state
     */
    public boolean isGameRunning() {
        return GameRunning;
    }

    /**
     * private constructor
     */

    private Game() {
    }

    /**
     * Holds exactly one Map object that contains the Areas and PlayableCharacters
     */
    public Map map;

    /**
     * Return the only existing instance
     *
     * Singleton type
     */
    public static Game getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Game();
        }
        return INSTANCE;
    }

    /**
     * Initializes the game
     *
     * Creates an initial Map state
     *
     * @param w The window which should show the game's state
     */
    public void StartGame(Window w) {
        window = w;
        GameRunning = true;
        getInstance().CreateInitialMap();
    }

    /**
     * Shows the winning team, sets the running state to false
     */
    public void EndGame() {
        System.out.println("Points are Model.Saboteur : " + saboteurPoints + " vs. Model.Repairman : " + repairmanPoints);
        if (saboteurPoints != repairmanPoints)
            System.out.println((saboteurPoints > repairmanPoints ? "Model.Saboteur" : "Model.Repairman") + " team won!");
        else System.out.println("Controller.Game ended in a draw!");
        GameRunning = false;
    }

    /**
     * A kör befejezése, az elemek léptetése.
     */
    public void EndTurn() {
        if (!getInstance().GameRunning) return;
        System.out.println("->EndTurn[]");

        System.out.println("For all Model.Area");
        System.out.println("->Step[]");
        for (Area a : map.areas) a.step();
        System.out.println("<-Step[]");

        System.out.println("For all Model.PlayableCharacter");
        System.out.println("->Step[]");
        System.out.println("<-Step[]");

        System.out.println("<-EndTurn[m]");
    }

    /**
     * A pálya alapállásának kialakítása
     */
    private void CreateInitialMap() {
        if (!getInstance().GameRunning) return;

        map = new Map();

        System.out.println("->CreateInitialMap[]");

        Fountain f = new Fountain();
        Cistern c = new Cistern();
        Pipe p1 = new Pipe();
        Pipe p2 = new Pipe();

        f.setCoordinate(30, 30);
        c.setCoordinate(500, 500);
        p1.setCoordinate(500, 30);
        p2.setCoordinate(30, 500);

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
        c.Connect(p1);

        window.drawComponents();
    }


    /**
     * Saves the game's actual state
     *
     * Use the LoadGame(Window) to load
     */
    public void SaveGame() {
        try {
            FileWriter fw = new FileWriter("saveState.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Area area : map.areas) {
                bw.write(area.SavableState());
                bw.newLine();
            }
            for (PlayableCharacter character : map.playableCharacters) {
                bw.write(character.SavableState());
                bw.newLine();
            }
            bw.close();
            fw.close();
            System.out.println("Controller.Game saved");
        } catch (IOException e) {
            System.out.println("Save failed");
            System.out.println(e);
        }
    }

    /**
     * Load the game saved with SaveGame()
     * @param w The window which should show the game's state
     */
    public void LoadGame(Window w) {
        try {
            Game.getInstance().StartGame(w);
            RestoreMap();
            RestoreConnections();
            getWindow().drawComponents();
            System.out.println("Loaded game restored");
        } catch (IOException e) {
            System.out.println("Failed to load game");
            System.out.println(e);
        }
    }

    /**
     * Reads the file named saveState.txt and creates the map accordingly
     *
     * @throws IOException
     */
    private void RestoreMap() throws IOException {
        FileReader fr = new FileReader("saveState.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();

        map = new Map();

        while (line != null) {
            String playerType = null, playerId = null, areaType = null, areaId = null;
            int slipperyTimer = 0, stickyTimer = 0, brokenTimer = 0, maxCapacity = 0, waterLevel = 0, x = 0, y = 0;
            boolean broken = false;

            StringTokenizer st = new StringTokenizer(line, ",");
            while (st.hasMoreTokens()) {
                StringTokenizer tokenizer = new StringTokenizer(st.nextToken(), ":");

                String label = tokenizer.nextToken();
                String value = tokenizer.nextToken();

                switch (label) {
                    case "areaType":
                        areaType = value;
                        break;
                    case "playerType":
                        playerType = value;
                        break;
                    case "areaId":
                        areaId = value;
                        break;
                    case "playerId":
                        playerId = value;
                        break;
                    case "slipperyTimer":
                        slipperyTimer = Integer.parseInt(value);
                        break;
                    case "stickyTimer":
                        stickyTimer = Integer.parseInt(value);
                        break;
                    case "brokenTimer":
                        brokenTimer = Integer.parseInt(value);
                        break;
                    case "broken":
                        broken = true;
                        break;
                    case "maxCapacity":
                        maxCapacity = Integer.parseInt(value);
                        break;
                    case "waterLevel":
                        waterLevel = Integer.parseInt(value);
                        break;
                    case "x":
                        x = Integer.parseInt(value);
                        break;
                    case "y":
                        y = Integer.parseInt(value);
                        break;
                }
            }
            if (areaType != null) switch (areaType) {
                case "Model.Fountain":
                    Fountain fountain = new Fountain();
                    fountain.setID(Integer.parseInt(areaId));
                    fountain.setCoordinate(x, y);
                    map.AddArea(fountain);
                    break;
                case "Model.Cistern":
                    Cistern cistern = new Cistern();
                    cistern.setID(Integer.parseInt(areaId));
                    cistern.setCoordinate(x, y);
                    map.AddArea(cistern);
                    break;
                case "Model.Pipe":
                    Pipe pipe = new Pipe();
                    pipe.setID(Integer.parseInt(areaId));
                    pipe.setCoordinate(x, y);
                    if (slipperyTimer > 0) pipe.setSlipperyTimer(slipperyTimer);
                    if (stickyTimer > 0) pipe.setStickyTimer(stickyTimer);
                    if (brokenTimer > 0) pipe.setBrokenTimer(brokenTimer);
                    if (broken) pipe.Break();
                    if (maxCapacity > 0) pipe.setMaxCapacity(maxCapacity);
                    if (waterLevel > 0) pipe.setWaterLevel(waterLevel);
                    map.AddArea(pipe);
                    break;
                case "Model.Pump":
                    Pump pump = new Pump();
                    pump.setID(Integer.parseInt(areaId));
                    pump.setCoordinate(x, y);
                    map.AddArea(pump);
                    break;
            }
            if (playerType != null) switch (playerType) {
                case "Model.Repairman":
                    Repairman repairman = new Repairman(map.getAreabyID(Integer.parseInt(areaId)));
                    repairman.setID(Integer.parseInt(playerId));
                    if (line.contains("hasPump:true")) repairman.pipe = new Pipe();
                    if (line.contains("hasPipe:true")) repairman.pump = new Pump();
                    map.AddPlayer(repairman);
                    break;

                case "Model.Saboteur":
                    Saboteur saboteur = new Saboteur(map.getAreabyID(Integer.parseInt(areaId)));
                    saboteur.setID(Integer.parseInt(playerId));
                    map.AddPlayer(saboteur);
                    break;
            }
            line = br.readLine();
        }
        br.close();
        fr.close();
    }

    /**
     * Sets up the connections between areas
     *
     * @throws IOException
     */
    private void RestoreConnections() throws IOException {
        FileReader fr = new FileReader("saveState.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();

        while (line != null) {
            String areaId = null;
            ArrayList<String> connectingAreas = new ArrayList<>();
            ArrayList<String> players = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(line, ",");
            while (st.hasMoreTokens()) {
                StringTokenizer tokenizer = new StringTokenizer(st.nextToken(), ":");

                String label = tokenizer.nextToken();
                String value = tokenizer.nextToken();

                switch (label) {
                    case "areaId":
                        areaId = value;
                        break;
                    case "playerId":
                        players.add(value);
                        break;
                    case "connectedAreaId":
                        connectingAreas.add(value);
                        break;
                }
            }

            Area currentArea = map.getAreabyID(Integer.parseInt(areaId));
            if (currentArea != null) {
                for (String connectingArea : connectingAreas) {
                    currentArea.Connect(map.getAreabyID(Integer.parseInt(connectingArea)));
                }
                if (players != null) {
                    for(String player: players)
                    currentArea.addPlayer(map.getPlayerbyID(Integer.parseInt(player)));
                }
            }
            line = br.readLine();
        }
        br.close();
        fr.close();
    }
}
