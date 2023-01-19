package Classes;

import java.util.ArrayList;

public class Combination {


    private String type;
    private ArrayList<Piece> pieces;


 

    public Combination(String type, ArrayList<Piece> pieces){
        this.type = type;
        this.pieces = pieces;    
    }
   
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }
    

    
}
