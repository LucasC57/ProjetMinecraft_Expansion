package Jeu.Item;

public class EpeeBois implements Item {
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EpeeBois that = (EpeeBois) o;
        return getClass().getSimpleName().equals(o.getClass().getSimpleName());
    }
}
