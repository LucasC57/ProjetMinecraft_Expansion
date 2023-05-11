package Jeu;
import Exception.*;

import java.util.Objects;

/**
 * La classe Deplacement répresente l'action de se déplacer dans le monde où est le joueur.
 * Cette classe introduit un principe de direction.
 */
public class Deplacement {
    /**
     * Direction Gauche ou Droite qui indique la direction du déplacement effectué.
     */
    private String direction;
    /**
     * 2ème direction non obligatoire mais qui permet d'introduire le principe de diagonal.
     */
    private String direction2 = null;
    /**
     * Le joueur qui va effectuer le déplacement.
     */
    private Joueur joueur;
    /**
     * Constructeur de la classe Deplacement.
     * @param joueur Joueur qui va effectuer le déplacement.
     * @param direction Direction Gauche ou Droite qui indique la direction du déplacement effectué.
     * @throws DeplacementException
     * @throws CoordException
     * @throws PlayerArgumentException
     */
    public Deplacement(Joueur joueur, String direction) throws DeplacementException, CoordException, PlayerArgumentException {
        setJoueur(joueur);
        setDirection(direction);
        validerDirection();
    }
    /**
     * Constructeur de la classe Deplacement.
     * @param joueur Joueur qui va effectuer le déplacement.
     * @param direction1 Direction Haut ou Bas qui indique la première direction du déplacement effectué.
     * @param direction2 2ème direction Gauche ou Droite qui indique la deuxième direction du déplacement effectué.
     * @throws DeplacementException
     * @throws CoordException
     * @throws PlayerArgumentException
     */
    public Deplacement(Joueur joueur, String direction1, String direction2) throws DeplacementException, CoordException, PlayerArgumentException {
        setJoueur(joueur);
        setDirection(direction1);
        setDirection2(direction2);
        validerDirection();
    }
    /**
     * Getter pour le joueur effectuant le déplacement.
     * @return le joueur effectuant le déplacement.
     */
    public Joueur getJoueur() {
        return joueur;
    }
    /**
     * Setter pour le joueur effectuant le déplacement.
     * @param joueur Le joueur effectuant le déplacement.
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
    /**
     * Getter pour la première direction du déplacement.
     * @return La première direction du déplacement.
     */
    public String getDirection() {
        return direction;
    }
    /**
     * Setter qui gère la première direction du déplacement.
     * @param direction La première direction du déplacement.
     * @throws DeplacementException Si la direction donnée est inconnue.
     */
    public void setDirection(String direction) throws DeplacementException {
        if (direction == null) {
            throw new DeplacementException();
        }
        // Autoriser les directions
        if (!direction.matches("^(Gauche|Droite|Haut|Bas)$")){
            throw new DeplacementException();
        }
        this.direction = direction;
    }
    /**
     * Getter pour la deuxième direction du déplacement.
     * @return La deuxième direction du déplacement.
     */
    public String getDirection2() {
        return direction2;
    }
    /**
     * Setter qui gère la deuxième direction du déplacement.
     * @param direction2 La deuxième direction du déplacement.
     * @throws DeplacementException Si la direction donnée est inconnue.
     */
    public void setDirection2(String direction2) throws DeplacementException {
        if (direction2 == null) {
            throw new DeplacementException();
        }
        // Autoriser les directions
        if (!direction2.matches("^(Gauche|Droite|Haut|Bas)$")){
            throw new DeplacementException();
        }
        this.direction2 = direction2;
    }
    /**
     * Procédure qui gère la modification des coordonnées du joueur pour aller à droite.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerDroite() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getJoueur().getCoordonnees_joueur().getX(), this.getJoueur().getCoordonnees_joueur().getY()+1);
        this.getJoueur().setCoordonnees_joueur(nouvelleCoordonnees);
    }
    /**
     * Procédure qui gère la modification des coordonnées du joueur pour aller à gauche.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerGauche() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getJoueur().getCoordonnees_joueur().getX(), this.getJoueur().getCoordonnees_joueur().getY()-1);
        this.getJoueur().setCoordonnees_joueur(nouvelleCoordonnees);
    }
    /**
     * Procédure qui gère la modification des coordonnées du joueur pour aller en Haut.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerHaut() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getJoueur().getCoordonnees_joueur().getX()-1, this.getJoueur().getCoordonnees_joueur().getY());
        this.getJoueur().setCoordonnees_joueur(nouvelleCoordonnees);
    }
    /**
     * Procédure qui gère la modification des coordonnées du joueur pour aller en Bas.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerBas() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getJoueur().getCoordonnees_joueur().getX()+1, this.getJoueur().getCoordonnees_joueur().getY());
        this.getJoueur().setCoordonnees_joueur(nouvelleCoordonnees);
    }
    /**
     * Procédure qui gère la modification des coordonnées du joueur pour aller en Haut et à Gauche.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerHautGauche() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getJoueur().getCoordonnees_joueur().getX()-1, this.getJoueur().getCoordonnees_joueur().getY()-1);
        this.getJoueur().setCoordonnees_joueur(nouvelleCoordonnees);
    }
    /**
     * Procédure qui gère la modification des coordonnées du joueur pour aller en Haut et à Droite.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerHautDroite() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getJoueur().getCoordonnees_joueur().getX()-1, this.getJoueur().getCoordonnees_joueur().getY()+1);
        this.getJoueur().setCoordonnees_joueur(nouvelleCoordonnees);
    }
    /**
     * Procédure qui gère la modification des coordonnées du joueur pour aller en Bas et à Gauche.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerBasGauche() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getJoueur().getCoordonnees_joueur().getX()+1, this.getJoueur().getCoordonnees_joueur().getY()-1);
        this.getJoueur().setCoordonnees_joueur(nouvelleCoordonnees);
    }
    /**
     * Procédure qui gère la modification des coordonnées du joueur pour aller en Bas et à Droite.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void allerBasDroite() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getJoueur().getCoordonnees_joueur().getX()+1, this.getJoueur().getCoordonnees_joueur().getY()+1);
        this.getJoueur().setCoordonnees_joueur(nouvelleCoordonnees);
    }

    /**
     * Procédure qui va valider la direction ou les directions donnée(s) par le joueur.
     * Cette procédure va, après avoir valider la ou les directions, effectuer le déplacement donné par le joueur.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public void validerDirection() throws CoordException, PlayerArgumentException, DeplacementException {
        // Pour une seule direction :
        if (getDirection().matches("^(Haut|Bas)$")) {
            if (getDirection2() != null) {
                if (getDirection().equals("Haut")) {
                    // Bas :
                    if (getDirection2().equals("Gauche")) {
                       this.allerHautGauche();
                    }
                    // Haut :
                    if (getDirection2().equals("Droite")) {
                        this.allerHautDroite();
                    }
                }
                if (getDirection().equals("Bas")) {
                    // Bas :
                    if (getDirection2().equals("Gauche")) {
                        this.allerBasGauche();
                    }
                    // Haut :
                    if (getDirection2().equals("Droite")) {
                        this.allerBasDroite();
                    }
                }
            } else {
                if (getDirection().equals("Gauche")) {
                    this.allerGauche();
                }
                if (getDirection().equals("Droite")) {
                    this.allerDroite();
                }
                if (getDirection().equals("Haut")) {
                    this.allerHaut();
                }
                if (getDirection().equals("Bas")) {
                    this.allerBas();
                }
            }
        } else if (getDirection().matches("^(Gauche|Droite)$")) {
            // Une seule direction :
            if (getDirection().equals("Gauche")) {
                this.allerGauche();
            }
            if (getDirection().equals("Droite")) {
                this.allerDroite();
            }
            if (getDirection().equals("Haut")) {
                this.allerHaut();
            }
            if (getDirection().equals("Bas")) {
                this.allerBas();
            }
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deplacement that = (Deplacement) o;
        return Objects.equals(direction, that.direction) && Objects.equals(joueur, that.joueur);
    }
    @Override
    public String toString() {
        return "Deplacement{" +
                "direction='" + direction + '\'' +
                ", direction2='" + direction2 + '\'' +
                ", joueur=" + joueur +
                '}';
    }
}
