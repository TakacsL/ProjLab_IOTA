public class Pump extends Area{
    private int maxCapacity;
    private int waterLevel;
    private boolean broken;
    private Pipe input;
    private Pipe output;

    @Override
    void Connect(Area a) {
    }

    @Override
    void Fix(){
        broken = true;
    }

    @Override
    void Break() {
        broken = false;
    }

    @Override
    public void SetInput(Pipe p) {
        if(connectedAreas.contains(p)){
            input = p;
        }
    }

    @Override
    public void SetOutput(Pipe p) {
        if(connectedAreas.contains(p)){
            output = p;
        }
    }
}
