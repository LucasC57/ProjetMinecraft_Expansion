package Jeu;
import Exception.FichierInexistantException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Monde {
    String nom_fichier = null;
    //String mondeInco = "MondeIncoherent.csv";
    int largeur;
    int hauteur;
    String[][] tab_monde;
    public Monde(String mondeInco) throws FichierInexistantException {
        validerMonde(mondeInco);
        //setNom_fichier(nom_fichier);
    }
    public void setNom_fichier(String nom_fichier) throws FichierInexistantException {
        if (nom_fichier == null || nom_fichier.trim().isEmpty())
            throw new FichierInexistantException("Le fichier n'existe pas ou est vide");
        this.nom_fichier = nom_fichier;
    }
    public String getNom_Fichier() {
        return nom_fichier;
    }
    public boolean estExistant() throws FichierInexistantException {
        File fichier_monde = new File(this.getNom_Fichier());
        if (!fichier_monde.exists())
            throw new FichierInexistantException("Le fichier n'existe pas");
        return true;
    }
    public void setLargeur(int largeur) {
        if (largeur < 0)
            throw new IllegalArgumentException("La largeur ne peut pas être négative");
        this.largeur = largeur;
    }
    public void setHauteur(int hauteur) {
        if (hauteur < 0)
            throw new IllegalArgumentException("La hauteur ne peut pas être négative");
        this.hauteur = hauteur;
    }
    public void validerMonde( String mondeInco){
        String line = "";
        final String delimiter = ";";
        try
        {
            FileReader fichierMonde = new FileReader(mondeInco);
            BufferedReader reader = new BufferedReader(fichierMonde);
            while ((line = reader.readLine()) != null)
            {
                String[] cases = line.split(delimiter);
                System.out.println(line);
                // A FAIRE : les differents test pour valider le monde
                    //  - si la taille de la ligne = 20
                    //  - si il y a 10 lignes
                    //  - si le contenu de chaque case est contenu dans enum {A,T,P...}
            }
        }
        catch ( IOException e){
            e.printStackTrace();
        }
    }
}
