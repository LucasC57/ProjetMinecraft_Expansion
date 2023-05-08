package Tests;

import Jeu.Coord;
import Jeu.Joueur;
import Jeu.Minage;
import Jeu.Monde;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestsMinage {

    private String monde_test = "projet_minecraft/src/Fichiers/MondeTestMine.csv";
    @Test
    public void testMinageImpossible() throws Exception {
        Monde monde_create = new Monde(monde_test);

        // Création de Steve
        Joueur steve = new Joueur(monde_create);
        Coord co_valide = new Coord(4, 6);
        assertEquals(co_valide, steve.getCoordonnees_joueur());
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());

        Coord co_case = new Coord(4, 7);
        Minage miner_case = new Minage(steve, co_case);
    }
}
