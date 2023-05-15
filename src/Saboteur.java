/**
 * Szabotõr karakter. Olyan játszható karakter, akinek a célja, 
 * hogy minél több víz elfolyjon a sivatagba. A csapatának a 
 * pontjai az elfolyt vizek hatására nõnek. Ha csõ mezõn áll, 
 * akkor ki tudja lyukasztani a csövet, ezzel elfolyik belõle 
 * a víz, amíg meg nem javítja valaki.

 * */

public class Saboteur extends PlayableCharacter{
	public Saboteur(Area a) {
		super(a);
	}

	/**
	 *
	 */
	@Override
	void FixArea() {
		System.out.println("->Saboteur.FixArea[]");
		System.out.println("I cant do this");
		System.out.println("<-Saboteur.FixArea[]");}

	/*
	 * Annak a csõnek a kilyukasztása, amelyen a karakter áll.
	 */
	@Override
    void BreakArea(){
        System.out.println("->Saboteur.BreakArea()");
    	System.out.println("->[a1].Break()");
        GetArea().Break();
    	System.out.println("<-[a1].Break()");
        System.out.println("<-Saboteur.BreakArea()");
    }
    
	/*
	 * konzolra írást segítõ fv
	 */
    public String toString() {return "[Saboteur]ID : " + getID() + " on [Area]ID : " + a1.getID();}

	public void makeSlippery(Pipe p){
		p.setSlipperyTimer();
	}

	public String SavableState() {
		String res = "playerType:Saboteur,playerId:" + getID() + ",areaId:" + a1.getID() + ",";
		return res;
	}
}
