package Classes;

import java.io.IOException;
import java.text.ParseException;




public class Commandes {
    public static final String SEPARATEUR = ";";

    public static void AfficherMenu() {
        System.out.println("------ MENU ------");
        System.out.println("1- Jouer seul");
        System.out.println("2- Versus 1V1");
        System.out.println("3- Top 10");
        System.out.println("4- Quitter");
        System.out.println("---------------------");

       

/*         if (Handler.isNumeric(choix))
            value = Integer.parseInt(choix);
        else
            value = -1;
        return value;
    */


    }

    public static void JouerSeul() throws ParseException, IOException {
        System.out.println("e");
    }

    public static void Versus() throws ParseException, IOException {
        Game game = new Game();
        game.playMultiplayer();
       
            
        }

    public static void TopScore() throws ParseException, IOException {

    }
}