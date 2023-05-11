package Jeu;

import Jeu.Bloc.Bloc;
import Jeu.Item.Item;
import Exception.*;

import java.util.ArrayList;
import java.util.Objects;

public class Case {
    private boolean presence_tete = false;
    private boolean presence_pieds = false;
    private Coord position;
    private Bloc contenu;
    ArrayList<Objets> items_au_sol = new ArrayList<Objets>();
    private Monde monde;

    public Case(Bloc contenu, Coord coordCase, Monde monde) throws BlocInconnuException, MondeException {
        setCoordCase(coordCase);
        // Pour set le contenu, donc le bloc
        setContenu(contenu);
        setMonde(monde);
    }
    public void setContenu(Bloc contenu) throws BlocInconnuException {
        if (contenu == null) {
            throw new BlocInconnuException();
        }
        /*
         * Il va falloir utiliser ici une COR pour tous les blocs différents
         */
        this.contenu = contenu;
    }
    public void setMonde(Monde monde) throws MondeException {
        if (monde == null) {
            throw new MondeException();
        }
        this.monde = monde;
    }
    public void setPresence_tete(boolean presence_tete) {
        this.presence_tete = presence_tete;
    }
    public void setPresence_pieds(boolean presence_pieds) {
        this.presence_pieds = presence_pieds;
    }
    private void setCoordCase(Coord coordCase) {
        if (coordCase == null) {
            throw new IllegalArgumentException("Les coordonnées de la case sont incorrect !");
        }
        this.position = coordCase;
    }
    public Coord getCoord() {
        return this.position;
    }

    public Bloc getContenu() {
        return contenu;
    }
    public ArrayList<Objets> getItems_au_sol() {
        return items_au_sol;
    }
    public void setItems_au_sol(ArrayList<Objets> items_au_sol) throws BlocNonFluideException, BlocFluideException {
        // Il faut gérer le fait que l'on puisse seulement mettre des objets au sol que si c'est un bloc fluide
        if (this.getContenu().isFluidite()) {
            Case[][] listCases = monde.getTab_monde();
            if (listCases[this.position.getY()][this.position.getX()+1].getContenu().isFluidite()) {
                System.out.println(listCases[this.position.getY()][this.position.getX()+1].getCoord());
                throw new BlocFluideException();
            }
            this.items_au_sol = items_au_sol;
        } else {
            throw new BlocNonFluideException();
        }
    }
    public void addItemsAuSol(Objets obj) throws ObjetInexistantException, BlocFluideException, BlocNonFluideException {
        // On part du principe que c'est inutile de rajouter un objet vide et/ou inexistant au sol
        if (obj == null) {
            throw new ObjetInexistantException();
        }
        if (this.getContenu().isFluidite()) {
            Case[][] listCases = monde.getTab_monde();
            this.items_au_sol.add(obj);
        } else {
            throw new BlocNonFluideException();
        }
    }
    public Objets get(int i) throws ListObjetsInexistantException {
        if (this.getItems_au_sol() == null) {
            throw new ListObjetsInexistantException();
        }
        return this.items_au_sol.get(i);
    }
    public void removeObjets(int i) throws ListObjetsInexistantException {
        if (this.getItems_au_sol() == null) {
            throw new ListObjetsInexistantException();
        }
        this.items_au_sol.remove(i);
    }
    public int getTaille() throws ListObjetsInexistantException {
        if (this.getItems_au_sol() == null) {
            throw new ListObjetsInexistantException();
        }
        return this.items_au_sol.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return Objects.equals(position, aCase.position);
    }
}
