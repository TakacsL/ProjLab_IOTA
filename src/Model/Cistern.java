package Model;

import Controller.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/*
 * A ciszterna mez�t reprezent�lja.
 * Cs�veket kre�l, amelyeket a rajta
 * �ll� szerel�k fel tudnak venni.
 * Az ide �rkez� v�z a szerel� csapatnak ad pontot.
 */
public class Cistern extends Area {


    /*
     * Az elemen l�v� pump�t tov�bb�tja a szerel�nek aki fel akarja venni azt.
     */
    public Pump PickupPump() {
        System.out.println("->" + toString() + ".PickupPump()");
        System.out.println("<-" + toString() + ".PickupPump()");
        return new Pump();
    }

    /*
     * Az elemhez egy �jabb elem csatlakoztat�sa.
     */
    @Override
    public void Connect(Area a) {
        if (connectedAreas.contains(a)) return;
        System.out.println("->" + toString() + ".Connect[" + a.toString() + "]");
        a.Connect(this);
        if (a.connectedAreas.contains(this))
            connectedAreas.add(a);
        System.out.println("<-" + toString() + ".Connect[" + a.toString() + "]");
    }

    /*
     * konzolra �r�st seg�t� fv
     */
    @Override
    public String toString() {
        return "[Model.Cistern] ID : " + getID();
    }

    public String SavableState() {
        String res = "areaType:Model.Cistern,areaId:" + getID() + ",";
        res += "x:" + x + ",y:" + y + ",";
        for (var player:players) {
            res += "playerId:" + player.getID() + ",";
        }
        for (Area area : connectedAreas) {
            res += "connectedAreaId:" + area.getID() + ",";
        }
        return res;
    }

    public Cistern() {
        super();
        System.out.println("Create " + this.toString() + ": " + getID());
    }

    /**
     *
     */
    @Override
    public void step() {
    }

    /**
     * AddWaterLevel override, specific for this type of area
     * incr repairmans points
     */
    @Override
    public void addWaterLevel(Area AreaFrom) {
        Game.repairmanPoints++;
    }

    /**
     * @return
     */
    @Override
    public JComponent draw() {
        JButton component = (JButton) super.draw();

        try {
            Image img = ImageIO.read(getClass().getResourceAsStream("/Assets/Cistern.png"));
            component.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return component;
    }
}
