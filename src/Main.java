import javax.swing.*;
import java.awt.*;
import View.Window;
import java.util.Scanner;

public class Main {
	/**
	 * A menüelemek konzolra írása függvénybe kiszervezve
	 */
   
    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }
    
    /**
     * Konzol képernyő törlése függvénybe kiszervezve
     */
    public static void cls(){
        try {
        	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception ex) {
        	System.out.println(ex);
        }
    }
/**
 * Entry point of the program
 */
    public static void main(String[] args) {

        Window w = new Window();

        return;
    }

    //régi menüvezérelt működés, kikommentelve
//    public static void main(String[] args) {
//        String[] options = {"1.  Move",
//                "2.  Model.Map Init",
//                "3.  End turn",
//                "4.  Show map",
//                "5.  Place pipe",
//                "6.  Fix area",
//                "7.  Pick up Model.Pipe",
//                "8.  Wreck area",
//                "9. Place pump",
//                "10. Set pump",
//                "11. Create pipe",
//                "12. Controller Wreck Model.Pump",
//                "13. Pick up Model.Pump",
//                "14. Make Model.Area Sticky",
//                "15. Make Model.Area Slippery",
//                "16. Save Controller.Game",
//                "17. Load Controller.Game",
//                "18. End Controller.Game",
//                "19. Exit"
//        };
//        Scanner scanner = new Scanner(System.in);
//        int option;
//        Boolean NotFinished = true;
//        while (NotFinished){
//        	cls();
//            printMenu(options);
//            option = scanner.nextInt();
//            try {System.out.println("Chosen item was : " + options[option - 1]);}
//            catch (ArrayIndexOutOfBoundsException e) {
//            	System.out.println("Please choose a valid menu item!");
//            	scanner.nextLine();scanner.nextLine();
//            }
//            switch(option) {
//            case 1:
//            	/*
//            	 * A játékos a karaktert mozgatni tudja.
//            	 * A játékos a karaktert egyik mezőről a másikra mozgatja.
//            	 */
//                if (!Controller.Game.getInstance().isGameRunning()) {
//                    System.out.println("Controller.Game not started, please init first");
//                }
//                else {
//                    Controller.Game.getInstance().map.printAreas();
//                    Controller.Game.getInstance().map.printPlayers();
//                    System.out.println("Give ID of player");
//                    int playerID = scanner.nextInt();
//                    System.out.println("Give ID of Model.Area to move to");
//                    int areaID = scanner.nextInt();
//                    Controller.Game.getInstance().map.getPlayerbyID(playerID).MoveTo(Controller.Game.getInstance().map.getAreabyID(areaID));
//                }
//            	/*Model.PlayableCharacter PC = new Model.PlayableCharacter();
//            	Model.Area a2 = new Model.Area();
//            	PC.MoveTo(a2);*/
//                scanner.nextLine();scanner.nextLine();
//              break;
//            case 2:
//            /**
//             * A controller a játék elején inicializálja a mezőt, amelyen a játékosok interakciókat végezhetnek.
//             */
//                Controller.Game.getInstance().StartGame();
//                System.out.println("Controller.Game initiated, let's play");
//                scanner.nextLine();scanner.nextLine();
//              break;
//            case 3:
//            /**
//             * A controller meghívja a kör végét. Az összes Step() függvénnyel rendelkező entitás Step() függvénye meghívódik.
//             */
//                Controller.Game.getInstance().EndTurn();
//                scanner.nextLine();scanner.nextLine();
//                break;
//            case 4:
//            	/*
//            	 * A játékos megtekinti a pályát.
//            	 */
//                if (Controller.Game.getInstance().isGameRunning()) {
//                    Controller.Game.getInstance().map.printPlayers();
//                    Controller.Game.getInstance().map.printAreas();
//                }
//                else System.out.println("Controller.Game not started, please init first");
//
//                scanner.nextLine();scanner.nextLine();
//                break;
//            case 5:
//            /**
//             * A szerelőkkel lévő játékos elhelyez a pályán egy csövet
//             */
//                System.out.println("Give the ID of the repairman");
//                Model.Repairman r5 = (Model.Repairman) Controller.Game.getInstance().map.getPlayerbyID(scanner.nextInt());
//                r5.PlacePipe();
//                scanner.nextLine();
//                scanner.nextLine();
//                break;
//            case 6:
//            /**
//             * A szerelőkkel lévő játékos megjavít egy megrongált csövet.
//             */
//                if (Controller.Game.getInstance().isGameRunning()) {
//                    Controller.Game.getInstance().map.printPlayers();
//                    Controller.Game.getInstance().map.printAreas();
//                    System.out.println("Give ID of player");
//                    int playerID = scanner.nextInt();
//                    Controller.Game.getInstance().map.getPlayerbyID(playerID).FixArea();
//                }
//                else System.out.println("Controller.Game not started, please init first");
//                scanner.nextLine();scanner.nextLine();
//                break;
//            case 7:
//            	/*
//            	 * A játékos felvesz egy csövet.
//            	 * A szerelőkkel lévő játékos felvesz egy, a pályán lévő csövet.
//            	 */
//                System.out.println("Give the ID of the Model.Repairman");
//                Model.Repairman r7 = (Model.Repairman) Controller.Game.getInstance().map.getPlayerbyID(scanner.nextInt());
//                System.out.println("Give the ID of the pipe");
//                r7.PickupPipe((Model.Pipe) Controller.Game.getInstance().map.getAreabyID(scanner.nextInt()));
//                scanner.nextLine();
//                scanner.nextLine();
//                break;
//            case 8:
//            /**
//             * A szabotőrökkel lévő játékos megrongál egy, a pályán lévő csövet.
//             */
//                if (Controller.Game.getInstance().isGameRunning()) {
//                    Controller.Game.getInstance().map.printPlayers();
//                    Controller.Game.getInstance().map.printAreas();
//                    System.out.println("Give ID of player");
//                    int playerID = scanner.nextInt();
//                    Controller.Game.getInstance().map.getPlayerbyID(playerID).BreakArea();
//                } else System.out.println("Controller.Game not started, please init first");
//                scanner.nextLine();
//                scanner.nextLine();
//                break;
//            case 9:
//            /**
//             * A szerelőkkel lévő játékos elhelyez egy pumpát, amelyhez egy csövön kell állnia a pályán.
//             */
//                System.out.println("Give the ID of the Model.Repairman");
//                Model.Repairman r9 = (Model.Repairman) Controller.Game.getInstance().map.getPlayerbyID(scanner.nextInt());
//                r9.PlacePump();
//                scanner.nextLine();
//                scanner.nextLine();
//                break;
//            case 10:
//            /**
//             * A játékos átállítja a pumpát, hogy melyik csőből melyik csőbe folyjon a víz.
//             */
//                System.out.println("Give the ID of the player");
//                Model.PlayableCharacter p10 = Controller.Game.getInstance().map.getPlayerbyID(scanner.nextInt());
//                if (p10.GetArea().getConfigureOptions().size() >= 2)
//                    p10.SetPumpConfiguration(p10.GetArea().getConnectedAreas().get(0), p10.GetArea().getConnectedAreas().get(1));
//                scanner.nextLine();
//                scanner.nextLine();
//                break;
//            case 11:
//            	/*
//            	 * A controller létrehoz egy csövet.
//            	 * A controller folyamatosan létrehoz a
//            	 * forrásokon újabb csöveket, amelyeket a szerelők fel tudnak venni.
//            	 */
//                Model.Fountain f = new Model.Fountain();
//                Model.Pipe pipe6 = f.PickupPipe();
//                scanner.nextLine();scanner.nextLine();
//                break;
//            case 12:
//            	/*
//            	 * A controller megrongálja a pumpát.
//            	 * A controller véletlenszerű időközönként(1/4) megrongál egy,
//            	 *  a pályán lévő pumpát, amelyet a szerelőknek meg kell javítani.
//            	 */
//                System.out.println("Should I use random?(Y/N)");
//                if (scanner.next().charAt(0) == 'Y') {
//                    if (Math.random() * 4 < 1) break;           //25% chance to break, and do nothing
//                    int randomID = (int) (Math.random() * Controller.Game.getInstance().map.areas.size());     //produces int from 0 to size - 1
//                    Controller.Game.getInstance().map.getAreabyID(randomID).Break();
//                    break;
//                }
//                Controller.Game.getInstance().map.printAreas();
//                System.out.println("Give ID of area to break");
//                Controller.Game.getInstance().map.getAreabyID(scanner.nextInt()).Break();
//                scanner.nextLine();scanner.nextLine();
//                break;
//            case 13:
//            	/*
//            	 * pumpa felvétele
//            	 * karbantartó felvesz egy pump elemet a nyelő mezőn
//            	 * amin áll.
//            	 */
//                System.out.println("Give the ID of the Model.Repairman");
//                Model.Repairman r18 = (Model.Repairman) Controller.Game.getInstance().map.getPlayerbyID(scanner.nextInt());
//                r18.PickupPump();
//                scanner.nextLine();
//                scanner.nextLine();
//                break;
//            case 14:            //make area sticky
//                if (Controller.Game.getInstance().isGameRunning()) {
//                    Controller.Game.getInstance().map.printPlayers();
//                    Controller.Game.getInstance().map.printAreas();
//                    System.out.println("Give ID of player");
//                    int playerID = scanner.nextInt();
//                    Controller.Game.getInstance().map.getPlayerbyID(playerID).makeSticky(Controller.Game.getInstance().map.getPlayerbyID(playerID).GetArea());
//                    System.out.println("Made Player " + playerID + " area sticky");
//                }
//                else System.out.println("Controller.Game not started, please init first");
//                scanner.nextLine();scanner.nextLine();
//                break;
//                case 15:            //make area slippery
//                    if (Controller.Game.getInstance().isGameRunning()) {
//                        Controller.Game.getInstance().map.printPlayers();
//                        Controller.Game.getInstance().map.printAreas();
//                        System.out.println("Give ID of player");
//                        int playerID = scanner.nextInt();
//                        Controller.Game.getInstance().map.getPlayerbyID(playerID).GetArea().setSlipperyTimer();
//                        System.out.println("Made Player " + playerID + " area slippery");
//                    }
//                    else System.out.println("Controller.Game not started, please init first");
//                    scanner.nextLine();scanner.nextLine();
//                    break;
//                case 16:
//                    /*
//                     * A játék jelenlegi állapotának fájlba mentése
//                     */
//                    System.out.println("Saving game...");
//                    Controller.Game.getInstance().SaveGame();
//                    break;
//                case 17:
//                    /*
//                     * A játék mentett állapotának betöltése
//                     */
//                    System.out.println("Loading game...");
//                    Controller.Game.getInstance().LoadGame();
//                    break;
//                case 18:
//                    /**
//                     * Controller.Game End is called, result is printed to console
//                     */
//                    Controller.Game.getInstance().EndGame();
//                case 19:
//                    /*
//                     * kilépés a menüvezérelt részből, a program jelenlegi verziójában
//                     * a futtatás befejezése
//                     */
//                    System.out.println("Exiting program now...");
//                    NotFinished = false;
//                    break;
//
//            default:
//          }
//        }
//
//        scanner.close();
//    }
}