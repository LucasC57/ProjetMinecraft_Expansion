package Jeu.Item;

import Jeu.Bloc.BlocBois;

public class PiochePierre implements Item {
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PiochePierre that = (PiochePierre) o;
        return getClass().getSimpleName().equals(o.getClass().getSimpleName());
    }
}
