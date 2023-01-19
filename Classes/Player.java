package Classes;

import java.awt.Color;



public class Player {
  
    private String nom;
    private Color couleur;
    private int ia_level;
    private int score;

    public Player(String nom, Color color,int score) {
        /*
         * Constructor of the player
         * @param nom(String): name of the player
         * @param color (Color): Color of the player's pieces
         * @param score (int): Score of the player
         */
        this.nom = nom;
        this.couleur = color; 
        this.score = score;
    }

    public int getIa_level() {
        /*
         * Get ia level
         * @return (int): ia level
         */
        return ia_level;
    }

    public void setIa_level(int ia_level) {
        /*
         * Set a new value for ia level
         * @param ia_level (int): new ia level
         */
        this.ia_level = ia_level;
    }

    public String getNom() { // pour prendre le nom du user
        /*
         * Get player name
         * @return (String): player name
         */
        return nom;
    }

    public Color getCouleur() {    //pour prendre la couleur que le user va choisir 
        /*
         * Get player color
         * @return (Color): player color
         */
        return couleur;
    }

    public void setCouleur(Color couleur) {
        /*
         * Set player color
         * @param couleur(Color): new player color
         */
        this.couleur = couleur;
    }

    public int getscore(){
        /*
         * Get player score
         * @return (int): player score
         */
        return  score;
    }
    public void setscore(int score){
        /*
         * Set player score
         * @param score(int): new player score
         */
        this.score = score;
    }

    
}

