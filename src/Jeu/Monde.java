package Jeu;
import Exception.*;
import Jeu.Bloc.*;
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
    public Case[][] getTab_monde() {
        return tab_monde;
    }
    public void setTab_monde(Case[][] tab_monde) {
        this.tab_monde = tab_monde;
    }
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
                    switch(cases[i]) {
                        case "A", "R": {
                            bloc_create = new BlocAir();
                            break;
                        }
                        case "B": {
                            bloc_create = new BlocBois();
                            break;
                        }
                        case "H": {
                            bloc_create = new BlocHerbe();
                            break;
                        }
                        case "P": {
                            bloc_create = new BlocPierre();
                            break;
                        }
                        case "T": {
                            bloc_create = new BlocTerre();
                            break;
                        }
                        default: {
                            throw new BlocInconnuException();
                        }
                    }
                    Case case_monde = new Case(bloc_create, coord_case); // Bloc, coord
                    if (cases[i].equals("R")) {
                        this.point_respawn = case_monde.getCoord();
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
