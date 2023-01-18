package Classes;
import java.util.Scanner;
import java.awt.Color;
import java.util.List;
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
        return player;

    }

    public void showMenuColors(){
        System.out.println("Veuillez choisir une couleur");
        System.out.println("1 - Rouge");
        System.out.println("2 - Jaune");
        System.out.println("3 - Bleu");
        System.out.println("4 - Vert");
        System.out.println("5 - Rose");
        System.out.println("6 - Gris");

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
                    return Color.GRAY;
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

    public int getColumnMin(List<Piece> pieces){
        int min = pieces.get(0).getColumn();
        for (Piece piece : pieces) {
            if(piece.getColumn()<min){
                min = piece.getColumn();
            }
        }
        return min;
    }

    public int getColumnMax(List<Piece> pieces){
        int max = pieces.get(0).getColumn();
        for (Piece piece : pieces) {
            if(piece.getColumn()>max){
                max = piece.getColumn();
            }
        }
        return max;
    }

    
    public void chooseIALevel(){
        //int ia_level =
    }

    public int IAChooseColumnLvl1(){
        int random_number = new Random().nextInt(7);
        return random_number;
    }

    public int IAChooseColumnLvl2(Grid grid,Piece piece){
        //Verticalement
        List<Piece> alignied_pieces = grid.checkVertically(piece);
        if(alignied_pieces.size()>2 && piece.getLine()<ROWS-1){
            return piece.getColumn();
        }
        //Horizontalement
        alignied_pieces = grid.checkHorizontally(piece);
        int left_column = getColumnMin(alignied_pieces)-1;
        int right_column = getColumnMax(alignied_pieces)+1;
        System.out.println("Left column : " + left_column);
        System.out.println("Right column : " + right_column);


        if(alignied_pieces.size()>2){

            /*Gauche : 
             * Colonne piece à gauche!=0
             * Colonne-1 piece à gauche est vide
             * Si ligne!=ROWS-1:
             *      Colonne -1 ligne+1 piece à gauche est pleine
             * 
             * 
             * Droite : 
             * Colonne piece à droite!= COLS-1
             * Colonne+1 piece à droite est vide
             * 
             * Si ligne!=ROWS-1:
             *      Colonne +1 ligne+1 piece à droite est pleine
             */

            //regarder à gauche

            //GAUCHE
            if(left_column>=0){ // Si on est pas dans la colonne tout a gauche
                System.out.println("gauche A");
                if(grid.getGrid()[piece.getLine()][left_column].getColor()==null){ //Si colonne-1 de la pièce à gauche est vide
                    System.out.println("gauche B");
                    if(piece.getLine()!=ROWS-1){//Si on est pas dans la ligne du bas
                        System.out.println("gauche C1");
                        if(grid.getGrid()[piece.getLine()+1][left_column].getColor()!=null){ // On vérifie que la case colonne à gauche de la piece + ligne-1 est une case pleine
                            System.out.println("gauche D");
                            return left_column;
                        }
                    }
                    else{
                        System.out.println("gauche C2");
                        return left_column;
                    }
                }
            }

            //DROITE
            if(right_column-1<COLS-1){ //Si on est pas dans la colonne tout à droite
                System.out.println("Droite A");
                if(grid.getGrid()[piece.getLine()][right_column].getColor()==null){ //Si colonne+1 de la pièce à droite est vide
                    System.out.println("Droite B");
                    if(piece.getLine()!=ROWS-1){ //Si on est pas dans la ligne du bas
                        System.out.println("Droite C1");
                        if(grid.getGrid()[piece.getLine()+1][right_column].getColor()!=null){ // On vérifie que la case colonne à droite de la piece + ligne-1 est une case pleine
                            System.out.println("Droite D");
                            return right_column;
                        }
                    }
                    else{
                        System.out.println("Droite C2");
                        return right_column;
                    }
                }
            }   

            /*if(piece.getLine()==ROWS-1 && grid.getGrid()[piece.getLine()][left_column].getColor()==null && grid.getGrid()[piece.getLine()][left_column].getColumn()>0){
                System.out.println("gauche : a");
                return left_column;
                
            }
            else if(grid.getGrid()[piece.getLine()+1][left_column].getColor()!=null && grid.getGrid()[piece.getLine()][left_column].getColor()==null && grid.getGrid()[piece.getLine()][left_column].getColumn()>0){
                System.out.println("gauche : b");
                return left_column;
            }

            //regarder à droite


            if(piece.getLine()==ROWS-1 && grid.getGrid()[piece.getLine()][right_column].getColor()==null && grid.getGrid()[piece.getLine()][left_column].getColumn()<COLS-1){
                System.out.println("droite : a");
                return right_column;
                
            }
            else if(grid.getGrid()[piece.getLine()+1][right_column].getColor()!=null && grid.getGrid()[piece.getLine()][right_column].getColor()==null && grid.getGrid()[piece.getLine()][left_column].getColumn()<COLS-1){
                System.out.println("droite : b");
                return right_column;
            }*/
        }
        return IAChooseColumnLvl1();
    }

    public void playSingleplayer(){
        Player player = createPlayer();
        Player IA = new Player("IA", Color.WHITE);

        Grid grid = new Grid();
        grid.createGrid();
        grid.printGrid();

        while(true){
            if(grid.isFull()){
               return;
            }
            
            int column = chooseColumn();
            Piece piece = grid.addPiece(column, player);
            grid.printGrid();
            if(grid.checkVictory(piece)){
                grid.printGrid();
                System.out.println("Vous avez gagné la partie bravo ! ");
                Menu.showMenu();
                return;
            }

            piece = grid.addPiece(IAChooseColumnLvl2(grid,piece), IA);
            grid.printGrid();

            if(grid.checkVictory(piece)){
                grid.printGrid();
                System.out.println("L'IA a gagné ! ");
                Menu.showMenu();
                return;
            }

        }
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
                System.out.println(player1.getNom() +" a gagné la partie bravo ! ");
                Menu.showMenu();
                return;
            }

            int column2 = chooseColumn();
            piece =  grid.addPiece(column2, player2);
            grid.printGrid();

            if(grid.checkVictory(piece)){
                grid.printGrid();
                System.out.println(player2.getNom() +" a gagné la partie bravo ! ");
                Menu.showMenu();
                return;
            }

        }
        ;
        
    }
}
