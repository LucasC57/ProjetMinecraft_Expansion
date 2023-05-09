package Tests;

import Jeu.Coord;
import Jeu.Item.Item;
import Jeu.Item.MainVide;
import Jeu.Joueur;
import Jeu.Monde;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsFabrication {

    private String monde_test = "projet_minecraft/src/Fichiers/MondeTestMine.csv";
    @Test
    public void testFabricationPlanches() throws Exception {
        // On va choisir un monde quelconque
        Monde monde_create = new Monde(monde_test);

        // Création de Steve
        Joueur steve = new Joueur(monde_create);
        Item mainVidePourTest = new MainVide();
        steve.setMain(mainVidePourTest);
        Coord co_valide = new Coord(4, 6);
        assertEquals(co_valide, steve.getCoordonnees_joueur());
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());

    }
}
