package Jeu.Experts.ExpertPotions;

import Jeu.Item.Nourriture.Potion;
import Jeu.Item.Nourriture.Potion_SOIN;

import java.util.ArrayList;

public class ExpertPotions_SOIN extends ExpertPotions {
    public ExpertPotions_SOIN(ExpertPotions suivant) {
        super(suivant);
    }

    @Override
    public ArrayList<Object> donneMontant(Potion quelPotion) throws Exception {
        // On va add dans l'arraylist le montant de la vie à donner
        int montantVie = 20;
        ArrayList<Object> res = new ArrayList<Object>();
        res.add(montantVie);
        return res;
    }

    @Override
    public boolean estExistant(Potion quelPotion) {
        if (quelPotion.equals(new Potion_SOIN())) {
            return true;
        }
        return false;
    }
}
