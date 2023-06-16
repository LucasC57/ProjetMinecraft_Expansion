package Jeu.Experts.ExpertPotions;

import Jeu.Experts.ExpertNourritures.ExpertNourritures;
import Jeu.Item.Nourriture.Nourriture;
import Jeu.Item.Nourriture.Potion;

import java.util.ArrayList;

public abstract class ExpertPotions {
    private ExpertPotions suivant = null;

    public ExpertPotions(ExpertPotions suivant) {
        this.suivant = suivant;
    }

    public ArrayList<Object> expertiserPotions(Potion quelPotion) throws Exception {

        // On va renvoyer une arraylist, pour prévoir la possibilité d'autres potions

        ArrayList<Object> res = null;
        if (estExistant(quelPotion))
        {
            res = donneMontant(quelPotion); // Cela va renvoyer true ou false cela l'existence ou non du type de l'entité
        } else if (aUnSuivant()) {
            // S'il ne sait pas mais qu'il a un suivant dans la liste chaine, il lui repasse la ligne et qu'il se débrouille !
            getSuivant().expertiserPotions(quelPotion);
        }
        return res;
    }
    private ExpertPotions getSuivant() {
        return suivant;
    }

    private boolean aUnSuivant() {
        return suivant != null;
    }
    public abstract ArrayList<Object> donneMontant(Potion quelPotion) throws Exception;
    public abstract boolean estExistant(Potion quelPotion);
}

