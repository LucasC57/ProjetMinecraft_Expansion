package Jeu.Item.Nourriture;

public class Potion_SOIN implements Potion {
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Potion_SOIN that = (Potion_SOIN) o;
        return getClass().getSimpleName().equals(o.getClass().getSimpleName());
    }
}
