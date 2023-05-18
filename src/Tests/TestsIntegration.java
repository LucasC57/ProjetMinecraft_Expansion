package Tests;

import Jeu.*;
import Jeu.Bloc.BlocAir;
import Jeu.Bloc.BlocBois;
import Jeu.Bloc.BlocPierre;
import Jeu.Bloc.BlocPlanche;
import Jeu.Experts.ExpertCraft.*;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Experts.ExpertMinage.ExpertMain_Bois;
import Jeu.Experts.ExpertMinage.ExpertPiocheBois_Pierre;
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
        Monde monde = new Monde(monde_test);

        // Création du joueur
        Joueur steve = new Joueur(monde);
        Coord co_r = new Coord(4, 5);
        assertEquals(co_r, steve.getCoordonnees_joueur());
        assertEquals(steve.getCoordonnees_joueur(), monde.getPoint_respawn());

        // Possède rien :
        assertEquals(steve.getMain().getClass(), MainVide.class);
        assertEquals(steve.getInventaire().getTaille(), 0);

        // COR Minage :
        Expert expertPremier = null;
        expertPremier = new ExpertMain_Bois(expertPremier);

        // COR Craft :
        ExpertCraft expertCraftPremier = null;
        expertCraftPremier = new ExpertCraft_Bois_Planches(expertCraftPremier);

        // Steve se déplace vers la droite
        steve.allerDroite();
        assertEquals(steve.getCoordonnees_joueur().getY(), 6);

        // Steve mine les 3 blocs de bois
        Case[][] tab_monde = steve.getMonde().getTab_monde();
        Coord bois1 = new Coord(4, 7);
        Coord bois2 = new Coord(3, 7);
        Coord bois3 = new Coord(2, 7);

        steve.minerBloc(expertPremier, bois3);
        steve.ramasserItems(bois3);

        steve.minerBloc(expertPremier, bois2);
        steve.ramasserItems(bois2);

        steve.minerBloc(expertPremier, bois1);
        steve.ramasserItems(bois1);

        // Vérifier qu'il a 3 blocs de bois dans son inventaire
        assertEquals(steve.getInventaire().getTaille(), 3);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocBois()), 3);

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

        steve.effectuerRecette(expertCraftPremier, recette_planches);

        // Vérification de l'inventaire
        assertEquals(steve.getInventaire().getTaille(), 6);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocBois()), 2);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPlanche()), 4);

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
        steve.effectuerRecette(expertCraftPremier, recette_batons);

        // Vérification inventaire
        assertEquals(steve.getInventaire().getTaille(), 8);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocBois()), 2);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPlanche()), 2);
        assertEquals(steve.getNbrObjetDansInventaire(new Baton()), 4);

        // Steve se déplace au 2eme arbre
        steve.allerDroite();
        steve.allerDroite();
        steve.allerDroite();
        steve.allerDroite();
        steve.allerDroite();
        steve.allerDroite();
        steve.allerBasDroite();
        steve.allerDroite();

        // Vérification coord steve
        Coord co_steve = new Coord(5, 14);
        assertEquals(co_steve, steve.getCoordonnees_joueur());

        Coord bois2em_1 = new Coord(5, 15);
        Coord bois2em_2 = new Coord(4, 15);

        steve.minerBloc(expertPremier, bois2em_2);
        steve.minerBloc(expertPremier, bois2em_1);

        steve.ramasserItems(bois2em_1);
        steve.ramasserItems(bois2em_2);

        // Vérification inventaire
        assertEquals(steve.getInventaire().getTaille(), 10);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocBois()), 4);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPlanche()), 2);
        assertEquals(steve.getNbrObjetDansInventaire(new Baton()), 4);

        // Steve fabrique une pioche en bois
        // Il faut fabriquer en plus des planches
        expertCraftPremier = null;
        expertCraftPremier = new ExpertCraft_Bois_Planches(expertCraftPremier);
        steve.effectuerRecette(expertCraftPremier, recette_planches);

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
        steve.effectuerRecette(expertCraftPremier, recette_pioche_bois);

        // Vérification inventaire
        assertEquals(steve.getInventaire().getTaille(), 9);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocBois()), 3);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPlanche()), 3);
        assertEquals(steve.getNbrObjetDansInventaire(new Baton()), 2);
        assertEquals(steve.getNbrObjetDansInventaire(new PiocheBois()), 1);

        // Steve va vers la zone en pierre
        steve.allerGauche();
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

        steve.minerBloc(expertPremier, pierre1);
        steve.ramasserItems(pierre1);
        steve.minerBloc(expertPremier, pierre2);
        steve.ramasserItems(pierre2);

        // Vérification de l'inventaire
        assertEquals(steve.getInventaire().getTaille(), 11);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocBois()), 3);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPlanche()), 3);
        assertEquals(steve.getNbrObjetDansInventaire(new Baton()), 2);
        assertEquals(steve.getNbrObjetDansInventaire(new PiocheBois()), 1);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPierre()), 2);

        // Steve descend dans le trou qu'il vient de miner :
        steve.allerBasGauche();
        Coord verif_coord_trou = new Coord(6, 12);
        assertEquals(steve.getCoordonnees_joueur(), verif_coord_trou);

        // Steve mine les 2 blocs de pierre qui se trouve à droite et les ramasse
        Coord pierre_droite = new Coord(5, 11);
        Coord pierre2_droite = new Coord(6, 11);

        steve.minerBloc(expertPremier, pierre_droite);
        steve.minerBloc(expertPremier, pierre2_droite);

        // Il ramasse les deux blocs
        steve.ramasserItems(pierre_droite);
        steve.ramasserItems(pierre2_droite);

        // Vérification de l'inventaire :
        assertEquals(steve.getInventaire().getTaille(), 13);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocBois()), 3);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPlanche()), 3);
        assertEquals(steve.getNbrObjetDansInventaire(new Baton()), 2);
        assertEquals(steve.getNbrObjetDansInventaire(new PiocheBois()), 1);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPierre()), 4);

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

        steve.effectuerRecette(expertCraftPremier, recette_pioche_pierre);

        // Vérification de l'inventaire
        assertEquals(steve.getInventaire().getTaille(), 9);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocBois()), 3);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPlanche()), 3);
        assertEquals(steve.getNbrObjetDansInventaire(new PiocheBois()), 1);
        assertEquals(steve.getNbrObjetDansInventaire(new PiochePierre()), 1);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPierre()), 1);
    }
}
