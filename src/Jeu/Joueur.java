package Jeu;

import Jeu.Item.Item;
import Exception.*;

import java.util.Objects;

public class Joueur {
    // Champs :
    private String nom;
    private Inventory inventaire = null;
    private Item main = null;
    private Coord coordonnees_joueur;
    private Monde monde;
    // Constructeur
    public Joueur(String nom, Inventory inventaire, Item main, Coord coordonnees_joueur, Monde monde) throws CoordException, PlayerArgumentException, DeplacementException {
        setNom(nom);
        setInventaire(inventaire);
        setMain(main);
        setMonde(monde);
        setCoordonnees_joueur(coordonnees_joueur);
    }
    // On peut ici faire un constructeur avec seulement le monde qui est obligatoire pour avoir le pts de respawn :
    public Joueur(Monde monde) throws CoordException, PlayerArgumentException, DeplacementException {
        setNom("Steve");
        // Pas de setInventaire car l'inventaire est initialisé à null
        // Pareil pour la main
        // On va partir du principe que si l'on ne précise pas les co du joueur, alors c'est au pts de respawn
        setMonde(monde);
        setCoordonnees_joueur(monde.getPoint_respawn());
    }
    // Les getters et setters :
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) throws PlayerArgumentException {
        if (nom == null || nom.trim().isEmpty()) {
            throw new PlayerArgumentException();
        }
        this.nom = nom;
    }
    public Inventory getInventaire() {
        return inventaire;
    }
    public void setInventaire(Inventory inventaire) throws PlayerArgumentException {
        if (inventaire == null) {
            throw new PlayerArgumentException();
        }
        this.inventaire = inventaire;
    }
    public Item getMain() {
        return main;
    }
    public void setMain(Item main) throws PlayerArgumentException {
        // Sa main peut être vide (=null)
        this.main = main;
    }
    public Coord getCoordonnees_joueur() {
        return this.coordonnees_joueur;
    }
    public void setCoordonnees_joueur(Coord coordonnees_joueur) throws PlayerArgumentException, CoordException, DeplacementException {
        if (coordonnees_joueur == null) {
            throw new PlayerArgumentException();
        }
        // Vérification pour la sortie du monde
        if (coordonnees_joueur.getY() < 0) {
            throw new DeplacementException();
        }
        if (coordonnees_joueur.getX() - 1 < 0) {
            throw new DeplacementException();
        }
        if (coordonnees_joueur.getX() > monde.getHauteur() - 1) {
            throw new DeplacementException();
        }
        if (coordonnees_joueur.getY() > monde.getLargeur() - 1) {
            throw new DeplacementException();
        }
        this.coordonnees_joueur = coordonnees_joueur;
        Case[][] test = this.getMonde().getTab_monde();
        if (test[getCoordonnees_joueur().getY()][getCoordonnees_joueur().getX()].getContenu().isFluidite()) {
            test[getCoordonnees_joueur().getY()][getCoordonnees_joueur().getX()].setPresence_pieds(true);
        } else {
            throw new DeplacementException();
        }
        if (test[getCoordonnees_joueur().getY()][getCoordonnees_joueur().getX()-1].getContenu().isFluidite()) {
            test[getCoordonnees_joueur().getY()][getCoordonnees_joueur().getX()-1].setPresence_tete(true);
        } else {
            throw new DeplacementException();
        }
    }
    public Monde getMonde() {
        return monde;
    }
    public void setMonde(Monde monde) throws PlayerArgumentException {
        if (monde == null) {
            throw new PlayerArgumentException();
        }
        this.monde = monde;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return Objects.equals(nom, joueur.nom) && Objects.equals(coordonnees_joueur, joueur.coordonnees_joueur) && Objects.equals(monde, joueur.monde);
    }
    @Override
    public String toString() {
        return "Joueur{" +
                "nom='" + nom + '\'' +
                ", inventaire=" + inventaire +
                ", main=" + main +
                ", coordonnees_joueur=" + coordonnees_joueur +
                ", monde=" + monde +
                '}';
    }
}
