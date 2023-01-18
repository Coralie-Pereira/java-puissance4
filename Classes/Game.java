package Classes;
import java.util.Scanner;
import java.awt.Color;
import java.util.Random;


public class Game {

    public static Scanner scan = new Scanner(System.in);
    public static final int ROWS = 6;
    public static final int COLS = 7;
    
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
        while(choice<0 || choice>=COLS){
            System.out.println("Veuillez entrer une colonne valide");
            choice = scan.nextInt();
        }
        return choice;
    }

    
    public void chooseIALevel(){
        //int ia_level =
    }

    public int IAChooseColumn(){
        int random_int = (int)Math.floor(Math.random() * ((COLS-1) + 1));
        //int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
        return random_int;
    }

    public void playSingleplayer(){

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
            Piece piece = grid.addPiece(column, player1);
            grid.printGrid();
            if(grid.checkVictory(piece)){
                grid.printGrid();
                System.out.println("Vous avez gagné la partie bravo ! ");
                Menu.showMenu();
                return;
            }

            int column2 = chooseColumn();
            piece =  grid.addPiece(column2, player2);
            grid.printGrid();

            if(grid.checkVictory(piece)){
                grid.printGrid();
                System.out.println("Vous avez gagné la partie bravo ! ");  
                Menu.showMenu();
                return;
            }

        }
        ;
        
    }
}
