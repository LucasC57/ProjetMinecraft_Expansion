package Jeu.Bloc;

public class BlocPierre implements Bloc {
    private boolean fluidite = false;
    public boolean isFluidite() {
        return fluidite;
    }

    @Override
    public void setFluidite(boolean fluidite) {
        this.fluidite = fluidite;
    }
}
