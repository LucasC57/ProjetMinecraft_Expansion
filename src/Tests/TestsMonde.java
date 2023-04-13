package Tests;

import Exception.*;
import Jeu.Monde;
import org.junit.jupiter.api.*;

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
    public void testfichierMalFormate() throws FichierMalFormate, FichierInexistantException, MondeException {
        String mondeTest = "projet_minecraft/src/Fichiers/MondeIncoherent.csv";
        Monde monde_inco = new Monde(mondeTest);
    }
}
