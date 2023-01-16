package Classes;
import Classes.Piece;

public class Grid{

    private Piece[][] grid;

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
        for (int line = 0; line < ROWS; line++) {
            for (int column = 0; column < COLS; column++) {
                
                System.out.print(this.grid[line][column] + " ");
            }
            // Passage à la ligne après chaque ligne de la grille
            System.out.println();
        }
    }
    
}