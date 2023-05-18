package Jeu;
import Exception.*;

import java.util.Objects;

/**
 * La classe Coord est la représentation du principe de coordonnées dans ce projet.
 */
public class Coord {
    /**
     * La valeur de x en coordonnée.
     */
    private int x;
    /**
     * La valeur de y en coordonnée.
     */
    private int y;
    /**
     * Le constructeur de la classe Coord.
     * @param x La valeur de x.
     * @param y La valeur de y.
     * @throws CoordException
     */
    public Coord(int x, int y) throws CoordException {
        setX(x);
        setY(y);
    }
    /**
     * Getter concernant la valeur x.
     * @return La valeur de x.
     */
    public int getX() {
        return x;
    }
    /**
     * Setter concernant la valeur de x.
     * @param x La valeur de x.
     * @throws CoordException Si x est inférieur à 0 donc négative.
     */
    public void setX(int x) throws CoordException {
        if (x < 0) {
            throw new CoordException();
        }
        this.x = x;
    }
    /**
     * Getter concernant la valeur y.
     * @return La valeur de y.
     */
    public int getY() {
        return y;
    }
    /**
     * Setter concernant la valeur de y.
     * @param y La valeur de y.
     * @throws CoordException Si la valeur de y est inférieur à 0 donc négative.
     */
    public void setY(int y) throws CoordException {
        if (y < 0) {
            throw new CoordException();
        }
        this.y = y;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return x == coord.x && y == coord.y;
    }
    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
