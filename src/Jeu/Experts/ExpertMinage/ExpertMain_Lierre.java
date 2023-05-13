package Jeu.Experts.ExpertMinage;

import Exception.*;
import Jeu.Bloc.BlocHerbe;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Item.MainVide;
import Jeu.Objets;

public class ExpertMain_Lierre extends Expert {
    public ExpertMain_Lierre(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        if (dansMain == null && blocVise == null) {
            throw new ExpertManquantException();
        }
        return null; // On peut miner des feuilles à main nue mais cela nous donne rien logiquement
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof MainVide) && (blocVise instanceof BlocHerbe)) {
            return true;
        }
        return false;
    }
}
