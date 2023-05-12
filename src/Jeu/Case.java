package Jeu;

import Jeu.Bloc.Bloc;
import Jeu.Item.Item;
import Exception.*;
import java.util.ArrayList;
import java.util.Objects;
/**
 * La classe Case représente chaque case dans le tab_monde de chaque monde.
 * Cette classe utilise dans quelques fonctions le principe de fluidité d'un Bloc.
 */
public class Case {
    /**
     * Booléen qui indique la présence de la tête d'un joueur dans la case.
     */
    private boolean presence_tete = false;
    /**
     * Booléen qui indique la présence des pieds d'un joueur dans la case.
     */
    private boolean presence_pieds = false;
    /**
     * Les coordonnées de la case.
     */
    private Coord position;
    /**
     * Le contenu de la case.
     */
    private Bloc contenu;
    /**
     * Les items qui sont au sol dans la case.
     */
    ArrayList<Objets> items_au_sol = new ArrayList<Objets>();
    /**
     * Le monde là où se trouve la case.
     */
    private Monde monde;

    /**
     * Constructeur de la classe Case.
     * @param contenu Le bloc contenu dans la case.
     * @param coordCase Les coordoonées de la case.
     * @param monde Le monde là où se trouve la case.
     * @throws BlocInconnuException
     * @throws MondeException
     * @throws CoordException
     */
    public Case(Bloc contenu, Coord coordCase, Monde monde) throws BlocInconnuException, MondeException, CoordException {
        setCoordCase(coordCase);
        // Pour set le contenu, donc le bloc
        setContenu(contenu);
        setMonde(monde);
    }
    /**
     * Setter concernant le contenu de la case.
     * @param contenu Le bloc contenu dans la case.
     * @throws BlocInconnuException Si le bloc n'est pas initialisé ou qu'il n'existe pas.
     */
    public void setContenu(Bloc contenu) throws BlocInconnuException {
        if (contenu == null) {
            throw new BlocInconnuException();
        }
        this.contenu = contenu;
    }
    /**
     * Setter pour le monde là où se trouve la case.
     * @param monde le monde là où se trouve la case.
     * @throws MondeException Si le monde n'est pas intialisé ou qu'il n'existe pas.
     */
    public void setMonde(Monde monde) throws MondeException {
        if (monde == null) {
            throw new MondeException();
        }
        this.monde = monde;
    }
    /**
     * Setter concernant la présence de la tête dans la case.
     * @param presence_tete Le booléen concernant la présence de la tête dans la case.
     */
    public void setPresence_tete(boolean presence_tete) {
        this.presence_tete = presence_tete;
    }
    /**
     * Setter concernant la présence des pieds dans la case.
     * @param presence_pieds Le booléen concernant la présence des pieds dans la case.
     */
    public void setPresence_pieds(boolean presence_pieds) {
        this.presence_pieds = presence_pieds;
    }
    /**
     * Setter concernant les coordonnées de la case.
     * @param coordCase Les coordoonées de la case.
     * @throws CoordException Si les coordonnées ne sont pas intialisées ou qu'elles n'existent pas.
     */
    private void setCoordCase(Coord coordCase) throws CoordException {
        if (coordCase == null) {
            throw new CoordException();
        }
        this.position = coordCase;
    }
    /**
     * Getter concernant les coordonnées de la case.
     * @return Les coordonnées de la case.
     */
    public Coord getCoord() {
        return this.position;
    }
    /**
     * Getter concernant le bloc contenu dans la case.
     * @return Le bloc contenu dans la case.
     */
    public Bloc getContenu() {
        return contenu;
    }
    /**
     * Getter pour les items au sol.
     * @return Les items au sol.
     */
    public ArrayList<Objets> getItems_au_sol() {
        return items_au_sol;
    }
    /**
     * Setter concernant les items au sol.
     * @param items_au_sol Les items au sol.
     * @throws BlocNonFluideException Si le bloc contenu dans la case n'est pas un bloc fluide.
     * @throws BlocFluideException Si le contenu dans la case en dessous de la case (this) est un bloc fluide.
     */
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
    /**
     * Procédure d'ajout d'un objet dans l'arraylist des items au sol.
     * @param obj L'objet que l'on souhaite ajouter.
     * @throws ObjetInexistantException Si l'objet donné n'est pas initialisé ou qu'il n'existe pas.
     * @throws BlocFluideException Si le bloc contenu dans la case en dessous de this est un bloc fluide.
     * @throws BlocNonFluideException Si le bloc contenu dans this n'est pas un bloc fluide.
     */
    public void addItemsAuSol(Objets obj) throws ObjetInexistantException, BlocFluideException, BlocNonFluideException {
        // On part du principe que c'est inutile de rajouter un objet vide et/ou inexistant au sol
        if (obj == null) {
            throw new ObjetInexistantException();
        }
        if (this.getContenu().isFluidite()) {
            Case[][] listCases = monde.getTab_monde();
            if (listCases[position.getY()][position.getX()+1].getContenu().isFluidite()) {
                throw new BlocFluideException();
            }
            this.items_au_sol.add(obj);
        } else {
            throw new BlocNonFluideException();
        }
    }
    /**
     * Getter concernant les objets contenu dans l'arraylist des items au sol.
     * @param i L'indice de l'objet que l'on souhaite retourner.
     * @return l'objet que l'on souhaite retourner qui est contenu dans this.
     * @throws ListObjetsInexistantException Si la liste des items au sol n'est pas intialisé ou qu'elle n'existe pas.
     */
    public Objets get(int i) throws ListObjetsInexistantException {
        if (this.getItems_au_sol() == null) {
            throw new ListObjetsInexistantException();
        }
        return this.items_au_sol.get(i);
    }
    /**
     * Procédure concernant la suppression d'un objet dans la liste des items au sol.
     * @param i L'indice de l'objet que l'on souhaite supprimer dans la liste des items au sol.
     * @throws ListObjetsInexistantException Si la liste des items au sol n'est pas intialisé ou qu'elle n'existe pas.
     */
    public void removeObjets(int i) throws ListObjetsInexistantException {
        if (this.getItems_au_sol() == null) {
            throw new ListObjetsInexistantException();
        }
        this.items_au_sol.remove(i);
    }
    /**
     * Fonction pour récupérer la taille de la liste des items au sol de this.
     * @return La taille de la liste des items au sol de this.
     * @throws ListObjetsInexistantException Si la liste des items au sol n'est pas intialisé ou qu'elle n'existe pas.
     */
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
