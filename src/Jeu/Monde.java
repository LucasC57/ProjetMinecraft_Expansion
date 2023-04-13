package Jeu;
import Exception.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Monde {

    private String nom_fichier = null;
    //String mondeInco = "MondeIncoherent.csv";
    private int largeur = 20;
    private int hauteur = 10;
    private Case[][] tab_monde;
    private Case point_respawn;
    public Monde(String mondeInco) throws FichierInexistantException, MondeException {
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
    public void validerMonde(String mondeInco) throws MondeException {
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
                System.out.println(line);
                compteur++;
                // Vérification
                if (cases.length != this.largeur) {
                    throw new MondeException();
                }
                //  - si le contenu de chaque case est contenu dans enum {A,T,P...}
                // On attribue largeur et hauteur :
            }
            if (compteur != this.hauteur) {
                throw new MondeException();
            }
        }
        catch ( IOException e){
            e.printStackTrace();
        }
    }
}
