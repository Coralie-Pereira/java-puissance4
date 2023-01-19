package Classes;
import java.util.Scanner;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class Game {

    public static Scanner scan = new Scanner(System.in);
    public static final int ROWS = 6;
    public static final int COLS = 7;

    public static ArrayList<Combination> list_combinations = new ArrayList<>();

   public void sortBySize() {
        
        Collections.sort(list_combinations, new Comparator<Combination>() {
        @Override
        public int compare(Combination c1, Combination c2) {
            return c1.getPieces().size() - c2.getPieces().size();
        }
    });
    }
    
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

    public int getLineMin(List<Piece> pieces){
        int min = pieces.get(0).getLine();
        for (Piece piece : pieces) {
            if(piece.getLine()<min){
                min = piece.getLine();
            }
        }
        return min;
    }

    public int getLineMax(List<Piece> pieces){
        int max = pieces.get(0).getLine();
        for (Piece piece : pieces) {
            if(piece.getLine()>max){
                max = piece.getLine();
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

    public int getValidMove(Combination combination, Grid grid){
        if(combination.getType()=="vertical" ){
            if(combination.getPieces().size()>2 &&combination.getPieces().get(combination.getPieces().size()-1).getColumn() <ROWS-1){
                list_combinations.remove(combination);
                return combination.getPieces().get(0).getColumn();
            }
        }
        if(combination.getType()=="horizontal"){
            if(combination.getPieces().size()>2){
                int left_column = getColumnMin(combination.getPieces())-1;
                int right_column = getColumnMax(combination.getPieces())+1;

   

                //Gauche
                if(left_column>=0){//Si on est pas dans la colonne tout a gauche
                    int line = combination.getPieces().get(0).getLine();
                    if(grid.getGrid()[line][left_column].getColor() ==null){//Si colonne-1 de la pièce à gauche est vide
                        if(line!=ROWS-1){//Si on est pas dans la ligne du bas
                            if(grid.getGrid()[line+1][left_column].getColor()!=null){// On vérifie que la case colonne à gauche de la piece + ligne+1 est une case pleine
                                if(grid.getGrid()[line][right_column].getColor()!=null){
                                    list_combinations.remove(combination);
                                }
                                return left_column;
                            }
                        }else{
                            if(grid.getGrid()[line][right_column].getColor()!=null){
                                list_combinations.remove(combination);
                            }
                            return left_column;
                        }
                    }
                }
                //Droite
                if(right_column-1<COLS-1){ //Si on est pas dans la colonne tout à droite
                    int line = combination.getPieces().get(0).getLine();
                    if(grid.getGrid()[line][right_column].getColor() ==null){//Si colonne+1 de la pièce à droite est vide
                        if(line!=ROWS-1){//Si on est pas dans la ligne du bas
                            if(grid.getGrid()[line+1][right_column].getColor()!=null){ // On vérifie que la case colonne à droite de la piece + ligne+1 est une case pleine
                                if(grid.getGrid()[line][left_column].getColor()!=null){
                                    list_combinations.remove(combination);
                                }
                                return right_column;
                            }
                        }else{
                            if(grid.getGrid()[line][left_column].getColor()!=null){
                                list_combinations.remove(combination);
                            }
                            return right_column;
                        }
                    }
                }


                
            }
        }
        if(combination.getType()=="first-diagonal"){
           

        }
        if(combination.getType()=="second-diagonal"){
             /*
             * Gauche :
             * Pion à gauche ne soit pas tout à gauche
             * Pion à gauche ne soit pas tout en haut
             * 
             * Case c-1 l-1 vide
             * Case c-1 l pleine
             * 
             * Droite
             * Pion à droite ne soit pas tout à droite
             * Pion à droite ne soit pas tout en bas
             * 
             * Case c+1 l+1 vide
             * Case c+1 l pleine
             */

             if(combination.getPieces().size()>2){
                int left_column = getColumnMin(combination.getPieces())-1;
                int right_column = getColumnMax(combination.getPieces())+1;
                int up_line = getLineMin(combination.getPieces())-1;
                int bottom_line = getLineMax(combination.getPieces())+1;

                //Gauche
                if(left_column>=0 && up_line>=0){ //Si on est pas dans la colonne tout a gauche ni dans la ligne tout en haut
                    if(grid.getGrid()[up_line][left_column].getColor()==null){//On vérifie que case en haut à gauche est vide
                        if(grid.getGrid()[up_line+1][left_column].getColor()!=null){//On vérifie que la case de gauche est pleine
                            
                            return left_column;
                        }
                    }
                    if(grid.getGrid()[up_line][left_column].getColor()!=null){
                        list_combinations.remove(combination);
                    }
                }
                //Droite
                
                System.out.println(right_column);
                System.out.println(bottom_line);
                if(right_column-1<COLS-1 && bottom_line-1<ROWS-1){//Si on est pas dans la colonne tout a droite ni dans la ligne tout en bas
                    if(grid.getGrid()[bottom_line][right_column].getColor()==null){//On vérifie que case en bas à droite est vide
                        if(bottom_line<ROWS-1){
                            if(grid.getGrid()[bottom_line+1][right_column].getColor()!=null){//On vérifie que la case de droite est pleine
                                
                                return right_column;
                            }
                        }
                        else{
                            System.out.println("D");
                            return right_column;

                        }
                    }
                    if(grid.getGrid()[bottom_line][right_column].getColor()!=null){
                        list_combinations.remove(combination);
                    }
                }

             }
        }


        return IAChooseColumnLvl1();
    }
   

    public int IAChooseColumnLvl2(Grid grid,Piece piece){
        //Verticalement

        Combination combination = grid.checkVertically2(piece);
        if(combination.getPieces().size()>2){
            list_combinations.add(combination);
        }

        //Horizontalement
        combination = grid.checkHorizontally2(piece);
        if(combination.getPieces().size()>2){
            list_combinations.add(combination); 
        }

        //Second Diagonal
        combination = grid.checkSecondDiagonal2(piece);
        if(combination.getPieces().size()>2){
            list_combinations.add(combination);
        }


        System.out.println(list_combinations);
        sortBySize();
        if(!list_combinations.isEmpty()){
            combination = list_combinations.get(0);
        }
        return getValidMove(combination,grid);
       
    }
    

    public void playSingleplayer(){
        Player player = createPlayer();
        Player IA = new Player("IA", Color.WHITE);

        Grid grid = new Grid();
        grid.createGrid();

        /*grid.addPiece(0, IA);
        grid.addPiece(0, IA);
        grid.addPiece(0, IA);
        grid.addPiece(0, player);
        grid.addPiece(0, IA);

        grid.addPiece(1, IA);
        grid.addPiece(1, IA);
        grid.addPiece(1, player);
        grid.addPiece(1, IA);

        grid.addPiece(2, player);
        grid.addPiece(2, IA);
        grid.addPiece(2, IA);

        grid.addPiece(3, IA);
        grid.addPiece(3, player);

        grid.addPiece(3, player);
        grid.addPiece(2, player);*/


        //---------------------------

        /*grid.addPiece(5, IA);
        grid.addPiece(4, IA);
        grid.addPiece(4, IA);

        grid.addPiece(3, player);
        grid.addPiece(3, IA);
        grid.addPiece(3, IA);

        grid.addPiece(6, player);
        grid.addPiece(5, player);*/
        

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
                System.out.println(player.getNom()+" a gagné la partie bravo ! ");
                Menu.showMenu();
                return;
            }

            piece = grid.addPiece(IAChooseColumnLvl2(grid,piece), IA);
            grid.printGrid();

           

            if(grid.checkVictory(piece)){
                grid.printGrid();
                System.out.println(player.getNom()+" a gagné la partie bravo ! ");
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
