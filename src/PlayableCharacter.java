
public class PlayableCharacter {
	Area a1;

	public PlayableCharacter() {
		// TODO Auto-generated constructor stub
		a1 = new Area();
	}
	
	
	
	public void MoveTo(Area a2){
		System.out.println("->" + toString() + '.' + "MoveTo[a2]");
		System.out.println("->[a1].RemoveCharacter[character]");
		Boolean result = a1.RemoveCharacter(this);
		System.out.println("<-[a1].RemoveCharacter[character]");
		if (!result) System.out.println("a1 Failed to remove, returning");
		else {
			System.out.println("a1 Succesfully removed character");
			System.out.println("->[a2].AcceptCharacter[character]");
			Boolean result2 = a2.AcceptCharacter(this);
			System.out.println("<-[a2].AcceptCharacter[character]");
			if (!result2) System.out.println("a2 Failed to accept");
			else System.out.println("a2 Succesfully accepted character");
		}
		
		System.out.println("<-" + toString() + '.' + "MoveTo[a2]");
	}

	protected Area GetArea(){
		return a1;
	}

	public void SetPumpConfiguration(Pipe p1, Pipe p2){
		System.out.println("->PlayableCharacter.SetPumpConfiguration[Pipe, Pipe]");
		GetArea().SetInput(p1);
		GetArea().SetOutput(p2);
		System.out.println("<-PlayableCharacter.SetPumpConfiguration[Pipe, Pipe]");
	}
	
	public String toString() {return "[:PlayableCharacter]";}

}
