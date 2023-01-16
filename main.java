import java.util.Scanner;

import Classes.*;

public class main {
    public static void main(String[] args) {
        
        Grid grid = new Grid();
        grid.createGrid();
        grid.printGrid();

        while(!grid.isFull()){
            grid.addPiece(chooseColumn());
            grid.printGrid();
       }
    
    }

    public static int chooseColumn(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Veuillez choisir une colonne entre 0 et 6");
        int choice = scan.nextInt();
        return choice;


    }
}
