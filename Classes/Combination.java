package Classes;

import java.util.ArrayList;

public class Combination {


    private String type;
    private ArrayList<Piece> pieces;


    public Combination(String type, ArrayList<Piece> pieces){
        /*
         * Constructor of a Combination
         * @param type(String): type of the combination (vertical, horizontal...)
         * @param pieces(ArrayList<Piece>): ArrayList of alignied pieces
         */
        this.type = type;
        this.pieces = pieces;    
    }
   
    public String getType() {
        /*
         * Return type of Combination
         * @return (String): type of combination
         */
        return type;
    }

    public void setType(String type) {
        /*
         * Set type of combination
         * @param type (String): new type
         */
        this.type = type;
    }

    public ArrayList<Piece> getPieces() {
        /*
         * Get combination's pieces
         * @return (ArrayList<Piece>): Return combination's pieces
         */
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        /*
         * Set pieces of combination
         * @param pieces (ArrayList<Piece>) : new pieces
         */
        this.pieces = pieces;
    }
       
}
