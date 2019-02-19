public class DistanciaMedia implements Heuristica {

    public float calcular(Posicion origen, Posicion destino) {

        Posicion distancia;

        distancia = origen.distancia(destino);

        return ((distancia.x + distancia.y) / 2);

    }

}
