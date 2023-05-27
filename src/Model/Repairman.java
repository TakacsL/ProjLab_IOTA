package Model;/*
 * Vízvezeték szerelõ karakter. Olyan játszható karakter, 
 * akinek a célja, hogy minél több víz eljusson a csiszternák 
 * valamelyikébe, és eközben minél kevesebb folyjon el. Képes 
 * megjavítani a csövet, vagy a pumpát, ha ilyen mezõkön áll, 
 * és azok éppen hibásak. Ha ciszterna mezõn áll, akkor vehet 
 * fel egy pumpát, amit egy csõ mezõn tud lehelyezni, ezzel két 
 * részre osztva a csövet.

 */

public class Repairman extends PlayableCharacter{
	/*
	 * pumpa példány, amit felvett a karakter
	 */
	public Pump pump;

    /*
     * A szerelõ által felvett csõ
     */
    public Pipe pipe;

    public Repairman(Area a) {
        super(a);
    }

    /*
	 * Annak a területnek a megjavítása, amelyen a karakter áll.
	 */
    public void FixArea(){
        System.out.println("->Model.Repairman.FixArea[]");
    	System.out.println("->[a1].Fix()");
        GetArea().Fix();
    	System.out.println("<-[a1].Fix()");
        System.out.println("<-Model.Repairman.FixArea[]");
    }
    /*
     * Csõ elem elhelyezése a pályán.
     */
    void PlacePipe(){
        if(pipe == null) return;
        System.out.println("->" + toString() + ".PlacePipe[]");
        pipe.Connect(GetArea());
        pipe = null;
        System.out.println("<-" + toString() + ".PlacePipe[]");
    }
    /*
     * Pumpa elem elhelyezése egy csõ elemre.
     */
    void PlacePump(){
        if(pump == null) return;
        System.out.println("->" + toString() + ".PlacePump()");
        a1.PlacePump(pump);
        pump = null;
        System.out.println("<-" + toString() + ".PlacePump()");
    }

    void PickupPump(){
        System.out.println("->" + toString() + ".PickupPump()");
        pump = a1.PickupPump();
        System.out.println("<-" + toString() + ".PickupPump()");
    }

    /*
     * A csõrendszer egyik csõ elemének a felvéte.
     */
    public void PickupPipe(Pipe p) {
        if(!a1.getConnectedAreas().contains(p)) return;
        System.out.println("->" + toString() + ".PickupPipe(" + p + ")");
        p.SetInput(null);
        p.SetOutput(null);
        for(int i = 0; i < p.getConnectedAreas().size(); i++) {
            p.Disconnect(p.getConnectedAreas().get(i));
        }
        pipe = p;
        System.out.println("<-" + toString() + ".PickupPipe(" + p + ")");
    }

	/*
	 * konzolra írást segítõ fv
	 */
    @Override
    public String toString() {return "[Model.Repairman]ID : " + getID() + " on [Model.Area]ID : " + a1.getID();}

    /*
    * Annak a csõnek a kilyukasztása, amelyen a karakter áll.
     */
    public void BreakArea(){
        System.out.println("->Model.Saboteur.BreakArea()");
        System.out.println("->[a1].Break()");
        GetArea().Break();
        System.out.println("<-[a1].Break()");
        System.out.println("<-Model.Saboteur.BreakArea()");
    }

    public String SavableState() {
        String res = "playerType:Model.Repairman,playerId:" + getID() + ",areaId:" + a1.getID() + ",";
        if (pump != null) res += "hasPump:true,";
        if (pipe != null) res += "hasPipe:true,";
        return res;
    }

    /**
     * @return a string to view pc type
     */
    @Override
    public String getViewString(){
        return "Repairman";
    }
}
