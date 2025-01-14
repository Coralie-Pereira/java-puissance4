package Classes;
import java.util.Scanner;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class Game {

    public static Scanner scan = new Scanner(System.in);
    public static final int ROWS = 6;
    public static final int COLS = 7;

    int counttours=0;

    public static ArrayList<Combination> list_combinations = new ArrayList<>();

   public void sortBySize() {
        /*
         * Sort combinations by size
         * 
         */
        
        Collections.sort(list_combinations, new Comparator<Combination>() {
        @Override
        public int compare(Combination c1, Combination c2) {
            /*
             * Override compare function to sort Combination by size
             * @param c1(Combination): combination 1 
             * @param c2(Combination): combination 2
             * @return (int): size combination1 - size combination2
             */
            return c2.getPieces().size() - c1.getPieces().size() ;
        }
    });
    }
    
    public Player createPlayer(){ 
        /*
         * Create a new player
         * @return (Player): new player
         */
        System.out.println("Entrez le nom du joueur");

        String name = scan.nextLine();

        Color color = chooseColor();
        Player player = new Player(name, color,0);
        return player;

    }


    public void showMenuColors(){
        /*
         * Print colors menu
         */
        System.out.println("Veuillez choisir une couleur");
        System.out.println("1 - Rouge");
        System.out.println("2 - Jaune");
        System.out.println("3 - Bleu");
        System.out.println("4 - Vert");
        System.out.println("5 - Rose");
        System.out.println("6 - Gris");

    }

    public Color chooseColor(){
        /*
         * Choose piece color
         * @return (Color) Choosen color
         */
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
    
    public static int chooseColumn(Grid grid){
        /*
         * Choose next piece column
         * @param grid (Grid): current grid
         * @return (int): choosen column
         */
        System.out.println("Veuillez choisir une colonne entre 0 et 6");
        int choice = scan.nextInt();
        while((choice<0 || choice>=COLS) || grid.getLastPieceLine(choice)==0){
            System.out.println("Veuillez entrer une colonne valide");
            choice = scan.nextInt();
        }
        return choice;
    }

    public int getColumnMin(List<Piece> pieces){
        /*
         * Get column min of a list of piece
         * @param pieces(List<Piece>) : list of pieces
         * @return (int): min column of the pieces
         */
        int min = pieces.get(0).getColumn();
        for (Piece piece : pieces) {
            if(piece.getColumn()<min){
                min = piece.getColumn();
            }
        }
        return min;
    }

    public int getColumnMax(List<Piece> pieces){
        /*
         * Get column max of a list of piece
         * @param pieces(List<Piece>) : list of pieces
         * @return (int): max column of the pieces
         */
        int max = pieces.get(0).getColumn();
        for (Piece piece : pieces) {
            if(piece.getColumn()>max){
                max = piece.getColumn();
            }
        }
        return max;
    }

    public int getLineMin(List<Piece> pieces){
        /*
         * Get line min of a list of piece
         * @param pieces(List<Piece>) : list of pieces
         * @return (int): min lin of the pieces
         */
        int min = pieces.get(0).getLine();
        for (Piece piece : pieces) {
            if(piece.getLine()<min){
                min = piece.getLine();
            }
        }
        return min;
    }

    public int getLineMax(List<Piece> pieces){
        /*
         * Get line max of a list of piece
         * @param pieces(List<Piece>) : list of pieces
         * @return (int): max lin of the pieces
         */
        int max = pieces.get(0).getLine();
        for (Piece piece : pieces) {
            if(piece.getLine()>max){
                max = piece.getLine();
            }
        }
        return max;
    }

    public int IAChooseColumnLvl1(Grid grid){
        /*
         * Get column choosen by ia lvl1
         * @param grid (Grid): current grid
         * @return (int): column choosen by ia lvl1
         */
        int random_number = new Random().nextInt(7);
        while(grid.getLastPieceLine(random_number)==0){
            random_number = new Random().nextInt(7);
        }
        return random_number;
    }

    public int getValidMove(Grid grid){
        /*
         * Get ia move
         * @param grid(Grid): current grid
         * @return (int): column choosen by ia lvl2
         */

        if(!list_combinations.isEmpty()){
            try{
                for (Combination combination : list_combinations) {
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
            
                            //Conditions pour remove une combinaison
                            if(left_column<=0){
                                if(right_column>=COLS-1){
                                    list_combinations.remove(combination);
                                }
                                else if(grid.getGrid()[combination.getPieces().get(0).getLine()][right_column].getColor() !=null){
                                    list_combinations.remove(combination);
                                }
                            }
                            else if(grid.getGrid()[combination.getPieces().get(0).getLine()][left_column].getColor() !=null){
                                if(right_column>=COLS-1){
                                    list_combinations.remove(combination);
                                }
                                else if(grid.getGrid()[combination.getPieces().get(0).getLine()][right_column].getColor() !=null){
                                    list_combinations.remove(combination);
                                }
                            }
  
                            //Gauche
                            if(left_column>=0){//Si on est pas dans la colonne tout a gauche

                                int line = combination.getPieces().get(0).getLine();
                                if(grid.getGrid()[line][left_column].getColor() ==null){//Si colonne-1 de la pièce à gauche est vide
                                    if(line!=ROWS-1){//Si on est pas dans la ligne du bas
                                        if(grid.getGrid()[line+1][left_column].getColor()!=null){// On vérifie que la case colonne à gauche de la piece + ligne+1 est une case pleine
                                            if(grid.getGrid()[line][right_column].getColor()!=null){
                                                //list_combinations.remove(combination);
                                            }
                                            return left_column;
                                        }
                                    }else{
                                        if(grid.getGrid()[line][right_column].getColor()!=null){
                                            //list_combinations.remove(combination);
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
                                                //list_combinations.remove(combination);
                                            }
                                            return right_column;
                                        }
                                    }
                                    else{
                                        return right_column;
                                    }
                                }
                            }         
                        }
                    }
                    if(combination.getType()=="first-diagonal"){
            
                        if(combination.getPieces().size()>2){
                            int left_column = getColumnMin(combination.getPieces())-1;
                            int right_column = getColumnMax(combination.getPieces())+1;
                            int up_line = getLineMin(combination.getPieces())-1;
                            int bottom_line = getLineMax(combination.getPieces())+1;

                             if(left_column<=0 || bottom_line-1>=ROWS-1){
                            
                                if(right_column>=COLS-1 || up_line<0){
                                    list_combinations.remove(combination);
                                }
                                else if(grid.getGrid()[up_line][right_column].getColor()!=null){
                                    list_combinations.remove(combination);
                                }
                             }
 
                             else if(grid.getGrid()[bottom_line][left_column].getColor()!=null){
                                
                                if(right_column>=COLS-1 && up_line<0){
                                    list_combinations.remove(combination);
                                }
                                else if(grid.getGrid()[up_line][right_column].getColor()!=null){
                                    list_combinations.remove(combination);
                                }
                             }                       
            
                            //Bas à gauche

                            if(left_column>=0 && bottom_line-1<ROWS-1){//Si on est pas dans la colonne tout à gauche ni dans la ligne tout à bas
                                if(grid.getGrid()[bottom_line][left_column].getColor()==null){//On vérifie que case en bas à gauche est vide
                                    if(bottom_line<ROWS-1){
                                        if(grid.getGrid()[bottom_line+1][left_column].getColor()!=null){//On vérifie que la case de gauche est pleine
                                            return left_column;
                                        }
                                    }else{
                                        return left_column;
                                    } 
                                }
                            }
            
                            //Haut à droite
                            if(right_column-1<COLS-1 && up_line>=0){ //Si on est pas dans la colonne tout a droite ni dans la ligne tout en  haut
                                if(grid.getGrid()[up_line][right_column].getColor()==null){//On vérifie que case en haut à droite est vide
                                    
                                    if(grid.getGrid()[up_line+1][right_column].getColor()!=null){//On vérifie que la case de gauche est pleine
                                        return right_column;
                                    }      
                                }
                            }
                        }
                    }
                        if(combination.getType()=="second-diagonal"){

                            if(combination.getPieces().size()>2){
                               int left_column = getColumnMin(combination.getPieces())-1;
                               int right_column = getColumnMax(combination.getPieces())+1;
                               int up_line = getLineMin(combination.getPieces())-1;
                               int bottom_line = getLineMax(combination.getPieces())+1;


                                if(left_column<=0 || up_line<=0){

                                    if(right_column>=COLS-1 || bottom_line >=ROWS-1){
                                        list_combinations.remove(combination);
                                    }
                                    else if(grid.getGrid()[bottom_line][right_column].getColor()!=null){
                                        list_combinations.remove(combination);
                                    }

                                }
                                else if(grid.getGrid()[up_line][left_column].getColor()!=null){
                                    if(right_column>=COLS-1 && bottom_line >=ROWS-1){
                                        list_combinations.remove(combination);
                                    }
                                    else if(grid.getGrid()[bottom_line][right_column].getColor()!=null){
                                        list_combinations.remove(combination);
                                    }
                                }
               
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
                               
                               if(right_column-1<COLS-1 && bottom_line-1<ROWS-1){//Si on est pas dans la colonne tout a droite ni dans la ligne tout en bas
                                   if(grid.getGrid()[bottom_line][right_column].getColor()==null){//On vérifie que case en bas à droite est vide
                                       if(bottom_line<ROWS-1){
                                           if(grid.getGrid()[bottom_line+1][right_column].getColor()!=null){//On vérifie que la case de droite est pleine
                                               
                                               return right_column;
                                           }
                                       }
                                       else{
                                           return right_column;
               
                                       }
                                   }
                               }
                           }
                       }
                    }
                }

            catch(Exception e){  
                getValidMove(grid);
            }
        }
        return IAChooseColumnLvl1(grid);
    }
   

    public int IAChooseColumnLvl2(Grid grid,Piece piece){
        /*
         * Add a combination in the list if combination size>2
         * @param grid(Grid): current grid
         * @param piece(Piece): piece to check
         * @return (int): getValidMove(grid) -> column choosen by ia
         */

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

        //First Diagonal
        combination = grid.checkFirstDiagonal2(piece);
        if(combination.getPieces().size()>2){
            list_combinations.add(combination);
        }

        //Second Diagonal
        combination = grid.checkSecondDiagonal2(piece);
        if(combination.getPieces().size()>2){
            list_combinations.add(combination);
        }

        sortBySize();
        return getValidMove(grid);
       
    }

static int niveau = 0;
    public void menuIALEVEL(){
        /*
         * Do the link between menu ia level and functions
         */
        chooseIALevel();
        
        
        while (true) {
            String choix = scan.nextLine();
            switch (choix) {
                case "1":
                playSingleplayer(1);
                System.out.println("niveau facile");
                break;

                case "2":
                playSingleplayer(2);
                System.out.println("niveau moyen");
                break;

                case "3":
                System.out.println("niveau difficile");

                break;

                case "4":
                scan.close();
                System.out.println("niveau expert");

                break;
                }
            }
}

    public void chooseIALevel(){
        /*
         * Print menu ia level
         */
        System.out.println("Veuillez choisir une difficulté");
        System.out.println("1 - facile");
        System.out.println("2 - moyen");
        System.out.println("3 - difficile");
        System.out.println("4 - expert");
    }
    
    public void playSingleplayer(int ia_level){
        /*
         * Game function vs ia
         * @param ia_level (int) ia level
         */
        Player player = createPlayer();
        Player IA = new Player("IA", Color.WHITE, 0);

        Grid grid = new Grid();
        grid.createGrid();

        niveau = ia_level;

        grid.printGrid();

        while(true){
            if(grid.isFull()){
               return;
            }
            //Tour du joueur
            int column = chooseColumn(grid);
            counttours ++;
            Piece piece = grid.addPiece(column, player);
            grid.printGrid();
            if(grid.checkVictory(piece)){
                int score = Score(niveau, counttours);
                player.setscore(score);
                grid.printGrid();
                System.out.println(player.getNom()+ " a gagné la partie bravo ! VOTRE SCORE EST DE " + player.getscore() );
                saveScore(player);
                list_combinations = new ArrayList<>();
                Menu.showMenu();
                return;
            }

            //Tour de l'IA
            if(ia_level==1){
                piece = grid.addPiece(IAChooseColumnLvl1(grid),IA);
            }
            else{
                piece = grid.addPiece(IAChooseColumnLvl2(grid,piece), IA);
            }
            
            grid.printGrid();

           

            if(grid.checkVictory(piece)){
                grid.printGrid();
                System.out.println(IA.getNom()+" a gagné la partie dommage ! ");
                list_combinations = new ArrayList<>();
                Menu.showMenu();
                return;
            }
        }
    }


    public void playMultiplayer(){
        /*
         * Game function vs another player
         */
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
            
            int column = chooseColumn(grid);
            counttours ++;
            Piece piece = grid.addPiece(column, player1);
            grid.printGrid();
            if(grid.checkVictory(piece)){
                int score = ScoreVersus(counttours);
                player1.setscore(score);
                grid.printGrid();
                System.out.println(player1.getNom()+ " a gagné la partie bravo ! VOTRE SCORE EST DE " + player1.getscore() );
                saveScore(player1);
                Menu.showMenu();
                return;
            }

            int column2 = chooseColumn(grid);
            piece =  grid.addPiece(column2, player2);
            grid.printGrid();

            counttours++;

            if(grid.checkVictory(piece)){
                int score = ScoreVersus(counttours);
                player2.setscore(score);
                grid.printGrid();
                System.out.println(player2.getNom()+ " a gagné la partie bravo ! VOTRE SCORE EST DE " + player2.getscore() );
                saveScore(player2);
                Menu.showMenu();
                return;
            }

        }
        ;
        
    }

    public int Score(int niveau,int counttours){
        /*
         * Get player score vs ia
         * @param niveau(int): ia level
         * @param counttours(int): number of rounds
         * @return (int): player score
         */

        // on prepare le niveau + le compteur de points
        int level = niveau; // le niveau de difficulté choisi par le user 
        int points = 10000; // le compteur de point

        // on fait les conditions pour les points en fonction du level 
        if (level == 1) {
            //si on retire 10 points pour chaque coup joué en mode facile
                points -= 10 * counttours; 
                // ajoute 15 points pour chaque victoire en mode facile
                points += 15;
                return points;
            
            } else if (level == 2) {
                // sinon on retire pas de points ni rajouter de points pour chaque coup joué en  moyen
                points -= 5 * counttours;
                // on ajoute 20 points pour chaque victoire en mode moyen
                points += 20;
                return points;

            } else if (level == 3) {
                // sinon on rajoute 3 points pour chaque coup joué en mode difficile
                points -= 3 * counttours; 
                // et on rajoute 40 points pour chaque victoire en mode difficile
                points += 40;
                return points;
                
            }else if (level == 4){
                points -= 2 * counttours;
                points += 60;
                return points;

            }
            return points;
        }

    public int ScoreVersus(int counttours){
        /*
         *Get player score vs another player
         *@param (int): number of rounds
         */

        // on prepare le niveau + le compteur de points
        int points = 10000; // le compteur de point
                points -= 100 * counttours; 
                // ajoute 15 points pour chaque victoire en mode facile
                points += 15;
                return points;
    }

    public void saveScore(Player player){
        /*
         * Save score in csv file
         * @param player (Player):current player
         */
        try{
            File myObj = new File("scores.csv");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
            FileWriter writer = new FileWriter("scores.csv",true);
            writer.append(player.getNom()+";"+player.getscore()+"\n");
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void printScores(){
        /*
         * Get scores of the csv file and print them in console
         * 
         */
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader("scores.csv"));
            String line;
            int count_line=1;

            ArrayList<Player> players= new ArrayList<>();

            while((line=reader.readLine())!= null){
                String[] parts = line.split(";");
                players.add(new Player(parts[0], null, Integer.parseInt(parts[1])));
                count_line++;
            }

            reader.close();

            Collections.sort(players, new PlayerComparator());

            for (int i = 0; i < players.size() && i<10; i++) {
                System.out.println((i + 1) + "| Player: " + players.get(i).getNom() + " / Score: " + players.get(i).getscore());
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Menu.AfficherMenu();
    }

}
