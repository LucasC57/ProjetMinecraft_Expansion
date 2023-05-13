package Jeu.Experts.ExpertMinage;

import Jeu.Bloc.BlocTerre;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Item.PiochePierre;
import Jeu.Objets;
import Exception.*;

public class ExpertPiochePierre_Terre extends Expert {
    public ExpertPiochePierre_Terre(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        if (dansMain == null && blocVise == null) {
            throw new ExpertManquantException();
        }
        return new BlocTerre(); // On peut même si ce n'est pas très optimal
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof PiochePierre) && (blocVise instanceof BlocTerre)) {
            return true;
        }
        return false;
    }
}
