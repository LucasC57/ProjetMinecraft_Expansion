package Tests;

import Jeu.*;
import Exception.*;
import Jeu.Bloc.Bloc;
import Jeu.Bloc.BlocBois;
import Jeu.Bloc.BlocHerbe;
import Jeu.Bloc.BlocPierre;
import Jeu.Item.Item;
import Jeu.Item.PiocheBois;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestsRamassage {

    private String monde_test = "projet_minecraft/src/Fichiers/MondeTestCreation.csv";

    @Test
    public void testRamassageObjets() throws Exception {
        Monde monde_create = new Monde(monde_test);

        // Création du joueur
        Joueur steve = new Joueur(monde_create);
        Coord coo_valide = new Coord(2, 6);
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());
        assertEquals(steve.getCoordonnees_joueur(), coo_valide);
        ArrayList<Objets> itemSol = new ArrayList<Objets>();
        // Case 2,7
        Case[][] list_case_monde = monde_create.getTab_monde();
        Case case_concerne = list_case_monde[7][2];
        assertTrue(case_concerne.getItems_au_sol().isEmpty());
        // Création de la pioche et la placer dans 2,7
        Item pioche = new PiocheBois();
        case_concerne.addItemsAuSol(pioche);
        assertEquals(case_concerne.getTaille(), 1);
        assertEquals(case_concerne.get(0).getClass(), PiocheBois.class);
        // Création du bloc de pierre et ajouter dans 2,7
        Bloc pierre = new BlocPierre();
        case_concerne.addItemsAuSol(pierre);
        assertEquals(case_concerne.getTaille(), 2);
        assertEquals(case_concerne.get(0).getClass(), PiocheBois.class);
        assertEquals(case_concerne.get(1).getClass(), BlocPierre.class);
        // Planche
        Bloc planche = new BlocBois();
        assertEquals(list_case_monde[7][1].getTaille(), 0);
        assertThrows(BlocFluideException.class, () -> {
            list_case_monde[7][1].addItemsAuSol(planche);
        });
        assertEquals(list_case_monde[7][1].getTaille(), 0);
        // Planches en 3,7
        assertThrows(BlocNonFluideException.class, () -> {
            list_case_monde[7][3].addItemsAuSol(planche);
        });
        assertEquals(list_case_monde[7][3].getTaille(), 0);
    }

    @Test
    public void testRamassageAvecSteve() throws Exception {
        Monde monde_create = new Monde(monde_test);
        Joueur steve = new Joueur(monde_create);
        Coord coo_valide = new Coord(2, 6);
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());
        assertEquals(steve.getCoordonnees_joueur(), coo_valide);
        // Mettre les objets dans la case :
        Case[][] list_case_monde = monde_create.getTab_monde();
        Case case_concerne = list_case_monde[7][2];
        Item pioche = new PiocheBois();
        case_concerne.addItemsAuSol(pioche);
        Bloc pierre = new BlocPierre();
        case_concerne.addItemsAuSol(pierre);
        // Ramassage :
        Coord case_ramassage = new Coord(2, 7);
        assertEquals(case_concerne.getTaille(), 2);
        steve.ramasserItems(case_ramassage);
        assertEquals(case_concerne.getTaille(), 0);
        assertEquals(steve.getInventaire().getTaille(), 2);
        assertEquals(steve.getNbrObjetDansInventaire(new PiocheBois()), 1);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPierre()), 1);
    }

    @Test
    public void testRamassageImpossiblePlanches() throws Exception {
        Monde monde_create = new Monde(monde_test);
        Joueur steve = new Joueur(monde_create);
        Coord coo_valide = new Coord(2, 6);
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());
        assertEquals(steve.getCoordonnees_joueur(), coo_valide);
        // Mettre les objets dans la case :
        ArrayList<Objets> list_items_inv = new ArrayList<Objets>();
        Case[][] list_case_monde = monde_create.getTab_monde();
        list_case_monde[7][2].setItems_au_sol(list_items_inv);
        Bloc planches = new BlocBois();
        list_case_monde[8][2].addItemsAuSol(planches);
        Coord co_case = new Coord(2, 8);
        assertThrows(CaseNonVoisineException.class, () -> {
            steve.ramasserItems(co_case);
        });
    }
}
