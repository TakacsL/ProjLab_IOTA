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
