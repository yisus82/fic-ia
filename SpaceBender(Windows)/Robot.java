
import java.util.Vector;

public class Robot {

    private Posicion avance;

    private Posicion posicion;

    private boolean girar;

    private int recarga;

    private int variante;

    private int[][] casillas;
    
    private int algoritmo;

    private int dimension;

    public Robot(Posicion inicio, boolean girar, int recarga, int variante,
            int[][] casillas, int algoritmo)
    {
        posicion = inicio;
        this.girar = girar;
        this.avance = posicion.este();
        this.recarga = recarga;
        this.variante = variante;
        this.casillas = casillas;
        dimension = casillas.length;
        this.algoritmo = algoritmo;
    }

    public void setAvance(Posicion avance) {
        this.avance = avance;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Posicion getAvance() {
        return avance;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public Vector siguientes() {

        Vector sucesores = new Vector();
        Posicion p;
        Nodo nodo;

        p = posicion.norte();
        if ((p.x >= 0) && (p.x < dimension) && (p.y >= 0) && (p.y < dimension)
                && (casillas[p.x][p.y] < 4))
                sucesores.addElement(new Nodo(p, p.norte(), algoritmo));

        p = posicion.sur();
        if ((p.x >= 0) && (p.x < dimension) && (p.y >= 0) && (p.y < dimension)
                && (casillas[p.x][p.y] < 4))
                sucesores.addElement(new Nodo(p, p.sur(), algoritmo));

        p = posicion.este();
        if ((p.x >= 0) && (p.x < dimension) && (p.y >= 0) && (p.y < dimension)
                && (casillas[p.x][p.y] < 4))
                sucesores.addElement(new Nodo(p, p.este(), algoritmo));

        p = posicion.oeste();
        if ((p.x >= 0) && (p.x < dimension) && (p.y >= 0) && (p.y < dimension)
                && (casillas[p.x][p.y] < 4))
                sucesores.addElement(new Nodo(p, p.oeste(), algoritmo));

        if (girar) {
            p = posicion.noroeste();
            if ((p.x >= 0) && (p.x < dimension) && (p.y >= 0)
                    && (p.y < dimension) && (casillas[p.x][p.y] < 4))
                    sucesores.addElement(new Nodo(p, p.noroeste(), algoritmo));

            p = posicion.nordeste();
            if ((p.x >= 0) && (p.x < dimension) && (p.y >= 0)
                    && (p.y < dimension) && (casillas[p.x][p.y] < 4))
                    sucesores.addElement(new Nodo(p, p.nordeste(), algoritmo));
            p = posicion.sudeste();
            if ((p.x >= 0) && (p.x < dimension) && (p.y >= 0)
                    && (p.y < dimension) && (casillas[p.x][p.y] < 4))
                    sucesores.addElement(new Nodo(p, p.sudeste(), algoritmo));
            p = posicion.sudoeste();
            if ((p.x >= 0) && (p.x < dimension) && (p.y >= 0)
                    && (p.y < dimension) && (casillas[p.x][p.y] < 4))
                    sucesores.addElement(new Nodo(p, p.sudoeste(), algoritmo));
        }

        for (int i = 0; i < sucesores.size(); i++) {
            nodo = (Nodo) sucesores.elementAt(i);
            int giros = girosRealizados(nodo.getPosicion());
            nodo.setGiros(giros);
            nodo.setAvances(1);
            int ec = energiaConsumida(giros);
            int er = energiaRecargada(giros);
            nodo.setEnergiaConsumida(ec);
            nodo.setEnergiaRecargada(er);
        }

        return sucesores;
    }

    private int girosRealizados(Posicion destino) {
        int grados = 0;
        Posicion dDestino = avance.distancia(destino);
        Posicion dPosicion = avance.distancia(posicion);

        switch (dDestino.x) {
            case 0: {
                switch (dDestino.y) {
                    case 0: { //(0,0)
                        grados = 0;
                        break;
                    }
                    case 1: { //(0,1)
                        grados = 45;
                        break;
                    }
                    case 2: { //(0,2)

                        if ((dPosicion.x == 1) && (dPosicion.y == 1))
                            grados = 90;
                        else grados = 180;
                        break;
                    }

                }
                break;
            }
            case 1: {
                switch (dDestino.y) {
                    case 0: { //(1,0)
                        grados = 45;
                        break;
                    }
                    case 1: { //(1,1)
                        grados = 90;
                        break;
                    }
                    case 2: { //(1,2)
                        grados = 135;
                        break;
                    }

                }
                break;
            }
            case 2: {
                switch (dDestino.y) {
                    case 0: { //(2,0)
                        if ((dPosicion.x == 1) && (dPosicion.y == 1))
                            grados = 90;
                        else grados = 180;
                        break;
                    }
                    case 1: { //(2,1)
                        grados = 135;
                        break;
                    }
                    case 2: { //(2,2)
                        grados = 180;
                        break;
                    }

                }

                break;
            }
        }

        if (girar) return grados / 45;

        return grados / 90;

    }

    private int energiaConsumida(int giros) {
        return (giros + 1);
    }

    private int energiaRecargada(int giros) {

        if (variante == 2)
            return (giros * recarga);
        if (giros == 0)	return recarga;
        return 0;

    }
}
