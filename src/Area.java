import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Area {
	
	PlayableCharacter player;
	List<Area> connectedAreas;

	public List<Area> getConnectedAreas() {
		return connectedAreas;
	}

	public Area() {
		// TODO Auto-generated constructor stub
		connectedAreas = new ArrayList<Area>();
	}
	
	public Boolean RemoveCharacter(PlayableCharacter character) {
        Scanner scanner = new Scanner(System.in);
		System.out.println("Can I remove? (Y/N)");
        if (scanner.next().charAt(0) == 'Y') {
        	return true;
        }
        else {
    		return false;
        }
	}
	
	public Boolean AcceptCharacter(PlayableCharacter character) {
        Scanner scanner = new Scanner(System.in);
		System.out.println("Can I accept? (Y/N)");
        if (scanner.next().charAt(0) == 'Y') {
        	return true;
        }
        else {
    		return false;
        }
	}

	void Connect(Area a){}
	
	public void Disconnect(Area a1){}

	void Fix(){}
	
	void PlacePump(Pump p) {}

	void Break(){}
	
	public String toString() {return "[Area]";}

	public void SetInput(Area a) {
		a.Connect(this);
	}

	public void SetOutput(Area a) {
		a.Connect(this);
	}
}
