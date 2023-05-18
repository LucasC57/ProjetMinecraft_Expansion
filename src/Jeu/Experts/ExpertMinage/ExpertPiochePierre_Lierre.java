package Jeu.Experts.ExpertMinage;

import Jeu.Bloc.BlocLierre;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Item.PiochePierre;
import Jeu.Objets;
import Exception.*;

public class ExpertPiochePierre_Lierre extends Expert {
    public ExpertPiochePierre_Lierre(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        return null; // On ne peut pas récupérer de la feuille
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof PiochePierre) && (blocVise instanceof BlocLierre)) {
            return true;
        }
        return false;
    }
}
