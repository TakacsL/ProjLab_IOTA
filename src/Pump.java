public class Pump extends Area{
    private int maxCapacity;
    private int waterLevel;
    private boolean broken;
    private Area input;
    private Area output;

    @Override
    void Connect(Area a) {
    	this.connectedAreas.add(a);
    }
    
    @Override
    public String toString() {return "[pump]"; }

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
    public void SetInput(Area a) {
        System.out.println("->Pump.SetInput[Area]");
        if(connectedAreas.contains(a)){
            input = a;
        }
        System.out.println("<-Pump.SetInput[Area]");
    }

    @Override
    public void SetOutput(Area a) {
        System.out.println("->Pump.SetOutput[Area]");
        if(connectedAreas.contains(a)){
            output = a;
        }
        System.out.println("<-Pump.SetOutput[Area]");
    }
    
    @Override
    void PlacePump(Pump p) {
    	this.Connect(new Pipe());
        System.out.println("->[" + toString() +".ConnectedAreas[0]].Disconnect(a1)");
        this.getConnectedAreas().get(0).Disconnect(this);
        System.out.println("<-[" + toString() +".ConnectedAreas[0]].Disconnect(a1)");
        System.out.println("+New Pipe Created");
        Pipe newPipe = new Pipe();
        System.out.println("->[newPipe].Connect(a1)");
        newPipe.Connect(this);
        System.out.println("<-[newPipe].Connect(a1)");
        System.out.println("->[newPipe].Connect(pump)");
        newPipe.Connect(p);
        System.out.println("<-[newPipe].Connect(pump)");
        System.out.println("->[pump].SetInput(a1)");
        p.SetInput(this);
        System.out.println("<-[pump].SetInput(a1)");
        System.out.println("->[pump].SetOutput(newPipe)");
        p.SetOutput(newPipe);
        System.out.println("<-[pump].SetOutput(newPipe)");
        System.out.println("->" + toString() +".Connect(p)");
        this.Connect(p);
        System.out.println("<-" + toString() +".Connect(p)");
        
    }
}
