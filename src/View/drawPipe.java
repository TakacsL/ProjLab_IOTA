package View;

import Model.Area;

import javax.accessibility.Accessible;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * Cs� megjelen�t�s�re haszn�lt
 * Nem haszn�ljuk benne a component v�ltoz�t, mert 2D-s razol�shoz nem haszn�lhat� a JButton
 * //TODO: oldd meg
 */
public class drawPipe extends Drawable{

    /**
     * Constructor
     * @param idOfModel ID tagja a model p�rj�nak
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
    public Component draw() {
        return component;
    }
}
