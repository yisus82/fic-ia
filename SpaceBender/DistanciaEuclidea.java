
public class DistanciaEuclidea implements Heuristica {

    public float calcular(Posicion origen, Posicion destino) {
        return (float) (Math.sqrt(Math.pow(origen.x - destino.x, 2)
                + Math.pow(origen.y - destino.y, 2)));
    }

}