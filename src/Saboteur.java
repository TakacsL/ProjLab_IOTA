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

	/*
	 * Annak a csõnek a kilyukasztása, amelyen a karakter áll.
	 */
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
}
