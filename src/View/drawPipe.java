package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class drawPipe extends Drawable{



    /**
     * Constructor
     * @param idOfModel ID tagja a model p�rj�nak
     * @param x megjelen�t�shez haszn�lt x koordin�ta
     * @param y megjelen�t�shez haszn�lt y koordin�ta
     * super h�v�s ut�n bet�ltj�k a k�pet, ami az icon lesz
     */
    public drawPipe(int idOfModel, int x, int y){
        super(idOfModel, x, y);
        component = new JButton();
    }

    /**
     * Visszaadja a megjelen�t�shez haszn�lt swing komponenst
     */
    @Override
    public JButton draw() {
        component.setBounds(0,0,0,0);
        return component;
    }
}
