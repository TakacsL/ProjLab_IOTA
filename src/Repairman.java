/**
 * Vízvezeték szerelő karakter. Olyan játszható karakter, akinek a célja, hogy minél több víz eljusson a csiszternák valamelyikébe, és eközben minél kevesebb folyjon el. Képes megjavítani a csövet, vagy a pumpát, ha ilyen mezőkön áll, és azok éppen hibásak. Ha ciszterna mezőn áll, akkor vehet fel egy pumpát, amit egy cső mezőn tud lehelyezni, ezzel két részre osztva a csövet.
 */

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
