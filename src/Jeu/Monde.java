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
    public Monde(String mondeInco) throws FichierInexistantException, MondeException, CoordException {
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
    public void setPoint_respawn(Case point_respawn) {
        this.point_respawn = point_respawn;
    }
    public void validerMonde(String mondeInco) throws MondeException, CoordException {
        String line = "";
        int compteur_pdr = 0;
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
                    Coord coord_case = new Coord(i, compteur); // x, y
                    Case case_monde = new Case(cases[i], coord_case); // nom_case, coord
                    // Si c'est le point de respawn
                    if (case_monde.getNomCase().equals("R")) {
                        this.point_respawn = case_monde;
                    }
                    // On remplit le tab_monde
                    tab_monde[case_monde.getCoord().getX()][case_monde.getCoord().getY()] = case_monde;
                }
                System.out.println(line);
                compteur++;
                // Vérification
                if (cases.length != this.largeur) {
                    throw new MondeException();
                }
                //  - si le contenu de chaque case est contenu dans enum {A,T,P...}
                // On attribue largeur et hauteur :
            }
            // Parcours du tab_monde pour savoir si une case à un nom incorrect donc une case inexistante
            for (int i = 0; i < this.largeur; i++) {
                for (int j = 0; i < this.hauteur; i++) {
                    if (!tab_monde[i][j].verifierCase()) {
                        throw new MondeException();
                    }
                }
            }
            for (int i = 0; i < this.largeur; i++) {
                for (int j = 0; j < this.hauteur; i++) {
                    // On vérifie qu'il y a un seul pdr
                    if (tab_monde[i][j].getContenu() instanceof BlocRespawn) {
                        compteur_pdr++;
                    }
                }
            }
            if (compteur_pdr != 1) {
                throw new MondeException();
            }
            if (compteur != this.hauteur) {
                throw new MondeException();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
