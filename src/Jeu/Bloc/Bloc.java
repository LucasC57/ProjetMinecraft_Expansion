package Jeu.Bloc;

import Jeu.Objets;

public interface Bloc extends Objets {
    boolean fluidite = false;
    boolean isFluidite();
    void setFluidite(boolean fluidite);
    boolean equals(Object o);
}
