package Model;

import Controller.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Pumpa mezõ. Több bekötött csöve lehet, ezek közül minden
 * pillanatban egy bemenõ, és egy kimenõ csöve van. Ezt a két
 * csövet minden játékos tudja állítani, aki rajta van a pumpa
 * mezõn. Véletlen idõközönként elromolhat. Vagy a játék elején
 * fix helyen vannak, vagy vízvezeték szerelõ karakterek helyezhetik le.
 */

public class Pump extends Area {
    /*
     * Az elemhez egy újabb elem csatlakoztatása.
     */
    @Override
    public void Connect(Area a) {
        if (connectedAreas.contains(a)) return;
        System.out.println("->Model.Pump.Connect[" + a.toString() + "]");
        a.Connect(this);
        if (a.connectedAreas.contains(this))
            connectedAreas.add(a);
        System.out.println("<-Model.Pump.Connect[" + a.toString() + "]");
    }

    /*
     * konzolra írást segítõ fv
     */
    @Override
    public String toString() {
        return "[Model.Pump]ID : " + getID() + (getWaterLevel() > 0 ? ", hasWater" : ", has no water");
    }

    /*
     * A hibás pumpa megjavítása.
     */
    @Override
    void Fix() {
        System.out.println("->Model.Pump.Fix[]");
        setBroken(false);
        System.out.println("<-Model.Pump.Fix[]");
    }

    /*
     * 	 * Az elem lyukasztása
     */
    @Override
    void Break() {
        System.out.println("->Model.Pump.Break[]");
        setBroken(true);
        System.out.println("<-Model.Pump.Break[]");
    }


    /*
     * A pumpa kimenetének kiválasztása.
     */
    @Override
    public void SetOutput(Area a) {
        System.out.println("->Model.Pump.SetOutput(" + a + ")");
        if (connectedAreas.contains(a)) {
            output = a;
        }
        System.out.println("<-Model.Pump.SetOutput(" + a + ")");
    }

    public String SavableState() {
        String res = "areaType:Model.Pump,areaId:" + getID() + ",";
        res += "x:" + x + ",y:" + y + ",";
        for (var player:players) {
            res += "playerId:" + player.getID() + ",";
        }
        if (isBroken()) res += "broken:" + true + ",";
        if (maxCapacity > 0) res += "maxCapacity:" + maxCapacity + ",";
        if (getWaterLevel() > 0) res += "waterLevel:" + getWaterLevel() + ",";
        for (Area area : connectedAreas) {
            res += "connectedAreaId:" + area.getID() + ",";
        }
        return res;
    }

    //A játékos lekéri az állítható be- és kimeneti opciókat
    public List<Area> getConfigureOptions() {
        return getConnectedAreas();
    }

    public Pump() {
        super();
        System.out.println("Create " + this + ": " + getID());
    }

    /**
     * If empty, nothhing happens
     * else decrease waterlevel
     * if broken, sabouteur ponits incr
     * else add water to output
     */
    @Override
    public void step() {
        if (getWaterLevel() > 0) {
            this.setWaterLevel(this.getWaterLevel() - 1);
            if (isBroken()) {
                Game.saboteurPoints++;
                return;
            }
            output.addWaterLevel(this);
        }
    }

    /**
     * AddWaterLevel override, specific for this type of area
     * If not full, and input ID is same as caller, incr. WaterLevel
     */
    @Override
    public void addWaterLevel(Area AreaFrom) {
        if (this.getWaterLevel() < this.maxCapacity && AreaFrom.getID() == input.getID())
            this.setWaterLevel(this.getWaterLevel() + 1);
    }

    @Override
    public JComponent draw() {
        JButton component = (JButton) super.draw();
        try {
            Image img = ImageIO.read(getClass().getResourceAsStream("/Assets/Pump.png"));
            component.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return component;
    }
}
