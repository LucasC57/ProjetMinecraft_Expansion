package Jeu.Bloc;

import java.util.ArrayList;

public class BlocAir implements Bloc {
    // Champs :
    private boolean fluidite = true;
    public boolean isFluidite() {
        return fluidite;
    }
    public void setFluidite(boolean fluidite) {
        this.fluidite = fluidite;
    }
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BlocAir that = (BlocAir) o;
        return getClass().getSimpleName().equals(o.getClass().getSimpleName());
    }
}

