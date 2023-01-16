import Classes.*;

public class main {
    public static void main(String[] args) {
        
        Grid grid = new Grid();
        grid.createGrid();
        
        grid.addPiece(6); //Try 
        grid.addPiece(5);
        grid.addPiece(5);
        grid.addPiece(5);
        grid.addPiece(5);
        grid.addPiece(5);
        grid.addPiece(9);
       

 

        grid.printGrid();
    
    }
}
