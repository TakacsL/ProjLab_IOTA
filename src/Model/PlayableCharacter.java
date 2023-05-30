package Model;

import Controller.Game;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Karakterek gy�jt� oszt�lya, minden j�t�kosnak egy 
 * ilyen karaktert kell ir�ny�tania a j�t�k folyam�n. 
 * K�pes mozogni a megadott mez�k�n, illetve, ha pump�n 
 * van, akkor �t�ll�thatja annak a konfigur�ci�j�t.
 * */

public abstract class PlayableCharacter {
	/*
	 * Az az elem, amelyen a karakter �ll.
	 */
	Area a1;

	//A karakter egyed�l�ll� azonos�t�ja, id
	private int ID;
	//statikus sz�ml�l�, ami egyr�szt sz�mon tartja az karakterek sz�m�t,
	// de az ID l�trehoz�s�ban haszn�ljuk, minden haszn�latn�l n� egyel az �rt�ke
	private static int numOfIDs = 0;

	//getted for ID
	public int getID() {
		return ID;
	}

	public void setID(int id) {
		this.ID = id;
	}

	/*
	 * konstruktor, skeleton miatt a1 inicianil�z�sa
	 */
	public PlayableCharacter(Area a) {
		// TODO Auto-generated constructor stub
		a1 = a;
		if (!a1.AcceptCharacter(this)) a1 = null;		//if a1 can't accept then not on map
		ID = numOfIDs++;
	}

	/*
	* A j�t�kos ennyi ideig nem tud mozogni
	 */
	private int stuckTimer = 0;

	/*
	* A j�t�kos nem mozg�si idej�t be�ll�tja
	 */
	public void setStuckTimer(){
		stuckTimer = 3;
	}

	/*
	* Visszadja mennyi ideig nem tud mozogni m�g a j�t�kos
	 */
	public int getStuckTimer(){
		return stuckTimer;
	}

	/*
	 *  A karakter �tl�p egy m�sik elemre. 
	 */
	public void MoveTo(Area a2){

		if (getStuckTimer() == 0) {
			if (this.GetArea().connectedAreas.contains(a2)){				//checks if new area is connected to the current
				System.out.println("->" + toString() + ".MoveTo[a2]");
				System.out.println("->[a1].RemoveCharacter[character]");
				Boolean result = a1.RemoveCharacter(this);
				System.out.println("<-[a1].RemoveCharacter[character]");

				//Ha a r�l�pni k�v�nt mez� cs�sz�s �llapotban van, akkor egy szomsz�dos mez�re ker�l.
				Area slippery_area = new Area();
				if (a2.Slippery()) {
					List<Area> areas = a2.getConnectedAreas();
					for (int i = 0; i < areas.size(); i++) {
						if (areas.get(i) != a2) {
							slippery_area = areas.get(i);
							Boolean result3 = slippery_area.AcceptCharacter(this);
							System.out.println("a1 Succesfully removed character");
							System.out.println("->[a2].Slippery()");
							Boolean result4 = slippery_area.AcceptCharacter(this);
							System.out.println("->[" + slippery_area + "].AcceptCharacter[character]");
							if (!result4) System.out.println(slippery_area + "Failed to accept");
							else System.out.println(slippery_area + "Succesfully accepted character");
							System.out.println("<-" + toString() + ".MoveTo[a2]");
							if (slippery_area.Sticky()) setStuckTimer();
						}
					}
				}

				if (a2.Slippery()) return;

				if (!result) System.out.println("a1 Failed to remove, returning");
				else {
					System.out.println("a1 Succesfully removed character");
					System.out.println("->[a2].AcceptCharacter[character]");
					Boolean result2 = a2.AcceptCharacter(this);
					System.out.println("<-[a2].AcceptCharacter[character]");
					if (!result2) System.out.println("a2 Failed to accept");
					else {
						System.out.println("a2 Succesfully accepted character");
						if (a2.Sticky()) setStuckTimer();
					}
				}
				System.out.println("<-" + toString() + ".MoveTo[a2]");
			}
			else {
				System.out.println("Cant move to an area which is not adjacent");
				Game.getInstance().getWindow().showFailedMessage();
			}
		} else {
			System.out.println("Player cannot move for " + getStuckTimer() + " turns");
			Game.getInstance().getWindow().showFailedMessage();
		}
	}

	/*
	 * A cs�rendszer egyik elem�nek a felv�te, �res fv
	 */
	public void PickupPipe(Pipe p) {}

	/*
	 * a1 getter
	 */
	public Area GetArea(){
		return a1;
	}

	/**
	 * @return a string to view pc type
	 */
	public String getViewString(){return "PlayableCharacter";}

	/*
	 * A pumpa be- �s kimenet�nek �t�ll�t�sa.
	 */
	public void SetPumpConfiguration(Area a1, Area a2){
		if(GetArea().getConfigureOptions().size() > 0)
		System.out.println("->" + toString() + ".SetPumpConfiguration("+ a1 + ", " + a2 +")");
		GetArea().SetInput(a1);
		GetArea().SetOutput(a2);
		System.out.println("<-" + toString() + ".SetPumpConfiguration("+ a1 + ", " + a2 +")");
	}

	/*
	 * A kiv�lasztott tev�kenys�g v�grehajt�sa.
	 */
	public void step() {}

	/*
	 * Annak a ter�letnek a megjav�t�sa, amelyen a karakter �ll.
	 */
	public abstract void FixArea();

	/*
	 * konzolra �r�st seg�t� fv
	 */
	public String toString() {return "[Model.PlayableCharacter]";}

	/*
	* A cs�v�n be�ll�tja a ragad�s �llapotot
	 */

	public void makeSticky(){
		GetArea().setStickyTimer();
	}

	public abstract String SavableState();
	/*
	* Annak a cs�nek a kilyukaszt�sa, amelyen a karakter �ll.
	 */
	public abstract void BreakArea();

	public void makeSlippery(){}

	public JLabel draw() {
		JLabel component = new JLabel();
		component.setBounds(a1.x, a1.y - 50, 150, 66);
		component.setBorder(BorderFactory.createEmptyBorder());
		component.setVerticalTextPosition(SwingConstants.CENTER);
		component.setHorizontalTextPosition(SwingConstants.CENTER);
		component.setForeground(Color.GREEN);

		return component;
	};
}
