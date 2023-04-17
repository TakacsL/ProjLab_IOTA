/**
 * Szabotőr karakter. Olyan játszható karakter, akinek a célja, hogy minél több víz elfolyjon a sivatagba. A csapatának a pontjai az elfolyt vizek hatására nőnek. Ha cső mezőn áll, akkor ki tudja lyukasztani a csövet, ezzel elfolyik belőle a víz, amíg meg nem javítja valaki.
 */

public class Saboteur extends PlayableCharacter{
    void BreakArea(){
        System.out.println("->Saboteur.BreakArea()");
    	System.out.println("->[a1].Break()");
        GetArea().Break();
    	System.out.println("<-[a1].Break()");
        System.out.println("<-Saboteur.BreakArea()");
    }
    
    public String toString() {return "[saboteur]";}
}
