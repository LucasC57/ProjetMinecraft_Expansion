package Jeu.Experts.ExpertMinage;

import Jeu.Bloc.BlocTerre;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Item.PiocheBois;
import Jeu.Objets;
import Exception.*;

public class ExpertPiocheBois_Terre extends Expert {
    public ExpertPiocheBois_Terre(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        return new BlocTerre();
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof PiocheBois) && (blocVise instanceof BlocTerre)) {
            return true;
        }
        return false;
    }
}
