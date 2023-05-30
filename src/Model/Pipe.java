package Model;

import Controller.Game;
import View.PipePanel;

import javax.swing.*;
import java.awt.*;


/**
 * Csõ mezõ. A két vége forráshoz, ciszternához, vagy pumpához van kötve.
 * Két állapota van, lyukas, vagy mûködõképes. Egy karakter tartózkodhat
 * rajta egyszerre. A bemenet, és kimenet csövek között víz folyhat,
 * ha nem lyukas a csõ. A csövek mozgathatók, bármelyik végét le lehet csatlakoztatni, és elvinni máshova
 */
public class Pipe extends Area {

    /*
     * Foltozás után a csõ egy ideig nem romolhat el
     */
    private int brokenTimer;

    /*
     * maradék idõ, ameddig még ragadós
     */
    private int stickyTimer;

    /*
     * maradék idõ, ameddig még csúszós
     */
    private int slipperyTimer;

    /*
     * Az elemhez egy újabb elem csatlakoztatása.
     */
    @Override
    public void Connect(Area a) {
        if (connectedAreas.contains(a)) return;

        System.out.println("->Model.Pipe.Connect[" + a.toString() + "]");
        if (connectedAreas.size() >= 2) {
            System.out.println("Model.Pipe cannot connect to more than 2 areas, returning");
            return;
        }

        connectedAreas.add(a);
        a.Connect(this);
        System.out.println("<-Model.Pipe.Connect[" + a.toString() + "]");
    }

    /*
     * A lyukas csõ megjavítása.
     */
    @Override
    void Fix() {
        System.out.println("->Model.Pipe.Fix[]");
        setBroken(false);
        brokenTimer = 3;
        System.out.println("<-Model.Pipe.Fix[]");
    }

    /*
     * A csõ kilyukasztása.
     */
    @Override
    public void Break() {
        if (brokenTimer == 0) {
            System.out.println("->Model.Pipe.Break[]");
            setBroken(true);
            System.out.println("<-Model.Pipe.Break[]");
        } else System.out.println("The pipe cannot be broken for " + brokenTimer + " turns");
    }

    /*
     * konzolra írást segítõ fv
     */
    @Override
    public String toString() {
        return "[Model.Pipe]ID : " + getID() + (isBroken() ? ", broken" : ", not broken") + (stickyTimer > 0 ? ", sticky" : ", not sticky") + (getSlipperyTimer() > 0 ? ", slippery" : ", not slippery") + (getWaterLevel() > 0 ? ", hasWater" : ", has no water");
    }

    /*
     * Pumpa mezõ lehelyezése a csõre
     * Model.Repairman hívja meg
     */
    @Override
    void PlacePump(Pump p) {
        System.out.println("->" + toString() + ".PlacePump(" + p + ")");
        Pipe newPipe = new Pipe();
        Game.getInstance().map.AddArea(newPipe);
        Game.getInstance().map.AddArea(p);
        p.Connect(this);
        p.Connect(newPipe);
        newPipe.SetInput(this.input);
        p.SetInput(newPipe);
        this.SetInput(p);
        System.out.println("<-" + toString() + ".PlacePump(" + p + ")");
    }


    /*
     * beállítja a csõ ragadós idõzítõjét, ameddig ragadós állapotban marad
     */
    public void setStickyTimer() {
        stickyTimer = 3;
    }

    public void setStickyTimer(int timer) {
        stickyTimer = timer;
    }

    /*
     * Megadja mennyi ideig ragadós még a csõ
     */
    public int getStickyTimer() {
        return stickyTimer;
    }

    /*
     * beállítja a csõ csúszós idõzítõjét, ameddig csúszós állapotban marad
     */
    public void setSlipperyTimer() {
        slipperyTimer = 3;
    }

    public void setSlipperyTimer(int timer) {
        slipperyTimer = timer;
    }

    /*
     * Megadja mennyi ideig csúszós még a csõ
     */
    public int getSlipperyTimer() {
        return slipperyTimer;
    }

    public void setBrokenTimer(int brokenTimer) {
        this.brokenTimer = brokenTimer;
    }

    /*
     * Megadja, hogy a csõ csúszós-e
     */
    @Override
    public boolean Slippery() {
        if (getSlipperyTimer() == 0) return false;
        else return true;
    }

    /*
     * Megadja, hogy a csõ ragadós-e
     */
    @Override
    public boolean Sticky() {
        if (getStickyTimer() == 0) return false;
        else return true;
    }

    public String SavableState() {
        String res = "areaType:Model.Pipe,areaId:" + getID() + ",";
        res += "x:" + x + ",y:" + y + ",";
        for (var player:players) {
            res += "playerId:" + player.getID() + ",";
        }
        if (slipperyTimer > 0) res += "slipperyTimer:" + slipperyTimer + ",";
        if (stickyTimer > 0) res += "stickyTimer:" + stickyTimer + ",";
        if (brokenTimer > 0) res += "brokenTimer:" + brokenTimer + ",";
        if (isBroken()) res += "broken:" + true + ",";
        if (maxCapacity > 0) res += "maxCapacity:" + maxCapacity + ",";
        if (getWaterLevel() > 0) res += "waterLevel:" + getWaterLevel() + ",";
        for (Area area : connectedAreas) {
            res += "connectedAreaId:" + area.getID() + ",";
        }
        return res;
    }

    @Override
    public Boolean AcceptCharacter(PlayableCharacter character) {
        return players.size() == 0 && super.AcceptCharacter(character);
    }

    /**
     * Water flowing. If this has water, then decrease it.
     * If this is broken, then incr. saboteur points, and return
     * else addWaterLevel for each connectedArea
     */
    @Override
    public void step() {
        if (getWaterLevel() > 0) {
            this.setWaterLevel(this.getWaterLevel() - 1);
            if (isBroken()) {
                Game.saboteurPoints++;
                return;
            }
            for (Area a : connectedAreas)
                a.addWaterLevel(this);
        }
    }

    /**
     * AddWaterLevel override, specific for this type of area
     * If not full, incr. WaterLevel
     */
    @Override
    public void addWaterLevel(Area AreaFrom) {
        if (this.getWaterLevel() < this.maxCapacity) this.setWaterLevel(this.getWaterLevel() + 1);
    }

    @Override
    public JComponent draw() {
        String label = "Field Id: " + getID();
        if (isBroken()) label += " Broken";
        if (Slippery()) label += " Slippery";
        if (Sticky()) label += " Sticky";
        PipePanel component = new PipePanel(x - 20, y, x + 20, y, x, y, label);
        if (connectedAreas.size() == 2)
            component = new PipePanel(connectedAreas.get(0).x, connectedAreas.get(0).y, connectedAreas.get(1).x, connectedAreas.get(1).y, x, y, label);

        if (connectedAreas.size() == 1)
            component = new PipePanel(connectedAreas.get(0).x, connectedAreas.get(0).y, x, y, x, y, label);

        component.setBorder(BorderFactory.createEmptyBorder());
        component.setVerticalTextPosition(SwingConstants.CENTER);
        component.setHorizontalTextPosition(SwingConstants.CENTER);
        component.setForeground(Color.RED);

        return component;
    }
}
