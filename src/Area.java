import java.util.Scanner;

public class Area {
	
	PlayableCharacter player;

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
	
	
	public String toString() {return "[:Area]";}

}
