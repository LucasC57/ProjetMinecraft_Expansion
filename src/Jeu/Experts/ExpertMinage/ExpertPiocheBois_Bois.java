package Jeu.Experts.ExpertMinage;

import Jeu.Bloc.BlocBois;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Item.PiocheBois;
import Jeu.Objets;
import Exception.*;

public class ExpertPiocheBois_Bois extends Expert {
    public ExpertPiocheBois_Bois(Expert suivant) {
        super(suivant);
    }

    @Override
    public Objets resout(Objets dansMain, Objets blocVise) throws Exception {
        return new BlocBois(); // On peut récupérer du bois même si ce n'est pas l'outil optimal
    }

    @Override
    public boolean peutResoudre(Objets dansMain, Objets blocVise) {
        if ((dansMain instanceof PiocheBois) && (blocVise instanceof BlocBois)) {
            return true;
        }
        return false;
    }
}
