package Jeu.Item;

public class EpeePierre implements Item {
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EpeePierre that = (EpeePierre) o;
        return getClass().getSimpleName().equals(o.getClass().getSimpleName());
    }
}
