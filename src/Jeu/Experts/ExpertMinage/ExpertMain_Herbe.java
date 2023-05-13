package Jeu.Experts.ExpertMinage;

import Exception.*;
import Jeu.Bloc.BlocHerbe;
import Jeu.Bloc.BlocTerre;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Item.MainVide;
import Jeu.Objets;

public class ExpertMain_Herbe extends Expert {
    public ExpertMain_Herbe(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        if (dansMain == null && blocVise == null) {
            throw new ExpertManquantException();
        }
        return new BlocTerre(); // On peut miner de l'herbe à main nue
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof MainVide) && (blocVise instanceof BlocHerbe)) {
            return true;
        }
        return false;
    }
}
