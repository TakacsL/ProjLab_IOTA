package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class drawPipe extends Drawable{



    /**
     * Constructor
     * @param idOfModel ID tagja a model párjának
     * @param x megjelenítéshez használt x koordináta
     * @param y megjelenítéshez használt y koordináta
     * super hívás után betöltjük a képet, ami az icon lesz
     */
    public drawPipe(int idOfModel, int x, int y){
        super(idOfModel, x, y);
        component = new JButton();
    }

    /**
     * Visszaadja a megjelenítéshez használt swing komponenst
     */
    @Override
    public JButton draw() {
        component.setBounds(0,0,0,0);
        return component;
    }
}
