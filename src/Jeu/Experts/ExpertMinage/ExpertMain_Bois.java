package Jeu.Experts.ExpertMinage;

import Exception.*;
import Jeu.Bloc.BlocBois;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Item.MainVide;
import Jeu.Objets;

public class ExpertMain_Bois extends Expert {
    public ExpertMain_Bois(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
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
