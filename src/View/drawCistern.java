package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class drawCistern extends Drawable{

    /**
     * Constructor
     * @param idOfModel ID tagja a model p�rj�nak
     * @param x megjelen�t�shez haszn�lt x koordin�ta
     * @param y megjelen�t�shez haszn�lt y koordin�ta
     * super h�v�s ut�n bet�ltj�k a k�pet, ami az icon lesz
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
     * Visszaadja a megjelen�t�shez haszn�lt swing komponenst
     */
    @Override
    public JButton draw() {
        component.setBounds(x, y, 66, 66);
        component.setBorder(BorderFactory.createEmptyBorder());
        return component;
    }
}
