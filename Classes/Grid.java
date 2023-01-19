package Classes;

import java.awt.Color;
import java.util.ArrayList;

public class Grid{

    private Piece[][] grid;

    public Piece[][] getGrid() {
        return grid;
    }



    public static final int ROWS = 6;
    public static final int COLS = 7;
   


    public void createGrid(){
        /*
         * Create a new grid 
         */
        
        Piece[][] grid = new Piece[ROWS][COLS];

        for(int line=0; line<ROWS;line++){   
            for(int column=0; column <COLS; column++){
                grid[line][column] = new Piece(column, line);
            }
        }
        this.grid = grid;
    }
    

    public void printGrid() {
        /*
         * Print grid in console
         */

        System.out.print("  0  1  2  3  4  5  6");
        System.out.println();
        
        for(int t=0; t<COLS+2+2*COLS;t++){
            System.out.print('-');
        }
        System.out.println();

        for(int y=0; y<ROWS ;y++){
            System.out.print("|");
            for(int x=0; x<COLS; x++){
                System.out.print(" " + this.grid[y][x].printPiece() + " ");
            }
            System.out.print("|");
            System.out.println();
        }

        for(int t=0; t<COLS+2+2*COLS;t++){
            System.out.print('-');
        }
        System.out.println();

    }

    public Piece addPiece(int column, Player player){
        /*
         * Add a piece in the grid (change element)
         * @param column(int): piece to add column
         * @param player(Player): new piece's player
         * @return (Piece): piece added 
         */

            for( int line = ROWS-1; line>=0; line--){
                if(this.grid[line][column].getColor()==null){
                    this.grid[line][column].setColor(player.getCouleur());
                    return this.grid[line][column];
                }
                else{
                    if(line==0){
                        System.out.println("Impossible");
                    }
                }
            }
            return new Piece(-1, -1);
            
        }
    
    public int getLastPieceLine(int column){
        /*
         * Get last piece's line of the column
         * @param column(int) : index of the column
         * @return (int): index of the line
         */
        int last_line = ROWS-1;
        for(int line = ROWS-1; line>0; line--){
            if(this.grid[line-1][column].getColor() !=null){
                last_line = line-1;
            }
        }
        return last_line;
    }

    

    public boolean isFull(){
        /*
         * Return if the grid is full
         * @return (boolean): true if grid is full, false if no
         */
        for (int line = 0; line < ROWS; line++) {
            for (int column = 0; column < COLS; column++) {
                if(this.grid[line][column].getColor() ==null){
                    return false;
                }
            }
        }
        return true;
    }
    
    public Combination checkVertically2(Piece piece){
        /*
         * Create a new vertical Combination with alignied pieces
         * @param piece(Piece): piece added waiting for check Combination
         * @return (Combination): Combination of alignied pieces 
         */
        int y = piece.getLine();
        ArrayList<Piece> alignied_pieces = new ArrayList<Piece>();
        alignied_pieces.add(piece);

        while(y<ROWS-1 && grid[y+1][piece.getColumn()].getColor() == piece.getColor()){
            alignied_pieces.add(grid[y+1][piece.getColumn()]);
            y++;
        }
        
        Combination combination = new Combination("vertical", alignied_pieces);
        return combination;

    }

    public Combination checkHorizontally2(Piece piece){
        /*
         * Create a new horizontal Combination with alignied pieces
         * @param piece(Piece): piece added waiting for check Combination
         * @return (Combination): Combination of alignied pieces 
         */
        int x = piece.getColumn();
        ArrayList<Piece> alignied_pieces = new ArrayList<Piece>();

        alignied_pieces.add(piece);
        
        while(x>0 && grid[piece.getLine()][x-1].getColor() == piece.getColor()){
           /* if(x>1 &&grid[piece.getLine()][x-1].getColor() ==null && grid[piece.getLine()][x-2].getColor()== piece.getColor()){
                alignied_pieces.add(grid[piece.getLine()][x-2]);
            }*/
            alignied_pieces.add(grid[piece.getLine()][x-1]);
            x--;
        }
        //Verifier à droite
        x = piece.getColumn();

        while(x<COLS-1 && grid[piece.getLine()][x+1].getColor() == piece.getColor()){
            /*if(x<COLS-2 && grid[piece.getLine()][x+1].getColor() ==null && grid[piece.getLine()][x+2].getColor()== piece.getColor()){
                alignied_pieces.add(grid[piece.getLine()][x+2]);
            }*/
            alignied_pieces.add(grid[piece.getLine()][x+1]);
            x++;
        }

        Combination combination = new Combination("horizontal", alignied_pieces);
        return combination;
    }



    public Combination checkFirstDiagonal2(Piece piece){
        /*
         * Create a new first-diagonal Combination with alignied pieces
         * @param piece(Piece): piece added waiting for check Combination
         * @return (Combination): Combination of alignied pieces 
         */
        ArrayList<Piece> alignied_pieces = new ArrayList<Piece>();
        alignied_pieces.add(piece);

        //Cas n°1 : diagonale gauche->droite, bas -> haut

        //Verifier à gauche
        int x = piece.getColumn();
        int y = piece.getLine();

        while(x>0 && y<ROWS-1 && grid[y+1][x-1].getColor() == piece.getColor()){
            alignied_pieces.add(grid[y+1][x-1]);
            y++;
            x--;
        }
        //Verifier à droite
        x = piece.getColumn();
        y = piece.getLine();

        while(x<COLS-1 && y>0 && grid[y-1][x+1].getColor() == piece.getColor()){

            alignied_pieces.add(grid[y-1][x+1]);
            y--;
            x++;
        }
        Combination combination = new Combination("first-diagonal", alignied_pieces);
        return combination;

    }

    public Combination checkSecondDiagonal2(Piece piece){
        /*
         * Create a new second-diagonal Combination with alignied pieces
         * @param piece(Piece): piece added waiting for check Combination
         * @return (Combination): Combination of alignied pieces 
         */

        int x = piece.getColumn();
        int y = piece.getLine();
        ArrayList<Piece> alignied_pieces = new ArrayList<Piece>();
        alignied_pieces.add(piece);

        while(x>0 && y>0 && grid[y-1][x-1].getColor() == piece.getColor()){

            alignied_pieces.add(grid[y-1][x-1]);
            x--;
            y--;
        }
         //Verifier à droite
         x = piece.getColumn();
         y = piece.getLine();

         while(x<COLS-1 && y<ROWS-1 && grid[y+1][x+1].getColor() == piece.getColor()){
            
            alignied_pieces.add(grid[y+1][x+1]);
            x++;
            y++;
        }
        Combination combination = new Combination("second-diagonal", alignied_pieces);
        return combination;

    }

    public boolean checkVictory(Piece piece){
        /*
         * Return if combination has at least 4 alignied pieces (win)
         * @param piece(Piece): piece to check
         * @return (boolean): true if 4 pieces are alignied, false if no
         */

        Combination combination = checkVertically2(piece);
        if(combination.getPieces().size() >=4){
            highlightWinningPieces(combination);
            return true;
        }

        combination = checkHorizontally2(piece);
        if(combination.getPieces().size() >=4){
            highlightWinningPieces(combination);
            return true;
        }

        combination = checkFirstDiagonal2(piece);
        if(combination.getPieces().size() >=4){
            highlightWinningPieces(combination);
            return true;
        }
        combination = checkSecondDiagonal2(piece);
        if(combination.getPieces().size() >=4) {
            highlightWinningPieces(combination);
            return true;
        }
        

        return false;
                
    }
    public void highlightWinningPieces(Combination pieces){

        for (Piece piece : pieces.getPieces()) {
            piece.setColor(Color.CYAN);
        }
    }

 

    
}