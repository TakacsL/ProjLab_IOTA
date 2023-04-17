
/**
 * Karakterek gyûjtõ osztálya, minden játékosnak egy 
 * ilyen karaktert kell irányítania a játék folyamán. 
 * Képes mozogni a megadott mezõkön, illetve, ha pumpán 
 * van, akkor átállíthatja annak a konfigurációját.
 * */

public class PlayableCharacter {
	/*
	 * Az az elem, amelyen a karakter áll.
	 */
	Area a1;

	/*
	 * konstruktor, skeleton miatt a1 inicianilázása
	 */
	public PlayableCharacter() {
		// TODO Auto-generated constructor stub
		a1 = new Area();
	}
	
	
	/*
	 *  A karakter átlép egy másik elemre. 
	 */
	public void MoveTo(Area a2){
		System.out.println("->" + toString() + ".MoveTo[a2]");
		System.out.println("->[a1].RemoveCharacter[character]");
		Boolean result = a1.RemoveCharacter(this);
		System.out.println("<-[a1].RemoveCharacter[character]");
		if (!result) System.out.println("a1 Failed to remove, returning");
		else {
			System.out.println("a1 Succesfully removed character");
			System.out.println("->[a2].AcceptCharacter[character]");
			Boolean result2 = a2.AcceptCharacter(this);
			System.out.println("<-[a2].AcceptCharacter[character]");
			if (!result2) System.out.println("a2 Failed to accept");
			else System.out.println("a2 Succesfully accepted character");
		}
		
		System.out.println("<-" + toString() + ".MoveTo[a2]");
	}
	
	/*
	 * A csõrendszer egyik elemének a felvéte, üres fv
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
	 * konzolra írást segítõ fv
	 */
	public String toString() {return "[PlayableCharacter]";}

}
