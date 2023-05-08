package Jeu.Experts;

import Jeu.Bloc.BlocLierre;
import Jeu.Item.PiocheBois;
import Jeu.Objets;
import Exception.*;

public class ExpertPiocheBois_Lierre extends Expert {
    public ExpertPiocheBois_Lierre(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        if (dansMain == null && blocVise == null) {
            throw new ExpertManquantException();
        }
        return null; // On en peut pas récupérer de la feuille
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof PiocheBois) && (blocVise instanceof BlocLierre)) {
            return true;
        }
        return false;
    }
}
