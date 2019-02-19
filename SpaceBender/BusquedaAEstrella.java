
import java.util.Vector;

public class BusquedaAEstrella extends Algoritmo {
    
    public static final int AESTRELLA = 0;

    public static final int ANCHURA = 1;
    
    public static final int COLINAS = 2;
    
    private Vector abiertos;

    private Vector cerrados;

    private Vector camino;

    private FuncionCoste g;

    private Heuristica h;

    private Robot robot;

    private Posicion meta;
    
    private Ventana ventana;

    private int giros = 0;

    private int avances = 0;
    
    private int tipo;

    public BusquedaAEstrella(Heuristica h, FuncionCoste g, Robot robot,
            Posicion meta, Ventana ventana, int tipo)
    {

        super(robot.getPosicion(), meta);
        abiertos = new Vector();
        cerrados = new Vector();
        this.h = h;
        this.g = g;
        this.robot = robot;
        this.meta = meta;
        this.ventana = ventana;
        this.tipo = tipo;

    }

    private Nodo buscarMejorNodo() {
        Nodo mejor, actual;

        mejor = (Nodo) abiertos.elementAt(0);

        for (int i = 1; i < abiertos.size(); i++) {
            actual = (Nodo) abiertos.elementAt(i);

            if (actual.getF() < mejor.getF()) {
                mejor = actual;
            }
        }

        return mejor;
    }

    public void propagarMejora(Nodo viejo) {
        Vector sucesores;
        Nodo hijoActual;
        sucesores = viejo.getSucesores();

        for (int i = 0; i < sucesores.size(); i++) {
            hijoActual = (Nodo) sucesores.elementAt(i);
            if (hijoActual.getPadre().equals(viejo)) {
                hijoActual.setG(g.calcular(viejo, hijoActual));
                hijoActual.setF(hijoActual.getG() + hijoActual.getH());
            } else {
                if (viejo.getG() < (hijoActual.getPadre().getG())) {
                    hijoActual.setPadre(viejo);
                    hijoActual.setG(g.calcular(viejo, hijoActual));
                    hijoActual.setF(hijoActual.getG() + hijoActual.getH());

                }
            }
            if (cerrados.contains(hijoActual)) {
                propagarMejora(hijoActual);
            }
        }

    }

    protected boolean esMeta(Nodo actual) {
        return (actual.getPosicion().equals(meta));
    }

    private Vector calcularCamino(Nodo meta) {

        Vector camino = new Vector();
        Nodo nodo = meta;
        int j = 0;
        camino.addElement(meta);

        while ((nodo.getPadre() != null) && (j < cerrados.size())) {
            camino.add(0, nodo.getPadre());
            nodo = nodo.getPadre();
            j++;
        }

        return camino;

    }

    public Vector aplicar() {

        boolean estaEnAbiertos = false;
        boolean estaEnCerrados = false;
        Nodo mejorNodo;
        Vector siguientesMejorNodo = new Vector();
        Vector siguientes;
        Vector nuevosDescendientes = new Vector();
        
        Nodo nodo = new Nodo(robot.getPosicion(), 0, 0, 0, h.calcular(robot
                .getPosicion(), meta), robot.getAvance(), tipo);
        abiertos.add(nodo);

        if (abiertos.isEmpty()) return null;

        while (!abiertos.isEmpty()) {
            
            if (ventana.verTrazas()) 
                ventana.escribir("Lista de nodos abiertos:\n" + abiertos.toString() + "\n\n");

            mejorNodo = buscarMejorNodo();
            
            camino = calcularCamino(mejorNodo);
            
            if ((ventana.verTrazas()) && (tipo != ANCHURA))
                ventana.escribir("Mejor nodo: "+ mejorNodo + 
                        "\n\nCamino actual hasta el mejor nodo:\n" 
                        + camino.toString() + "\n\n");

            abiertos.removeElement(mejorNodo);

            cerrados.addElement(mejorNodo);

            robot.setAvance(mejorNodo.getAvance());

            robot.setPosicion(mejorNodo.getPosicion());

            if (esMeta(mejorNodo)) {
                Vector camino = calcularCamino((Nodo) cerrados
                        .elementAt(cerrados.size() - 1));

                for (int i = 0; i < camino.size(); i++) {
                    giros += ((Nodo) camino.elementAt(i)).getGiros();
                    avances += ((Nodo) camino.elementAt(i)).getAvances();
                }

                ventana.escribir("Solución encontrada");
                ventana.escribir("Nodos generados: " + (cerrados.size() + abiertos.size()));
                return camino;
            }

            siguientes = robot.siguientes();

            if (siguientes == null) continue;

            Nodo sucesor;
            Nodo viejo;
            for (int i = 0; i < siguientes.size(); i++) {
                estaEnAbiertos = false;
                
        		estaEnCerrados = false;
        		
                sucesor = ((Nodo) siguientes.elementAt(i));

                sucesor.setPadre(mejorNodo);

                sucesor.setG(g.calcular(mejorNodo, sucesor));

                for (int j = 0; j < abiertos.size(); j++) {
                    if (((Nodo) abiertos.elementAt(j)).getPosicion().equals(sucesor.getPosicion())) {
                        estaEnAbiertos = true;
                        viejo = ((Nodo) abiertos.elementAt(j));
                        siguientesMejorNodo.addElement(viejo);
                        if (viejo.getG() > sucesor.getG()) {
                            viejo.setPadre(mejorNodo);
                            viejo.setGiros(sucesor.getGiros());
                            viejo.setEnergiaConsumida(sucesor
                                    .getEnergiaConsumida());
                            viejo.setG(sucesor.getG());
                            viejo.setF(sucesor.getG() + viejo.getH());
                            viejo.setAvance(sucesor.getAvance());
                        }

                        break;
                    }
                }

                for (int j = 0; j < cerrados.size(); j++) {
                    if (((Nodo) cerrados.elementAt(j)).getPosicion().equals(sucesor.getPosicion())) {
                        estaEnCerrados = true;
                        viejo = ((Nodo) cerrados.elementAt(j));
                        siguientesMejorNodo.addElement(viejo);
                        if (viejo.getG() > sucesor.getG()) {
                            viejo.setPadre(mejorNodo);
                            viejo.setGiros(sucesor.getGiros());
                            viejo.setEnergiaConsumida(sucesor
                                    .getEnergiaConsumida());
                            viejo.setG(sucesor.getG());
                            viejo.setF(sucesor.getG() + viejo.getH());
                            viejo.setAvance(sucesor.getAvance());
                            propagarMejora(viejo);
                        }

                        break;
                    }
                }

                if (!estaEnAbiertos && !estaEnCerrados) {
                    nuevosDescendientes.addElement(sucesor);
                    sucesor.setH(h.calcular(sucesor.getPosicion(), meta));
                    sucesor.setF(sucesor.getG() + sucesor.getH());
                    abiertos.addElement(sucesor);
                    siguientesMejorNodo.addElement(sucesor);
                }
            }
            
            if ((ventana.verTrazas()) && (tipo != ANCHURA))
                ventana.escribir("\nNuevos descendientes generados:\n" 
                        + nuevosDescendientes.toString() + "\n\n");

            mejorNodo.setSucesores(siguientesMejorNodo);

            siguientes.clear();
            siguientesMejorNodo.clear();
            nuevosDescendientes.clear();
        }

        ventana.escribir("Solución no encontrada");
        System.exit(-1);
        return null;

    }
}
