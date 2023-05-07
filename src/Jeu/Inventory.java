package Jeu;

import Jeu.Item.Item;
import Exception.*;
import java.util.ArrayList;
import java.util.Objects;

public class Inventory {
    // Champs :
    private ArrayList<Objets> listItems = new ArrayList<Objets>();
    // Constructeur :
    public Inventory(ArrayList<Objets> listItems) {
        setListItems(listItems);
    }
    public void setListItems(ArrayList<Objets> listItems) {
        // On accepte un inventaire vide
        this.listItems = listItems;
    }
    public ArrayList<Objets> getListItems() {
        return listItems;
    }
    // Ajouter un item dans l'inventaire :
    public void addInventory(Objets obj) {
        this.listItems.add(obj);
    }
    // Retourne l'item indexé par i
    public Objets get(int i) throws InventoryException {
        if (this.getListItems() == null) {
            throw new InventoryException();
        }
        return this.listItems.get(i);
    }
    // Supprimer l'item indexé par i
    public void removeItem(int i) throws InventoryException {
        if (this.getListItems() == null) {
            throw new InventoryException();
        }
        this.listItems.remove(i);
    }
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
