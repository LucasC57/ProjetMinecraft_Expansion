package Jeu;

import Jeu.Item.Item;
import Exception.*;
import Jeu.Item.MainVide;

import java.util.Objects;

/**
 * La classe Joueur répresente tout simplement le ou les joueurs introduit dans un monde spécifique
 */
public class Joueur {
    /**
     * Nom du joueur.
     */
    private String nom;
    /**
     * Inventaire du joueur créé, initialisé à vide.
     */
    private Inventory inventaire = new Inventory();
    /**
     * Main du joueur, initialisé à vide.
     */
    private Item main = new MainVide();
    /**
     * Coordonnées du joueur créé.
     */
    private Coord coordonnees_joueur;
    /**
     * Monde où le joueur est créé.
     */
    private Monde monde;
    /**
     * Constructeur de la classe Joueur
     * @param nom Nom du joueur créé.
     * @param inventaire Inventaire du joueur créé.
     * @param main Main du joueur créé.
     * @param coordonnees_joueur Coordonnées du joueur créé.
     * @param monde Monde là où le joueur est créé.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    // Constructeur
    public Joueur(String nom, Inventory inventaire, Item main, Coord coordonnees_joueur, Monde monde) throws CoordException, PlayerArgumentException, DeplacementException {
        setNom(nom);
        setInventaire(inventaire);
        setMain(main);
        setMonde(monde);
        setCoordonnees_joueur(coordonnees_joueur);
    }
    /**
     * Deuxième constructeur de la classe Joueur. Le joueur à un nom par défaut "Steve", il a l'inventaire de base,
     * donc initialisé à vide, il a rien dans sa main et les coordonnées du joueur créé sont les coordonnées du point de respawn
     * du monde donné.
     * @param monde Monde là où le joueur est créé.
     * @throws CoordException
     * @throws PlayerArgumentException
     * @throws DeplacementException
     */
    public Joueur(Monde monde) throws CoordException, PlayerArgumentException, DeplacementException {
        setNom("Steve");
        // Pas de setInventaire car l'inventaire est initialisé à vide
        // Pareil pour la main
        // On va partir du principe que si l'on ne précise pas les co du joueur, alors c'est au pts de respawn
        setMonde(monde);
        setCoordonnees_joueur(monde.getPoint_respawn());
    }
    /**
     * Getter concernant le nom du joueur.
     * @return Le nom du joueur créé.
     */
    public String getNom() {
        return nom;
    }
    /**
     * Setter concernant le nom du joueur créé.
     * @param nom Le nom du joueur créé.
     * @throws PlayerArgumentException Si le nom est null ou qu'il est vide.
     */
    public void setNom(String nom) throws PlayerArgumentException {
        if (nom == null || nom.trim().isEmpty()) {
            throw new PlayerArgumentException();
        }
        this.nom = nom;
    }
    /**
     * Getter concernant l'inventaire du joueur créé.
     * @return L'inventaire du joueur créé.
     */
    public Inventory getInventaire() {
        return inventaire;
    }
    /**
     * Setter concernant l'inventaire du joueur créé.
     * @param inventaire L'inventaire du joueur créé.
     * @throws PlayerArgumentException Si l'inventaire donné n'est pas initialisé ou qu'il n'existe pas.
     */
    public void setInventaire(Inventory inventaire) throws PlayerArgumentException {
        if (inventaire == null) {
            throw new PlayerArgumentException();
        }
        this.inventaire = inventaire;
    }
    /**
     * Getter concernant la main du joueur créé.
     * @return La main du joueur créé.
     */
    public Item getMain() {
        return main;
    }
    /**
     * Setter concernant la main du joueur créé.
     * @param main La main du joueur créé.
     * @throws PlayerArgumentException Si la main du joueur n'est pas initialisé ou qu'elle n'existe pas.
     */
    public void setMain(Item main) throws PlayerArgumentException {
        if (main == null) {
            throw new PlayerArgumentException();
        }
        this.main = main;
    }
    /**
     * Getter concernant les coordonnées du joueur créé.
     * @return Les coordonnées du joueur créé.
     */
    public Coord getCoordonnees_joueur() {
        return this.coordonnees_joueur;
    }
    /**
     * Setter concernant les coordonnées du joueur créé.
     * @param coordonnees_joueur Les coordonnées du joueur créé.
     * @throws PlayerArgumentException Si les coordonnées ne sont pas initialisées ou qu'elles n'existent pas.
     * @throws DeplacementException Si les coordonnées données ne sont pas valable avec les dimensions du monde, si les blocs dans les cases
     * liées à la tête et aux pieds du joueur ne sont pas fluides.
     */
    public void setCoordonnees_joueur(Coord coordonnees_joueur) throws PlayerArgumentException, DeplacementException {
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
    /**
     * Getter concernant le monde du joueur créé.
     * @return Le monde là où le joueur est créé.
     */
    public Monde getMonde() {
        return monde;
    }
    /**
     * Setter concernant le monde du joueur créé.
     * @param monde Le monde là ou le joueur sera créé.
     * @throws PlayerArgumentException Si le monde donné n'est pas initialisé ou qu'il est vide.
     */
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
