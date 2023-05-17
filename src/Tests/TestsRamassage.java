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
        Coord case_co27 = new Coord(2, 7);
        Case[][] list_case_monde = monde_create.getTab_monde();
        list_case_monde[7][2].setItems_au_sol(itemSol); // Y, X
        assertTrue(list_case_monde[7][2].getItems_au_sol().isEmpty());
        // Création de la pioche et la placer dans 2,7
        Item pioche = new PiocheBois();
        list_case_monde[7][2].addItemsAuSol(pioche);
        assertEquals(list_case_monde[7][2].getTaille(), 1);
        assertEquals(list_case_monde[7][2].get(0).getClass(), PiocheBois.class);
        // Création du bloc de pierre et ajouter dans 2,7
        Bloc pierre = new BlocPierre();
        list_case_monde[7][2].addItemsAuSol(pierre);
        assertEquals(list_case_monde[7][2].getTaille(), 2);
        assertEquals(list_case_monde[7][2].get(0).getClass(), PiocheBois.class);
        assertEquals(list_case_monde[7][2].get(1).getClass(), BlocPierre.class);
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
        ArrayList<Objets> list_inv = new ArrayList<Objets>();
        Inventory inv = new Inventory(list_inv);
        steve.setInventaire(inv);
        Coord coo_valide = new Coord(2, 6);
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());
        assertEquals(steve.getCoordonnees_joueur(), coo_valide);
        // Mettre les objets dans la case :
        ArrayList<Objets> list_items_inv = new ArrayList<Objets>();
        Case[][] list_case_monde = monde_create.getTab_monde();
        list_case_monde[7][2].setItems_au_sol(list_items_inv);
        Item pioche = new PiocheBois();
        list_case_monde[7][2].addItemsAuSol(pioche);
        Bloc pierre = new BlocPierre();
        list_case_monde[7][2].addItemsAuSol(pierre);
        // Ramassage :
        Coord case_ramassage = new Coord(2, 7);
        assertEquals(list_case_monde[7][2].getTaille(), 2);
        new Ramassage(steve, case_ramassage);
        assertEquals(list_case_monde[7][2].getTaille(), 0);
        inv = steve.getInventaire();
        assertEquals(inv.getTaille(), 2);
        assertEquals(inv.get(0).getClass(), PiocheBois.class);
        assertEquals(inv.get(1).getClass(), BlocPierre.class);
    }

    @Test
    public void testRamassageImpossiblePlanches() throws Exception {
        Monde monde_create = new Monde(monde_test);
        Joueur steve = new Joueur(monde_create);
        ArrayList<Objets> list_inv = new ArrayList<Objets>();
        Inventory inv = new Inventory(list_inv);
        steve.setInventaire(inv);
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
            Ramassage ramasser_Steve = new Ramassage(steve, co_case);
        });
    }
}
