package Tests;

import Jeu.*;
import Jeu.Bloc.BlocAir;
import Jeu.Bloc.BlocBois;
import Jeu.Bloc.BlocPlanche;
import Jeu.Experts.ExpertCraft;
import Jeu.Experts.ExpertCraft_Bois_Planches;
import Jeu.Experts.ExpertCraft_Planches_Baton;
import Jeu.Item.Baton;
import Jeu.Item.Item;
import Jeu.Item.MainVide;
import org.junit.jupiter.api.Test;
import Exception.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TestsFabrication {

    private String monde_test = "projet_minecraft/src/Fichiers/MondeTestCreation.csv";
    @Test
    public void testAutre() throws Exception {
        // On va choisir un monde quelconque
        Monde monde_create = new Monde(monde_test);
        // Création de Steve
        Joueur steve = new Joueur(monde_create);
        Item mainVidePourTest = new MainVide();
        steve.setMain(mainVidePourTest);
        Coord co_valide = new Coord(2, 6);
        assertEquals(co_valide, steve.getCoordonnees_joueur());
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());
        // Remplir l'inventaire
        ArrayList<Objets> inv_steve = new ArrayList<Objets>();
        Inventory inv = new Inventory(inv_steve);
        steve.setInventaire(inv);
        steve.getInventaire().addInventory(new BlocPlanche());
        steve.getInventaire().addInventory(new BlocPlanche());
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
        recetteBaton.add(new BlocPlanche());
        recetteBaton.add(new BlocAir());
        recetteBaton.add(new BlocAir());
        recetteBaton.add(new BlocPlanche());
        recetteBaton.add(new BlocAir());
        assertEquals(recetteBaton.size(), 9);
        Fabrication fabriTest = new Fabrication(steve, recetteBaton, expertCraftPremier);
        assertEquals(steve.getInventaire().getTaille(), 4);
        for (int i = 0; i < 4; i++) {
            assertEquals(steve.getInventaire().get(i).getClass(), Baton.class);
        }
    }
    @Test
    public void testFabricationPlanche() throws Exception {
        // On va choisir un monde quelconque
        Monde monde_create = new Monde(monde_test);
        // Création de Steve
        Joueur steve = new Joueur(monde_create);
        Item mainVidePourTest = new MainVide();
        steve.setMain(mainVidePourTest);
        Coord co_valide = new Coord(2, 6);
        assertEquals(co_valide, steve.getCoordonnees_joueur());
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());
        // Steve ramasse un bloc de bois :
        Coord co_bois = new Coord(2, 7);
        ArrayList<Objets> itemSol = new ArrayList<Objets>();
        Objets bois = new BlocBois();
        Case[][] list_case_monde = monde_create.getTab_monde();
        list_case_monde[7][2].setItems_au_sol(itemSol);
        list_case_monde[7][2].addItemsAuSol(bois);
        assertEquals(list_case_monde[7][2].getTaille(), 1);
        Ramassage ramasser_bois = new Ramassage(steve, co_bois);
        assertEquals(steve.getInventaire().getTaille(), 1);
        assertEquals(steve.getInventaire().get(0).getClass(), BlocBois.class);

        // COR :
        ExpertCraft expertCraftPremier = null;
        expertCraftPremier = new ExpertCraft_Bois_Planches(expertCraftPremier);

        // Recette :
        ArrayList<Objets> recettePlanches = new ArrayList<Objets>();
        recettePlanches.add(new BlocAir());
        recettePlanches.add(new BlocAir());
        recettePlanches.add(new BlocAir());
        recettePlanches.add(new BlocAir());
        recettePlanches.add(new BlocBois());
        recettePlanches.add(new BlocAir());
        recettePlanches.add(new BlocAir());
        recettePlanches.add(new BlocAir());
        recettePlanches.add(new BlocAir());
        assertEquals(recettePlanches.size(), 9);

        // Steve fabrique selon cette recette :
        Fabrication steveFabriquerPlanches = new Fabrication(steve, recettePlanches, expertCraftPremier);
        assertEquals(steve.getInventaire().getTaille(), 4);
        for (int i = 0; i < steve.getInventaire().getTaille(); i++) {
            assertNotSame(steve.getInventaire().get(i).getClass(), BlocBois.class);
        }
        for (int i = 0; i < 4; i++) {
            assertEquals(steve.getInventaire().get(i).getClass(), BlocPlanche.class);
        }
        // Steve fabrique une deuxième fois en n'ayant plus les matériaux nécessaire
        ExpertCraft finalExpertCraftPremier = expertCraftPremier;
        Throwable testFabriqueDeux = assertThrows(InventoryException.class, () -> {
            Fabrication steveFabriquerPlanche = new Fabrication(steve, recettePlanches, finalExpertCraftPremier);
        });
        assertEquals(InventoryException.class, testFabriqueDeux.getClass());
    }
    @Test
    public void testFabricationBaton() throws Exception {
        // On va choisir un monde quelconque
        Monde monde_create = new Monde(monde_test);
        // Création de Steve
        Joueur steve = new Joueur(monde_create);
        Item mainVidePourTest = new MainVide();
        steve.setMain(mainVidePourTest);
        Coord co_valide = new Coord(2, 6);
        assertEquals(co_valide, steve.getCoordonnees_joueur());
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());
        // Steve ramasse 2 bloc de planche :
        Coord co_planche = new Coord(2, 7);
        ArrayList<Objets> itemSol = new ArrayList<Objets>();
        Objets planche = new BlocPlanche();
        Case[][] list_case_monde = monde_create.getTab_monde();
        list_case_monde[7][2].setItems_au_sol(itemSol);
        list_case_monde[7][2].addItemsAuSol(planche);
        list_case_monde[7][2].addItemsAuSol(planche);
        assertEquals(list_case_monde[7][2].getTaille(), 2);
        Ramassage ramasser_bois = new Ramassage(steve, co_planche);
        assertEquals(steve.getInventaire().getTaille(), 2);
        assertEquals(steve.getInventaire().get(0).getClass(), BlocPlanche.class);
        assertEquals(steve.getInventaire().get(1).getClass(), BlocPlanche.class);

        // COR :
        ExpertCraft expertCraftPremier = null;
        expertCraftPremier = new ExpertCraft_Planches_Baton(expertCraftPremier);

        // Recette :
        ArrayList<Objets> recetteBatons = new ArrayList<Objets>();
        recetteBatons.add(new BlocAir());
        recetteBatons.add(new BlocAir());
        recetteBatons.add(new BlocAir());
        recetteBatons.add(new BlocAir());
        recetteBatons.add(new BlocPlanche());
        recetteBatons.add(new BlocAir());
        recetteBatons.add(new BlocAir());
        recetteBatons.add(new BlocPlanche());
        recetteBatons.add(new BlocAir());
        assertEquals(recetteBatons.size(), 9);

        // Steve fabrique selon cette recette :
        Fabrication steveFabriquerBatons = new Fabrication(steve, recetteBatons, expertCraftPremier);
        assertEquals(steve.getInventaire().getTaille(), 4);
        for (int i = 0; i < steve.getInventaire().getTaille(); i++) {
            assertNotSame(steve.getInventaire().get(i).getClass(), BlocPlanche.class);
        }
        for (int i = 0; i < 4; i++) {
            assertEquals(steve.getInventaire().get(i).getClass(), Baton.class);
        }
    }
}
