import java.util.List;

/**
 * Karakterek gy�jt� oszt�lya, minden j�t�kosnak egy 
 * ilyen karaktert kell ir�ny�tania a j�t�k folyam�n. 
 * K�pes mozogni a megadott mez�k�n, illetve, ha pump�n 
 * van, akkor �t�ll�thatja annak a konfigur�ci�j�t.
 * */

public class PlayableCharacter {
	/*
	 * Az az elem, amelyen a karakter �ll.
	 */
	Area a1;

	/*
	 * konstruktor, skeleton miatt a1 inicianil�z�sa
	 */
	public PlayableCharacter() {
		// TODO Auto-generated constructor stub
		a1 = new Area();
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
		} else System.out.println("Player cannot move for " + getStuckTimer() + " turns");
	}

	/*
	 * A cs�rendszer egyik elem�nek a felv�te, �res fv
	 */
	public void PickupArea(Area a) {

	}

	/*
	 * a1 getter
	 */
	protected Area GetArea(){
		return a1;
	}

	/*
	 * A pumpa be- �s kimenet�nek �t�ll�t�sa.
	 */
	public void SetPumpConfiguration(Pipe p1, Pipe p2){
		System.out.println("->" + toString() + ".SetPumpConfiguration[Pipe, Pipe]");
		System.out.println("->[GetArea()].SetInput(p1)");
		GetArea().SetInput(p1);
		System.out.println("<-[GetArea()].SetInput(p1)");
		System.out.println("->[GetArea()].SetOutput(p)");
		GetArea().SetOutput(p2);
		System.out.println("<-[GetArea()].SetOutput(p)");
		System.out.println("<-" + toString() + ".SetPumpConfiguration[Pipe, Pipe]");
	}

	/*
	 * A kiv�lasztott tev�kenys�g v�grehajt�sa.
	 */
	public void step() {}

	/*
	 * konzolra �r�st seg�t� fv
	 */
	public String toString() {return "[PlayableCharacter]";}

	/*
	* A cs�v�n be�ll�tja a ragad�s �llapotot
	 */
	public void makeSticky(Pipe p){
		p.setStickyTimer();
	}

}
