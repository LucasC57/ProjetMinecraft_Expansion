package Tests;

import Jeu.Experts.ExpertNourritures.ExpertNourritures;
import Jeu.Experts.ExpertNourritures.ExpertNourritures_Pomme;
import Jeu.Item.MainVide;
import Jeu.Item.Nourriture.Pomme;
import Jeu.Joueur;
import Exception.*;
import Jeu.Monde;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestsNourriture {
    @Test
    public void testNourritureJoueur() throws Exception {
        String mondeTest = "src/Fichiers/MondeTestCreation.csv";
        Monde monde_test = new Monde(mondeTest);
        Joueur steve = new Joueur(monde_test);
        Pomme pomme = new Pomme();
        ExpertNourritures expertNourrituresPremier = null;

        assertEquals(steve.getMain().getClass(), MainVide.class);
        steve.setMain(pomme);
        assertEquals(steve.getFaim(), 100);
        assertEquals(steve.getMain().getClass(), Pomme.class);
        steve.setFaim(0);
        expertNourrituresPremier = new ExpertNourritures_Pomme(expertNourrituresPremier);
        steve.consommerItemDansMain(expertNourrituresPremier);

        assertEquals(steve.getMain().getClass(), MainVide.class);
        assertEquals(steve.getFaim(), 15);
    }
}
