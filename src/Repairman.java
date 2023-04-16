public class Repairman extends PlayableCharacter{

    void PlaceArea(Area a){
        GetArea().Connect(a);
    }

    void FixArea(){
        GetArea().Fix();
    }
}
