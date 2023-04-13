package Jeu;
import Exception.*;

public class Coord {
    private int x;
    private int y;
    public Coord(int x, int y) throws CoordException {
        setX(x);
        setY(y);
    }
    public int getX() {
        return x;
    }
    public void setX(int x) throws CoordException {
        if (x < 0) {
            throw new CoordException();
        }
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) throws CoordException {
        if (y < 0) {
            throw new CoordException();
        }
        this.y = y;
    }
    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}