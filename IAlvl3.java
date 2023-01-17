import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IAlvl3 {
  private int[][] tableau;
  private int joueur;
  private Random random;

  public IAlvl3(int[][] tableau, int joueur) {
    this.tableau = tableau;
    this.joueur = joueur;
    random = new Random();
  }

  public int makeMove() {
    List < Integer > coupsPossibles = new ArrayList < > ();
    for (int i = 0; i < 7; i++) {
      if (tableau[5][i] == 0) {
        coupsPossibles.add(i);
      }
    }

    for (int coup: coupsPossibles) {
      if (coupGagnant(coup, joueur)) {
        return coup;
      }
    }
    for (int coup: coupsPossibles) {
      if (coupGagnant(coup, (joueur % 2) + 1)) {
        return coup;
      }
    }

    int coup = coupsPossibles.get(random.nextInt(coupsPossibles.size()));
    for (int i = 5; i >= 0; i--) {
      if (tableau[i][coup] == 0) {
        tableau[i][coup] = joueur;
        return coup;
      }
    }
    return -1;
  }

  private boolean coupGagnant(int coup, int joueur) {
    for (int i = 5; i >= 0; i--) {
      if (tableau[i][coup] == 0) {
        tableau[i][coup] = joueur;
        if (checkVictoire(i, coup, joueur)) {
          tableau[i][coup] = 0;
          return true;
        }
        tableau[i][coup] = 0;
        return false;
      }
    }
    return false;
  }

  private boolean checkVictoire(int row, int col, int joueur) {
    //Check horizontale
    int compteur = 0;
    for (int i = 0; i < 7; i++) {
      if (tableau[row][i] == joueur) {
        compteur++;
        if (compteur == 4) {
          return true;
        }
      } else {
        compteur = 0;
      }
    }

    //Check verticale
    compteur = 0;
    for (int i = 0; i < 6; i++) {
      if (tableau[i][col] == joueur) {
        compteur++;
        if (compteur == 4) {
          return true;
        }
      } else {
        compteur = 0;
      }
    }

    //Check diagonale
    compteur = 0;
    for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
      if (tableau[i][j] == joueur) {
        compteur++;
        if (compteur == 4) {
          return true;
        }
      } else {
        compteur = 0;
      }
    }
    compteur = 0;
    for (int i = row, j = col; i < 6 && j < 7; i++, j++) {
      if (tableau[i][j] == joueur) {
        compteur++;
        if (compteur == 4) {
          return true;
        }
      } else {
        compteur = 0;
      }
    }
    compteur = 0;
    for (int i = row, j = col; i >= 0 && j < 7; i--, j++) {
      if (tableau[i][j] == joueur) {
        compteur++;
        if (compteur == 4) {
          return true;
        }
      } else {
        compteur = 0;
      }
    }
    compteur = 0;
    for (int i = row, j = col; i < 6 && j >= 0; i++, j--) {
      if (tableau[i][j] == joueur) {
        compteur++;
        if (compteur == 4) {
          return true;
        }
      } else {
        compteur = 0;
      }
    }
    return false;
  }
}