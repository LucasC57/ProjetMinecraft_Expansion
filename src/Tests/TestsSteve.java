package Tests;

import Jeu.Monde;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestsSteve {

    @Test
    public void testConstructionSteve() throws Exception {
        String mondeTest = "projet_minecraft/src/Fichiers/MondeTestCreation.csv";
        Monde monde_normal = new Monde(mondeTest);

        Inventory inventaire = new Inventory();
        Joueur Steve = new Joueur("Steve", inventaire, null, coord);
    }
}
