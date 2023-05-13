package Jeu;

import Jeu.Bloc.BlocAir;
import Jeu.Experts.ExpertMinage.Expert;
import Exception.*;

import java.util.Objects;

/**
 * La classe Minage répresente l'action de miner un bloc spécifique par un joueur.
 * Cette classe introduit un principe de voisinage du joueur ainsi qu'une opération de minage en fonction
 * de l'expert fourni.
 */
public class Minage {
    /**
     * Joueur effectuant le minage.
     */
    private Joueur joueur_Miner;
    /**
     * Les coordonnées de la case où se trouve le bloc qui sera miné.
     */
    private Coord case_concerne;

    /**
     * Constructeur de la classe Minage.
     * @param joueur_Miner Joueur effectuant le minage.
     * @param case_concerne Coordonnées de la case où se trouve le bloc à miner.
     * @param expert Expert utilisé pour effectuer le minage, simule une permission.
     * @throws Exception
     */
    public Minage(Joueur joueur_Miner, Coord case_concerne, Expert expert) throws Exception {
        setJoueur_Miner(joueur_Miner);
        setCase_concerne(case_concerne);
        minerBloc(expert); // Il faudra donner le premier maillon
    }
    /**
     * Getter pour le joueur effectuant le minage.
     * @return Le joueur effectuant le minage.
     */
    public Joueur getJoueur_Miner() {
        return joueur_Miner;
    }
    /**
     * Setter pour le joueur effectuant le minage.
     * @param joueur_Miner Le joueur effectuant le minage.
     */
    public void setJoueur_Miner(Joueur joueur_Miner) {
        this.joueur_Miner = joueur_Miner;
    }
    /**
     * Getter pour les coordonnées de la case où se trouve le bloc à miner.
     * @return Les coordonnées de la case où se trouve le bloc à miner.
     */
    public Coord getCase_concerne() {
        return case_concerne;
    }

    /**
     * Setter pour les coordonnées de la case où se trouve le bloc à miner.
     * @param case_concerne Les coordonnées de la case où se trouve le bloc à miner.
     */
    public void setCase_concerne(Coord case_concerne) {
        this.case_concerne = case_concerne;
    }
    /**
     * Fonction qui gère le voisinage du joueur effectuant le minage.
     * @return True si la case où se trouve le bloc à miner est dans le voisinage proche ou lointain du joueur effectuant le minage.
     */
    public boolean blocDansVoisinageJoueur() {
        boolean voisin = false;
        Coord coo_case = this.getCase_concerne();
        int x_case = coo_case.getX();
        int y_case = coo_case.getY();
        int x_joueur = joueur_Miner.getCoordonnees_joueur().getX();
        int y_joueur = joueur_Miner.getCoordonnees_joueur().getY();

        // Voisinage proche :
        if ((x_case <= x_joueur - 1 || x_case <= x_joueur + 1) && (y_case <= y_joueur - 1 || y_case <= y_joueur + 1)) {
            voisin = true;
        }

        // Voisinage lointain (2 Blocs) :

        if ((x_case == x_joueur - 3 || x_case == x_joueur + 2) && (y_case == y_joueur - 2 || y_case == y_joueur + 2)
                || ((x_case == x_joueur - 3) && (y_case >= y_joueur - 1 && y_case <= y_joueur + 1)) ||
                ((x_case == x_joueur + 2) && (y_case >= y_joueur - 1 && y_case <= y_joueur + 1)) ||
                ((y_case == y_joueur - 2) && (x_case >= x_joueur - 2 && x_case <= x_joueur + 2)) ||
                ((y_case == y_joueur + 2) && (x_case >= x_joueur - 2 && x_case <= x_joueur + 2))) {
            // 8 Cas :
            // Cas 1 : Plage horizontale haut
            if (x_case == x_joueur - 3 && (y_case != y_joueur + 2 && y_case != y_joueur - 2)) {
                if (joueur_Miner.getMonde().getTab_monde()[y_case][x_case + 1].getContenu() instanceof BlocAir) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Cas 2 : Plage horizontale bas
            if (x_case == x_joueur + 2 && (y_case != y_joueur + 2 && y_case != y_joueur - 2)) {
                if (joueur_Miner.getMonde().getTab_monde()[y_case][x_case - 1].getContenu() instanceof BlocAir) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Cas 3 : Plage verticale gauche
            if (y_case == y_joueur - 2 && (x_case != x_joueur - 3 && x_case != x_joueur + 2)) {
                if (joueur_Miner.getMonde().getTab_monde()[y_case + 1][x_case].getContenu() instanceof BlocAir) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Cas 4 : Plage verticale droite
            if (y_case == y_joueur + 2 && (x_case != x_joueur - 3 && x_case != x_joueur + 2)) {
                if (joueur_Miner.getMonde().getTab_monde()[y_case - 1][x_case].getContenu() instanceof BlocAir) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Coins :
            // Coin haut gauche :
            if (x_case == x_joueur - 3 && y_case == y_joueur - 2) {
                if (joueur_Miner.getMonde().getTab_monde()[y_case + 1][x_case + 1].getContenu() instanceof BlocAir) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Coin haut droit :
            if (x_case == x_joueur - 3 && y_case == y_joueur + 2) {
                if (joueur_Miner.getMonde().getTab_monde()[y_case - 1][x_case + 1].getContenu() instanceof BlocAir) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Coin bas gauche :
            if (x_case == x_joueur + 2 && y_case == y_joueur - 2) {
                if (joueur_Miner.getMonde().getTab_monde()[y_case + 1][x_case - 1].getContenu() instanceof BlocAir) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Coin bas droit :
            if (x_case == x_joueur + 2 && y_case == y_joueur + 2) {
                if (joueur_Miner.getMonde().getTab_monde()[y_case - 1][x_case - 1].getContenu() instanceof BlocAir) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
        }
        return voisin;
    }
    /**
     * Fonction qui effectue le minage du bloc à la case concernée en utilisant l'expert donné en paramètre.
     * @param expert Expert utilisé pour vérifier la permission du joueur sur le bloc concerné.
     * @throws Exception
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
                // Si c'est bien dans le voisinage du joueur :
                if (blocDansVoisinageJoueur()) {
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
                //System.out.printf("\nIl n'y a pas d'expert pour cette configuration : \n%s\n%s\n", mainJoueur.getClass().getSimpleName(), blocVise.getClass().getSimpleName());
            }
        } else {
            throw new CORVideException();
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Minage minage = (Minage) o;
        return Objects.equals(joueur_Miner, minage.joueur_Miner) && Objects.equals(case_concerne, minage.case_concerne);
    }
    @Override
    public String toString() {
        return "Minage{" +
                "joueur_Miner=" + joueur_Miner +
                ", case_concerne=" + case_concerne +
                '}';
    }
}
