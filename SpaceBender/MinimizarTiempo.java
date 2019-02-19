public class MinimizarTiempo implements FuncionCoste {

    public float calcular(Nodo anterior, Nodo actual) {

        return anterior.getG() + 1;

    }

}
