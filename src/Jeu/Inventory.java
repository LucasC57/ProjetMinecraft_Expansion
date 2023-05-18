package Jeu;

import Jeu.Item.Item;
import Exception.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * La classe Inventory représente le principe d'inventaire d'un joueur.
 */
public class Inventory {
    /**
     * La liste des objets dans l'inventaire.
     */
    private ArrayList<Objets> listItems = new ArrayList<Objets>();
    /**
     * Constructeur de la classe Inventory.
     * @param listItems La liste des items dans l'inventaire.
     */
    public Inventory(ArrayList<Objets> listItems) {
        setListItems(listItems);
    }
    /**
     * Deuxième constructeur par défaut de la classe Inventory.
     */
    public Inventory() {
        setListItems(listItems);
    }
    /**
     * Setter concernant la liste des items dans l'inventaire.
     * @param listItems La liste des items.
     */
    public void setListItems(ArrayList<Objets> listItems) {
        // On accepte un inventaire vide
        this.listItems = listItems;
    }
    /**
     * Getter concernant la liste des items dans l'inventaire.
     * @return La liste des items.
     */
    public ArrayList<Objets> getListItems() {
        return listItems;
    }
    /**
     * Procédure qui permet d'ajouter un objet dans l'inventaire.
     * @param obj L'objet à ajouter.
     */
    public void addInventory(Objets obj) {
        this.listItems.add(obj);
    }
    /**
     * Fonction qui renvoit l'item dans l'inventaire indexé par l'indice i.
     * @param i L'indice de l'objet à avoir.
     * @return L'objet en question.
     * @throws InventoryException Si la liste des items est vide.
     */
    public Objets get(int i) throws InventoryException {
        if (this.getListItems() == null) {
            throw new InventoryException();
        }
        return this.listItems.get(i);
    }
    /**
     * Procédure de suppression d'un objet dans l'inventaire indexé par l'indice i.
     * @param i L'indice de l'objet en question.
     * @throws InventoryException Si la liste des items est vide.
     */
    public void removeItem(int i) throws InventoryException {
        if (this.getListItems() == null) {
            throw new InventoryException();
        }
        this.listItems.remove(i);
    }
    /**
     * Getter concernant la taille de l'inventaire.
     * @return La taille de l'inventaire.
     */
    public int getTaille() {
        return this.getListItems().size();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(listItems, inventory.listItems);
    }
    @Override
    public String toString() {
        return "Inventory{" +
                "listItems=" + listItems +
                '}';
    }
}
