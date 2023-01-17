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
        System.out.print("0 1 2 3 4 5 6");
        System.out.println();
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
     
    public static void GenerateRandom() {
          int min = 0; // Minimum value of range
          int max = 6; // Maximum value of range
          // Generate random int value from min to max
          int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
          // Printing the generated random numbers
          System.out.println(random_int);
        }
    }
