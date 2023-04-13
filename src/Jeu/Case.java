package Jeu;

import java.util.ArrayList;

public class Case {
    private boolean presence_tete = false;
    private boolean presence_pieds = false;
    private Coord position;
    private Bloc contenu;
    ArrayList<Item> items_au_sol = new ArrayList<Item>();

    public Case(Bloc contenu, Coord coordCase) {
        setCoordCase(coordCase);
        // Pour set le contenu, donc le bloc
        setContenu(contenu);
    }
    private void setContenu(String aCase) {

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
}
