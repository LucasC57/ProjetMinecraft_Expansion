package Jeu.Item;

import Jeu.Bloc.BlocBois;

public class PiocheBois implements Item {
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PiocheBois that = (PiocheBois) o;
        return getClass().getSimpleName().equals(o.getClass().getSimpleName());
    }
}
