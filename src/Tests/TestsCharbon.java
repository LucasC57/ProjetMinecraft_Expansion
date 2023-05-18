package Tests;

import Jeu.*;
import Jeu.Bloc.BlocAir;
import Jeu.Bloc.BlocBois;
import Jeu.Bloc.BlocMineraiCharbon;
import Jeu.Bloc.BlocPlanche;
import Jeu.Experts.ExpertCraft.*;
import Jeu.Experts.ExpertMinage.Expert;
import Jeu.Experts.ExpertMinage.ExpertMain_Bois;
import Jeu.Experts.ExpertMinage.ExpertPiocheBois_Charbon;
import Jeu.Item.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsCharbon {

    private String monde_test = "projet_minecraft/src/Fichiers/MondeTestCharbon.csv";

    @Test
    public void testExtensionCharbon() throws Exception {
        Monde monde_create = new Monde(monde_test);

        // Création de Steve :
        Joueur steve = new Joueur(monde_create);
        Coord co_valide = new Coord(4, 6);
        assertEquals(co_valide, steve.getCoordonnees_joueur());
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());

        // Coordonnées des blocs de bois :
        Coord co_bois_1 = new Coord(4, 7);
        Coord co_bois_2 = new Coord(3, 7);

        // COR :
        Expert expertMinagePremier = null;
        ExpertCraft expertCraftPremier = null;

        // MAJ de la COR Minage pour le bois :
        expertMinagePremier = new ExpertMain_Bois(expertMinagePremier);

        // Miner les 3 blocs de bois et les ramasser :
        steve.minerBloc(expertMinagePremier, co_bois_2);
        steve.ramasserItems(co_bois_2);
        steve.minerBloc(expertMinagePremier, co_bois_1);
        steve.ramasserItems(co_bois_1);

        // Vérification de l'inventaire :
        assertEquals(steve.getInventaire().getTaille(), 2);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocBois()), 2);

        // MAJ de la COR Craft pour les planches :
        expertCraftPremier = new ExpertCraft_Bois_Planches(expertCraftPremier);

        // Recette pour les planches :
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

        // Fabrication des planches :
        steve.effectuerRecette(expertCraftPremier, recette_planches);
        steve.effectuerRecette(expertCraftPremier, recette_planches);

        // Vérification de l'inventaire :
        assertEquals(steve.getInventaire().getTaille(), 8);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocBois()), 0);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPlanche()), 8);

        // MAJ de la COR Craft pour les batons :
        expertCraftPremier = new ExpertCraft_Planches_Baton(expertCraftPremier);

        // Recette pour les batons :
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

        // Fabrications des batons :
        steve.effectuerRecette(expertCraftPremier, recette_batons);

        // Vérification de l'inventaire :
        assertEquals(steve.getInventaire().getTaille(), 10);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPlanche()), 6);
        assertEquals(steve.getNbrObjetDansInventaire(new Baton()), 4);

        // MAJ de la COR Craft pour la pioche en bois :
        expertCraftPremier = new ExpertCraft_PiocheBois(expertCraftPremier);

        // Recette pour la pioche en bois :
        ArrayList<Objets> recette_pioche_en_bois = new ArrayList<Objets>();
        recette_pioche_en_bois.add(new BlocPlanche());
        recette_pioche_en_bois.add(new BlocPlanche());
        recette_pioche_en_bois.add(new BlocPlanche());
        recette_pioche_en_bois.add(new BlocAir());
        recette_pioche_en_bois.add(new Baton());
        recette_pioche_en_bois.add(new BlocAir());
        recette_pioche_en_bois.add(new BlocAir());
        recette_pioche_en_bois.add(new Baton());
        recette_pioche_en_bois.add(new BlocAir());

        // Fabrication de la pioche en bois :
        steve.effectuerRecette(expertCraftPremier, recette_pioche_en_bois);

        // Vérification de l'inventaire :
        assertEquals(steve.getInventaire().getTaille(), 6);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPlanche()), 3);
        assertEquals(steve.getNbrObjetDansInventaire(new Baton()), 2);
        assertEquals(steve.getNbrObjetDansInventaire(new PiocheBois()), 1);

        // Coordonnées du charbon :
        Coord co_charbon = new Coord(5, 5);

        // Mettre la pioche en bois dans la main du joueur :
        steve.setMain((Item) steve.getInventaire().get(5));

        // MAJ de la COR Minage pour le minerai de charbon :
        expertMinagePremier = new ExpertPiocheBois_Charbon(expertMinagePremier);

        // Minage du minerai de charbon et ramasser les charbons :
        steve.minerBloc(expertMinagePremier, co_charbon);
        steve.ramasserItems(co_charbon);

        // Vérification de l'inventaire :
        assertEquals(steve.getInventaire().getTaille(), 7);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPlanche()), 3);
        assertEquals(steve.getNbrObjetDansInventaire(new Baton()), 2);
        assertEquals(steve.getNbrObjetDansInventaire(new PiocheBois()), 1);
        assertEquals(steve.getNbrObjetDansInventaire(new Charbon()), 1);

        // MAJ de la COR Craft pour les torches :
        expertCraftPremier = new ExpertCraft_Torche(expertCraftPremier);

        // Recette pour la pioche en bois :
        ArrayList<Objets> recette_torches = new ArrayList<Objets>();
        recette_torches.add(new BlocAir());
        recette_torches.add(new BlocAir());
        recette_torches.add(new BlocAir());
        recette_torches.add(new BlocAir());
        recette_torches.add(new Charbon());
        recette_torches.add(new BlocAir());
        recette_torches.add(new BlocAir());
        recette_torches.add(new Baton());
        recette_torches.add(new BlocAir());

        // Fabrication des torches :
        steve.effectuerRecette(expertCraftPremier, recette_torches);

        // Vérification de l'inventaire :
        assertEquals(steve.getInventaire().getTaille(), 9);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPlanche()), 3);
        assertEquals(steve.getNbrObjetDansInventaire(new Baton()), 1);
        assertEquals(steve.getNbrObjetDansInventaire(new PiocheBois()), 1);
        assertEquals(steve.getNbrObjetDansInventaire(new Charbon()), 0);
        assertEquals(steve.getNbrObjetDansInventaire(new Torche()), 4);
    }
}
