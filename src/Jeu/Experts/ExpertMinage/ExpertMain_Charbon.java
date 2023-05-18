package Jeu.Experts.ExpertMinage;

import Jeu.Bloc.BlocMineraiCharbon;
import Jeu.Item.MainVide;
import Jeu.Objets;

public class ExpertMain_Charbon extends Expert {
    public ExpertMain_Charbon(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        return null; // On ne peut pas miner du charbon à main nue.
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof MainVide) && (blocVise instanceof BlocMineraiCharbon)) {
            return true;
        }
        return false;
    }
}
