package Classes;


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

    public void addPiece(int column){
        //args : color (String)
        if(column>=0 && column<=COLS){
        
            for(int line = ROWS-1; line>=0; line--){
                if(this.grid[line][column].getColor()==null){
                    this.grid[line][column].setColor("red");
                    return;
                }
                else{
                    if(line==0){
                        System.out.println("Impossible");
                    }
                }
            }
        }
        else{
            System.out.println("Mauvaise colonne");
        }
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
    
    
}