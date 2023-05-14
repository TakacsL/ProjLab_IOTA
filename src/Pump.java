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
    public String toString() {return "[Pump]ID : " + getID();}

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

    public String SavableState() {
        String res = "areaType:Pump,areaId:" + getID() + ",";
        if (player != null) res += "playerId:" + player.getID() + ",";
        if (broken) res += "broken:" + true + ",";
        if (maxCapacity > 0) res += "maxCapacity:" + maxCapacity + ",";
        if (waterLevel > 0) res += "waterLevel:" + waterLevel + ",";
        for (Area area : connectedAreas) {
            res += "connectedAreaId:" + area.getID() + ",";
        }
        return res;
    }

    public Pump() {
        super();
        System.out.println("Create " + this.toString() + ": " + getID());
    }

}
