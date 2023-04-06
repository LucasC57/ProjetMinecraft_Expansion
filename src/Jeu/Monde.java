package Jeu;
import Exception.FichierInexistantException
public class Monde {
    String nom_fichier = null;
    public Monde(String nom_fichier) throws FichierInexistantException {
        setNom_fichier(nom_fichier);
    }
    public void setNom_fichier(String nom_fichier) throws FichierInexistantException {
        if (nom_fichier == null || nom_fichier.trim().isEmpty())
            throw new FichierInexistantException("Le fichier n'existe pas ou est vide");
        this.nom_fichier = nom_fichier;
    }
}
