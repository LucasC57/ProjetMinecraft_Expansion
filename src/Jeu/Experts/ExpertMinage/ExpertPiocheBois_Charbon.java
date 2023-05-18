package Jeu.Experts.ExpertMinage;

import Jeu.Bloc.BlocMineraiCharbon;
import Jeu.Item.Charbon;
import Jeu.Item.PiocheBois;
import Jeu.Objets;

public class ExpertPiocheBois_Charbon extends Expert {
    public ExpertPiocheBois_Charbon(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        return new Charbon();
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof PiocheBois) && (blocVise instanceof BlocMineraiCharbon)) {
            return true;
        }
        return false;
    }
}
