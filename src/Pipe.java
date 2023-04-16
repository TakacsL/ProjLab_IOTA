import java.util.List;

public class Pipe extends Area{
    private int maxCapacity;
    private int waterLevel;
    private boolean broken;

    @Override
    void Connect(Area a){
        if(connectedAreas.size()>=2){
            System.out.println("Pipe cannot connect to more than 2 areas, returning");
            return;
        }

        connectedAreas.add(a);
    }

    @Override
    void Fix(){
        broken = true;
    }

    @Override
    void Break() {
        broken = false;
    }
}
