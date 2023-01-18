package Classes;
import java.awt.Color;
public class Piece {
  
    private int column;
    private int line;
    private Color color;

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

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String colorToAnsiCode(){
        int r = this.color.getRed();
        int g = this.color.getGreen();
        int b = this.color.getBlue();
        return String.format("\033[38;2;%d;%d;%dm", r, g, b);
    }

    public String printPiece(){
        if(this.getColor()==null){
            return "_";
        }
        else{
            String ansi_code = this.colorToAnsiCode();
            return ansi_code + "O" + "\033[0m";
        }
    }

  



    

}