package Tests;

import Jeu.*;
import Jeu.Bloc.BlocAir;
import Jeu.Bloc.BlocBois;
import Jeu.Experts.ExpertCraft;
import Jeu.Experts.ExpertCraft_Planches_Baton;
import Jeu.Item.Baton;
import Jeu.Item.Item;
import Jeu.Item.MainVide;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsFabrication {

    private String monde_test = "projet_minecraft/src/Fichiers/MondeTestMine.csv";
    @Test
    public void testFabricationPlanches() throws Exception {
        // On va choisir un monde quelconque
        Monde monde_create = new Monde(monde_test);
        // Création de Steve
        Joueur steve = new Joueur(monde_create);
        Item mainVidePourTest = new MainVide();
        steve.setMain(mainVidePourTest);
        Coord co_valide = new Coord(4, 6);
        assertEquals(co_valide, steve.getCoordonnees_joueur());
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());
        // Remplir l'inventaire
        ArrayList<Objets> inv_steve = new ArrayList<Objets>();
        Inventory inv = new Inventory(inv_steve);
        steve.setInventaire(inv);
        steve.getInventaire().addInventory(new BlocBois());
        steve.getInventaire().addInventory(new BlocBois());
        assertEquals(steve.getInventaire().getTaille(), 2);
        // Tests :
        ExpertCraft expertCraftPremier = null;
        expertCraftPremier = new ExpertCraft_Planches_Baton(expertCraftPremier);
        ArrayList<Objets> recetteBaton = new ArrayList<Objets>();
        // Création de la recette :
        // 4 et 7 : Bois
        recetteBaton.add(new BlocAir());
        recetteBaton.add(new BlocAir());
        recetteBaton.add(new BlocAir());
        recetteBaton.add(new BlocAir());
        recetteBaton.add(new BlocBois());
        recetteBaton.add(new BlocAir());
        recetteBaton.add(new BlocAir());
        recetteBaton.add(new BlocBois());
        recetteBaton.add(new BlocAir());
        assertEquals(recetteBaton.size(), 9);
        Fabrication fabriTest = new Fabrication(steve, recetteBaton, expertCraftPremier);
        assertEquals(steve.getInventaire().getTaille(), 4);
        for (int i = 0; i < 4; i++) {
            assertEquals(steve.getInventaire().get(i).getClass(), Baton.class);
        }
    }
}
