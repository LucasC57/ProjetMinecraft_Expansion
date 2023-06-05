package Tests;

import Jeu.Coord;
import Jeu.Entity;
import Exception.*;
import Jeu.Experts.ExpertTypeEntity.ExpertTypeEntity;
import Jeu.Experts.ExpertTypeEntity.ExpertTypeEntity_Squelette;
import Jeu.Experts.ExpertTypeEntity.ExpertTypeEntity_Zombie;
import Jeu.Joueur;
import Jeu.Monde;
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
    }
}
