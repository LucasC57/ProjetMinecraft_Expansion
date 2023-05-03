package Jeu;
import Exception.*;

public class Deplacement {
    // Champs
    private String direction;
    private String direction2 = null;
    private Joueur joueur;
    // Constructeur
    public Deplacement(Joueur joueur, String direction) throws DeplacementException, CoordException, PlayerArgumentException {
        setJoueur(joueur);
        setDirection(direction);
        validerDirection();
    }
    public Deplacement(Joueur joueur, String direction1, String direction2) throws DeplacementException, CoordException, PlayerArgumentException {
        setJoueur(joueur);
        setDirection(direction1);
        setDirection2(direction2);
        validerDirection();
    }
    public Joueur getJoueur() {
        return joueur;
    }
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
    public String getDirection() {
        return direction;
    }
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
    public String getDirection2() {
        return direction2;
    }
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
    public void allerDroite() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getJoueur().getCoordonnees_joueur().getX(), this.getJoueur().getCoordonnees_joueur().getY()+1);
        this.getJoueur().setCoordonnees_joueur(nouvelleCoordonnees);
    }
    public void allerGauche() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getJoueur().getCoordonnees_joueur().getX(), this.getJoueur().getCoordonnees_joueur().getY()-1);
        this.getJoueur().setCoordonnees_joueur(nouvelleCoordonnees);
    }
    public void allerHaut() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getJoueur().getCoordonnees_joueur().getX()-1, this.getJoueur().getCoordonnees_joueur().getY());
        this.getJoueur().setCoordonnees_joueur(nouvelleCoordonnees);
    }
    public void allerBas() throws CoordException, PlayerArgumentException, DeplacementException {
        Coord nouvelleCoordonnees = new Coord(this.getJoueur().getCoordonnees_joueur().getX()+1, this.getJoueur().getCoordonnees_joueur().getY());
        this.getJoueur().setCoordonnees_joueur(nouvelleCoordonnees);
    }
    public void allerHautGauche() throws CoordException, PlayerArgumentException, DeplacementException {
        this.allerHaut();
        this.allerGauche();
    }
    public void allerHautDroite() throws CoordException, PlayerArgumentException, DeplacementException {
        this.allerHaut();
        this.allerDroite();
    }
    public void allerBasGauche() throws CoordException, PlayerArgumentException, DeplacementException {
        this.allerBas();
        this.allerGauche();
    }
    public void allerBasDroite() throws CoordException, PlayerArgumentException, DeplacementException {
        this.allerBas();
        this.allerDroite();
    }
    public void validerDirection() throws CoordException, PlayerArgumentException, DeplacementException {
        // Pour une seule direction :
        if (getDirection().matches("^(Haut|Bas)$")) {
            if (getDirection2() != null) {
                if (getDirection2().equals("Gauche")) {
                    this.allerGauche();
                    // Bas :
                    if (getDirection().equals("Haut")) {
                        this.allerHaut();
                    }
                    // Haut :
                    if (getDirection().equals("Bas")) {
                        this.allerBas();
                    }
                }
                if (getDirection2().equals("Droite")) {
                    this.allerDroite();
                    // Bas :
                    if (getDirection().equals("Haut")) {
                        this.allerHaut();
                    }
                    // Haut :
                    if (getDirection().equals("Bas")) {
                        this.allerBas();
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
}
