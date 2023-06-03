package Tests;

import Exception.*;
import Jeu.Joueur;
import Jeu.Monde;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestsVie {
    @Test
    public void testVieJoueur() throws Exception {
        String mondeTest = "src/Fichiers/MondeTestCreation.csv";
        Monde monde_normal = new Monde(mondeTest);
        Joueur steve = new Joueur(monde_normal);

        assertEquals(monde_normal.getPoint_respawn(), steve.getCoordonnees_joueur());
        // Tests sur les points de vie du joueur :
        assertEquals(steve.getVie(), 20);
        steve.enleverPointDeVie(4);
        assertEquals(steve.getVie(), 16);
        steve.ajouterPointDeVie(1);
        assertEquals(steve.getVie(), 17);
        assertThrows(MontantVieException.class, () -> {
            steve.enleverPointDeVie(18);
        });
    }

}
