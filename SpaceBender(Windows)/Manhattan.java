
public class Manhattan implements Heuristica {

    public float calcular(Posicion origen, Posicion destino) {

        return (Math.abs(origen.x - destino.x) + Math.abs(origen.y - destino.y));

    }

}