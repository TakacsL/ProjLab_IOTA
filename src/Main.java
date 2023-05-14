import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	/*
	 * A menüelemek konzolra írása függvénybe kiszervezve
	 */
   
    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }
    
    /*
     * Konzol képernyő törlése függvénybe kiszervezve
     */
    public static void cls(){
        try {
        	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception ex) {
        	System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        String[] options = {"1.  Move",
                            "2.  Map Init",
                            "3.  End turn",
                            "4.  Show map",
                            "5.  Place pipe",
                            "6.  Fix pipe",
                            "7.  Pick up Pipe",
                            "8.  Wreck Pipe",
                            "9.  Fix pump",
                            "10. Place pump",
                            "11. Set pump",
                            "12. Create pipe",
                            "13. Wreck Pump",
                            "14. Pick up Pump",
                            "15. Exit"
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
            	/*
            	 * A játékos a karaktert mozgatni tudja.
            	 * A játékos a karaktert egyik mezőről a másikra mozgatja.
            	 */
                if (!Game.getInstance().isGameRunning()) {
                    System.out.println("Game not started, please init first");
                }
                else {
                    Game.getInstance().map.printAreas();
                    Game.getInstance().map.printPlayers();
                    System.out.println("Give ID of player");
                    int playerID = scanner.nextInt();
                    System.out.println("Give ID of Area to move to");
                    int areaID = scanner.nextInt();
                    Game.getInstance().map.getPlayerbyID(playerID).MoveTo(Game.getInstance().map.getAreabyID(areaID));
                }
            	/*PlayableCharacter PC = new PlayableCharacter();
            	Area a2 = new Area();
            	PC.MoveTo(a2);*/
                scanner.nextLine();scanner.nextLine();
              break;
            case 2:
            /**
             * A controller a játék elején inicializálja a mezőt, amelyen a játékosok interakciókat végezhetnek.
             */
                Game.getInstance().StartGame();
                System.out.println("Game initiated, let's play");
                scanner.nextLine();scanner.nextLine();
              break;
            case 3:
            /**
             * A controller meghívja a kör végét. Az összes Step() függvénnyel rendelkező entitás Step() függvénye meghívódik.
             */
                Game.getInstance().EndTurn();
                scanner.nextLine();scanner.nextLine();
                break;
            case 4:
            	/*
            	 * A játékos megtekinti a pályát.
            	 */
                if (Game.getInstance().isGameRunning()) {
                    Game.getInstance().map.printPlayers();
                    Game.getInstance().map.printAreas();
                }
                else System.out.println("Game not started, please init first");

                scanner.nextLine();scanner.nextLine();
                break;
            case 5:
            /**
             * A szerelőkkel lévő játékos elhelyez a pályán egy csövet, amelynek legalább az egyik vége csatlakozik egy másik elemhez.
             */
                /*Repairman r = new Repairman();
                Pipe pipe = new Pipe();
                r.PlacePipe(pipe);*/
                scanner.nextLine();scanner.nextLine();
                break;
            case 6:
            /**
             * A szerelőkkel lévő játékos megjavít egy megrongált csövet.
             */
                /*Repairman r2 = new Repairman();
                Pipe pipe2 = new Pipe();
                r2.MoveTo(pipe2);
                r2.FixArea();*/
                scanner.nextLine();scanner.nextLine();
                break;
            case 7:
            	/*
            	 * A játékos felvesz egy csövet.
            	 * A szerelőkkel lévő játékos felvesz egy, a pályán lévő csövet.
            	 */
                /*Area a70 = new Area();
                Repairman r70 = new Repairman();
                Pipe p70 = new Pipe();
                p70.Disconnect(a70);
                System.out.println("->GetArea().Disconnect[p70]");
                r70.PickupArea(p70);*/
                scanner.nextLine();scanner.nextLine();
                break;
            case 8:
            /**
             * A szabotőrökkel lévő játékos megrongál egy, a pályán lévő csövet.
             */
                /*Saboteur s = new Saboteur();
                Pipe pipe3 = new Pipe();
                s.MoveTo(pipe3);
                s.BreakArea();*/
                scanner.nextLine();scanner.nextLine();
                break;
            case 9:
            /** 
             * A szerelőkkel lévő játékos megjavítja az elromlott pumpát.
             */
                /*Repairman r3 = new Repairman();
                Pump pump = new Pump();
                r3.MoveTo(pump);
                r3.FixArea();*/
                scanner.nextLine();scanner.nextLine();
                break;
            case 10:
            /**
             * A szerelőkkel lévő játékos elhelyez egy pumpát, amelyhez egy csövön kell állnia a pályán.
             */
                /*Repairman r4 = new Repairman();
                Pipe pTarget = new Pipe();
                Pump pump2 = new Pump();
                r4.PlacePump(pump2);*/
                scanner.nextLine();scanner.nextLine();
                break;
            case 11:
            /**
             * A játékos átállítja a pumpát, hogy melyik csőből melyik csőbe folyjon a víz.
             */
                /*Repairman r5 = new Repairman();
                Pump pump3 = new Pump();
                Pipe pipe4 = new Pipe();
                Pipe pipe5 = new Pipe();
                r5.MoveTo(pump3);
                r5.SetPumpConfiguration(pipe4, pipe5);*/
                scanner.nextLine();scanner.nextLine();
                break;
            case 12:
            	/*
            	 * A controller létrehoz egy csövet.
            	 * A controller folyamatosan létrehoz a 
            	 * forrásokon újabb csöveket, amelyeket a szerelők fel tudnak venni.
            	 */
                Fountain f = new Fountain();
                Pipe pipe6 = f.PickupPipe();
                scanner.nextLine();scanner.nextLine();
                break;
            case 13:
            	/*
            	 * A controller megrongálja a pumpát.
            	 * A controller véletlenszerű időközönként megrongál egy,
            	 *  a pályán lévő pumpát, amelyet a szerelőknek meg kell javítani.
            	 */
                /*Saboteur s2 = new Saboteur();
                Pump pump4 = new Pump();
                s2.MoveTo(pump4);
                s2.BreakArea();*/
                scanner.nextLine();scanner.nextLine();
                break;
            case 14:
            	/*
            	 * pumpa felvétele
            	 * karbantartó felvesz egy pump elemet a nyelő mezőn
            	 * amin áll. 
            	 */
            	/*Repairman rm = new Repairman();
                Cistern c = new Cistern();
                rm.PickupArea(c);*/
                scanner.nextLine();scanner.nextLine();
                break;
            case 15:
            	/*
            	 * kilépés a menüvezérelt részből, a program jelenlegi verziójában 
            	 * a futtatás befejezése
            	 */
                System.out.println("Exiting program now...");
                Game.getInstance().EndGame();
                NotFinished = false;
                break;
           
            default:
          }
        }
        
        scanner.close();
    }
}