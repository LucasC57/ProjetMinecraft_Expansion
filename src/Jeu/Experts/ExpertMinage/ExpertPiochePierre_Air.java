package Jeu.Experts.ExpertMinage;

import Jeu.Bloc.BlocAir;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Item.PiochePierre;
import Jeu.Objets;
import Exception.*;

public class ExpertPiochePierre_Air extends Expert {
    public ExpertPiochePierre_Air(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        if (dansMain == null && blocVise == null) {
            throw new ExpertManquantException();
        }
        return null; // On ne peut pas miner de l'air
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof PiochePierre) && (blocVise instanceof BlocAir)) {
            return true;
        }
        return false;
    }
}
