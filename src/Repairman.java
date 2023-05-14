/*
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
	public Pump p;

    public Repairman(Area a) {
        super(a);
    }

    /*
	 * Annak a területnek a megjavítása, amelyen a karakter áll.
	 */
    @Override
    void FixArea(){
        System.out.println("->Repairman.FixArea[]");
    	System.out.println("->[a1].Fix()");
        GetArea().Fix();
    	System.out.println("<-[a1].Fix()");
        System.out.println("<-Repairman.FixArea[]");
    }
    /*
     * Csõ elem elhelyezése a pályán.
     */
    void PlacePipe(Pipe pipe){
        System.out.println("->" + toString() + ".PlacePipe[]");
    	System.out.println("->[a1].Connect(pipe)");
        GetArea().Connect(pipe);
    	System.out.println("<-[a1].Connect(pipe)");
        System.out.println("<-" + toString() + ".PlacePipe[]");
    }
    /*
     * Pumpa elem elhelyezése a pályán.
     */
    void PlacePump(Pump pump){
        System.out.println("->" + toString() + ".PlacePump()");
        this.PickupArea(new Cistern());
        System.out.println("->p.PlacePump(pump)");
        p.PlacePump(pump);
        System.out.println("<-p.PlacePump(pump)");
        System.out.println("<-" + toString() + ".PlacePump()");
    }
    
    /*
     * A csõrendszer egyik nyelõ elemének a felvéte.
     */
    public void PickupArea(Cistern c) {
        System.out.println("->" + toString() + ".PickupArea(c)");
        System.out.println("->c.PickupPump()");
        p = c.PickupPump();
        System.out.println("<-c.PickupPump()");
        System.out.println("<-" + toString() + ".PickupArea(c)");
    }
    /*
     * A csõrendszer egyik csõ elemének a felvéte.
     */
    public void PickupArea(Pipe p) {
        System.out.println("->" + toString() + ".PickupArea(p)");
        System.out.println("->p.Disconnect(a1)");
        System.out.println("<-p.Disconnect(a1)");
        System.out.println("<-" + toString() + ".PickupArea(p)");
    }
    
	/*
	 * konzolra írást segítõ fv
	 */
    @Override
    public String toString() {return "[Repairman]ID : " + getID() + " on [Area]ID : " + a1.getID();}

    /*
    * Annak a csõnek a kilyukasztása, amelyen a karakter áll.
     */
    @Override
    void BreakArea(){
        System.out.println("->Repairman.BreakArea()");
        System.out.println("->[a1].Break()");
        GetArea().Break();
        System.out.println("<-[a1].Break()");
        System.out.println("<-Repairman.BreakArea()");
    }
}
