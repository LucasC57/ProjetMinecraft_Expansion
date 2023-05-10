package Jeu.Experts;

import Jeu.Bloc.BlocAir;
import Jeu.Bloc.BlocPierre;
import Jeu.Item.Baton;
import Jeu.Item.PiochePierre;
import Jeu.Objets;
import Exception.*;

import java.util.ArrayList;

public class ExpertCraft_PiochePierre extends ExpertCraft {
    public ExpertCraft_PiochePierre(ExpertCraft suivant) {
        super(suivant);
    }

    @Override
    public ArrayList<Objets> resout(ArrayList<Objets> recette) throws Exception {
        if (recette.size() != 9) {
            throw new ExpertManquantException();
        }
        ArrayList<Objets> resRecette = new ArrayList<Objets>();
        resRecette.add(new PiochePierre());
        return resRecette;
    }

    @Override
    public boolean peutResoudre(ArrayList<Objets> recette) {
        /*
                P P P
                * B *           -> En sachant que les étoiles sont des BlocAir
                * B *

            On part donc du principe qu'ici on ne va gérer que la table de craft 3x3
            Que les 3 premiers objets sont la première ligne, etc
         */
        if (recette.size() == 9 &&
                        (recette.get(0) instanceof BlocPierre &&
                        recette.get(1) instanceof BlocPierre &&
                        recette.get(2) instanceof BlocPierre &&
                        recette.get(4) instanceof Baton &&
                        recette.get(7) instanceof Baton &&
                        recette.get(3) instanceof BlocAir &&
                        recette.get(5) instanceof BlocAir &&
                        recette.get(6) instanceof BlocAir &&
                        recette.get(8) instanceof BlocAir))
        {
            return true;
        }
        return false;
    }

}
