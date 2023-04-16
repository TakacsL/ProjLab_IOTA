import java.util.List;
import java.util.Scanner;

public class Area {
	
	PlayableCharacter player;
	List<Area> connectedAreas;

	public Area() {
		// TODO Auto-generated constructor stub
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

	void Fix(){}

	void Break(){}
	
	public String toString() {return "[:Area]";}

	public void SetInput(Pipe p) {
		p.Connect(this);
	}

	public void SetOutput(Pipe p) {
		p.Connect(this);
	}
}
