package Jeu.Experts;

import Jeu.Bloc.BlocAir;
import Jeu.Bloc.BlocBois;
import Jeu.Bloc.BlocPlanche;
import Jeu.Item.Baton;
import Jeu.Objets;
import Exception.*;

import java.util.ArrayList;

public class ExpertCraft_Planches_Baton extends ExpertCraft {
    public ExpertCraft_Planches_Baton(ExpertCraft suivant) {
        super(suivant);
    }

    @Override
    public ArrayList<Objets> resout(ArrayList<Objets> recette) throws Exception {
        if (recette.size() != 9) {
            throw new ExpertManquantException();
        }
        // On va créer l'ArrayList avec le résultat
        ArrayList<Objets> resRecette = new ArrayList<Objets>();
        for (int i = 0; i < 4; i++) {
            resRecette.add(i, new Baton());
        }
        return resRecette;
    }

    @Override
    public boolean peutResoudre(ArrayList<Objets> recette) {
        /*
                * * *
                * b *           -> En sachant que les étoiles sont des BlocAir
                * b *

            On part donc du principe qu'ici on ne va gérer que la table de craft 3x3
            Que les 3 premiers objets sont la première ligne, etc
        */
        if ((recette.size() == 9) && (recette.get(4) instanceof BlocPlanche) && (recette.get(7) instanceof BlocPlanche)
            && recette.get(0) instanceof BlocAir &&
                recette.get(1) instanceof BlocAir &&
                recette.get(2) instanceof BlocAir &&
                recette.get(3) instanceof BlocAir &&
                recette.get(5) instanceof BlocAir &&
                recette.get(6) instanceof BlocAir &&
                recette.get(8) instanceof BlocAir) {
            // On vérifie que l'on est bien dans une table de craft, et que la répartition des éléments est réspecté
            return true;
        }
        return false;
    }

}
