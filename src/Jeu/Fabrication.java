package Jeu;

import Jeu.Bloc.BlocAir;
import Jeu.Experts.ExpertCraft;
import java.util.ArrayList;
import Exception.*;

public class Fabrication {
    // Champs :
    private Joueur joueur_Craft;
    private ArrayList<Objets> recette = new ArrayList<Objets>();
    // Constructeur
    public Fabrication(Joueur joueur, ArrayList<Objets> recette, ExpertCraft expertRecette) throws Exception {
        setJoueur_Craft(joueur);
        setRecette(recette);
        effectuerRecette(expertRecette);
    }
    public Joueur getJoueur_Craft() {
        return joueur_Craft;
    }
    public void setJoueur_Craft(Joueur joueur_Craft) {
        this.joueur_Craft = joueur_Craft;
    }
    public ArrayList<Objets> getRecette() {
        return recette;
    }
    public void setRecette(ArrayList<Objets> recette) {
        this.recette = recette;
    }
    private void effectuerRecette(ExpertCraft expertRecette) throws Exception {
        if (expertRecette != null) {
            ArrayList<Objets> inv_Joueur = this.getJoueur_Craft().getInventaire().getListItems();
            ArrayList<Objets> recette_Joueur = this.getRecette();

            // Avant tout de chose, on va vérifier que le joueur possède les éléments qui se trouve dans sa recette donnée
            for (int i = 0; i < recette_Joueur.size(); i++) {
                // On va éviter toute comparaison avec les blocs d'air
                if (!(recette_Joueur.get(i) instanceof BlocAir)) {
                    if (!inv_Joueur.contains(recette_Joueur.get(i))) {
                        throw new InventoryException();
                    }
                }
            }
            // Le Joueur à bien tous les éléments dans son inventaire
            // Le travail de l'expert
            try {
                ArrayList<Objets> res_recette = expertRecette.expertiserCraft(recette_Joueur);
                // On doit supprimer de l'inventaire du Joueur les éléments dans la recette :
                for (int i = 0; i < recette_Joueur.size(); i++) {
                    // On va éviter tout travail avec les blocs d'air
                    if (!(recette_Joueur.get(i) instanceof BlocAir)) {
                        inv_Joueur.remove(recette_Joueur.get(i));
                    }
                }
                // On doit parcourir l'arraylist et ajouter chaque élément de celle-ci dans l'inventaire du Joueur
                for (int j = 0; j < res_recette.size(); j++) {
                    this.getJoueur_Craft().getInventaire().addInventory(res_recette.get(j));
                }
            } catch (ExpertManquantException e) {
                System.out.printf("\nIl n'y a pas d'expert pour cette recette : %s", recette_Joueur);
            }
        } else {
            throw new CORVideException();
        }
    }
}
