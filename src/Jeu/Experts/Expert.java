package Jeu.Experts;
import Exception.*;
import Jeu.Bloc.Bloc;
import Jeu.Objets;

public abstract class Expert {
    private Expert suivant = null;

    public Expert(Expert suivant) {
        this.suivant = suivant;
    }

    public Objets expertiser(Objets dansMain, Objets blocVise) throws Exception {
        Objets res = null;
        if (peutResoudre(dansMain, blocVise))
        // Si le parser sait parser la ligne, il la parse
        {
            res = resout(dansMain, blocVise); // Ca va renvoyer le bloc qui est cassé
        } else if (aUnSuivant())
            // S'il ne sait pas mais qu'il a un suivant dans la liste chaine, il lui repasse la ligne et qu'il se débrouille !
            getSuivant().expertiser(dansMain, blocVise);
        else
            // Sinon, on est arrivé au bout sans trouver un parser
            // et on lance une exception ! Que le prog appelant se débrouille avec cette ligne !
            throw new ExpertManquantException();
        return res;
    }

    private Expert getSuivant() {
        return suivant;
    }

    private boolean aUnSuivant() {
        return suivant != null;
    }

    public abstract Objets resout(Objets dansMain, Objets blocVise) throws Exception;

    public abstract boolean peutResoudre(Objets dansMain, Objets blocVise);
}
