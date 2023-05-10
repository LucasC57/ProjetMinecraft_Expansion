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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BlocPierre that = (BlocPierre) o;
        return getClass().getSimpleName().equals(o.getClass().getSimpleName());
    }
}
