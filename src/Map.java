import java.util.ArrayList;
import java.util.List;

public class Map {

    public List<Area> areas = new ArrayList<Area>();
    public List<PlayableCharacter> playableCharacters;


    void AddArea(Area a){

        areas.add(a);
        System.out.println("->AddArea["+a.toString()+"]");
        System.out.println("<-AddArea["+a.toString()+"]");
    }
    void RemoveArea(Area a){

        areas.remove(a);
        System.out.println("->RemoveArea["+a.toString()+"]");
        System.out.println("<-RemoveArea["+a.toString()+"]");
    }
}
