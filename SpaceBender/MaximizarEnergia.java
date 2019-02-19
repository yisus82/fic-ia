public class MaximizarEnergia implements FuncionCoste {

    public float calcular(Nodo anterior, Nodo actual) {
        float g = 0;
        g = anterior.getG();
        g = g
                + costeIndividual(actual.getEnergiaConsumida(), actual
                        .getEnergiaRecargada());
        return g;
    }

    private float costeIndividual(int energiaConsumida, int energiaRecargada) {
        return (float) energiaConsumida + (5 / (energiaRecargada + 1));
    }

}
