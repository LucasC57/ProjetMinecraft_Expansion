package Jeu.Bloc;

public class BlocMineraiCharbon implements Bloc {
    private boolean fluidite = false;

    @Override
    public boolean isFluidite() {
        return false;
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
        BlocMineraiCharbon that = (BlocMineraiCharbon) o;
        return getClass().getSimpleName().equals(o.getClass().getSimpleName());
    }
}
