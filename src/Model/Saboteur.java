package Model;

import javax.swing.*;
import java.awt.*;

/**
 * Szabot�r karakter. Olyan j�tszhat� karakter, akinek a c�lja, 
 * hogy min�l t�bb v�z elfolyjon a sivatagba. A csapat�nak a 
 * pontjai az elfolyt vizek hat�s�ra n�nek. Ha cs� mez�n �ll, 
 * akkor ki tudja lyukasztani a cs�vet, ezzel elfolyik bel�le 
 * a v�z, am�g meg nem jav�tja valaki.
 * */

public class Saboteur extends PlayableCharacter{
	public Saboteur(Area a) {
		super(a);
	}

	/**
	 * Fix the current area on which the saboteur is standing
	 */
	@Override
	public void FixArea() {
		System.out.println("->Model.Saboteur.FixArea[]");
		System.out.println("I cant do this");
		System.out.println("<-Model.Saboteur.FixArea[]");}

	/**
	 * Annak a cs�nek a kilyukaszt�sa, amelyen a karakter �ll.
	 */
	@Override
	public void BreakArea(){
        System.out.println("->Model.Saboteur.BreakArea()");
    	System.out.println("->[a1].Break()");
        GetArea().Break();
    	System.out.println("<-[a1].Break()");
        System.out.println("<-Model.Saboteur.BreakArea()");
    }
    
	/**
	 * Konzolra �r�st seg�t� fv
	 */
    public String toString() {return "[Model.Saboteur]ID : " + getID() + " on [Model.Area]ID : " + a1.getID();}

	/**
	 * Makes the area slippery on which the saboteur is standing
	 */
	public void makeSlippery(){
		GetArea().setSlipperyTimer();
	}

	/**
	 * Creates a string that holds information about the saboteur's state
	 * @return state string
	 */
	public String SavableState() {
		String res = "playerType:Model.Saboteur,playerId:" + getID() + ",areaId:" + a1.getID() + ",";
		return res;
	}

	/**
	 * @return a string to view pc type
	 */
	@Override
	public String getViewString(){
		return "Saboteur";
	}

	/**
	 * Creates a component that represents this Saboteur
	 * @return component representing this saboteur
	 */
	@Override
	public JLabel draw() {
		JLabel component = super.draw();
		component.setText("Saboteur Id: " + getID());

		return component;
	}
}
