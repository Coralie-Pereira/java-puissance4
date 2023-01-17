import java.util.Scanner;
import java.awt.Color;
import javax.swing.JColorChooser; // importer de java 


import Classes.*;

public class main {

    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        Player joueur = new Player("bob");
        Color couleurChoisie = JColorChooser.showDialog(null, "couleur pour les jetons ", joueur.getCouleur());  // choisir la couleur 
        joueur.setCouleur(couleurChoisie);

        Player joueur2 = new Player("bob");
        Color couleurChoisie2 = JColorChooser.showDialog(null, "couleur pour les jetons ", joueur.getCouleur());  // choisir la couleur 
        joueur2.setCouleur(couleurChoisie2);


        
        Grid grid = new Grid();
        grid.createGrid();
        grid.printGrid();

        boolean in_game = true;

        while(in_game){
            if(grid.isFull()){
                in_game = false;
            }
            int column = chooseColumn();
            grid.addPiece(column, joueur);
            grid.printGrid();

            int column2 = chooseColumn();
            grid.addPiece(column2, joueur2);
            grid.printGrid();
       }
       scan.close();
    
    }

    

     

    public static int chooseColumn(){
        
        System.out.println("Veuillez choisir une colonne entre 0 et 6");
        int choice = scan.nextInt();
        return choice;


    }

    
}
