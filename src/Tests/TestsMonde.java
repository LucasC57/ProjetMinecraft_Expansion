package Tests;

import Jeu.Monde;
import org.junit.jupiter.api.*;
import Exception.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestsMonde {
    /*+@Test
    public void testfichierInexistant() throws FichierInexistantException {
        // On test quand le fichier donné est inexistant :
        Monde le_monde = new Monde("test.txt");
        // Si le fichier est vide :
        try {
            le_monde.estExistant();
        } catch (FichierInexistantException e) {
            System.out.println("Le fichier n'existe pas");
        }
    }*/

    @Test
    public void testfichierMalFormate() throws Exception {
        String mondeTest = "projet_minecraft/src/Fichiers/MondeIncoherent.csv";

        // Pour vérifier l'assertion :
        FichierMalFormateException exception = assertThrows(FichierMalFormateException.class, () ->
           new Monde(mondeTest), "Exception lancee"
        );
        assertNotNull(exception);
    }
}
