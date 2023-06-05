package Jeu;

import Jeu.Item.Item;
import Exception.*;

public class Entity {
    // Champs :
    private String type;
    private Item main;
    private Joueur cible;
    private Monde mondeApparition;
    public Entity(String type, Item main, Joueur cible, Monde quelMonde) throws Exception {
        setMondeApparition(quelMonde);
        setType(type);
        setMain(main);
        setCible(cible);
    }
    public String getType() {
        return type;
    }
    public void setType(String type) throws Exception {
        if (getMondeApparition().getExpertTypeEntity() == null) {
            throw new CORVideException();
        }
        boolean existence = getMondeApparition().getExpertTypeEntity().expertiserEntity(type);
        if (!existence) {
            throw new EntityInconnuException();
        }
        this.type = type;
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
    public void setCible(Joueur cible) {
        // Système de voisinage : à faire
        this.cible = cible;
    }
    public Monde getMondeApparition() {
        return mondeApparition;
    }
    public void setMondeApparition(Monde mondeApparition) {
        this.mondeApparition = mondeApparition;
    }
}
