import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        Commandes.AfficherMenu();

        while (true) {
            String choix = sc.nextLine();
            

            switch (choix) {
                case "1":
                    System.out.println("test");
                    Commandes.JouerSeul();
                    break;
                case "2":
                    Commandes.Versus();
                    break;
                    
                case "3":
                    Commandes.TopScore();
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

}