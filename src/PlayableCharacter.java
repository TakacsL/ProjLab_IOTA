import java.util.List;

/**
 * Karakterek gyûjtõ osztálya, minden játékosnak egy 
 * ilyen karaktert kell irányítania a játék folyamán. 
 * Képes mozogni a megadott mezõkön, illetve, ha pumpán 
 * van, akkor átállíthatja annak a konfigurációját.
 * */

public abstract class PlayableCharacter {
	/*
	 * Az az elem, amelyen a karakter áll.
	 */
	Area a1;

	//A karakter egyedülálló azonosítója, id
	private int ID;
	//statikus számláló, ami egyrészt számon tartja az karakterek számát,
	// de az ID létrehozásában használjuk, minden használatnál nõ egyel az értéke
	private static int numOfIDs = 0;

	//getted for ID
	public int getID() {
		return ID;
	}

	public void setID(int id) {
		this.ID = id;
	}

	/*
	 * konstruktor, skeleton miatt a1 inicianilázása
	 */
	public PlayableCharacter(Area a) {
		// TODO Auto-generated constructor stub
		a1 = a;
		if (!a1.AcceptCharacter(this)) a1 = null;		//if a1 can't accept then not on map
		ID = numOfIDs++;
	}

	/*
	* A játékos ennyi ideig nem tud mozogni
	 */
	private int stuckTimer = 0;

	/*
	* A játékos nem mozgási idejét beállítja
	 */
	public void setStuckTimer(){
		stuckTimer = 3;
	}

	/*
	* Visszadja mennyi ideig nem tud mozogni még a játékos
	 */
	public int getStuckTimer(){
		return stuckTimer;
	}

	/*
	 *  A karakter átlép egy másik elemre. 
	 */
	public void MoveTo(Area a2){

		if (getStuckTimer() == 0) {
			if (this.GetArea().connectedAreas.contains(a2)){				//checks if new area is connected to the current
				System.out.println("->" + toString() + ".MoveTo[a2]");
				System.out.println("->[a1].RemoveCharacter[character]");
				Boolean result = a1.RemoveCharacter(this);
				System.out.println("<-[a1].RemoveCharacter[character]");

				//Ha a rálépni kívánt mezõ csúszós állapotban van, akkor egy szomszédos mezõre kerül.
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
			else System.out.println("Cant move to an area which is not adjacent");
		} else System.out.println("Player cannot move for " + getStuckTimer() + " turns");
	}

	/*
	 * A csõrendszer egyik elemének a felvéte, üres fv
	 */
	public void PickupPipe(Pipe p) {

	}

	/*
	 * a1 getter
	 */
	protected Area GetArea(){
		return a1;
	}

	/*
	 * A pumpa be- és kimenetének átállítása.
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
	 * A kiválasztott tevékenység végrehajtása.
	 */
	public void step() {}

	/*
	 * Annak a területnek a megjavítása, amelyen a karakter áll.
	 */
	abstract void FixArea();

	/*
	 * konzolra írást segítõ fv
	 */
	public String toString() {return "[PlayableCharacter]";}

	/*
	* A csövön beállítja a ragadós állapotot
	 */

	public void makeSticky(Area a){
		a.setStickyTimer();
	}

	public abstract String SavableState();
	/*
	* Annak a csõnek a kilyukasztása, amelyen a karakter áll.
	 */
	abstract void BreakArea();
}
