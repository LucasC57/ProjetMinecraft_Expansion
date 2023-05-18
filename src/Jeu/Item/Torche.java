package Jeu.Item;

public class Torche implements Item {
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Torche that = (Torche) o;
        return getClass().getSimpleName().equals(o.getClass().getSimpleName());
    }
}
