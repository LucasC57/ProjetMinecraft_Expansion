package Jeu.Experts.ExpertDegats;

import Jeu.Item.Item;
import Jeu.Item.MainVide;

public class ExpertDegats_MainVide extends ExpertDegats {
    public ExpertDegats_MainVide(ExpertDegats suivant) {
        super(suivant);
    }

    @Override
    public int donneMontant(Item arme) throws Exception {
        return 1; // 1 pour la mainVide
    }

    @Override
    public boolean estValide(Item arme) {
        if (arme.equals(new MainVide())) {
            return true;
        }
        return false;
    }
}
