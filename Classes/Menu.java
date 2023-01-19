package Classes;
import java.util.Scanner;

public class Menu {

    public static void showMenu(){
        /*
         * Show menu gamemodes in console and do the link with functions
         */
        Menu.AfficherMenu();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String choix = sc.nextLine();
            

            switch (choix) {
                case "1": 
                    JouerSeul(); 
                            
                    break;
                case "2":
                    Versus();
                    
                    break;
                    
                case "3":
                    TopScore();
                    
                    break;
                       
                case "4":
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Mauvaise valeur!!");
                    break;
            }
        }
    }

    public static final String SEPARATEUR = ";";

    public static void AfficherMenu() {
        /*
         * Print menu gamemodes in console
         */
        System.out.println("------ MENU ------");
        System.out.println("1- Jouer seul");
        System.out.println("2- Versus 1V1");
        System.out.println("3- Top 10");
        System.out.println("4- Quitter");
        System.out.println("---------------------");

    }

    public static void JouerSeul(){
        /*
         * Create a game and call function game vs ia
         */
        Game game = new Game();
        game.menuIALEVEL();
    }

    public  static void Versus(){
        /*
         * Create a game and call function game vs another player
         */
        Game game = new Game();
        game.playMultiplayer();
            
        }

    public  static void TopScore(){
        /*
         * Call function who print top 10 scores in the console
         */
        Game.printScores();
    }

}