package Jeu.Experts.ExpertCraft;

import Jeu.Objets;
import Exception.*;

import java.util.ArrayList;

public abstract class ExpertCraft {
    private ExpertCraft suivant = null;

    public ExpertCraft(ExpertCraft suivant) {
        this.suivant = suivant;
    }

    public ArrayList<Objets> expertiserCraft(ArrayList<Objets> recette) throws Exception {
        ArrayList<Objets> res = new ArrayList<Objets>();
        if (peutResoudre(recette))
        {
            res = resout(recette); // Cela va renvoyer une arraylist d'objets qui correspond au res de la recette
        } else if (aUnSuivant()) {
            // S'il ne sait pas mais qu'il a un suivant dans la liste chaine, il lui repasse la ligne et qu'il se débrouille !
            getSuivant().expertiserCraft(recette);
        } else {
            // Sinon, on est arrivé au bout sans trouver un expert
            // et on lance une exception ! Que le prog appelant se débrouille avec cette ligne !
            throw new ExpertManquantException();
        }
        return res;
    }
    private ExpertCraft getSuivant() {
        return suivant;
    }

    private boolean aUnSuivant() {
        return suivant != null;
    }
    public abstract ArrayList<Objets> resout(ArrayList<Objets> recette) throws Exception;
    public abstract boolean peutResoudre(ArrayList<Objets> recette);
}
