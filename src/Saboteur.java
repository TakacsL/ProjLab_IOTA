public class Saboteur extends PlayableCharacter{
    void BreakArea(){
        System.out.println("->Saboteur.BreakArea()");
        GetArea().Break();
        System.out.println("<-Saboteur.BreakArea()");
    }
}
