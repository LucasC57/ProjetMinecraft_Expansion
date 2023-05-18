package Jeu.Item;

public class Charbon implements Item {
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Charbon that = (Charbon) o;
        return getClass().getSimpleName().equals(o.getClass().getSimpleName());
    }
}
