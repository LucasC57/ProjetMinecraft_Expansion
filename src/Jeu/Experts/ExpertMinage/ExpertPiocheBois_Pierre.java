package Jeu.Experts.ExpertMinage;

import Jeu.Bloc.BlocPierre;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Item.PiocheBois;
import Jeu.Objets;
import Exception.*;

public class ExpertPiocheBois_Pierre extends Expert {
    public ExpertPiocheBois_Pierre(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        return new BlocPierre(); // On peut avec une pioche en bois
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof PiocheBois) && (blocVise instanceof BlocPierre)) {
            return true;
        }
        return false;
    }
}
