package Jeu.Item;

import Jeu.Bloc.BlocBois;

public class Baton implements Item{
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Baton that = (Baton) o;
        return getClass().getSimpleName().equals(o.getClass().getSimpleName());
    }
}
