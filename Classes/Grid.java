package Classes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

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
    
    public int getLastPieceLine(int column){
        int last_line = ROWS-1;
        for(int line = ROWS-1; line>0; line--){
            if(this.grid[line-1][column].getColor() !=null){
                last_line = line-1;
            }
        }
        return last_line;
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
    
    public List<Piece> checkVertically(Piece piece){
        int y = piece.getLine();
        List<Piece> alignied_pieces = new ArrayList<Piece>();
        alignied_pieces.add(piece);
        
        while(y<ROWS-1 && grid[y+1][piece.getColumn()].getColor() == piece.getColor()){
            alignied_pieces.add(grid[y+1][piece.getColumn()]);
            y++;
        }
        return alignied_pieces;
    }

    public Combination checkVertically2(Piece piece){
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
        int x = piece.getColumn();
        ArrayList<Piece> alignied_pieces = new ArrayList<Piece>();

        alignied_pieces.add(piece);
        
        while(x>0 && grid[piece.getLine()][x-1].getColor() == piece.getColor()){
            alignied_pieces.add(grid[piece.getLine()][x-1]);
            x--;
        }
        //Verifier à droite
        x = piece.getColumn();

        while(x<COLS-1 && grid[piece.getLine()][x+1].getColor() == piece.getColor()){
            alignied_pieces.add(grid[piece.getLine()][x+1]);
            x++;
        }

        Combination combination = new Combination("horizontal", alignied_pieces);
        return combination;
    }

    public List<Piece> checkHorizontally(Piece piece){
        //Verifier à gauche
        int x = piece.getColumn();
        List<Piece> alignied_pieces = new ArrayList<Piece>();
        alignied_pieces.add(piece);
        
        while(x>0 && grid[piece.getLine()][x-1].getColor() == piece.getColor()){
            alignied_pieces.add(grid[piece.getLine()][x-1]);
            x--;
        }
        //Verifier à droite
        x = piece.getColumn();

        while(x<COLS-1 && grid[piece.getLine()][x+1].getColor() == piece.getColor()){
            alignied_pieces.add(grid[piece.getLine()][x+1]);
            x++;
        }
        return alignied_pieces;
    }

    public List<Piece> checkFirstDiagonal(Piece piece){
        List<Piece> alignied_pieces = new ArrayList<Piece>();
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
        return alignied_pieces;

    }

    public List<Piece> checkSecondDiagonal(Piece piece){
        //Cas n°2 : diagonale gauche->droite, haut -> bas

        List<Piece> alignied_pieces = new ArrayList<Piece>();
        alignied_pieces.add(piece);
        //Verifier à gauche

        int x = piece.getColumn();
        int y = piece.getLine();

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
        return alignied_pieces;
    }

    public boolean checkVictory(Piece piece){


        List<Piece> alignied_pieces = checkVertically(piece);
        if(alignied_pieces.size()>=4){
            highlightWinningPieces(alignied_pieces);
            return true;
        }
       alignied_pieces = checkFirstDiagonal(piece);
        if(alignied_pieces.size()>=4){
            highlightWinningPieces(alignied_pieces);
            return true;
        }
        alignied_pieces = checkSecondDiagonal(piece);
        if(alignied_pieces.size()>=4){
            highlightWinningPieces(alignied_pieces);
            return true;
        }
        alignied_pieces = checkHorizontally(piece);
        if(alignied_pieces.size()>=4){
            highlightWinningPieces(alignied_pieces);
            return true;
        }

        return false;
                
    }
    public void highlightWinningPieces(List<Piece> pieces){
        for (Piece piece : pieces) {
            piece.setColor(Color.CYAN);
        }
    }

 

    
}