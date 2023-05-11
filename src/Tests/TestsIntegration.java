package Tests;

import Jeu.*;
import Jeu.Bloc.BlocAir;
import Jeu.Bloc.BlocBois;
import Jeu.Bloc.BlocPierre;
import Jeu.Bloc.BlocPlanche;
import Jeu.Experts.*;
import Jeu.Item.*;
import Jeu.Joueur;
import Jeu.Monde;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestsIntegration {
    private String monde_test = "projet_minecraft/src/Fichiers/MondeTestComplet.csv";

    @Test
    public void testFinal() throws Exception {
        // Variables utiles :
        int cpt_bois = 0;
        int cpt_planche = 0;
        int cpt_baton = 0;
        int cpt_pierre = 0;
        int cpt_piocheB = 0;
        int cpt_piocheP = 0;
        Monde monde = new Monde(monde_test);

        // Création du joueur
        Joueur steve = new Joueur(monde);
        Coord co_r = new Coord(4, 5);
        assertEquals(co_r, steve.getCoordonnees_joueur());
        assertEquals(steve.getCoordonnees_joueur(), monde.getPoint_respawn());

        // Possède rien :
        Item main = new MainVide();
        steve.setMain(main);
        Inventory inv = new Inventory();
        steve.setInventaire(inv);
        assertEquals(steve.getMain().getClass(), MainVide.class);
        assertEquals(steve.getInventaire().getTaille(), 0);

        // COR Minage :
        Expert expertPremier = null;
        expertPremier = new ExpertMain_Bois(expertPremier);

        // COR Craft :
        ExpertCraft expertCraftPremier = null;
        expertCraftPremier = new ExpertCraft_Bois_Planches(expertCraftPremier);

        // Steve se déplace vers la droite
        Deplacement dep_droite = new Deplacement(steve, "Droite");
        assertEquals(steve.getCoordonnees_joueur().getY(), 6);

        // Steve mine les 3 blocs de bois
        Case[][] tab_monde = steve.getMonde().getTab_monde();
        Coord bois1 = new Coord(4, 7);
        Coord bois2 = new Coord(3, 7);
        Coord bois3 = new Coord(2, 7);

        Minage miner_bois1 = new Minage(steve, bois3, expertPremier);
        Ramassage ram_bois1 = new Ramassage(steve, bois3);

        Minage miner_bois2 = new Minage(steve, bois2, expertPremier);
        Ramassage ram_bois2 = new Ramassage(steve, bois2);

        Minage miner_bois3 = new Minage(steve, bois1, expertPremier);
        Ramassage ram_bois3 = new Ramassage(steve, bois1);

        // Vérifier qu'il a 3 blocs de bois dans son inventaire
        assertEquals(steve.getInventaire().getTaille(), 3);
        for (int i = 0; i < steve.getInventaire().getTaille(); i++) {
            assertEquals(steve.getInventaire().get(i).getClass(), BlocBois.class);
        }

        // Il craft 4 planches avec un seul bloc de bois

        ArrayList<Objets> recette_planches = new ArrayList<Objets>();
        recette_planches.add(new BlocAir());
        recette_planches.add(new BlocAir());
        recette_planches.add(new BlocAir());
        recette_planches.add(new BlocAir());
        recette_planches.add(new BlocBois());
        recette_planches.add(new BlocAir());
        recette_planches.add(new BlocAir());
        recette_planches.add(new BlocAir());
        recette_planches.add(new BlocAir());

        Fabrication fabrique_planche = new Fabrication(steve, recette_planches, expertCraftPremier);

        // Vérification de l'inventaire
        assertEquals(steve.getInventaire().getTaille(), 6);
        for (int i = 0; i < steve.getInventaire().getTaille(); i++) {
            if (steve.getInventaire().get(i) instanceof BlocBois) {
                cpt_bois++;
            }
            if (steve.getInventaire().get(i) instanceof BlocPlanche) {
                cpt_planche++;
            }
        }
        assertEquals(cpt_bois, 2);
        assertEquals(cpt_planche, 4);

        // Steve fabrique 4 batons
        ArrayList<Objets> recette_batons = new ArrayList<Objets>();
        recette_batons.add(new BlocAir());
        recette_batons.add(new BlocAir());
        recette_batons.add(new BlocAir());
        recette_batons.add(new BlocAir());
        recette_batons.add(new BlocPlanche());
        recette_batons.add(new BlocAir());
        recette_batons.add(new BlocAir());
        recette_batons.add(new BlocPlanche());
        recette_batons.add(new BlocAir());

        // COR :
        expertCraftPremier = null;
        expertCraftPremier = new ExpertCraft_Planches_Baton(expertCraftPremier);
        Fabrication batons = new Fabrication(steve, recette_batons, expertCraftPremier);

        // Vérification inventaire
        assertEquals(steve.getInventaire().getTaille(), 8);
        cpt_bois = 0;
        cpt_planche = 0;
        for (int i = 0; i < steve.getInventaire().getTaille(); i++) {
            if (steve.getInventaire().get(i) instanceof BlocBois) {
                cpt_bois++;
            }
            if (steve.getInventaire().get(i) instanceof BlocPlanche) {
                cpt_planche++;
            }
            if (steve.getInventaire().get(i) instanceof Baton) {
                cpt_baton++;
            }
        }
        assertEquals(cpt_bois, 2);
        assertEquals(cpt_planche, 2);
        assertEquals(cpt_baton, 4);

        // Steve se déplace au 2eme arbre
        dep_droite.allerDroite();
        dep_droite.allerDroite();
        dep_droite.allerDroite();
        dep_droite.allerDroite();
        dep_droite.allerDroite();
        dep_droite.allerDroite();
        dep_droite.allerBasDroite();
        dep_droite.allerDroite();

        // Vérification coord steve
        Coord co_steve = new Coord(5, 14);
        assertEquals(co_steve, steve.getCoordonnees_joueur());

        Coord bois2em_1 = new Coord(5, 15);
        Coord bois2em_2 = new Coord(4, 15);

        Minage minerbois2em1 = new Minage(steve, bois2em_2, expertPremier);
        minerbois2em1.setCase_concerne(bois2em_1);
        minerbois2em1.minerBloc(expertPremier);

        Ramassage ramasser_bois = new Ramassage(steve, bois2em_1);
        ramasser_bois.setCoord_case(bois2em_2);
        ramasser_bois.ramasserItems();

        // Vérification inventaire
        assertEquals(steve.getInventaire().getTaille(), 10);
        cpt_bois = 0;
        cpt_planche = 0;
        cpt_baton = 0;
        for (int i = 0; i < steve.getInventaire().getTaille(); i++) {
            if (steve.getInventaire().get(i) instanceof BlocBois) {
                cpt_bois++;
            }
            if (steve.getInventaire().get(i) instanceof BlocPlanche) {
                cpt_planche++;
            }
            if (steve.getInventaire().get(i) instanceof Baton) {
                cpt_baton++;
            }
        }
        assertEquals(cpt_bois, 4);
        assertEquals(cpt_planche, 2);
        assertEquals(cpt_baton, 4);

        // Steve fabrique une pioche en bois
        // Il faut fabriquer en plus des planches
        expertCraftPremier = null;
        expertCraftPremier = new ExpertCraft_Bois_Planches(expertCraftPremier);
        Fabrication fabriquerPlanches = new Fabrication(steve, recette_planches, expertCraftPremier);

        // Fabrication de la pioche en bois :
        ArrayList<Objets> recette_pioche_bois = new ArrayList<Objets>();
        recette_pioche_bois.add(new BlocPlanche());
        recette_pioche_bois.add(new BlocPlanche());
        recette_pioche_bois.add(new BlocPlanche());
        recette_pioche_bois.add(new BlocAir());
        recette_pioche_bois.add(new Baton());
        recette_pioche_bois.add(new BlocAir());
        recette_pioche_bois.add(new BlocAir());
        recette_pioche_bois.add(new Baton());
        recette_pioche_bois.add(new BlocAir());

        expertCraftPremier = null;
        expertCraftPremier = new ExpertCraft_PiocheBois(expertCraftPremier);
        Fabrication pioche_bois = new Fabrication(steve, recette_pioche_bois, expertCraftPremier);

        // Vérification inventaire
        assertEquals(steve.getInventaire().getTaille(), 9);
        cpt_bois = 0;
        cpt_planche = 0;
        cpt_baton = 0;
        for (int i = 0; i < steve.getInventaire().getTaille(); i++) {
            if (steve.getInventaire().get(i) instanceof BlocBois) {
                cpt_bois++;
            }
            if (steve.getInventaire().get(i) instanceof BlocPlanche) {
                cpt_planche++;
            }
            if (steve.getInventaire().get(i) instanceof Baton) {
                cpt_baton++;
            }
            if (steve.getInventaire().get(i) instanceof PiocheBois) {
                cpt_piocheB++;
            }
        }
        assertEquals(cpt_bois, 3);
        assertEquals(cpt_planche, 3);
        assertEquals(cpt_baton, 2);
        assertEquals(cpt_piocheB, 1);

        // Steve va vers la zone en pierre
        Deplacement dep_gauche = new Deplacement(steve, "Gauche");
        Coord co_valide = new Coord(5, 13);
        assertEquals(co_valide, steve.getCoordonnees_joueur());

        // Steve mine les deux pierres de son voisinage et les ramasse
        Coord pierre1 = new Coord(5, 12);
        Coord pierre2 = new Coord(6, 12);

        steve.setMain((Item) steve.getInventaire().getListItems().get(8));
        assertEquals(steve.getMain().getClass(), PiocheBois.class);

        // COR
        expertPremier = null;
        expertPremier = new ExpertPiocheBois_Pierre(expertPremier);

        Minage miner_pierre = new Minage(steve, pierre1, expertPremier);
        Ramassage ramasser_pierre = new Ramassage(steve, pierre1);
        miner_pierre.setCase_concerne(pierre2);
        miner_pierre.minerBloc(expertPremier);
        ramasser_pierre.setCoord_case(pierre2);
        ramasser_pierre.ramasserItems();

        // Vérification de l'inventaire
        assertEquals(steve.getInventaire().getTaille(), 11);
        cpt_bois = 0;
        cpt_planche = 0;
        cpt_baton = 0;
        cpt_piocheB = 0;
        for (int i = 0; i < steve.getInventaire().getTaille(); i++) {
            if (steve.getInventaire().get(i) instanceof BlocBois) {
                cpt_bois++;
            }
            if (steve.getInventaire().get(i) instanceof BlocPlanche) {
                cpt_planche++;
            }
            if (steve.getInventaire().get(i) instanceof Baton) {
                cpt_baton++;
            }
            if (steve.getInventaire().get(i) instanceof PiocheBois) {
                cpt_piocheB++;
            }
            if (steve.getInventaire().get(i) instanceof BlocPierre) {
                cpt_pierre++;
            }
        }
        assertEquals(cpt_bois, 3);
        assertEquals(cpt_planche, 3);
        assertEquals(cpt_baton, 2);
        assertEquals(cpt_piocheB, 1);
        assertEquals(cpt_pierre, 2);

        // Steve descend dans le trou qu'il vient de miner :
        Deplacement aller_dans_trou = new Deplacement(steve, "Bas", "Gauche");
        Coord verif_coord_trou = new Coord(6, 12);
        assertEquals(steve.getCoordonnees_joueur(), verif_coord_trou);

        // Steve mine les 2 blocs de pierre qui se trouve à droite et les ramasse
        Coord pierre_droite = new Coord(5, 11);
        Coord pierre2_droite = new Coord(6, 11);

        Minage miner_bloc_droite = new Minage(steve, pierre_droite, expertPremier);
        miner_bloc_droite.setCase_concerne(pierre2_droite);
        miner_bloc_droite.minerBloc(expertPremier);

        // Il ramasse les deux blocs
        Ramassage ramasser_pierre_droite = new Ramassage(steve, pierre_droite);
        ramasser_pierre_droite.setCoord_case(pierre2_droite);
        ramasser_pierre_droite.ramasserItems();

        // Vérification de l'inventaire :
        assertEquals(steve.getInventaire().getTaille(), 13);
        cpt_bois = 0;
        cpt_planche = 0;
        cpt_baton = 0;
        cpt_piocheB = 0;
        cpt_pierre = 0;
        for (int i = 0; i < steve.getInventaire().getTaille(); i++) {
            if (steve.getInventaire().get(i) instanceof BlocBois) {
                cpt_bois++;
            }
            if (steve.getInventaire().get(i) instanceof BlocPlanche) {
                cpt_planche++;
            }
            if (steve.getInventaire().get(i) instanceof Baton) {
                cpt_baton++;
            }
            if (steve.getInventaire().get(i) instanceof PiocheBois) {
                cpt_piocheB++;
            }
            if (steve.getInventaire().get(i) instanceof BlocPierre) {
                cpt_pierre++;
            }
        }
        assertEquals(cpt_bois, 3);
        assertEquals(cpt_planche, 3);
        assertEquals(cpt_baton, 2);
        assertEquals(cpt_piocheB, 1);
        assertEquals(cpt_pierre, 4);

        // Steve fabrique une pioche en pierre :
        expertCraftPremier = null;
        expertCraftPremier = new ExpertCraft_PiochePierre(expertCraftPremier);

        ArrayList<Objets> recette_pioche_pierre = new ArrayList<Objets>();
        recette_pioche_pierre.add(new BlocPierre());
        recette_pioche_pierre.add(new BlocPierre());
        recette_pioche_pierre.add(new BlocPierre());
        recette_pioche_pierre.add(new BlocAir());
        recette_pioche_pierre.add(new Baton());
        recette_pioche_pierre.add(new BlocAir());
        recette_pioche_pierre.add(new BlocAir());
        recette_pioche_pierre.add(new Baton());
        recette_pioche_pierre.add(new BlocAir());

        Fabrication fabriquer_pioche_pierre = new Fabrication(steve, recette_pioche_pierre, expertCraftPremier);

        // Vérification de l'inventaire
        assertEquals(steve.getInventaire().getTaille(), 9);
        cpt_bois = 0;
        cpt_planche = 0;
        cpt_baton = 0;
        cpt_piocheB = 0;
        cpt_pierre = 0;
        for (int i = 0; i < steve.getInventaire().getTaille(); i++) {
            if (steve.getInventaire().get(i) instanceof BlocBois) {
                cpt_bois++;
            }
            if (steve.getInventaire().get(i) instanceof BlocPlanche) {
                cpt_planche++;
            }
            if (steve.getInventaire().get(i) instanceof Baton) {
                cpt_baton++;
            }
            if (steve.getInventaire().get(i) instanceof PiocheBois) {
                cpt_piocheB++;
            }
            if (steve.getInventaire().get(i) instanceof BlocPierre) {
                cpt_pierre++;
            }
            if (steve.getInventaire().get(i) instanceof PiochePierre) {
                cpt_piocheP++;
            }
        }
        assertEquals(cpt_bois, 3);
        assertEquals(cpt_planche, 3);
        assertEquals(cpt_piocheB, 1);
        assertEquals(cpt_piocheP, 1);
        assertEquals(cpt_pierre, 1);
    }
}
