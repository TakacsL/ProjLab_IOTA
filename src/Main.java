import java.util.ArrayList;
import java.util.List;
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
        String[] options = {"1.  Mozgás",
                            "2.  Map Init",
                            "3.  End turn",
                            "4.  Pálya megjelenítése",
                            "5.  Csõ elhelyezés",
                            "6.  Csõ javítás",
                            "7.  Csõ felszedés",
                            "8.  Csõ rongálás",
                            "9.  Pumpa javítás",
                            "10. Pumpa elhelyezés",
                            "11. Pumpa állítása",
                            "12. Csõ létrehozása",
                            "13. Pumpa rongálása",
                            "14. Pumpa felvétele",
                            "15. Kilépés"
                            
        };
        Scanner scanner = new Scanner(System.in);
        int option;
        Boolean NotFinished = true;
        Game.getInstance().StartGame();
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
                Game.getInstance().CreateInitialMap();
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
                List<Area> areaList = new ArrayList<Area>();
                Pipe pipe40 = new Pipe();
                Pipe pipe41 = new Pipe();
                Pipe pipe42 = new Pipe();
                Pipe pipe43 = new Pipe();
                Cistern cistern40 = new Cistern();
                Cistern cistern41 = new Cistern();
                Fountain fountain40 = new Fountain();
                Fountain fountain41 = new Fountain();
                Pump pump40 = new Pump();

                areaList.add(pipe40);
                areaList.add(pipe41);
                areaList.add(pipe42);
                areaList.add(pipe43);
                areaList.add(cistern40);
                areaList.add(cistern41);
                areaList.add(fountain40);
                areaList.add(fountain41);
                areaList.add(pump40);
                System.out.println("Areas on the map:");
                for (Area a:areaList) {
                    System.out.println(a.toString()); }

                scanner.nextLine();scanner.nextLine();
                break;
            case 5:
            /**
             * A szerelőkkel lévő játékos elhelyez a pályán egy csövet, amelynek legalább az egyik vége csatlakozik egy másik elemhez.
             */
                Repairman r = new Repairman();
                Pipe pipe = new Pipe();
                r.PlacePipe(pipe);
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
                Area a70 = new Area();
                Repairman r70 = new Repairman();
                Pipe p70 = new Pipe();
                p70.Disconnect(a70);
                System.out.println("->GetArea().Disconnect[p70]");
                r70.PickupArea(p70);
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
                r4.PlacePump(pump2);
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
                Fountain f = new Fountain();
                Pipe pipe6 = f.PickupPipe();
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
            	Repairman rm = new Repairman();
                Cistern c = new Cistern();
                rm.PickupArea(c);
                scanner.nextLine();scanner.nextLine();
                break;
            case 15:
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