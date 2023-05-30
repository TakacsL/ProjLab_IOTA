package Model;


import javax.swing.*;
import java.awt.*;

/**
 * V�zvezet�k szerel� karakter. Olyan j�tszhat� karakter,
 * akinek a c�lja, hogy min�l t�bb v�z eljusson a csisztern�k
 * valamelyik�be, �s ek�zben min�l kevesebb folyjon el. K�pes
 * megjav�tani a cs�vet, vagy a pump�t, ha ilyen mez�k�n �ll,
 * �s azok �ppen hib�sak. Ha ciszterna mez�n �ll, akkor vehet
 * fel egy pump�t, amit egy cs� mez�n tud lehelyezni, ezzel k�t
 * r�szre osztva a cs�vet.

 */
public class Repairman extends PlayableCharacter{
	/**
	 * pumpa p�ld�ny, amit felvett a karakter
	 */
	public Pump pump;

    /**
     * A szerel� �ltal felvett cs�
     */
    public Pipe pipe;

    /**
     * Constructs a repairman
     * @param a area where the repairman should be placed
     */
    public Repairman(Area a) {
        super(a);
    }

    /**
	 * Annak a ter�letnek a megjav�t�sa, amelyen a karakter �ll.
	 */
    public void FixArea(){
        System.out.println("->Model.Repairman.FixArea[]");
    	System.out.println("->[a1].Fix()");
        GetArea().Fix();
    	System.out.println("<-[a1].Fix()");
        System.out.println("<-Model.Repairman.FixArea[]");
    }
    /**
     * Cs� elem elhelyez�se a p�ly�n.
     */
    void PlacePipe(){
        if(pipe == null) return;
        System.out.println("->" + toString() + ".PlacePipe[]");
        pipe.Connect(GetArea());
        pipe = null;
        System.out.println("<-" + toString() + ".PlacePipe[]");
    }
    /**
     * Pumpa elem elhelyez�se egy cs� elemre.
     */
    void PlacePump(){
        if(pump == null) return;
        System.out.println("->" + toString() + ".PlacePump()");
        a1.PlacePump(pump);
        pump = null;
        System.out.println("<-" + toString() + ".PlacePump()");
    }

    /**
     * Picking up a pump
     */
    void PickupPump(){
        System.out.println("->" + toString() + ".PickupPump()");
        pump = a1.PickupPump();
        System.out.println("<-" + toString() + ".PickupPump()");
    }

    /**
     * A cs�rendszer egyik cs� elem�nek a felv�te.
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

	/**
	 * konzolra �r�st seg�t� fv
	 */
    @Override
    public String toString() {return "[Model.Repairman]ID : " + getID() + " on [Model.Area]ID : " + a1.getID();}

    /**
    * Annak a cs�nek a kilyukaszt�sa, amelyen a karakter �ll.
     */
    public void BreakArea(){
        System.out.println("->Model.Saboteur.BreakArea()");
        System.out.println("->[a1].Break()");
        GetArea().Break();
        System.out.println("<-[a1].Break()");
        System.out.println("<-Model.Saboteur.BreakArea()");
    }

    /**
     * Create a component based on the repairman
     * @return component which represents a repairman
     */
    @Override
    public JLabel draw() {
        JLabel component = super.draw();
        component.setText("Repairman Id: " + getID());

        return component;
    }

    /**
     * Create a string that represents the Repairman's state
     * @return state string
     */
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
