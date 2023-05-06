package Tests;

import Jeu.*;
import Exception.*;
import Jeu.Bloc.Bloc;
import Jeu.Bloc.BlocBois;
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
        Throwable testPresencePlanche = assertThrows(BlocFluideException.class, () -> {
            list_case_monde[7][1].addItemsAuSol(planche);
        });
        assertEquals(BlocFluideException.class, testPresencePlanche.getClass());
    }
}
