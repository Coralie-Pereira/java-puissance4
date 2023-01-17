import java.util.HexFormat;
import java.util.Scanner;
import java.awt.Color;
import javax.swing.JColorChooser; // importer de java 
import javax.swing.JLabel;
import javax.swing.JTextArea;

import Classes.*;

public class main {

    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        Player joueur = new Player("bob");

        Color couleurChoisie = JColorChooser.showDialog(null, "couleur pour les jetons ", joueur.getCouleur());  // choisir la couleur 
    
        joueur.setCouleur(couleurChoisie);

        System.out.println(colorToAnsiCode(joueur.getCouleur()) + "Message à afficher" + "\033[0m");

        
        Grid grid = new Grid();
        grid.createGrid();
        grid.printGrid();

        boolean in_game = true;

        while(in_game){
            if(!grid.isFull()){
                in_game = false;
            }
            int column = chooseColumn();
            grid.addPiece(column);
            grid.printGrid();
       }
       scan.close();
    
    }

    public static String colorToAnsiCode(Color color){
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        return String.format("\033[38;2;%d;%d;%dm", r, g, b);
    }

     

    public static int chooseColumn(){
        
        System.out.println("Veuillez choisir une colonne entre 0 et 6");
        int choice = scan.nextInt();
        return choice;


    }

    
}
