package Jeu;

public class Minage {

    // Champs
    private Joueur joueur_Miner;
    private Coord case_concerne;

    // Constructeur
    public Minage(Joueur joueur_Miner, Coord case_concerne) {
        setJoueur_Miner(joueur_Miner);
        setCase_concerne(case_concerne);
        minerBloc();
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
    public void minerBloc() {

    }
}
