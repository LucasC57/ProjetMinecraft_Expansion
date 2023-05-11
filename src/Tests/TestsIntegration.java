package Tests;

import Jeu.*;
import Jeu.Bloc.BlocAir;
import Jeu.Bloc.BlocBois;
import Jeu.Bloc.BlocPierre;
import Jeu.Bloc.BlocPlanche;
import Jeu.Experts.*;
import Jeu.Item.Baton;
import Jeu.Item.Item;
import Jeu.Item.MainVide;
import Jeu.Item.PiocheBois;
import Jeu.Joueur;
import Jeu.Monde;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestsIntegration {
    private String monde_test = "projet_minecraft/src/Fichiers/MondeTestComplet.csv";

    @Test
    public void testFinal() throws Exception {
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
        expertCraftPremier = new ExpertCraft_Planches_Baton(expertCraftPremier);
        expertCraftPremier = new ExpertCraft_PiocheBois(expertCraftPremier);

        // Steve se déplace vers la droite
        Deplacement dep_droite = new Deplacement(steve, "Droite");
        assertEquals(steve.getCoordonnees_joueur().getY(), 6);

        // Steve mine les 3 blocs de bois
        Case[][] tab_monde = steve.getMonde().getTab_monde();
        Coord bois1 = new Coord(4, 7);
        Coord bois2 = new Coord(3, 7);
        Coord bois3 = new Coord(2, 7);

        Minage miner_bois1 = new Minage(steve, bois1, expertPremier);
        Ramassage ram_bois1 = new Ramassage(steve, bois1);

        Minage miner_bois2 = new Minage(steve, bois2, expertPremier);
        Ramassage ram_bois2 = new Ramassage(steve, bois2);

        Minage miner_bois3 = new Minage(steve, bois3, expertPremier);
        Ramassage ram_bois3 = new Ramassage(steve, bois3);

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
        assertEquals(steve.getInventaire().getTaille(), 6);
        int cpt_bois = 0;
        int cpt_planche = 0;
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

        Fabrication batons = new Fabrication(steve, recette_batons, expertCraftPremier);

        // Vérification inventaire
        assertEquals(steve.getInventaire().getTaille(), 8);
        int cpt_bois2 = 0;
        int cpt_planche2 = 0;
        int cpt_batons = 0;
        for (int i = 0; i < steve.getInventaire().getTaille(); i++) {
            if (steve.getInventaire().get(i) instanceof BlocBois) {
                cpt_bois++;
            }
            if (steve.getInventaire().get(i) instanceof BlocPlanche) {
                cpt_planche++;
            }
            if (steve.getInventaire().get(i) instanceof Baton) {
                cpt_batons++;
            }
        }
        assertEquals(cpt_bois2, 2);
        assertEquals(cpt_planche2, 2);
        assertEquals(cpt_batons, 4);

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
        Coord co_steve = new Coord(4, 14);
        assertEquals(co_steve, steve.getCoordonnees_joueur());

        Coord bois2em_1 = new Coord(4, 15);
        Coord bois2em_2 = new Coord(3, 15);

        Minage minerbois2em1 = new Minage(steve, bois2em_1, expertPremier);
        minerbois2em1.setCase_concerne(bois2em_2);
        minerbois2em1.minerBloc(expertPremier);

        Ramassage ramasser_bois = new Ramassage(steve, bois2em_1);
        ramasser_bois.setCoord_case(bois2em_2);
        ramasser_bois.ramasserItems();

        // Vérification inventaire
        assertEquals(steve.getInventaire().getTaille(), 10);
        int cpt_bois2_2 = 0;
        int cpt_planche2_2 = 0;
        int cpt_batons_2 = 0;
        for (int i = 0; i < steve.getInventaire().getTaille(); i++) {
            if (steve.getInventaire().get(i) instanceof BlocBois) {
                cpt_bois++;
            }
            if (steve.getInventaire().get(i) instanceof BlocPlanche) {
                cpt_planche++;
            }
            if (steve.getInventaire().get(i) instanceof Baton) {
                cpt_batons++;
            }
        }
        assertEquals(cpt_bois2, 4);
        assertEquals(cpt_planche2, 2);
        assertEquals(cpt_batons, 4);

        // Steve fabrique une pioche en bois
        // Il faut fabriquer en plus des planches
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

        Fabrication pioche_bois = new Fabrication(steve, recette_pioche_bois, expertCraftPremier);

        // Vérification inventaire
        assertEquals(steve.getInventaire().getTaille(), 9);
        int cpt_bois2_3 = 0;
        int cpt_planche2_3 = 0;
        int cpt_batons_3 = 0;
        int cpt_pioche = 0;
        for (int i = 0; i < steve.getInventaire().getTaille(); i++) {
            if (steve.getInventaire().get(i) instanceof BlocBois) {
                cpt_bois++;
            }
            if (steve.getInventaire().get(i) instanceof BlocPlanche) {
                cpt_planche++;
            }
            if (steve.getInventaire().get(i) instanceof Baton) {
                cpt_batons++;
            }
            if (steve.getInventaire().get(i) instanceof PiocheBois) {
                cpt_pioche++;
            }
        }
        assertEquals(cpt_bois2_3, 3);
        assertEquals(cpt_planche2_3, 3);
        assertEquals(cpt_batons_3, 2);
        assertEquals(cpt_pioche, 1);

        // Steve va vers la zone en pierre
        Deplacement dep_gauche = new Deplacement(steve, "Gauche");
        Coord co_valide = new Coord(5, 13);
        assertEquals(co_valide, steve.getCoordonnees_joueur());
    }
}
