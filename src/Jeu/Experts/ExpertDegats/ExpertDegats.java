package Jeu.Experts.ExpertDegats;

import Jeu.Experts.ExpertTypeEntity.ExpertTypeEntity;
import Jeu.Item.Item;
import Jeu.Objets;

public abstract class ExpertDegats {
    private ExpertDegats suivant = null;

    public ExpertDegats(ExpertDegats suivant) {
        this.suivant = suivant;
    }

    public int expertiserDegats(Item arme) throws Exception {
        int res = 0;
        if (estValide(arme))
        {
            res = donneMontant(arme); // Cela va renvoyer le montant de degats infligés en fonction de l'arme
        } else if (aUnSuivant()) {
            // S'il ne sait pas mais qu'il a un suivant dans la liste chaine, il lui repasse la ligne et qu'il se débrouille !
            getSuivant().expertiserDegats(arme);
        }
        return res;
    }
    private ExpertDegats getSuivant() {
        return suivant;
    }

    private boolean aUnSuivant() {
        return suivant != null;
    }
    public abstract int donneMontant(Item arme) throws Exception;
    public abstract boolean estValide(Item arme);
}
