import java.util.List;


/**
* Cső mező. A két vége forráshoz, ciszternához, vagy pumpához van kötve. Két állapota van, lyukas, vagy működőképes. Egy karakter tartózkodhat rajta egyszerre. A bemenet, és kimenet csövek között víz folyhat, ha nem lyukas a cső. A csövek mozgathatók, bármelyik végét le lehet csatlakoztatni, és elvinni máshova
*/
public class Pipe extends Area{
    private int maxCapacity;
    private int waterLevel;
    private boolean broken;

    @Override
    void Connect(Area a){
        System.out.println("->Pipe.Connect["+a.toString()+"]");
        if(connectedAreas.size()>=2){
            System.out.println("Pipe cannot connect to more than 2 areas, returning");
            return;
        }

        connectedAreas.add(a);
        System.out.println("<-Pipe.Connect["+a.toString()+"]");
    }

    @Override
    void Fix(){
        System.out.println("->Pipe.Fix[]");
        broken = true;
        System.out.println("<-Pipe.Fix[]");
    }

    @Override
    void Break() {
        System.out.println("->Pipe.Break[]");
        broken = false;
        System.out.println("<-Pipe.Break[]");
    }
    
    
    @Override
	public String toString() {return "[Pipe]"; }
}
