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
    ArrayList<Item> items_au_sol = new ArrayList<Item>();

    public Case(Bloc contenu, Coord coordCase) throws BlocInconnuException {
        setCoordCase(coordCase);
        // Pour set le contenu, donc le bloc
        setContenu(contenu);
    }
    private void setContenu(Bloc contenu) throws BlocInconnuException {
        if (contenu == null) {
            throw new BlocInconnuException();
        }
        /*
         * Il va falloir utiliser ici une COR pour tous les blocs différents
         */
        this.contenu = contenu;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return Objects.equals(position, aCase.position);
    }
}
