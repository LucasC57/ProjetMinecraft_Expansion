package Jeu.Experts.ExpertMinage;

import Jeu.Bloc.BlocBois;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Item.PiochePierre;
import Jeu.Objets;
import Exception.*;

public class ExpertPiochePierre_Bois extends Expert {
    public ExpertPiochePierre_Bois(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        if (dansMain == null && blocVise == null) {
            throw new ExpertManquantException();
        }
        return new BlocBois(); // On peut même si ce n'est pas optimal
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof PiochePierre) && (blocVise instanceof BlocBois)) {
            return true;
        }
        return false;
    }
}
