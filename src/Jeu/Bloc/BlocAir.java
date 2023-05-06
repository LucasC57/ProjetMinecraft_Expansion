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
}

