public class DistanciaMaxima implements Heuristica {

    public float calcular(Posicion origen, Posicion destino) {

        Posicion distancia;

        distancia = origen.distancia(destino);

        return Math.max(distancia.x, distancia.y);

    }

}
