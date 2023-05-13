/**
 * Szabot�r karakter. Olyan j�tszhat� karakter, akinek a c�lja, 
 * hogy min�l t�bb v�z elfolyjon a sivatagba. A csapat�nak a 
 * pontjai az elfolyt vizek hat�s�ra n�nek. Ha cs� mez�n �ll, 
 * akkor ki tudja lyukasztani a cs�vet, ezzel elfolyik bel�le 
 * a v�z, am�g meg nem jav�tja valaki.

 * */

public class Saboteur extends PlayableCharacter{
	/*
	 * Annak a cs�nek a kilyukaszt�sa, amelyen a karakter �ll.
	 */
    void BreakArea(){
        System.out.println("->Saboteur.BreakArea()");
    	System.out.println("->[a1].Break()");
        GetArea().Break();
    	System.out.println("<-[a1].Break()");
        System.out.println("<-Saboteur.BreakArea()");
    }
    
	/*
	 * konzolra �r�st seg�t� fv
	 */
    public String toString() {return "[saboteur]";}

	public void makeSlippery(Pipe p){
		p.setSlipperyTimer();
	}
}
