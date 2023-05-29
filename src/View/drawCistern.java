package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class drawCistern extends Drawable{

    /**
     * Constructor
     * @param idOfModel ID tagja a model párjának
     * @param x megjelenítéshez használt x koordináta
     * @param y megjelenítéshez használt y koordináta
     * super hívás után betöltjük a képet, ami az icon lesz
     */
    public drawCistern(int idOfModel, int x, int y){
        super(idOfModel, x, y);
        component = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResourceAsStream("/Assets/Cistern.png"));
            icon = new ImageIcon(img);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        component.setIcon(icon);

    }

    /**
     * Visszaadja a megjelenítéshez használt swing komponenst
     */
    @Override
    public JButton draw() {
        component.setBounds(x, y, 66, 66);
        component.setBorder(BorderFactory.createEmptyBorder());
        return component;
    }
}
