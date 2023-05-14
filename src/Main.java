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
                            "6.  Fix area",
                            "7.  Pick up Pipe",
                            "8.  Wreck area",
                            "9. Place pump",
                            "10. Set pump",
                            "11. Create pipe",
                            "12. Controller Wreck Pump",
                            "13. Pick up Pump",
                            "14. Make Area Sticky",
                            "15. Make Area Slimy",
                            "16. Exit"
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
                if (Game.getInstance().isGameRunning()) {
                    Game.getInstance().map.printPlayers();
                    Game.getInstance().map.printAreas();
                    System.out.println("Give ID of player");
                    int playerID = scanner.nextInt();
                    Game.getInstance().map.getPlayerbyID(playerID).FixArea();
                }
                else System.out.println("Game not started, please init first");
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
                if (Game.getInstance().isGameRunning()) {
                    Game.getInstance().map.printPlayers();
                    Game.getInstance().map.printAreas();
                    System.out.println("Give ID of player");
                    int playerID = scanner.nextInt();
                    Game.getInstance().map.getPlayerbyID(playerID).BreakArea();
                }
                else System.out.println("Game not started, please init first");
                scanner.nextLine();scanner.nextLine();
                break;
            case 9:
            /**
             * A szerelőkkel lévő játékos elhelyez egy pumpát, amelyhez egy csövön kell állnia a pályán.
             */
                /*Repairman r4 = new Repairman();
                Pipe pTarget = new Pipe();
                Pump pump2 = new Pump();
                r4.PlacePump(pump2);*/
                scanner.nextLine();scanner.nextLine();
                break;
            case 10:
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
            case 11:
            	/*
            	 * A controller létrehoz egy csövet.
            	 * A controller folyamatosan létrehoz a 
            	 * forrásokon újabb csöveket, amelyeket a szerelők fel tudnak venni.
            	 */
                Fountain f = new Fountain();
                Pipe pipe6 = f.PickupPipe();
                scanner.nextLine();scanner.nextLine();
                break;
            case 12:
            	/*
            	 * A controller megrongálja a pumpát.
            	 * A controller véletlenszerű időközönként(1/4) megrongál egy,
            	 *  a pályán lévő pumpát, amelyet a szerelőknek meg kell javítani.
            	 */
                System.out.println("Should I use random?(Y/N)");
                if (scanner.next().charAt(0) == 'Y') {
                    if (Math.random() * 4 < 1) break;           //25% chance to break, and do nothing
                    int randomID = (int) (Math.random() * Game.getInstance().map.areas.size());     //produces int from 0 to size - 1
                    Game.getInstance().map.getAreabyID(randomID).Break();
                    break;
                }
                Game.getInstance().map.printAreas();
                System.out.println("Give ID of area to break");
                Game.getInstance().map.getAreabyID(scanner.nextInt()).Break();
                scanner.nextLine();scanner.nextLine();
                break;
            case 13:
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
            case 14:            //make area sticky
                if (Game.getInstance().isGameRunning()) {
                    Game.getInstance().map.printPlayers();
                    Game.getInstance().map.printAreas();
                    System.out.println("Give ID of player");
                    int playerID = scanner.nextInt();
                    Game.getInstance().map.getPlayerbyID(playerID).makeSticky(Game.getInstance().map.getPlayerbyID(playerID).GetArea());
                    System.out.println("Made Player " + playerID + " area sticky");
                }
                else System.out.println("Game not started, please init first");
                scanner.nextLine();scanner.nextLine();
                break;
                case 15:            //make area slimy
                    if (Game.getInstance().isGameRunning()) {
                        Game.getInstance().map.printPlayers();
                        Game.getInstance().map.printAreas();
                        System.out.println("Give ID of player");
                        int playerID = scanner.nextInt();
                        Game.getInstance().map.getPlayerbyID(playerID).GetArea().setStickyTimer();
                        System.out.println("Made Player " + playerID + " area sticky");
                    }
                    else System.out.println("Game not started, please init first");
                    scanner.nextLine();scanner.nextLine();
                    break;
            case 16:
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