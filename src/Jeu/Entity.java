package Jeu;

import Jeu.Experts.ExpertDegats.ExpertDegats;
import Jeu.Item.Item;
import Exception.*;
import Jeu.Item.MainVide;

public class Entity {
    // Champs :
    private String type;
    private Item main = new MainVide();
    private Joueur cible;
    private Monde mondeApparition;
    private Coord coordEntity;
    private int vie = 20;
    public Entity(String type, Item main, Joueur cible, Monde quelMonde, Coord cooEntity) throws Exception {
        setMondeApparition(quelMonde);
        setCoordEntity(cooEntity);
        setType(type);
        setMain(main);
        setCible(cible);
        setVie(vie);
    }
    public String getType() {
        return type;
    }
    public void setType(String type) throws Exception {
        if (type == null || type.trim().equals("")) {
            throw new IllegalArgumentException();
        }
        if (getMondeApparition().getExpertTypeEntity() == null) {
            throw new CORVideException();
        }
        boolean existence = getMondeApparition().getExpertTypeEntity().expertiserEntity(type);
        if (!existence) {
            throw new EntityInconnuException();
        }
        this.type = type;
    }
    public int getVie() {
        return vie;
    }
    public void setVie(int vie) throws MontantVieException {
        if (vie < 0 || vie > 20) {
            throw new MontantVieException();
        }
        this.vie = vie;
    }
    public Item getMain() {
        return main;
    }
    public void setMain(Item main) {
        this.main = main;
    }
    public Joueur getCible() {
        return cible;
    }
    public Coord getCoordEntity() {
        return coordEntity;
    }
    public void setCoordEntity(Coord coordEntity) {
        this.coordEntity = coordEntity;
    }
    public boolean voisinageAvecJoueur(Coord coordJoueur) {
        boolean voisin = false;
        int x_joueur = coordJoueur.getX();
        int y_joueur = coordJoueur.getY();
        int x_entity = this.getCoordEntity().getX();
        int y_entity = this.getCoordEntity().getY();

        // Voisinage proche :
        if ((x_joueur <= x_entity - 1 || x_joueur <= x_entity + 1) && (y_joueur <= y_entity - 1 || y_joueur <= y_entity + 1)) {
            voisin = true;
        }

        // Voisinage lointain (2 Blocs) :

        if ((x_joueur == x_entity - 3 || x_joueur == x_entity + 2) && (y_joueur == y_entity - 2 || y_joueur == y_entity + 2)
                || ((x_joueur == x_entity - 3) && (y_joueur >= y_entity - 1 && y_joueur <= y_entity + 1)) ||
                ((x_joueur == x_entity + 2) && (y_joueur >= y_entity - 1 && y_joueur <= y_entity + 1)) ||
                ((y_joueur == y_entity - 2) && (x_joueur >= x_entity - 2 && x_joueur <= x_entity + 2)) ||
                ((y_joueur == y_entity + 2) && (x_joueur >= x_entity - 2 && x_joueur <= x_entity + 2))) {
            // 8 Cas :
            // Cas 1 : Plage horizontale haut
            if (x_joueur == x_entity - 3 && (y_joueur != y_entity + 2 && y_joueur != y_entity - 2)) {
                if (this.getMondeApparition().getTab_monde()[y_joueur][x_joueur + 1].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Cas 2 : Plage horizontale bas
            if (x_joueur == x_entity + 2 && (y_joueur != y_entity + 2 && y_joueur != y_entity - 2)) {
                if (this.getMondeApparition().getTab_monde()[y_joueur][x_joueur - 1].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Cas 3 : Plage verticale gauche
            if (y_joueur == y_entity - 2 && (x_joueur != x_entity - 3 && x_joueur != x_entity + 2)) {
                if (this.getMondeApparition().getTab_monde()[y_joueur + 1][x_joueur].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Cas 4 : Plage verticale droite
            if (y_joueur == y_entity + 2 && (x_joueur != x_entity - 3 && x_joueur != x_entity + 2)) {
                if (this.getMondeApparition().getTab_monde()[y_joueur - 1][x_joueur].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Coins :
            // Coin haut gauche :
            if (x_joueur == x_entity - 3 && y_joueur == y_entity - 2) {
                if (this.getMondeApparition().getTab_monde()[y_joueur + 1][x_joueur + 1].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Coin haut droit :
            if (x_joueur == x_entity - 3 && y_joueur == y_entity + 2) {
                if (this.getMondeApparition().getTab_monde()[y_joueur - 1][x_joueur + 1].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Coin bas gauche :
            if (x_joueur == x_entity + 2 && y_joueur == y_entity - 2) {
                if (this.getMondeApparition().getTab_monde()[y_joueur + 1][x_joueur - 1].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
            // Coin bas droit :
            if (x_joueur == x_entity + 2 && y_joueur == y_entity + 2) {
                if (this.getMondeApparition().getTab_monde()[y_joueur - 1][x_joueur - 1].getContenu().isFluidite()) {
                    voisin = true;
                } else {
                    voisin = false;
                }
            }
        }
        return voisin;
    }
    public void setCible(Joueur cible) throws CibleHorsMondeException, CibleHorsPorteeException {
        if (cible == null) {
            throw new IllegalArgumentException();
        }
        // On va d'abord vérifier que le joueur se trouve dans le même monde que l'entité
        if (!cible.getMonde().equals(this.getMondeApparition())) {
            throw new CibleHorsMondeException();
        }
        // Si la cible est dans le voisinage de l'entité :
        if (!voisinageAvecJoueur(cible.getCoordonnees_joueur())) {
            throw new CibleHorsPorteeException();
        }
        // Système de voisinage : à faire
        this.cible = cible;
    }
    public void ajouterPointDeVie(int vieAAjouter) throws MontantVieException {
        if (vieAAjouter < 0 || vieAAjouter > 20) {
            throw new MontantVieException();
        }
        if ((this.getVie() + vieAAjouter) > 20) {
            throw new MontantVieException();
        }
        // Sinon
        this.setVie(this.getVie() + vieAAjouter);
    }
    public void enleverPointDeVie(int vieAEnlever) throws MontantVieException {
        if (vieAEnlever < 0 || vieAEnlever > 20) {
            throw new MontantVieException();
        }
        if (this.getVie() < vieAEnlever) {
            throw new MontantVieException();
        }
        // Sinon
        this.setVie(this.getVie() - vieAEnlever);
    }
    public void taperJoueur(Joueur cible, ExpertDegats expertDegats) throws Exception {
        if (expertDegats == null) {
            throw new CORVideException();
        }
        if (cible.equals(this.cible)) {
            int degatsInfliges = expertDegats.expertiserDegats(this.main);
            cible.enleverPointDeVie(degatsInfliges);
        } else {
            throw new CibleHorsPorteeException();
        }
    }
    public Monde getMondeApparition() {
        return mondeApparition;
    }
    public void setMondeApparition(Monde mondeApparition) {
        this.mondeApparition = mondeApparition;
    }
}
