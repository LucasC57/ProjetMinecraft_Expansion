package Tests;

import Exception.*;
import Jeu.*;
import Jeu.Item.Item;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestDeplacements {

    private String mondeTest = "projet_minecraft/src/Fichiers/MondeTestDeplacement.csv";
    @Test
    public void testDeplacementsCorrectsTouteDirection() throws Exception {
        Monde monde_normal = new Monde(mondeTest);

        Joueur steve = new Joueur(monde_normal);
        assertEquals(steve.getCoordonnees_joueur(), monde_normal.getPoint_respawn());
        Deplacement dep = new Deplacement(steve, "Gauche");
        Coord nouvelleCo = new Coord(steve.getCoordonnees_joueur().getX(), steve.getCoordonnees_joueur().getY());
        Coord coValide = new Coord(2, 5);
        assertEquals(nouvelleCo, coValide);
        // Aller à droite
        dep.allerDroite();
        nouvelleCo = new Coord(steve.getCoordonnees_joueur().getX(), steve.getCoordonnees_joueur().getY());
        coValide = new Coord(2, 6);
        assertEquals(nouvelleCo, coValide);
        // Aller en haut
        dep.allerHaut();
        nouvelleCo = new Coord(steve.getCoordonnees_joueur().getX(), steve.getCoordonnees_joueur().getY());
        coValide = new Coord(1, 6);
        assertEquals(nouvelleCo, coValide);
        // Aller en bas
        dep.allerBas();
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
        Deplacement dep_diago = new Deplacement(steve, "Haut", "Gauche");
        Coord coValide = new Coord(3, 15);
        Coord coJoueur = steve.getCoordonnees_joueur();
        assertEquals(coValide, coJoueur);
        // Aller Bas Droit
        dep_diago.allerBasDroite();
        coValide = new Coord(4, 16);
        coJoueur = steve.getCoordonnees_joueur();
        assertEquals(coValide, coJoueur);
        // Aller haut Droit
        dep_diago.allerHautDroite();
        coValide = new Coord(3, 17);
        coJoueur = steve.getCoordonnees_joueur();
        assertEquals(coValide, coJoueur);
        // Aller Bas gauche
        dep_diago.allerBasGauche();
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
        Throwable testPresenceExc = assertThrows(CoordException.class, () -> {
            Deplacement depImpossible = new Deplacement(steve, "Gauche");
        });
        assertEquals(CoordException.class, testPresenceExc.getClass());
        Throwable testPresenceExcDiag = assertThrows(CoordException.class, () -> {
            Deplacement depImpossible = new Deplacement(steve, "Haut", "Gauche");
        });
        assertEquals(CoordException.class, testPresenceExcDiag.getClass());

        // Placer steve en 9, 19
        nouvelleCo = new Coord(9, 19);
        steve.setCoordonnees_joueur(nouvelleCo);
        Throwable testPresenceExcBas = assertThrows(DeplacementException.class, () -> {
            Deplacement depImpossible = new Deplacement(steve, "Bas");
        });
        assertEquals(DeplacementException.class, testPresenceExcBas.getClass());
        // Placer steve en 1, 19
        nouvelleCo = new Coord(1, 19);
        steve.setCoordonnees_joueur(nouvelleCo);
        Throwable testPresenceExcAutre = assertThrows(DeplacementException.class, () -> {
            Deplacement depImpossible = new Deplacement(steve, "Haut");
        });
        assertEquals(DeplacementException.class, testPresenceExcAutre.getClass());
    }
    @Test
    public void testDeplacementsAvecObstacle() throws Exception {
        Monde monde_normal = new Monde(mondeTest);

        Coord resp = new Coord(2, 6);
        ArrayList<Item> inventaire_steve = null;
        Inventory inventaire = new Inventory(inventaire_steve);
        Joueur steve = new Joueur("Steve", inventaire, null, resp, monde_normal);
        assertEquals(steve.getCoordonnees_joueur(), monde_normal.getPoint_respawn());
        Throwable testPresenceExcOBS = assertThrows(DeplacementException.class, () -> {
            Deplacement dep_obstacle = new Deplacement(steve, "Droite");
            System.out.printf("%d %d", dep_obstacle.getJoueur().getCoordonnees_joueur().getX(), dep_obstacle.getJoueur().getCoordonnees_joueur().getY());
        });
        assertEquals(DeplacementException.class, testPresenceExcOBS.getClass());
    }
}
