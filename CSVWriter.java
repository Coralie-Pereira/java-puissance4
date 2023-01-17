import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;






public class CSVWriter {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "pseudo,leScore,scoMax";

    public static void writeCsvFile(String fileName, topscore sc) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);

            // Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());

            // Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);
            fileWriter.append(sc.getPseudo());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(String.valueOf(sc.getLeScore()));
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(String.valueOf(sc.getScoMax()));
            fileWriter.append(NEW_LINE_SEPARATOR);

            System.out.println("Le file CSV a bien été crée");

        } catch (Exception e) {
            System.out.println("Erreur");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Erreur pendant la fermeture du fichier");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        topscore sc = new topscore();
        sc.setPseudo("Pierre");
        sc.setLeScore(10);
        sc.setScoMax(100);
        System.out.println(sc.getPseudo() + " : " + sc.getLeScore());
        writeCsvFile("topscore.csv", sc);
    }
}

class topscore {
    private String pseudo;
    private int leScore;
    private int scoMax;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getLeScore() {
        return leScore;
    }

    public void setLeScore(int leScore) {
        this.leScore = leScore;
    }

    public int getScoMax() {
        return scoMax;
    }

    public void setScoMax(int scoMax) {
        this.scoMax = scoMax;
    }
}




    

  
    
     