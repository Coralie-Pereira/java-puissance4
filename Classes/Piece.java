package Classes;
public class Piece {
  
    private int column;
    private int line;
    private String color;

    public Piece(int column, int line){
        this.column = column;
        this.line = line;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getLine() {
        return this.line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString(){
        
        if(this.color ==null){
            return "_";
        }
        
        return "O";
        
    

    }



    

}