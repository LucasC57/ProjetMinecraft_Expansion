package Tests;

import Jeu.Coord;
import Jeu.Entity;
import Exception.*;
import Jeu.Experts.ExpertDegats.ExpertDegats;
import Jeu.Experts.ExpertDegats.ExpertDegats_EpeeBois;
import Jeu.Experts.ExpertDegats.ExpertDegats_EpeePierre;
import Jeu.Experts.ExpertDegats.ExpertDegats_MainVide;
import Jeu.Experts.ExpertTypeEntity.ExpertTypeEntity;
import Jeu.Experts.ExpertTypeEntity.ExpertTypeEntity_Squelette;
import Jeu.Experts.ExpertTypeEntity.ExpertTypeEntity_Zombie;
import Jeu.Item.EpeeBois;
import Jeu.Item.EpeePierre;
import Jeu.Item.MainVide;
import Jeu.Joueur;
import Jeu.Monde;
import com.sun.tools.javac.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestsEntity {
    @Test
    public void testCreationEntity() throws Exception {
        String mondeTest = "src/Fichiers/MondeTestCreation.csv";
        ExpertTypeEntity expertPremier = null;
        Monde monde_normal = new Monde(mondeTest, expertPremier);
        Coord entityCoord = new Coord(2, 6);
        Joueur steve = new Joueur(monde_normal);

        // Tests
        assertThrows(CORVideException.class, () -> {
            Entity zombie = new Entity("Zombie", null, steve, monde_normal, entityCoord);
        });
        expertPremier = new ExpertTypeEntity_Zombie(expertPremier);
        monde_normal.setExpertTypeEntity(expertPremier);
        Entity zombie = new Entity("Zombie", null, steve, monde_normal, entityCoord);
        assertNotNull(monde_normal.getExpertTypeEntity());
        assertEquals(zombie.getType(), "Zombie");
        assertThrows(EntityInconnuException.class, () -> {
            Entity entityInconnu = new Entity("Blabala", null, steve, monde_normal, entityCoord);
        });
        assertThrows(EntityInconnuException.class, () -> {
            Entity squelette = new Entity("SqueLette", null, steve, monde_normal, entityCoord);
        });
        expertPremier = new ExpertTypeEntity_Squelette(expertPremier);
        monde_normal.setExpertTypeEntity(expertPremier);
        Entity squelette = new Entity("SqueLette", null, steve, monde_normal, entityCoord);

        // Tests avec le Joueur :
        assertEquals(steve.getVie(), 20);
        assertEquals(zombie.getVie(), 20);

        ExpertDegats expertPremierDegats = null;
        EpeePierre epeePierre = new EpeePierre();
        steve.setMain(epeePierre);

        assertEquals(steve.getMain().getClass(), epeePierre.getClass());
        ExpertDegats finalExpertPremierDegats = expertPremierDegats;
        assertThrows(CORVideException.class, () -> {
            steve.taperEntity(zombie, finalExpertPremierDegats);
        });

        expertPremierDegats = new ExpertDegats_EpeePierre(expertPremierDegats);
        steve.taperEntity(zombie, expertPremierDegats);
        assertEquals(zombie.getVie(), 15);

        // Tests avec le zombie :
        EpeeBois epeeBois = new EpeeBois();
        zombie.setMain(epeeBois);
        expertPremierDegats = new ExpertDegats_EpeeBois(expertPremierDegats);
        assertEquals(steve.getVie(), 20);
        zombie.taperJoueur(steve, expertPremierDegats);
        assertEquals(steve.getVie(), 17);
        assertEquals(steve.getEtat_mortalite(), "Vivant");
        zombie.taperJoueur(steve, expertPremierDegats);
        zombie.taperJoueur(steve, expertPremierDegats);
        zombie.taperJoueur(steve, expertPremierDegats);
        zombie.taperJoueur(steve, expertPremierDegats);
        zombie.taperJoueur(steve, expertPremierDegats);
        assertEquals(steve.getVie(), 2);
        assertEquals(steve.getEtat_mortalite(), "Vivant");

        MainVide mainVide = new MainVide();
        zombie.setMain(mainVide);
        expertPremierDegats = new ExpertDegats_MainVide(expertPremierDegats);
        zombie.taperJoueur(steve, expertPremierDegats);
        zombie.taperJoueur(steve, expertPremierDegats);
        assertEquals(steve.getVie(), 0);
        assertEquals(steve.getEtat_mortalite(), "Mort");
    }
}
