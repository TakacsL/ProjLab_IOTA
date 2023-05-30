package Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * A forrás mezõt reprezentálja. A hozzá csatlakoztatott elemekbe víz áramlik.
 */
public class Fountain extends Area {

    /**
     * Az elemen lévõ csövet továbbítja a szerelõnek aki fel akarja venni azt.
     */
    public Pipe PickupPipe() {
        return new Pipe();
    }

    /**
     * Az elemhez egy újabb elem csatlakoztatása.
     */
    @Override
    public void Connect(Area a) {
        if (connectedAreas.contains(a)) return;
        System.out.println("->" + toString() + ".Connect[" + a.toString() + "]");
        a.Connect(this);
        if (connectedAreas.contains(a)) return;
        if (a.connectedAreas.contains(this))
            connectedAreas.add(a);
        System.out.println("<-" + toString() + ".Connect[" + a.toString() + "]");
    }

    /**
     * Adds a water unit for each adjacent area
     */
    @Override
    public void step() {
        for (Area a : connectedAreas)
            a.addWaterLevel(this);
    }

    /**
     * AddWaterLevel override, specific for this type of area
     * Has no use this class
     */
    @Override
    public void addWaterLevel(Area AreaFrom) {
    }

    /**
     * konzolra írást segítõ fv
     */
    @Override
    public String toString() {
        return "[Model.Fountain] ID : " + getID();
    }

    /**
     *
     * @return
     */
    public String SavableState() {
        String res = "areaType:Model.Fountain,areaId:" + getID() + ",";
        res += "x:" + x + ",y:" + y + ",";
        for (var player:players) {
            res += "playerId:" + player.getID() + ",";
        }
        for (Area area : connectedAreas) {
            res += "connectedAreaId:" + area.getID() + ",";
        }
        return res;
    }

    /**
     * Constructs fountain object
     */
    public Fountain() {
        super();
        System.out.println("Create " + this.toString());
    }

    /**
     * Creates a component that represents this fountain's state
     * @return component representing the fountain
     */
    @Override
    public JComponent draw() {
        JButton component = (JButton) super.draw();

        try {
            Image img = ImageIO.read(getClass().getResourceAsStream("/Assets/Fountain.png"));
            component.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return component;
    }
}
