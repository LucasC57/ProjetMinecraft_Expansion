package Jeu.Experts.ExpertDegats;

import Jeu.Item.EpeeBois;
import Jeu.Item.Item;

public class ExpertDegats_EpeeBois extends ExpertDegats {
    public ExpertDegats_EpeeBois(ExpertDegats suivant) {
        super(suivant);
    }

    @Override
    public int donneMontant(Item arme) throws Exception {
        return 3; // On va ici mettre une valeur arbitraire, on va dire que l'épée en bois c'est 3 de dégats
    }

    @Override
    public boolean estValide(Item arme) {
        if (arme.equals(new EpeeBois())) {
            return true;
        }
        return false;
    }
}
