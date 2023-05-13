package Jeu.Experts;

import Jeu.Bloc.Bloc;
import Exception.*;
import Jeu.Bloc.BlocTerre;
import Jeu.Item.MainVide;
import Jeu.Objets;

public class ExpertMain_Terre extends Expert {
    public ExpertMain_Terre(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        if (dansMain == null && blocVise == null) {
            throw new ExpertManquantException();
        }
        return new BlocTerre(); // Il va récupérer de la terre
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof MainVide) && (blocVise instanceof BlocTerre)) {
            return true;
        }
        return false;
    }
}
