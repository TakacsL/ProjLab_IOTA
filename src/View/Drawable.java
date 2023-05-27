package View;

import javax.swing.*;

public abstract class Drawable {
    /**
     * megjelen�t�shez haszn�lt swing komponens
     */
    JButton component;

    /**
     * Koordin�t�k a megjelen�t�shez
     */
    int x ,y;

    /**
     * ID tagja a hozz� tartoz� model elemnek
     */
    int idOfModel;

    /**
     * Constructor
     * @param IDOfModel ID tagja a model p�rj�nak
     * @param X megjelen�t�shez haszn�lt x koordin�ta
     * @param Y megjelen�t�shez haszn�lt y koordin�ta
     */

    public Drawable(int IDOfModel, int X, int Y){
        idOfModel = IDOfModel;
        x = X;
        y = Y;
    }

    ImageIcon icon = null;

    /**
     * Visszaadja a megjelen�t�shez haszn�lt swing komponenst
     */
    public abstract JButton draw();

}
