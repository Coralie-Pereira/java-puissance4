package Classes;
import java.util.Scanner;
import java.awt.Color;


public class Game {

    public static Scanner scan = new Scanner(System.in);
    
    public Player createPlayer(){
        System.out.println("Entrez le nom du joueur");
        String name = scan.nextLine();
        Color color = chooseColor();
        Player player = new Player(name, color);
        
       // Color color = JColorChooser.showDialog(null, "couleur pour les jetons ", player.getCouleur());  // choisir la couleur 
        //player.setCouleur(color);

        return player;

    }

    public void showMenuColors(){
        System.out.println("Veuillez choisir une couleur");
        System.out.println("1 - Rouge");
        System.out.println("2 - Jaune");
        System.out.println("3 - Bleu");
        System.out.println("4 - Vert");
        System.out.println("5 - Rose");
        System.out.println("6 - Blanc");

    }

    public Color chooseColor(){
        this.showMenuColors();
        while(true){
            String color = scan.nextLine();
            switch(color){
                case "1":
                    return Color.RED;

                case "2":
                    return Color.YELLOW;

                case "3":
                    return Color.BLUE;

                case "4":
                    return Color.GREEN;

                case "5":
                    return Color.PINK;

                case "6":
                    return Color.WHITE;
                default:
                    System.out.println("Veuillez entrer une nouvelle couleur");
                    break;
                }
                
            }
    }
    
    public static int chooseColumn(){
        System.out.println("Veuillez choisir une colonne entre 0 et 6");
        int choice = scan.nextInt();
        return choice;
    }

    public void playMultiplayer(){
        Player player1 = createPlayer();
        Player player2 = createPlayer();

        while(player2.getCouleur()==player1.getCouleur()){
            System.out.println("Vous devez choisir une couleur différente");
            player2 = createPlayer();
        }

        Grid grid = new Grid();
        grid.createGrid();
        grid.printGrid();

        boolean in_game = true;

        while(in_game){
            if(grid.isFull()){
                in_game = false;
            }
            int column = chooseColumn();
            grid.addPiece(column, player1);
            grid.printGrid();

            int column2 = chooseColumn();
            grid.addPiece(column2, player2);
            grid.printGrid();

        }
        
    }
}
