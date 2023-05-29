package Controller;

import Model.*;
import View.Window;
import View.drawCistern;
import View.drawFountain;
import View.drawPipe;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public final class Game {

	//singleton m�k�d�s megval�s�t�sa
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

    public Window getWindow() {return window;}



    //v�lzot� ami alapj�n tudjuk k�vetni a j�t�k jelenlegi �llapot�t
    private boolean GameRunning = false;

    //getter for Gamerunning
    public boolean isGameRunning() {
        return GameRunning;
    }
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
    public void StartGame(Window w){
        window = w;
        GameRunning = true;
        getInstance().CreateInitialMap();
    }

    //A j�t�k befejez�se.
    public void EndGame(){
        System.out.println("Points are Model.Saboteur : " + saboteurPoints + " vs. Model.Repairman : " + repairmanPoints);
        if (saboteurPoints != repairmanPoints) System.out.println((saboteurPoints > repairmanPoints ? "Model.Saboteur" : "Model.Repairman") + " team won!");
        else System.out.println("Controller.Game ended in a draw!");
        GameRunning = false;
    }

    //A k�r befejez�se, az elemek l�ptet�se.
    public void EndTurn(){
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

    //A p�lya alap�ll�s�nak kialak�t�sa
    private void CreateInitialMap(){
        if (!getInstance().GameRunning) return;

        map = new Map();

        System.out.println("->CreateInitialMap[]");

        Fountain f = new Fountain();
        drawFountain df = new drawFountain(f.getID(), 30, 30);
        Cistern c = new Cistern();
        drawCistern dc = new drawCistern(c.getID(), 500, 500);
        Pipe p1 = new Pipe();
        drawPipe dp1 = new drawPipe(p1.getID(), 500, 30);
        Pipe p2 = new Pipe();
        drawPipe dp2 = new drawPipe(p2.getID(), 30, 500);

        map.AddArea(f);
        window.addComponent(df);
        map.AddArea(c);
        window.addComponent(dc);
        map.AddArea(p1);
        window.addComponent(dp1);
        map.AddArea(p2);
        window.addComponent(dp2);

        Repairman r1 = new Repairman(f);
        Repairman r2 = new Repairman(c);
        Saboteur s1 = new Saboteur(p1);
        Saboteur s2 = new Saboteur(p2);

        map.AddPlayer(r1);
        window.addPlayableCharacter(r1);
        map.AddPlayer(r2);
        window.addPlayableCharacter(r2);
        map.AddPlayer(s1);
        window.addPlayableCharacter(s1);
        map.AddPlayer(s2);
        window.addPlayableCharacter(s2);

        f.Connect(p1);
        p1.Connect(p2);
        p2.Connect(c);
        c.Connect(p1);

        window.drawComponents();

    }

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

    public void LoadGame(Window w) {
        try {
            Game.getInstance().StartGame(w);
            RestoreMap();
            RestoreConnections();
            System.out.println("Loaded game restored");
        } catch (IOException e) {
            System.out.println("Failed to load game");
            System.out.println(e);
        }
    }

    private void RestoreMap() throws IOException {
        FileReader fr = new FileReader("saveState.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();

        map = new Map();

        while (line != null) {
            String playerType = null, playerId = null, areaType = null, areaId = null;
            int slipperyTimer = 0, stickyTimer = 0, brokenTimer = 0, maxCapacity = 0, waterLevel = 0;
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
                }
            }
            if (areaType != null) switch (areaType) {
                case "Model.Fountain":
                    Fountain fountain = new Fountain();
                    fountain.setID(Integer.parseInt(areaId));
                    map.AddArea(fountain);
                    break;
                case "Model.Cistern":
                    Cistern cistern = new Cistern();
                    cistern.setID(Integer.parseInt(areaId));
                    map.AddArea(cistern);
                    break;
                case "Model.Pipe":
                    Pipe pipe = new Pipe();
                    pipe.setID(Integer.parseInt(areaId));
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

    private void RestoreConnections() throws IOException {
        FileReader fr = new FileReader("saveState.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();

        while (line != null) {
            String areaId = null, player = null;
            ArrayList<String> connectingAreas = new ArrayList<>();

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
                        player = value;
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
                if (player != null) {
                    currentArea.setPlayer(map.getPlayerbyID(Integer.parseInt(player)));
                }
            }
            line = br.readLine();
        }
        br.close();
        fr.close();
    }
}