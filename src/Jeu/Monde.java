package Jeu;
import Exception.*;
import Jeu.Bloc.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * La classe Monde représente le monde où le joueur se trouve.
 */
public class Monde {
    /**
     * Le fichier .csv en lien avec le monde voulu.
     */
    private String nom_fichier = null;
    /**
     * La largeur d'un monde, du fichier .csv.
     */
    private int largeur = 20;
    /**
     * la hauteur d'un monde, du fichier .csv.
     */
    private int hauteur = 10;
    /**
     * Le tableau à 2 dimensions de Case en lien avec le fichier donné.
     */
    private Case[][] tab_monde = new Case[largeur][hauteur];
    /**
     * Les coordonnées du point de respawn du monde.
     */
    private Coord point_respawn;
    /**
     * Constructeur de la classe Monde.
     * @param mondeInco Le fichier qui sera lié au monde.
     * @throws Exception
     */
    public Monde(String mondeInco) throws Exception {
        validerMonde(mondeInco);
        //setNom_fichier(nom_fichier);
    }
    /**
     * Setter concernant le nom du fichier en lien avec le monde.
     * @param nom_fichier Le fichier en question.
     * @throws FichierInexistantException Si le fichier n'est pas intialisé ou qu'il n'existe pas.
     */
    public void setNom_fichier(String nom_fichier) throws FichierInexistantException {
        if (nom_fichier == null || nom_fichier.trim().isEmpty())
            throw new FichierInexistantException("Le fichier n'existe pas ou est vide");
        this.nom_fichier = nom_fichier;
    }
    /**
     * Getter concernant le nom du fichier en lien avec le monde.
     * @return Le nom du fichier en question.
     */
    public String getNom_Fichier() {
        return nom_fichier;
    }
    /**
     * Fonction booléenne pour savoir si un fichier existe.
     * @return True si le fichier existe.
     * @throws FichierInexistantException Si le fichier n'existe pas.
     */
    public boolean estExistant() throws FichierInexistantException {
        try {
            File fichier_monde = new File(this.getNom_Fichier());
        } catch (Exception e) {
            throw new FichierInexistantException("Le fichier n'existe pas");
        }
        return true;
    }
    /**
     * Setter concernant la largeur du monde.
     * @param largeur La largeur du monde.
     */
    public void setLargeur(int largeur) {
        if (largeur < 0)
            throw new IllegalArgumentException("La largeur ne peut pas être négative");
        this.largeur = largeur;
    }
    /**
     * Setter concernant la hauteur du monde.
     * @param hauteur La hauteur du monde.
     */
    public void setHauteur(int hauteur) {
        if (hauteur < 0)
            throw new IllegalArgumentException("La hauteur ne peut pas être négative");
        this.hauteur = hauteur;
    }
    /**
     * Setter concernant le point de respawn du monde.
     * @param point_respawn Les coordonnées du point de respawn du monde.
     */
    public void setPoint_respawn(Coord point_respawn) {
        this.point_respawn = point_respawn;
    }
    /**
     * Getter concernant la largeur du monde .
     * @return La largeur du monde.
     */
    public int getLargeur() {
        return largeur;
    }
    /**
     * Getter concernant la hauteur du monde.
     * @return La hauteur du monde.
     */
    public int getHauteur() {
        return hauteur;
    }
    /**
     * Getter concernant le point de respawn du monde.
     * @return Les coordonnées du point de respawn du monde.
     */
    public Coord getPoint_respawn() {
        return point_respawn;
    }
    /**
     * Getter concernant le tableau à 2 dimensions de Case du monde.
     * @return Le tableau à 2 dimensions de Case.
     */
    public Case[][] getTab_monde() {
        return tab_monde;
    }
    /**
     * Setter concernant le tableau à 2 dimensions de Case du monde.
     * @param tab_monde Le tableau à 2 dimensions de Case.
     */
    public void setTab_monde(Case[][] tab_monde) {
        this.tab_monde = tab_monde;
    }
    /**
     * Procédure qui effectue la création d'un monde.
     * @param mondeInco Le monde en question.
     * @throws Exception Si le fichier est mal formaté.
     */
    public void validerMonde(String mondeInco) throws Exception {
        // Tab monde :
        Case[][] notreTabMonde = new Case[largeur][hauteur];
        String line = "";
        final String delimiter = ";";
        try
        {
            FileReader fichierMonde = new FileReader(mondeInco);
            BufferedReader reader = new BufferedReader(fichierMonde);
            int compteur = 0;
            while ((line = reader.readLine()) != null)
            {
                String[] cases = line.split(delimiter);
                // Remplir tab_monde
                for(int i = 0; i < cases.length; i++) {
                    Coord coord_case = new Coord(compteur, i); // y, x
                    Bloc bloc_create = null;
                    switch (cases[i]) {
                        case "A", "R" -> {
                            bloc_create = new BlocAir();
                        }
                        case "B" -> {
                            bloc_create = new BlocBois();
                        }
                        case "H" -> {
                            bloc_create = new BlocHerbe();
                        }
                        case "P" -> {
                            bloc_create = new BlocPierre();
                        }
                        case "T" -> {
                            bloc_create = new BlocTerre();
                        }
                        case "L" -> {
                            bloc_create = new BlocLierre();
                        }
                        case "C" -> {
                            bloc_create = new BlocMineraiCharbon();
                        }
                        default -> {
                            throw new BlocInconnuException();
                        }
                    }
                    Case case_monde = new Case(bloc_create, coord_case, this); // Bloc, coord
                    if (cases[i].equals("R")) {
                        setPoint_respawn(case_monde.getCoord());
                    }
                    // On remplit le tab_monde
                    if (tab_monde != null) {
                        notreTabMonde[case_monde.getCoord().getY()][case_monde.getCoord().getX()] = case_monde;
                    }
                }
                compteur++;
                // Vérification
                if (cases.length != this.largeur) {
                    throw new FichierMalFormateException();
                }
            }
            if (compteur != this.hauteur) {
                throw new FichierMalFormateException();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        setTab_monde(notreTabMonde);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monde monde = (Monde) o;
        return Objects.equals(nom_fichier, monde.nom_fichier);
    }
    @Override
    public String toString() {
        return "Monde{" +
                "nom_fichier='" + nom_fichier + '\'' +
                ", largeur=" + largeur +
                ", hauteur=" + hauteur +
                ", tab_monde=" + Arrays.toString(tab_monde) +
                ", point_respawn=" + point_respawn +
                '}';
    }
}
