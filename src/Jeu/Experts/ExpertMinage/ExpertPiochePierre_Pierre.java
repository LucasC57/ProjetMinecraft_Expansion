package Jeu.Experts.ExpertMinage;

import Jeu.Bloc.BlocPierre;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Item.PiochePierre;
import Jeu.Objets;
import Exception.*;

public class ExpertPiochePierre_Pierre extends Expert {
    public ExpertPiochePierre_Pierre(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        return new BlocPierre(); // On peut recup
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof PiochePierre) && (blocVise instanceof BlocPierre)) {
            return true;
        }
        return false;
    }
}
