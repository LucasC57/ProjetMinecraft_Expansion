package Jeu.Bloc;

public class BlocLierre implements Bloc{
    private boolean fluidite = false;
    @Override
    public boolean isFluidite() {
        return false;
    }

    @Override
    public void setFluidite(boolean fluidite) {
        this.fluidite = fluidite;
    }
}
