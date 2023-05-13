package Jeu.Experts;

import Jeu.Bloc.Bloc;
import Exception.*;
import Jeu.Bloc.BlocAir;
import Jeu.Bloc.BlocBois;
import Jeu.Item.MainVide;
import Jeu.Objets;

public class ExpertMain_Bois extends Expert {
    public ExpertMain_Bois(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        if (dansMain == null && blocVise == null) {
            throw new ExpertManquantException();
        }
        return new BlocBois(); // On peut miner du bois à la main
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof MainVide) && (blocVise instanceof BlocBois)) {
            return true;
        }
        return false;
    }

}
