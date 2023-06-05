package Jeu.Experts.ExpertTypeEntity;

import Exception.*;

public abstract class ExpertTypeEntity {
    private ExpertTypeEntity suivant = null;

    public ExpertTypeEntity(ExpertTypeEntity suivant) {
        this.suivant = suivant;
    }

    public boolean expertiserEntity(String type) throws Exception {
        boolean res = false;
        if (estExistant(type))
        {
            res = resout(type); // Cela va renvoyer true ou false cela l'existence ou non du type de l'entité
        } else if (aUnSuivant()) {
            // S'il ne sait pas mais qu'il a un suivant dans la liste chaine, il lui repasse la ligne et qu'il se débrouille !
            getSuivant().expertiserEntity(type);
        }
        return res;
    }
    private ExpertTypeEntity getSuivant() {
        return suivant;
    }

    private boolean aUnSuivant() {
        return suivant != null;
    }
    public abstract boolean resout(String type) throws Exception;
    public abstract boolean estExistant(String type);
}
