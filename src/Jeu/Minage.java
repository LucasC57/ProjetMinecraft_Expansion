package Jeu;

import Jeu.Bloc.BlocAir;
import Jeu.Bloc.BlocBois;
import Jeu.Experts.Expert;
import Exception.*;

public class Minage {

    // Champs
    private Joueur joueur_Miner;
    private Coord case_concerne;

    // Constructeur
    public Minage(Joueur joueur_Miner, Coord case_concerne, Expert expert) throws Exception {
        setJoueur_Miner(joueur_Miner);
        setCase_concerne(case_concerne);
        minerBloc(expert); // Il faudra donner le premier maillon
    }
    public Joueur getJoueur_Miner() {
        return joueur_Miner;
    }
    public void setJoueur_Miner(Joueur joueur_Miner) {
        this.joueur_Miner = joueur_Miner;
    }
    public Coord getCase_concerne() {
        return case_concerne;
    }
    public void setCase_concerne(Coord case_concerne) {
        this.case_concerne = case_concerne;
    }
    /*
    Fonction qui va gérer le minage
     */
    public void minerBloc(Expert expert) throws Exception {
        if (expert != null) {
            // Travail avec la COR :
            Objets mainJoueur = this.getJoueur_Miner().getMain();
            Case[][] caseViseParJoueur = this.getJoueur_Miner().getMonde().getTab_monde();
            Objets blocVise = caseViseParJoueur[this.case_concerne.getY()][this.case_concerne.getX()].getContenu();

            // Le résultat de expert :
            try {
                // On défini le voisinage proche pour le minage
                Coord coo_case = this.getCase_concerne();
                boolean voisin = false;
                Coord gaucheHaut = new Coord(joueur_Miner.getCoordonnees_joueur().getX()+1, joueur_Miner.getCoordonnees_joueur().getY()-1);
                Coord gaucheBas = new Coord(joueur_Miner.getCoordonnees_joueur().getX(), joueur_Miner.getCoordonnees_joueur().getY()-1);
                Coord droitHaut = new Coord(joueur_Miner.getCoordonnees_joueur().getX()+1, joueur_Miner.getCoordonnees_joueur().getY()+1);
                Coord droitBas = new Coord(joueur_Miner.getCoordonnees_joueur().getX(), joueur_Miner.getCoordonnees_joueur().getY()+1);
                Coord posBas = new Coord(joueur_Miner.getCoordonnees_joueur().getX()-1, joueur_Miner.getCoordonnees_joueur().getY());
                Coord posHaut = new Coord(joueur_Miner.getCoordonnees_joueur().getX()+2, joueur_Miner.getCoordonnees_joueur().getY());
                // Vérif du voisinage
                if (coo_case.equals(gaucheHaut) ||
                        coo_case.equals(gaucheBas) ||
                        coo_case.equals(droitBas) ||
                        coo_case.equals(droitHaut) ||
                        coo_case.equals(posBas) ||
                        coo_case.equals(posHaut))
                {
                    voisin = true;
                }
                // Si c'est bien dans le voisinage du joueur :
                if (voisin) {
                    Objets res = expert.expertiser(mainJoueur, blocVise);
                    // On doit remplacer le bloc par de l'air s'il peut bien miner
                    if (res != null) {
                        this.joueur_Miner.getMonde().getTab_monde()[coo_case.getY()][coo_case.getX()].setContenu(new BlocAir());
                        // On doit aussi ajouter le bloc en question dans la liste des items au sol de la case
                        this.joueur_Miner.getMonde().getTab_monde()[coo_case.getY()][coo_case.getX()].addItemsAuSol(res);
                    } else {
                        // Le bloc est que même cassé mais rien de drop
                        this.joueur_Miner.getMonde().getTab_monde()[coo_case.getY()][coo_case.getX()].setContenu(new BlocAir());
                    }
                } else {
                    throw new CaseNonVoisineException();
                }
            } catch (ExpertManquantException e) {
                System.out.printf("\nIl n'y a pas d'expert pour cette configuration : \n%s\n%s\n", mainJoueur.getClass().getSimpleName(), blocVise.getClass().getSimpleName());
            }
        } else {
            throw new CORVideException();
        }
    }
}
