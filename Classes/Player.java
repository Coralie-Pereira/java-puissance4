package Classes;

import java.awt.Color;


public class Player {
  
    private String nom;
    private Color couleur;
    private int score;

    public Player(String nom, Color color,int score) {
        this.nom = nom;

        this.couleur = color; // cst la couleur par default initial

        this.score = score;
    }

    public String getNom() { // pour prendre le nom du user
        return nom;
    }

    public Color getCouleur() {    //pour prendre la couleur que le user va choisir 
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    public int getscore(){
        return  score;
    }
    public void setscore(int score){
        this.score = score;
    }
  
}