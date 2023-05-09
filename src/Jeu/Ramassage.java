package Jeu;
import Exception.*;
import Jeu.Bloc.Bloc;
import Jeu.Bloc.BlocAir;

import java.util.ArrayList;
import java.util.Objects;

public class Ramassage {
    // Champs : 
    private Joueur joueur_ramassage;
    private Coord coord_case;
    
    // Constructeurs :
    public Ramassage(Joueur joueur_ramassage, Coord coord_case) throws PlayerArgumentException, ListObjetsInexistantException, CoordException, CaseNonVoisineException, BlocNonFluideException, BlocFluideException, ObjetInexistantException {
        setJoueur_Ramassage(joueur_ramassage);
        setCoord_case(coord_case);
        ramasserItems();
    }
    public void setJoueur_Ramassage(Joueur joueur_ramassage) throws PlayerArgumentException {
        if (joueur_ramassage == null) {
            throw new PlayerArgumentException();
        }
        this.joueur_ramassage = joueur_ramassage;
    }
    public Joueur getJoueur_ramassage() {
        return joueur_ramassage;
    }
    public Coord getCoord_case() {
        return coord_case;
    }
    public void setCoord_case(Coord coord_case) {
        this.coord_case = coord_case;
    }
    public void ramasserItems() throws ListObjetsInexistantException, CoordException, CaseNonVoisineException, BlocNonFluideException, BlocFluideException, ObjetInexistantException {
        // On va récupérer le tab_monde
        boolean voisin = false;
        Joueur joueur_concerne = this.getJoueur_ramassage();
        Case[][] liste_cases = joueur_concerne.getMonde().getTab_monde();

        // On va devoir gérer les cases voisines
        Coord coo_case = this.getCoord_case();

        // Le joueur peut ramasser les objets dans sa case
        if (coo_case.equals(joueur_concerne.getCoordonnees_joueur())) {
            // On va récupérer le contenu de la case en question et ajouter ce contenu dans l'inventaire du Joueur
            for (int i = 0; i < liste_cases[coo_case.getY()][coo_case.getX()].getTaille(); i++) {
                Objets obj_case = liste_cases[joueur_concerne.getCoordonnees_joueur().getY()][joueur_concerne.getCoordonnees_joueur().getX()].getItems_au_sol().get(i);
                // On ajoute dans son inventaire
                joueur_concerne.getInventaire().addInventory(obj_case);
                // On n'oublie pas de vider la case
                liste_cases[joueur_concerne.getCoordonnees_joueur().getY()][joueur_concerne.getCoordonnees_joueur().getX()].removeObjets(i);
            }
        } else {
            // Il faut savoir si la case donnée est dans le voisinage du joueur
            // 4 cas à gérer
            Coord gaucheHaut = new Coord(joueur_concerne.getCoordonnees_joueur().getX()+1, joueur_concerne.getCoordonnees_joueur().getY()-1);
            Coord gaucheBas = new Coord(joueur_concerne.getCoordonnees_joueur().getX(), joueur_concerne.getCoordonnees_joueur().getY()-1);
            Coord droitHaut = new Coord(joueur_concerne.getCoordonnees_joueur().getX()+1, joueur_concerne.getCoordonnees_joueur().getY()+1);
            Coord droitBas = new Coord(joueur_concerne.getCoordonnees_joueur().getX(), joueur_concerne.getCoordonnees_joueur().getY()+1);
            if (coo_case.equals(gaucheHaut) || coo_case.equals(gaucheBas) || coo_case.equals(droitHaut) || coo_case.equals(droitBas)) {
                voisin = true;
            }
            // Si c'est bien une case voisine :
            if (voisin) {
                for (int i = 0; i < liste_cases[coo_case.getY()][coo_case.getX()].getTaille(); i++) {
                    Objets obj_case = liste_cases[coo_case.getY()][coo_case.getX()].getItems_au_sol().get(i);
                    // On ajoute dans son inventaire
                    joueur_concerne.getInventaire().addInventory(obj_case);
                }
                // On n'oublie pas de supprimer tous les objets qui se trouve dans la case :
                ArrayList<Objets> listm = liste_cases[coo_case.getY()][coo_case.getX()].getItems_au_sol();
                int nb_objets_a_supprimer = listm.size(); // nombre d'objets à supprimer
                int objets_supprimes = 0; // nombre d'objets déjà supprimés
                while (objets_supprimes < nb_objets_a_supprimer && !listm.isEmpty()) {
                    Objets obj_case = listm.get(0);
                    liste_cases[coo_case.getY()][coo_case.getX()].removeObjets(0);
                    objets_supprimes++;
                }
            } else {
                throw new CaseNonVoisineException();
            }
        }
    
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ramassage ramassage = (Ramassage) o;
        return Objects.equals(joueur_ramassage, ramassage.joueur_ramassage) && Objects.equals(coord_case, ramassage.coord_case);
    }
    @Override
    public String toString() {
        return "Ramassage{" +
                "joueur_ramassage=" + joueur_ramassage +
                ", coord_case=" + coord_case +
                '}';
    }
}
