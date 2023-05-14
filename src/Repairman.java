/*
 * V�zvezet�k szerel� karakter. Olyan j�tszhat� karakter, 
 * akinek a c�lja, hogy min�l t�bb v�z eljusson a csisztern�k 
 * valamelyik�be, �s ek�zben min�l kevesebb folyjon el. K�pes 
 * megjav�tani a cs�vet, vagy a pump�t, ha ilyen mez�k�n �ll, 
 * �s azok �ppen hib�sak. Ha ciszterna mez�n �ll, akkor vehet 
 * fel egy pump�t, amit egy cs� mez�n tud lehelyezni, ezzel k�t 
 * r�szre osztva a cs�vet.

 */

public class Repairman extends PlayableCharacter{
	/*
	 * pumpa p�ld�ny, amit felvett a karakter
	 */
	public Pump p;

    public Repairman(Area a) {
        super(a);
    }

    /*
	 * Annak a ter�letnek a megjav�t�sa, amelyen a karakter �ll.
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
     * Cs� elem elhelyez�se a p�ly�n.
     */
    void PlacePipe(Pipe pipe){
        System.out.println("->" + toString() + ".PlacePipe[]");
    	System.out.println("->[a1].Connect(pipe)");
        GetArea().Connect(pipe);
    	System.out.println("<-[a1].Connect(pipe)");
        System.out.println("<-" + toString() + ".PlacePipe[]");
    }
    /*
     * Pumpa elem elhelyez�se a p�ly�n.
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
     * A cs�rendszer egyik nyel� elem�nek a felv�te.
     */
    public void PickupArea(Cistern c) {
        System.out.println("->" + toString() + ".PickupArea(c)");
        System.out.println("->c.PickupPump()");
        p = c.PickupPump();
        System.out.println("<-c.PickupPump()");
        System.out.println("<-" + toString() + ".PickupArea(c)");
    }
    /*
     * A cs�rendszer egyik cs� elem�nek a felv�te.
     */
    public void PickupArea(Pipe p) {
        System.out.println("->" + toString() + ".PickupArea(p)");
        System.out.println("->p.Disconnect(a1)");
        System.out.println("<-p.Disconnect(a1)");
        System.out.println("<-" + toString() + ".PickupArea(p)");
    }
    
	/*
	 * konzolra �r�st seg�t� fv
	 */
    @Override
    public String toString() {return "[Repairman]ID : " + getID() + " on [Area]ID : " + a1.getID();}

    /*
    * Annak a cs�nek a kilyukaszt�sa, amelyen a karakter �ll.
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
