package Jeu;

import Jeu.Item.Item;
import Exception.*;
import java.util.ArrayList;
import java.util.Objects;

public class Inventory {
    // Champs :
    private ArrayList<Item> listItems = new ArrayList<Item>();
    // Constructeur :
    public Inventory(ArrayList<Item> listItems) {
        setListItems(listItems);
    }
    public void setListItems(ArrayList<Item> listItems) {
        // On accepte un inventaire vide
        this.listItems = listItems;
    }
    public ArrayList<Item> getListItems() {
        return listItems;
    }
    // Ajouter un item dans l'inventaire :
    public void addInventory(Item item) {
        this.listItems.add(item);
    }
    // Retourne l'item indexé par i
    public Item get(int i) throws InventoryException {
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
