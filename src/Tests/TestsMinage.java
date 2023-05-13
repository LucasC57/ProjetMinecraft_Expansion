package Tests;

import Jeu.*;
import Jeu.Bloc.Bloc;
import Jeu.Bloc.BlocAir;
import Jeu.Bloc.BlocBois;
import Jeu.Bloc.BlocPierre;
import Jeu.Experts.ExpertMinage.*;
import Jeu.Item.Item;
import Jeu.Item.MainVide;
import Jeu.Item.PiochePierre;
import org.junit.jupiter.api.Test;
import Exception.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestsMinage {

    private String monde_test = "projet_minecraft/src/Fichiers/MondeTestMine.csv";
    @Test
    public void testMinageImpossible() throws Exception {
        Monde monde_create = new Monde(monde_test);

        // Création de Steve
        Joueur steve = new Joueur(monde_create);
        Item mainVidePourTest = new MainVide();
        steve.setMain(mainVidePourTest);
        Coord co_valide = new Coord(4, 6);
        assertEquals(co_valide, steve.getCoordonnees_joueur());
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());
        // COR
        Expert expertPremier = null;
        Coord co_case = new Coord(4, 7);
        // Mettre du bois dans la case :
        Case[][] tab_mondeMine = steve.getMonde().getTab_monde();
        Bloc planches = new BlocBois();
        tab_mondeMine[co_case.getY()][co_case.getX()].setContenu(planches);
        // Vérifier que la case contient du bois :
        assertEquals(tab_mondeMine[co_case.getY()][co_case.getX()].getContenu().getClass(), BlocBois.class);
        // Vérifier si la chaine de responsabilité est bien vide
        Expert finalExpertPremier1 = expertPremier;
        Throwable testMinageCORVide = assertThrows(CORVideException.class, () -> {
            Minage miner_case = new Minage(steve, co_case, finalExpertPremier1);
        });
        assertEquals(CORVideException.class, testMinageCORVide.getClass());
        // Vérifier qu'après la case contient toujours du bois :
        assertEquals(tab_mondeMine[co_case.getY()][co_case.getX()].getContenu().getClass(), BlocBois.class);
        // Vérifier pour la case 5,9
        Coord case_non_voisine = new Coord(5, 9);
        expertPremier = new ExpertMain_Bois(expertPremier); // Pour éviter qu'il lance en priorité l'exception de CORVide
        Expert finalExpertPremier = expertPremier;
        Throwable testMinageHorsVoisinage = assertThrows(CaseNonVoisineException.class, () -> {
            Minage miner_case = new Minage(steve, case_non_voisine, finalExpertPremier);
        });
        assertEquals(CaseNonVoisineException.class, testMinageHorsVoisinage.getClass());
    }
    @Test
    public void testMinageMainNue() throws Exception {
        Monde monde_create = new Monde(monde_test);
        // Création de Steve
        Joueur steve = new Joueur(monde_create);
        Item mainVidePourTest = new MainVide();
        steve.setMain(mainVidePourTest);
        Coord co_valide = new Coord(4, 6);
        assertEquals(co_valide, steve.getCoordonnees_joueur());
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());
        assertEquals(steve.getMain().getClass(), MainVide.class);

        // Mettre à jour la COR pour que steve puisse miner du bois à main nue
        Expert expertPremier = null;
        expertPremier = new ExpertMain_Bois(expertPremier);
        Case[][] tab_mondeMine = steve.getMonde().getTab_monde();
        Coord co_bois = new Coord(4, 7);
        Bloc buche = new BlocBois();
        tab_mondeMine[co_bois.getY()][co_bois.getX()].setContenu(buche);
        Minage miner_case = new Minage(steve, co_bois, expertPremier);
        assertEquals(tab_mondeMine[co_bois.getY()][co_bois.getX()].getContenu().getClass(), BlocAir.class);
        assertEquals(tab_mondeMine[co_bois.getY()][co_bois.getX()].getTaille(), 1);
        assertEquals(tab_mondeMine[co_bois.getY()][co_bois.getX()].get(0).getClass(), BlocBois.class);

        // Steve mine un bloc de pierre alors qu'il ne peut pas
        Coord case_pierre = new Coord(5, 5);
        miner_case.setCase_concerne(case_pierre);
        miner_case.minerBloc(expertPremier);
        assertEquals(tab_mondeMine[case_pierre.getY()][case_pierre.getX()].getContenu().getClass(), BlocPierre.class);

        // Mettre à jour la COR pour qu'il puisse miner de la pierre à main nue
        expertPremier = new ExpertMain_Pierre(expertPremier);
        miner_case.minerBloc(expertPremier);
        assertEquals(tab_mondeMine[case_pierre.getY()][case_pierre.getX()].getContenu().getClass(), BlocAir.class);
        assertEquals(tab_mondeMine[case_pierre.getY()][case_pierre.getX()].getTaille(), 0);
    }
    @Test
    public void testMinageAvecPioche() throws Exception {
        Monde monde_create = new Monde(monde_test);
        // Création de Steve
        Joueur steve = new Joueur(monde_create);
        Item mainVidePourTest = new MainVide();
        steve.setMain(mainVidePourTest);
        Coord co_valide = new Coord(4, 6);
        assertEquals(co_valide, steve.getCoordonnees_joueur());
        assertEquals(steve.getCoordonnees_joueur(), monde_create.getPoint_respawn());
        assertEquals(steve.getMain().getClass(), MainVide.class);

        // Création de la pioche en pierre et mettre dans la main de steve
        Item piochePierre = new PiochePierre();
        steve.setMain(piochePierre);
        assertEquals(steve.getMain().getClass(), piochePierre.getClass());

        // Mettre à jour la COR pour que steve puisse miner du bois
        Case[][] tab_mondeMinage = steve.getMonde().getTab_monde();
        Expert expertPremier = null;
        expertPremier = new ExpertPiochePierre_Bois(expertPremier);
        Coord co_bois = new Coord(4, 7);
        Minage miner_case = new Minage(steve, co_bois, expertPremier);
        assertEquals(tab_mondeMinage[co_bois.getY()][co_bois.getX()].getContenu().getClass(), BlocAir.class);
        assertEquals(tab_mondeMinage[co_bois.getY()][co_bois.getX()].getTaille(), 1);
        assertEquals(tab_mondeMinage[co_bois.getY()][co_bois.getX()].get(0).getClass(), BlocBois.class);

        // Mettre à jour la COR pour qu'il puisse miner de la pierre avec une pioche
        expertPremier = new ExpertPiochePierre_Pierre(expertPremier);
        Coord co_autreCase = new Coord(5, 5);
        miner_case.setCase_concerne(co_autreCase);
        miner_case.minerBloc(expertPremier);
        assertEquals(tab_mondeMinage[co_autreCase.getY()][co_autreCase.getX()].getContenu().getClass(), BlocAir.class);
        assertEquals(tab_mondeMinage[co_autreCase.getY()][co_autreCase.getX()].getTaille(), 1);
        assertEquals(tab_mondeMinage[co_autreCase.getY()][co_autreCase.getX()].get(0).getClass(), BlocPierre.class);

        // Steve n'a plus de pioche et mise à jour de la COR
        steve.setMain(new MainVide());
        expertPremier = new ExpertMain_Pierre(expertPremier);

        // Minage de la case 5,6
        Coord co_pierre = new Coord(5, 6);
        miner_case.setJoueur_Miner(steve);
        miner_case.setCase_concerne(co_pierre);
        miner_case.minerBloc(expertPremier);
        assertEquals(tab_mondeMinage[co_pierre.getY()][co_pierre.getX()].getContenu().getClass(), BlocAir.class);
        assertEquals(tab_mondeMinage[co_pierre.getY()][co_pierre.getX()].getTaille(), 0);
    }
}
