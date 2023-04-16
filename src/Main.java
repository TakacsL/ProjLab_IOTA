import java.util.Scanner;

public class Main {
   
    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }
    
    public static void cls(){
        try {

        	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception ex) {
        	System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        String[] options = {"1.  Mozg�s",
                            "2.  Map Init",
                            "3.  End turn",
                            "4.  P�lya megjelen�t�se",
                            "5.  Cs� elhelyez�s",
                            "6.  Cs� jav�t�s",
                            "7.  Cs� felszerel�s",
                            "8.  Cs� rong�l�s",
                            "9.  Pumpa jav�t�s",
                            "10. Pumpa elhelyez�s",
                            "11. Pumpa �ll�t�sa",
                            "12. Cs� l�trehoz�sa",
                            "13. Pumpa rong�l�sa",
                            "14. Kil�p�s"
        };
        Scanner scanner = new Scanner(System.in);
        int option;
        Boolean NotFinished = true;
        while (NotFinished){
        	cls();
            printMenu(options);
            option = scanner.nextInt();
            try {System.out.println("Chosen item was : " + options[option - 1]);}
            catch (ArrayIndexOutOfBoundsException e) {              
            	System.out.println("Please choose a valid menu item!");
            	scanner.nextLine();scanner.nextLine();
            }        	
            switch(option) {
            case 1:
            	PlayableCharacter PC = new PlayableCharacter();
            	Area a2 = new Area();
            	PC.MoveTo(a2);
                scanner.nextLine();scanner.nextLine();
              break;
            case 2:
            /**
             * A controller a játék elején inicializálja a mezőt, amelyen a játékosok interakciókat végezhetnek.
             */
                System.out.println("Not yet implemented");
                scanner.nextLine();scanner.nextLine();
              break;
            case 3:
            /**
             * A controller meghívja a kör végét. Az összes Step() függvénnyel rendelkező entitás Step() függvénye meghívódik.
             */
                System.out.println("Not yet implemented");
                scanner.nextLine();scanner.nextLine();
                break;
            case 4:
                System.out.println("Not yet implemented");
                scanner.nextLine();scanner.nextLine();
                break;
            case 5:
            /**
             * A szerelőkkel lévő játékos elhelyez a pályán egy csövet, amelynek legalább az egyik vége csatlakozik egy másik elemhez.
             */
                Repairman r = new Repairman();
                Pipe pipe = new Pipe();
                r.PlaceArea(pipe);
                scanner.nextLine();scanner.nextLine();
                break;
            case 6:
            /**
             * A szerelőkkel lévő játékos megjavít egy megrongált csövet.
             */
                Repairman r2 = new Repairman();
                Pipe pipe2 = new Pipe();
                r2.MoveTo(pipe2);
                r2.FixArea();
                scanner.nextLine();scanner.nextLine();
                break;
            case 7:

                scanner.nextLine();scanner.nextLine();
                break;
            case 8:
            /**
             * A szabotőrökkel lévő játékos megrongál egy, a pályán lévő csövet.
             */
                Saboteur s = new Saboteur();
                Pipe pipe3 = new Pipe();
                s.MoveTo(pipe3);
                s.BreakArea();
                scanner.nextLine();scanner.nextLine();
                break;
            case 9:
            /** 
             * A szerelőkkel lévő játékos megjavítja az elromlott pumpát.
             */
                Repairman r3 = new Repairman();
                Pump pump = new Pump();
                r3.MoveTo(pump);
                r3.FixArea();
                scanner.nextLine();scanner.nextLine();
                break;
            case 10:
            /**
             * A szerelőkkel lévő játékos elhelyez egy pumpát, amelyhez egy csövön kell állnia a pályán.
             */
                Repairman r4 = new Repairman();
                Pipe pTarget = new Pipe();
                Pump pump2 = new Pump();
                r4.PlaceArea(pump2);
                scanner.nextLine();scanner.nextLine();
                break;
            case 11:
            /**
             * A játékos átállítja a pumpát, hogy melyik csőből melyik csőbe folyjon a víz.
             */
                Repairman r5 = new Repairman();
                Pump pump3 = new Pump();
                Pipe pipe4 = new Pipe();
                Pipe pipe5 = new Pipe();
                r5.MoveTo(pump3);
                r5.SetPumpConfiguration(pipe4, pipe5);
                scanner.nextLine();scanner.nextLine();
                break;
            case 12:
                System.out.println("Not yet implemented");
                scanner.nextLine();scanner.nextLine();
                break;
            case 13:
                Saboteur s2 = new Saboteur();
                Pump pump4 = new Pump();
                s2.MoveTo(pump4);
                s2.BreakArea();
                scanner.nextLine();scanner.nextLine();
                break;
            case 14:
                System.out.println("Exiting program now...");
                NotFinished = false;
                break;
           
            default:
          }
        }
        
        scanner.close();
    }
}