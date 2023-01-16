import java.util.Scanner;
import java.awt.Color;
import javax.swing.JColorChooser; // importer de java 
import Classes.*;

public class main {
    public static void main(String[] args) {

        Player joueur = new Player("bob");

        Color couleurChoisie = JColorChooser.showDialog(null, "couleur pour les jetons ", joueur.getCouleur());  // choisir la couleur 
    
        joueur.setCouleur(couleurChoisie);
        System.out.println(joueur.getCouleur());
        
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
