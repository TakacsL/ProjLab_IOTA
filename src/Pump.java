/**
Pumpa mez�. T�bb bek�t�tt cs�ve lehet, ezek k�z�l minden 
pillanatban egy bemen�, �s egy kimen� cs�ve van. Ezt a k�t 
cs�vet minden j�t�kos tudja �ll�tani, aki rajta van a pumpa
mez�n. V�letlen id�k�z�nk�nt elromolhat. Vagy a j�t�k elej�n 
fix helyen vannak, vagy v�zvezet�k szerel� karakterek helyezhetik le.
 */

public class Pump extends Area{
	/*
	 * maxim�lis mennyis�g� v�z, amit t�rolni tud, eg�sz sz�mban
	 */
    private int maxCapacity;
    /*
     * jelenlegi v�zszint, eg�sz sz�mban
     */
    private int waterLevel;
    /*
     * �llapot t�rol�
     */
    private boolean broken;
    /*
     * Area ahonnan fogad vizet
     */
    private Area input;
    /*
     * Area ahov� ad vizet
     */
    private Area output;


    /*
     * Az elemhez egy �jabb elem csatlakoztat�sa.
     */
    @Override
    void Connect(Area a){
        System.out.println("->Pump.Connect["+a.toString()+"]");
        connectedAreas.add(a);
        System.out.println("<-Pump.Connect["+a.toString()+"]");
    }
    
	/*
	 * konzolra �r�st seg�t� fv
	 */
    @Override
    public String toString() {return "[pump]"; }

    /*
     * A hib�s pumpa megjav�t�sa.
     */
    @Override
    void Fix(){
        System.out.println("->Pump.Fix[]");
        broken = true;
        System.out.println("<-Pump.Fix[]");
    }

    /*
     * 	 * Az elem lyukaszt�sa
     */
    @Override
    void Break() {
        System.out.println("->Pump.Break[]");
        broken = false;
        System.out.println("<-Pump.Break[]");
    }

    /*
     * A pumpa bemenet�nek kiv�laszt�sa.
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
     * A pumpa kimenet�nek kiv�laszt�sa.
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
     * Pumpa mez� lehelyez�se az els� szomsz�dra
     * Repairman h�vja meg
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
