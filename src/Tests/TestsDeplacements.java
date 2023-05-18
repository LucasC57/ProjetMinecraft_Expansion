package Tests;

import Exception.*;
import Jeu.*;
import Jeu.Item.MainVide;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestsDeplacements {

    private String mondeTest = "projet_minecraft/src/Fichiers/MondeTestDeplacement.csv";
    @Test
    public void testDeplacementsCorrectsTouteDirection() throws Exception {
        Monde monde_normal = new Monde(mondeTest);

        Joueur steve = new Joueur(monde_normal);
        assertEquals(steve.getCoordonnees_joueur(), monde_normal.getPoint_respawn());
        steve.allerGauche();
        Coord nouvelleCo = new Coord(steve.getCoordonnees_joueur().getX(), steve.getCoordonnees_joueur().getY());
        Coord coValide = new Coord(2, 5);
        assertEquals(nouvelleCo, coValide);
        // Aller à droite
        steve.allerDroite();
        nouvelleCo = new Coord(steve.getCoordonnees_joueur().getX(), steve.getCoordonnees_joueur().getY());
        coValide = new Coord(2, 6);
        assertEquals(nouvelleCo, coValide);
        // Aller en haut
        steve.allerHaut();
        nouvelleCo = new Coord(steve.getCoordonnees_joueur().getX(), steve.getCoordonnees_joueur().getY());
        coValide = new Coord(1, 6);
        assertEquals(nouvelleCo, coValide);
        // Aller en bas
        steve.allerBas();
        nouvelleCo = new Coord(steve.getCoordonnees_joueur().getX(), steve.getCoordonnees_joueur().getY());
        coValide = new Coord(2, 6);
        assertEquals(nouvelleCo, coValide);
    }
    @Test
    public void testDeplacementsDansTrou() throws Exception {
        Monde monde_normal = new Monde(mondeTest);

        Joueur steve = new Joueur(monde_normal);
        assertEquals(steve.getCoordonnees_joueur(), monde_normal.getPoint_respawn());
        Coord nouvelleCo = new Coord(4, 16);
        steve.setCoordonnees_joueur(nouvelleCo);
        steve.allerHautGauche();
        Coord coValide = new Coord(3, 15);
        Coord coJoueur = steve.getCoordonnees_joueur();
        assertEquals(coValide, coJoueur);
        // Aller Bas Droit
        steve.allerBasDroite();
        coValide = new Coord(4, 16);
        coJoueur = steve.getCoordonnees_joueur();
        assertEquals(coValide, coJoueur);
        // Aller haut Droit
        steve.allerHautDroite();
        coValide = new Coord(3, 17);
        coJoueur = steve.getCoordonnees_joueur();
        assertEquals(coValide, coJoueur);
        // Aller Bas gauche
        steve.allerBasGauche();
        coValide = new Coord(4, 16);
        coJoueur = steve.getCoordonnees_joueur();
        assertEquals(coValide, coJoueur);
    }
    @Test
    public void testDeplacementsEnDehorsMonde() throws Exception {
        Monde monde_normal = new Monde(mondeTest);

        Joueur steve = new Joueur(monde_normal);
        assertEquals(steve.getCoordonnees_joueur(), monde_normal.getPoint_respawn());
        Coord nouvelleCo = new Coord(4, 0);
        steve.setCoordonnees_joueur(nouvelleCo);
        assertThrows(CoordException.class, () -> {
            steve.allerGauche();
        });
        assertThrows(CoordException.class, () -> {
            steve.allerHautGauche();
        });
        // Placer steve en 9, 19
        nouvelleCo = new Coord(9, 19);
        steve.setCoordonnees_joueur(nouvelleCo);
        assertThrows(DeplacementException.class, () -> {
            steve.allerBas();
        });
        // Placer steve en 1, 19
        nouvelleCo = new Coord(1, 19);
        steve.setCoordonnees_joueur(nouvelleCo);
        assertThrows(DeplacementException.class, () -> {
            steve.allerHaut();
        });
    }
    @Test
    public void testDeplacementsAvecObstacle() throws Exception {
        Monde monde_normal = new Monde(mondeTest);

        Coord co_Steve = new Coord(2, 6);
        ArrayList<Objets> inventaire_steve = null;
        Inventory inventaire = new Inventory(inventaire_steve);
        Joueur steve = new Joueur("Steve", inventaire, new MainVide(), co_Steve, monde_normal);
        assertEquals(steve.getCoordonnees_joueur(), monde_normal.getPoint_respawn());
        assertThrows(DeplacementException.class, () -> {
            steve.allerDroite();
        });
        // Steve en 3,4
        co_Steve = new Coord(3, 4);
        steve.setCoordonnees_joueur(co_Steve);
        assertThrows(DeplacementException.class, () -> {
            steve.allerDroite();
        });
        // Steve en 3,13
        co_Steve = new Coord(3, 13);
        steve.setCoordonnees_joueur(co_Steve);
        assertThrows(DeplacementException.class, () -> {
            steve.allerGauche();
        });
        // Grotte en 6,8
        co_Steve = new Coord(6, 8);
        steve.setCoordonnees_joueur(co_Steve);
        assertThrows(DeplacementException.class, () -> {
            steve.allerHaut();
        });
    }
}
