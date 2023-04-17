/**
 * Vízvezeték szerelő karakter. Olyan játszható karakter, akinek a célja, hogy minél több víz eljusson a csiszternák valamelyikébe, és eközben minél kevesebb folyjon el. Képes megjavítani a csövet, vagy a pumpát, ha ilyen mezőkön áll, és azok éppen hibásak. Ha ciszterna mezőn áll, akkor vehet fel egy pumpát, amit egy cső mezőn tud lehelyezni, ezzel két részre osztva a csövet.
 */

public class Repairman extends PlayableCharacter{
	public Pump p;

    void FixArea(){
        System.out.println("->Repairman.FixArea[]");
    	System.out.println("->[a1].Fix()");
        GetArea().Fix();
    	System.out.println("<-[a1].Fix()");
        System.out.println("<-Repairman.FixArea[]");
    }
    void PlacePipe(Pipe pipe){
        System.out.println("->" + toString() + ".PlacePipe[]");
    	System.out.println("->[a1].Connect(pipe)");
        GetArea().Connect(pipe);
    	System.out.println("<-[a1].Connect(pipe)");
        System.out.println("<-" + toString() + ".PlacePipe[]");
    }
    
    void PlacePump(Pump pump){
        System.out.println("->" + toString() + ".PlacePump()");
        this.PickupArea(new Cistern());
        System.out.println("->p.PlacePump(pump)");
        p.PlacePump(pump);
        System.out.println("<-p.PlacePump(pump)");
        System.out.println("<-" + toString() + ".PlacePump()");
    }
    
    public void PickupArea(Cistern c) {
        System.out.println("->" + toString() + ".PickupArea(c)");
        System.out.println("->c.PickupPump()");
        p = c.PickupPump();
        System.out.println("<-c.PickupPump()");
        System.out.println("<-" + toString() + ".PickupArea(c)");
    }
    public void PickupArea(Pipe p) {
        System.out.println("->" + toString() + ".PickupArea(p)");
        System.out.println("->p.Disconnect(a1)");
        System.out.println("<-p.Disconnect(a1)");
        System.out.println("<-" + toString() + ".PickupArea(p)");
    }
    
    @Override
    public String toString() {return "[repairman]";}
}
