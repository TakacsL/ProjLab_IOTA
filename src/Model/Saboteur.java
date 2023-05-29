package Model;

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
	 *
	 */
	@Override
	public void FixArea() {
		System.out.println("->Model.Saboteur.FixArea[]");
		System.out.println("I cant do this");
		System.out.println("<-Model.Saboteur.FixArea[]");}

	/*
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
    
	/*
	 * konzolra �r�st seg�t� fv
	 */
    public String toString() {return "[Model.Saboteur]ID : " + getID() + " on [Model.Area]ID : " + a1.getID();}

	public void makeSlippery(){
		GetArea().setSlipperyTimer();
	}

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
}