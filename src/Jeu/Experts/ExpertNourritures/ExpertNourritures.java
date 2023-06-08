package Jeu.Experts.ExpertNourritures;

import Jeu.Experts.ExpertTypeEntity.ExpertTypeEntity;
import Jeu.Item.Nourriture.Nourriture;

public abstract class ExpertNourritures {
    private ExpertNourritures suivant = null;

    public ExpertNourritures(ExpertNourritures suivant) {
        this.suivant = suivant;
    }

    public int expertiserNourriture(Nourriture quelNourriture) throws Exception {
        int res = 0;
        if (estExistant(quelNourriture))
        {
            res = donneMontant(quelNourriture); // Cela va renvoyer true ou false cela l'existence ou non du type de l'entité
        } else if (aUnSuivant()) {
            // S'il ne sait pas mais qu'il a un suivant dans la liste chaine, il lui repasse la ligne et qu'il se débrouille !
            getSuivant().expertiserNourriture(quelNourriture);
        }
        return res;
    }
    private ExpertNourritures getSuivant() {
        return suivant;
    }

    private boolean aUnSuivant() {
        return suivant != null;
    }
    public abstract int donneMontant(Nourriture quelNourriture) throws Exception;
    public abstract boolean estExistant(Nourriture quelNourriture);
}
