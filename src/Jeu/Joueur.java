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
    public Joueur(String nom, Inventory inventaire, Item main, Coord coordonnees_joueur, Monde monde) throws CoordException, PlayerArgumentException {
        setNom(nom);
        setInventaire(inventaire);
        setMain(main);
        setCoordonnees_joueur(coordonnees_joueur);
        setMonde(monde);
    }
    // On peut ici faire un constructeur avec seulement le monde qui est obligatoire pour avoir le pts de respawn :
    public Joueur(Monde monde) throws CoordException, PlayerArgumentException {
        setNom("Steve");
        // Pas de setInventaire car l'inventaire est initialisé à null
        // Pareil pour la main
        // On va partir du principe que si l'on ne précise pas les co du joueur, alors c'est au pts de respawn
        setCoordonnees_joueur(monde.getPoint_respawn());
        setMonde(monde);
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
    public void setCoordonnees_joueur(Coord coordonnees_joueur) throws PlayerArgumentException, CoordException {
        if (coordonnees_joueur == null) {
            throw new PlayerArgumentException();
        }
        this.coordonnees_joueur = coordonnees_joueur;
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
