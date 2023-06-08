package Jeu.Experts.ExpertDegats;

import Jeu.Item.EpeePierre;
import Jeu.Item.Item;

public class ExpertDegats_EpeePierre extends ExpertDegats {
    public ExpertDegats_EpeePierre(ExpertDegats suivant) {
        super(suivant);
    }

    @Override
    public int donneMontant(Item arme) throws Exception {
        return 5; // Idem, 5 pour l'épée en pierre
    }

    @Override
    public boolean estValide(Item arme) {
        if (arme.equals(new EpeePierre())) {
            return true;
        }
        return false;
    }
}
