package Tests;

import Jeu.Entity;
import Exception.*;
import Jeu.Experts.ExpertTypeEntity.ExpertTypeEntity;
import Jeu.Experts.ExpertTypeEntity.ExpertTypeEntity_Squelette;
import Jeu.Experts.ExpertTypeEntity.ExpertTypeEntity_Zombie;
import Jeu.Monde;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestsEntity {
    @Test
    public void testCreationEntity() throws Exception {
        String mondeTest = "src/Fichiers/MondeTestCreation.csv";
        ExpertTypeEntity expertPremier = null;
        Monde monde_normal = new Monde(mondeTest, expertPremier);

        // Tests
        assertThrows(CORVideException.class, () -> {
            Entity zombie = new Entity("Zombie", null, null, monde_normal);
        });
        expertPremier = new ExpertTypeEntity_Zombie(expertPremier);
        monde_normal.setExpertTypeEntity(expertPremier);
        Entity zombie = new Entity("Zombie", null, null, monde_normal);
        assertNotNull(monde_normal.getExpertTypeEntity());
        assertEquals(zombie.getType(), "Zombie");
        assertThrows(EntityInconnuException.class, () -> {
            Entity entityInconnu = new Entity("Blabala", null, null, monde_normal);
        });
        assertThrows(EntityInconnuException.class, () -> {
            Entity squelette = new Entity("SqueLette", null, null, monde_normal);
        });
        expertPremier = new ExpertTypeEntity_Squelette(expertPremier);
        monde_normal.setExpertTypeEntity(expertPremier);
        Entity squelette = new Entity("SqueLette", null, null, monde_normal);
    }
}
