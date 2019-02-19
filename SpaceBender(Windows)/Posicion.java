
import java.awt.Point;

public class Posicion extends Point {

    public Posicion() {

        super();

    }

    public Posicion(int x, int y) {

        super(x, y);

    }

    public Posicion norte() {
        return new Posicion(this.x - 1, this.y);
    }

    public Posicion sur() {
        return new Posicion(this.x + 1, this.y);
    }

    public Posicion este() {
        return new Posicion(this.x, this.y + 1);
    }

    public Posicion oeste() {
        return new Posicion(this.x, this.y - 1);
    }

    public Posicion noroeste() {
        return new Posicion(this.x - 1, this.y - 1);
    }

    public Posicion nordeste() {
        return new Posicion(this.x - 1, this.y + 1);
    }

    public Posicion sudeste() {
        return new Posicion(this.x + 1, this.y + 1);
    }

    public Posicion sudoeste() {
        return new Posicion(this.x + 1, this.y - 1);
    }

    public Posicion distancia(Posicion posicion) {
        int dx, dy;

        dx = Math.abs(this.x - posicion.x);
        dy = Math.abs(this.y - posicion.y);

        return new Posicion(dx, dy);
    }

    public String toString() {
        return ("(" + x + ", " + y + ")");
    }

}