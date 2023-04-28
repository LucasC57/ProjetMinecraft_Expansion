package Jeu;
import Exception.*;
import Jeu.Bloc.Bloc;
import Jeu.Parsers.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class Monde {
    private String nom_fichier = null;
    private int largeur = 20;
    private int hauteur = 10;
    private Case[][] tab_monde = new Case[largeur][hauteur];
    private Coord point_respawn;
    public Monde(String mondeInco) throws Exception {
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
    public void setPoint_respawn(Coord point_respawn) {
        this.point_respawn = point_respawn;
    }
    public int getLargeur() {
        return largeur;
    }
    public int getHauteur() {
        return hauteur;
    }
    public Coord getPoint_respawn() {
        return point_respawn;
    }
    public void validerMonde(String mondeInco) throws Exception {
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

                    // Travail avec les parsers :
                    Bloc blocLink = null;

                    // Pour le bloc d'air :
                    Parser premierParser = null;
                    premierParser = new ParserTerre(premierParser);
                    blocLink = creationBloc.lireCaractere(cases[i], premierParser);

                    Case case_monde = new Case(blocLink, coord_case); // Bloc, coord

                    //if (case_monde.getContenu() instanceof BlocAir)
                    //    System.out.println("Yes");
                    //System.out.printf("X : %d Y : %d", case_monde.getCoord().getX(), case_monde.getCoord().getY());
                    // Si c'est le point de respawn
                    if (cases[i].equals("R")) {
                        this.point_respawn = case_monde.getCoord();
                    }
                    // On remplit le tab_monde
                    if (tab_monde != null) {
                        this.tab_monde[case_monde.getCoord().getY()][case_monde.getCoord().getX()] = case_monde;
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
