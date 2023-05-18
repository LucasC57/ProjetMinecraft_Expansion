package Jeu.Experts.ExpertMinage;

import Jeu.Bloc.BlocMineraiCharbon;
import Jeu.Item.Charbon;
import Jeu.Item.PiochePierre;
import Jeu.Objets;

public class ExpertPiochePierre_Charbon extends Expert {
    public ExpertPiochePierre_Charbon(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        return new Charbon();
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof PiochePierre) && (blocVise instanceof BlocMineraiCharbon)) {
            return true;
        }
        return false;
    }
}
