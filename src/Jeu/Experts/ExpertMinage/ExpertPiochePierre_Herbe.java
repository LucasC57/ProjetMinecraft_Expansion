package Jeu.Experts.ExpertMinage;

import Jeu.Bloc.BlocHerbe;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Item.PiochePierre;
import Jeu.Objets;
import Exception.*;

public class ExpertPiochePierre_Herbe extends Expert {
    public ExpertPiochePierre_Herbe(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        if (dansMain == null && blocVise == null) {
            throw new ExpertManquantException();
        }
        return new BlocHerbe(); // Pas optimal
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof PiochePierre) && (blocVise instanceof BlocHerbe)) {
            return true;
        }
        return false;
    }
}
