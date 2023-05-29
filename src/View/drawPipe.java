package View;

import Model.Area;

import javax.accessibility.Accessible;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * Csõ megjelenítésére használt
 * Nem használjuk benne a component változót, mert 2D-s razoláshoz nem használható a JButton
 * //TODO: oldd meg
 */
public class drawPipe extends Drawable{

    /**
     * Constructor
     * @param idOfModel ID tagja a model párjának
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
    public Component draw() {
        return component;
    }
}
