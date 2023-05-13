package Jeu.Experts.ExpertMinage;

import Jeu.Bloc.BlocHerbe;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Item.PiocheBois;
import Jeu.Objets;
import Exception.*;

public class ExpertPiocheBois_Herbe extends Expert {
    public ExpertPiocheBois_Herbe(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        if (dansMain == null && blocVise == null) {
            throw new ExpertManquantException();
        }
        return new BlocHerbe();
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof PiocheBois) && (blocVise instanceof BlocHerbe)) {
            return true;
        }
        return false;
    }
}
