import java.util.List;

public class Pipe extends Area{
    private int maxCapacity;
    private int waterLevel;
    private boolean broken;

    @Override
    void Connect(Area a){
        System.out.println("->Pipe.Connect[area]");
        if(connectedAreas.size()>=2){
            System.out.println("Pipe cannot connect to more than 2 areas, returning");
            return;
        }

        connectedAreas.add(a);
        System.out.println("<-Pipe.Connect[Area]");
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
