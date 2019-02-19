public class Obstaculo {

    private Posicion posicion;

    private int tipo;

    public Obstaculo(Posicion posicion, int tipo) {

        this.posicion = posicion;
        this.tipo = tipo;

    }

    public Posicion getPosicion() {

        return posicion;

    }

    public int getTipo() {

        return tipo;

    }

}