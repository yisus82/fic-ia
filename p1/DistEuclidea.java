import java.lang.Math;

public class DistEuclidea implements Heuristica
{
  public double calcular(Nodo nodo, Posicion meta)
  {
    Posicion pos = nodo.getHabitacion().getRobot().getPosicion();    
    return (Math.sqrt(Math.pow(pos.x - meta.x, 2) + Math.pow(pos.y - meta.y, 2)));
  }
  
  public String toString()
  {
    return "Distancia Euclidea";
  }
}
