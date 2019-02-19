// Heuristica para usar con el algoritmo A*
public interface Heuristica
{
  public double calcular(Nodo nodo, Posicion meta);
}
