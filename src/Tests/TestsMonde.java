package Tests;

import Jeu.Coord;
import Jeu.Minage;
import Jeu.Monde;
import org.junit.jupiter.api.*;
import Exception.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestsMonde {
    @Test
    public void testfichierInexistant() throws Exception {
        // On test quand le fichier donné est inexistant :
        Monde le_monde = new Monde("test.txt");
        // Si le fichier est vide :
        try {
            le_monde.estExistant();
        } catch (Exception e) {
            System.out.println("Le fichier n'existe pas");
        }
    }

    @Test
    public void testfichierMalFormate() throws Exception {
        String mondeTest = "projet_minecraft/src/Fichiers/MondeIncoherent.csv";

        // Pour vérifier l'assertion :
        assertThrows(BlocInconnuException.class, () -> {
            Monde testMonde = new Monde(mondeTest);
        });
    }

    @Test
    public void testfichierBienFormate() throws Exception {
        String mondeTest = "projet_minecraft/src/Fichiers/MondeTestCreation.csv";
        Monde monde_normal = new Monde(mondeTest);

        assertEquals(20, monde_normal.getLargeur());
        assertEquals(10, monde_normal.getHauteur());
    }

    @Test
    public void testFichierBienFormateCoordRespawn() throws Exception {
        String mondeTest = "projet_minecraft/src/Fichiers/MondeTestCreation.csv";
        Monde monde_normal = new Monde(mondeTest);

        // Verification des coords du R:
        Coord respawn = new Coord(2, 6);
        assertEquals(respawn, monde_normal.getPoint_respawn());
    }
}
