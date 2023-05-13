package Jeu.Experts;
import Jeu.Bloc.Bloc;
import Exception.*;
import Jeu.Bloc.BlocAir;
import Jeu.Item.MainVide;
import Jeu.Objets;

public class ExpertMain_Air extends Expert {
    public ExpertMain_Air(Expert suivant) {
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
        if ((dansMain instanceof MainVide) && (blocVise instanceof BlocAir)) {
            return true;
        }
        return false;
    }
}
