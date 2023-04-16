public class Repairman extends PlayableCharacter{

    void PlaceArea(Area a){
        System.out.println("->Repairman.PlaceArea[]");
        GetArea().Connect(a);
        System.out.println("<-Repairman.PlaceArea[]");
    }

    void FixArea(){
        System.out.println("->Repairman.FixArea[]");
        GetArea().Fix();
        System.out.println("<-Repairman.FixArea[]");
    }
}
