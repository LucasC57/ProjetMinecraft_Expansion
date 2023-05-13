package Jeu.Experts.ExpertCraft;

import Jeu.Bloc.Bloc;
import Jeu.Bloc.BlocAir;
import Jeu.Bloc.BlocBois;
import Jeu.Bloc.BlocPlanche;
import Jeu.Experts.ExpertCraft.ExpertCraft;
import Jeu.Objets;
import Exception.*;

import java.util.ArrayList;

public class ExpertCraft_Bois_Planches extends ExpertCraft {
    public ExpertCraft_Bois_Planches(ExpertCraft suivant) {
        super(suivant);
    }

    @Override
    public ArrayList<Objets> resout(ArrayList<Objets> recette) throws Exception {
        if (recette.size() != 9) {
            throw new ExpertManquantException();
        }
        ArrayList<Objets> resRecette = new ArrayList<Objets>();
        for (int i = 0; i < 4; i++) {
            Bloc mm = new BlocPlanche();
            resRecette.add(mm);
        }
        return resRecette;
    }

    @Override
    public boolean peutResoudre(ArrayList<Objets> recette) {
        /*
                * * *
                * b *           -> En sachant que les étoiles sont des BlocAir
                * * *

            On part donc du principe qu'ici on ne va gérer que la table de craft 3x3
            Que les 3 premiers objets sont la première ligne, etc
         */
            if (recette.size() == 9 &&
                recette.get(0) instanceof BlocAir &&
                recette.get(1) instanceof BlocAir &&
                recette.get(2) instanceof BlocAir &&
                recette.get(3) instanceof BlocAir &&
                recette.get(4) instanceof BlocBois &&
                recette.get(5) instanceof BlocAir &&
                recette.get(6) instanceof BlocAir &&
                recette.get(7) instanceof BlocAir &&
                recette.get(8) instanceof BlocAir) {
                return true;
            }
            return false;
    }
}
