package Jeu.Experts.ExpertNourritures;

import Jeu.Item.Nourriture.Nourriture;
import Jeu.Item.Nourriture.Pomme;

public class ExpertNourritures_Pomme extends ExpertNourritures {
    public ExpertNourritures_Pomme(ExpertNourritures suivant) {
        super(suivant);
    }

    @Override
    public int donneMontant(Nourriture quelNourriture) throws Exception {
        return 15; // La pomme donne 15 de faim
    }

    @Override
    public boolean estExistant(Nourriture quelNourriture) {
        if (quelNourriture.equals(new Pomme())) {
            return true;
        }
        return false;
    }
}
