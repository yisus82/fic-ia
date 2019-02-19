
import java.util.Vector;

public abstract class Algoritmo {

    protected Vector camino = new Vector();

    protected Posicion origen;

    protected Posicion destino;

    protected Nodo actual = null;

    protected boolean esMeta(Nodo nodo) {

        if (nodo == null) return false;
        if ((nodo.getPosicion()).equals(destino)) return true;
        return false;

    }

    public Algoritmo(Posicion origen, Posicion destino) {

        this.destino = destino;
        this.origen = origen;

    }

    public abstract Vector aplicar();

}