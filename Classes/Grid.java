package Classes;

import java.awt.Color;

public class Grid{

    private Piece[][] grid;

    public Piece[][] getGrid() {
        return grid;
    }



    public static final int ROWS = 6;
    public static final int COLS = 7;
   


    public void createGrid(){
        
        Piece[][] grid = new Piece[ROWS][COLS];

        for(int line=0; line<ROWS;line++){   
            for(int column=0; column <COLS; column++){
                grid[line][column] = new Piece(column, line);
            }
        }
        this.grid = grid;
    }
    

    public void printGrid() {
        System.out.print("0 1 2 3 4 5 6");
        System.out.println();
        for (int line = 0; line < ROWS; line++) {
            for (int column = 0; column < COLS; column++) {
                
                System.out.print(this.grid[line][column].printPiece() + " ");
            }
            // Passage à la ligne après chaque ligne de la grille
            System.out.println();
        }
    }

    public Piece addPiece(int column, Player player){
        //args : color (String)

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

    

    public boolean isFull(){
        for (int line = 0; line < ROWS; line++) {
            for (int column = 0; column < COLS; column++) {
                if(this.grid[line][column].getColor() ==null){
                    return false;
                }
            }
        }
        return true;
    }
    
    public int checkVertically(Piece piece){
        int y = piece.getLine();
        int alignied_pieces = 1;
        while(y<ROWS-1 && grid[y+1][piece.getColumn()].getColor() == piece.getColor()){
            alignied_pieces++;
            y++;
        }
        return alignied_pieces;
    }

    public int checkHorizontally(Piece piece){
        //Verifier à gauche
        int x = piece.getColumn();
        int alignied_pieces = 1;
        
        while(x>0 && grid[piece.getLine()][x-1].getColor() == piece.getColor()){
            alignied_pieces++;
            x--;
        }
        //Verifier à droite
        x = piece.getColumn();

        while(x<COLS-1 && grid[piece.getLine()][x+1].getColor() == piece.getColor()){
            alignied_pieces++;
            x++;
        }
        return alignied_pieces;
    }

    public int checkFirstDiagonal(Piece piece){
        int alignied_pieces = 1;

        //Cas n°1 : diagonale gauche->droite, bas -> haut

        //Verifier à gauche
        int x = piece.getColumn();
        int y = piece.getLine();

        while(x>0 && y<ROWS-1 && grid[y+1][x-1].getColor() == piece.getColor()){
            alignied_pieces++;
            y++;
            x--;
        }
        //Verifier à droite
        x = piece.getColumn();
        y = piece.getLine();

        while(x<COLS-1 && y>0 && grid[y-1][x+1].getColor() == piece.getColor()){

            alignied_pieces++;
            y--;
            x++;
        }
        return alignied_pieces;

    }

    public int checkSecondDiagonal(Piece piece){
        //Cas n°2 : diagonale gauche->droite, haut -> bas

        int alignied_pieces = 1;
        //Verifier à gauche

        int x = piece.getColumn();
        int y = piece.getLine();

        while(x>0 && y>0 && grid[y-1][x-1].getColor() == piece.getColor()){

            alignied_pieces++;
            x--;
            y--;
        }
         //Verifier à droite
         x = piece.getColumn();
         y = piece.getLine();

         while(x<COLS-1 && y<ROWS-1 && grid[y+1][x+1].getColor() == piece.getColor()){
            
            alignied_pieces++;
            x++;
            y++;
        }
        return alignied_pieces;
    }

    public boolean checkVictory(Piece piece){


        int alignied_pieces = checkVertically(piece);
        if(alignied_pieces>=4){
            return true;
        }
        alignied_pieces = checkFirstDiagonal(piece);
        if(alignied_pieces>=4){
            return true;
        }
        alignied_pieces = checkSecondDiagonal(piece);
        if(alignied_pieces>=4){
            return true;
        }
        alignied_pieces = checkHorizontally(piece);
        if(alignied_pieces>=4){
            return true;
        }

        return false;
                
    }
    public void highlightWinningPieces(Piece[] pieces){
        for (Piece piece : pieces) {
            piece.setColor(Color.ORANGE);
        }
    }

   /*  public void highlightWinningPieces(Piece piece) {
        int alignied_pieces = checkVertically(piece);
        if(alignied_pieces>=4){
            int y = piece.getLine();
            while(y<ROWS-1 && grid[y+1][piece.getColumn()].getColor() == piece.getColor()){
                grid[y+1][piece.getColumn()].setColor(Color.ORANGE);
                y++;
            }
        }
        alignied_pieces = checkFirstDiagonal(piece);
        if(alignied_pieces>=4){
             int x = piece.getColumn();
            int y = piece.getLine();
            while(x>0 && y<ROWS-1 && grid[y+1][x-1].getColor() == piece.getColor()){
                grid[y+1][x-1].setColor(Color.ORANGE);
                x--;
                y++;
            }
            x = piece.getColumn();
            y = piece.getLine();
            while(x<COLS-1 && y>0 && grid[y-1][x+1].getColor() == piece.getColor()){
                grid[y-1][x+1].setColor(Color.ORANGE);
                x++;
                y--;
            }
        }
        alignied_pieces = checkSecondDiagonal(piece);
        if(alignied_pieces>=4){
            int x = piece.getColumn();
            int y = piece.getLine();
            while(x>0 && y>0 && grid[y-1][x-1].getColor() == piece.getColor()){
                grid[y-1][x-1].setColor(Color.ORANGE);
                x--;
                y--;
            }
            x = piece.getColumn();
            y = piece.getLine();
            while(x<COLS-1 && y<ROWS-1 && grid[y+1][x+1].getColor() == piece.getColor()){
                grid[y+1][x+1].setColor(Color.ORANGE);
                x++;
                y++;
            }
        }
        alignied_pieces = checkHorizontally(piece);
        if(alignied_pieces>=4){
             int x = piece.getColumn();
            while(x>0 && grid[piece.getLine()][x-1].getColor() == piece.getColor()){
                grid[piece.getLine()][x-1].setColor(Color.ORANGE);
                x--;
            }
            x = piece.getColumn();
            while(x<COLS-1 && grid[piece.getLine()][x+1].getColor() == piece.getColor()){
                grid[piece.getLine()][x+1].setColor(Color.ORANGE);
                x++;
            }
        }
    
    }*/
    

    
}