package Jeu.Experts.ExpertMinage;

import Exception.*;
import Jeu.Bloc.BlocPierre;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Item.MainVide;
import Jeu.Objets;

public class ExpertMain_Pierre extends Expert {
    public ExpertMain_Pierre(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        if (dansMain == null && blocVise == null) {
            throw new ExpertManquantException();
        }
        return null; // Il ne va pas récupérer de la pierre
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof MainVide) && (blocVise instanceof BlocPierre)) {
            return true;
        }
        return false;
    }
}
