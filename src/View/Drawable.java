package View;

import javax.swing.*;

public abstract class Drawable {
    /**
     * megjelenítéshez használt swing komponens
     */
    JButton component;

    /**
     * Koordináták a megjelenítéshez
     */
    int x ,y;

    /**
     * ID tagja a hozzá tartozó model elemnek
     */
    int idOfModel;

    /**
     * Constructor
     * @param IDOfModel ID tagja a model párjának
     * @param X megjelenítéshez használt x koordináta
     * @param Y megjelenítéshez használt y koordináta
     */

    public Drawable(int IDOfModel, int X, int Y){
        idOfModel = IDOfModel;
        x = X;
        y = Y;
    }

    ImageIcon icon = null;

    /**
     * Visszaadja a megjelenítéshez használt swing komponenst
     */
    public abstract JButton draw();

}
