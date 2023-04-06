package Tests;

import Exception.FichierInexistantException;
import Jeu.Monde;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestsMonde {
    @Test
    public void testfichierInexistant() throws FichierInexistantException {
        // On test quand le fichier donné est inexistant :
        Assertions.assertThrows(FichierInexistantException.class, () -> new Monde("test.txt"));
    }
}
