package Classes;

import java.awt.Color;


public class Player {
  
    private String nom;
    private Color couleur;

    public Player(String nom) {
        this.nom = nom;

        this.couleur = Color.RED; // cst la couleur par default initial
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
}
