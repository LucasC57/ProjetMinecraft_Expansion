package Tests;

import Jeu.*;
import Jeu.Item.Item;
import Jeu.Item.MainVide;
import Jeu.Item.PiocheBois;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestsSteve {
    @Test
    public void testInventory() {
        ArrayList<Objets> inventaire_steve = new ArrayList<>();
        PiocheBois piocheBoisItem1 = new PiocheBois();
        inventaire_steve.add(piocheBoisItem1);
        Inventory inventaire = new Inventory(inventaire_steve);
    }

    @Test
    public void testConstructionSteve() throws Exception {
        String mondeTest = "projet_minecraft/src/Fichiers/MondeTestCreation.csv";
        Monde monde_normal = new Monde(mondeTest);

        ArrayList<Objets> inventaire_steve = new ArrayList<Objets>();
        Inventory inventaire = new Inventory(inventaire_steve);
        Coord co_steve = new Coord(2, 6);

        // Test sur l'inventaire :
        Joueur Steve = new Joueur("Steve", inventaire, new MainVide(), co_steve, monde_normal);

        // Test sur steve :
        assertEquals("Steve", Steve.getNom());
        assertTrue(Steve.getInventaire().getListItems().isEmpty());
        assertEquals(Steve.getMain().getClass(), MainVide.class);
        assertEquals(monde_normal.getPoint_respawn(), Steve.getCoordonnees_joueur());
    }
}
