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
        System.out.println("->Pump.Fix[]");
        broken = true;
        System.out.println("<-Pump.Fix[]");
    }

    @Override
    void Break() {
        System.out.println("->Pump.Break[]");
        broken = false;
        System.out.println("<-Pump.Break[]");
    }

    @Override
    public void SetInput(Pipe p) {
        System.out.println("->Pump.SetInput[Pipe]");
        if(connectedAreas.contains(p)){
            input = p;
        }
        System.out.println("<-Pump.SetInput[Pipe]");
    }

    @Override
    public void SetOutput(Pipe p) {
        System.out.println("->Pump.SetOutput[Pipe]");
        if(connectedAreas.contains(p)){
            output = p;
        }
        System.out.println("<-Pump.SetOutput[Pipe]");
    }
}
