package Jeu.Item;

import Jeu.Bloc.BlocBois;

public class MainVide implements Item {
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MainVide that = (MainVide) o;
        return getClass().getSimpleName().equals(o.getClass().getSimpleName());
    }
}
