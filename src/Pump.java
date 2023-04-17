/**
Pumpa mezõ. Több bekötött csöve lehet, ezek közül minden 
pillanatban egy bemenõ, és egy kimenõ csöve van. Ezt a két 
csövet minden játékos tudja állítani, aki rajta van a pumpa
mezõn. Véletlen idõközönként elromolhat. Vagy a játék elején 
fix helyen vannak, vagy vízvezeték szerelõ karakterek helyezhetik le.
 */

public class Pump extends Area{
	/*
	 * maximális mennyiségû víz, amit tárolni tud, egész számban
	 */
    private int maxCapacity;
    /*
     * jelenlegi vízszint, egész számban
     */
    private int waterLevel;
    /*
     * állapot tároló
     */
    private boolean broken;
    /*
     * Area ahonnan fogad vizet
     */
    private Area input;
    /*
     * Area ahová ad vizet
     */
    private Area output;


    /*
     * Az elemhez egy újabb elem csatlakoztatása.
     */
    @Override
    void Connect(Area a){
        System.out.println("->Pump.Connect["+a.toString()+"]");
        connectedAreas.add(a);
        System.out.println("<-Pump.Connect["+a.toString()+"]");
    }
    
	/*
	 * konzolra írást segítõ fv
	 */
    @Override
    public String toString() {return "[pump]"; }

    /*
     * A hibás pumpa megjavítása.
     */
    @Override
    void Fix(){
        System.out.println("->Pump.Fix[]");
        broken = true;
        System.out.println("<-Pump.Fix[]");
    }

    /*
     * 	 * Az elem lyukasztása
     */
    @Override
    void Break() {
        System.out.println("->Pump.Break[]");
        broken = false;
        System.out.println("<-Pump.Break[]");
    }

    /*
     * A pumpa bemenetének kiválasztása.
     */
    @Override
    public void SetInput(Area a) {
        System.out.println("->Pump.SetInput[Area]");
        if(connectedAreas.contains(a)){
            input = a;
        }
        System.out.println("<-Pump.SetInput[Area]");
    }

    /*
     * A pumpa kimenetének kiválasztása.
     */
    @Override
    public void SetOutput(Area a) {
        System.out.println("->Pump.SetOutput[Area]");
        if(connectedAreas.contains(a)){
            output = a;
        }
        System.out.println("<-Pump.SetOutput[Area]");
    }
    
    /*
     * Pumpa mezõ lehelyezése az elsõ szomszédra
     * Repairman hívja meg
     */
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
