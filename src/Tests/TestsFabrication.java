package Tests;

import Jeu.*;
import Jeu.Bloc.BlocAir;
import Jeu.Bloc.BlocBois;
import Jeu.Bloc.BlocPierre;
import Jeu.Bloc.BlocPlanche;
import Jeu.Experts.ExpertCraft.*;
import Jeu.Item.*;
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
        Coord co_valide = new Coord(2, 6);
        assertEquals(co_valide, steve.getCoordonnees_joueur());
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());
        // Remplir l'inventaire
        steve.getInventaire().addInventory(new BlocPlanche());
        steve.getInventaire().addInventory(new BlocPlanche());
        assertEquals(steve.getInventaire().getTaille(), 2);
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
        steve.effectuerRecette(expertCraftPremier, recetteBaton);
        assertEquals(steve.getInventaire().getTaille(), 4);
        assertEquals(steve.getNbrObjetDansInventaire(new Baton()), 4);
    }
    @Test
    public void testFabricationPlanche() throws Exception {
        // On va choisir un monde quelconque
        Monde monde_create = new Monde(monde_test);
        // Création de Steve
        Joueur steve = new Joueur(monde_create);
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
        steve.ramasserItems(co_bois);
        assertEquals(steve.getInventaire().getTaille(), 1);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocBois()), 1);

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
        steve.effectuerRecette(expertCraftPremier, recettePlanches);
        assertEquals(steve.getInventaire().getTaille(), 4);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPlanche()), 4);
        // Steve fabrique une deuxième fois en n'ayant plus les matériaux nécessaire
        ExpertCraft finalExpertCraftPremier = expertCraftPremier;
        assertThrows(InventoryException.class, () -> {
            steve.effectuerRecette(finalExpertCraftPremier, recettePlanches);
        });
    }
    @Test
    public void testFabricationBaton() throws Exception {
        // On va choisir un monde quelconque
        Monde monde_create = new Monde(monde_test);
        // Création de Steve
        Joueur steve = new Joueur(monde_create);
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
        steve.ramasserItems(co_planche);
        assertEquals(steve.getInventaire().getTaille(), 2);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPlanche()), 2);

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
        steve.effectuerRecette(expertCraftPremier, recetteBatons);
        assertEquals(steve.getInventaire().getTaille(), 4);
        assertEquals(steve.getNbrObjetDansInventaire(new Baton()), 4);
        // Steve fabrique une deuxième fois n'ayant plus les matériaux nécessaire
        ExpertCraft finalExpertCraftPremier = expertCraftPremier;
        assertThrows(InventoryException.class, () -> {
            steve.effectuerRecette(finalExpertCraftPremier, recetteBatons);
        });
    }
    @Test
    public void testFabricationPiocheBois() throws Exception {
        // On va choisir un monde quelconque
        Monde monde_create = new Monde(monde_test);
        // Création de Steve
        Joueur steve = new Joueur(monde_create);
        Coord co_valide = new Coord(2, 6);
        assertEquals(co_valide, steve.getCoordonnees_joueur());
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());
        // Steve ramasse 2 Batons et 3 bloc de Bois :
        Coord co_planche = new Coord(2, 7);
        ArrayList<Objets> itemSol = new ArrayList<Objets>();
        Objets planche = new BlocPlanche();
        Objets batons = new Baton();
        Case[][] list_case_monde = monde_create.getTab_monde();
        list_case_monde[7][2].setItems_au_sol(itemSol);
        list_case_monde[7][2].addItemsAuSol(batons);
        list_case_monde[7][2].addItemsAuSol(batons);
        list_case_monde[7][2].addItemsAuSol(planche);
        list_case_monde[7][2].addItemsAuSol(planche);
        list_case_monde[7][2].addItemsAuSol(planche);
        assertEquals(list_case_monde[7][2].getTaille(), 5);
        steve.ramasserItems(co_planche);
        assertEquals(steve.getInventaire().getTaille(), 5);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPlanche()), 3);
        assertEquals(steve.getNbrObjetDansInventaire(new Baton()), 2);

        // COR :
        ExpertCraft experCraftPremier = null;
        experCraftPremier = new ExpertCraft_PiocheBois(experCraftPremier);

        // Recette :
        ArrayList<Objets> recettePiocheBois = new ArrayList<Objets>();
        recettePiocheBois.add(new BlocPlanche());
        recettePiocheBois.add(new BlocPlanche());
        recettePiocheBois.add(new BlocPlanche());
        recettePiocheBois.add(new BlocAir());
        recettePiocheBois.add(new Baton());
        recettePiocheBois.add(new BlocAir());
        recettePiocheBois.add(new BlocAir());
        recettePiocheBois.add(new Baton());
        recettePiocheBois.add(new BlocAir());
        assertEquals(recettePiocheBois.size(), 9);

        // Steve fabrique selon cette recette :
        steve.effectuerRecette(experCraftPremier, recettePiocheBois);
        assertEquals(steve.getInventaire().getTaille(), 1);
        assertEquals(steve.getNbrObjetDansInventaire(new PiocheBois()), 1);
        ExpertCraft finalExperCraftPremier = experCraftPremier;
        assertThrows(InventoryException.class, () -> {
            steve.effectuerRecette(finalExperCraftPremier, recettePiocheBois);
        });
    }
    @Test
    public void testFabricationPiochePierre() throws Exception {
        // On va choisir un monde quelconque
        Monde monde_create = new Monde(monde_test);
        // Création de Steve
        Joueur steve = new Joueur(monde_create);
        Coord co_valide = new Coord(2, 6);
        assertEquals(co_valide, steve.getCoordonnees_joueur());
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());
        // Steve ramasse 2 Batons et 3 bloc de Pierre :
        Coord co_items = new Coord(2, 7);
        ArrayList<Objets> itemSol = new ArrayList<Objets>();
        Objets pierre = new BlocPierre();
        Objets batons = new Baton();
        Case[][] list_case_monde = monde_create.getTab_monde();
        list_case_monde[7][2].setItems_au_sol(itemSol);
        list_case_monde[7][2].addItemsAuSol(batons);
        list_case_monde[7][2].addItemsAuSol(batons);
        list_case_monde[7][2].addItemsAuSol(pierre);
        list_case_monde[7][2].addItemsAuSol(pierre);
        list_case_monde[7][2].addItemsAuSol(pierre);
        assertEquals(list_case_monde[7][2].getTaille(), 5);
        steve.ramasserItems(co_items);
        assertEquals(steve.getInventaire().getTaille(), 5);
        assertEquals(steve.getNbrObjetDansInventaire(new BlocPierre()), 3);
        assertEquals(steve.getNbrObjetDansInventaire(new Baton()), 2);

        // COR :
        ExpertCraft experCraftPremier = null;
        experCraftPremier = new ExpertCraft_PiochePierre(experCraftPremier);

        // Recette :
        ArrayList<Objets> recettePiochePierre = new ArrayList<Objets>();
        recettePiochePierre.add(new BlocPierre());
        recettePiochePierre.add(new BlocPierre());
        recettePiochePierre.add(new BlocPierre());
        recettePiochePierre.add(new BlocAir());
        recettePiochePierre.add(new Baton());
        recettePiochePierre.add(new BlocAir());
        recettePiochePierre.add(new BlocAir());
        recettePiochePierre.add(new Baton());
        recettePiochePierre.add(new BlocAir());
        assertEquals(recettePiochePierre.size(), 9);

        // Steve fabrique selon cette recette :
        steve.effectuerRecette(experCraftPremier, recettePiochePierre);
        assertEquals(steve.getInventaire().getTaille(), 1);
        assertEquals(steve.getNbrObjetDansInventaire(new PiochePierre()), 1);
        ExpertCraft finalExperCraftPremier = experCraftPremier;
        assertThrows(InventoryException.class, () -> {
            steve.effectuerRecette(finalExperCraftPremier, recettePiochePierre);
        });
    }
}
