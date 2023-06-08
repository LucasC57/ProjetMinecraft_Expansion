package Jeu.Item.Nourriture;

import Jeu.Item.Charbon;

public class Pomme implements Nourriture {
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pomme that = (Pomme) o;
        return getClass().getSimpleName().equals(o.getClass().getSimpleName());
    }
}
