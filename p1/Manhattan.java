import java.lang.Math;

public class Manhattan implements Heuristica
{
  public double calcular(Nodo nodo, Posicion meta)
  {
    Posicion pos = nodo.getHabitacion().getRobot().getPosicion();    
    return (Math.abs(pos.x - meta.x) + Math.abs(pos.y - meta.y));
  }
  
  public String toString()
  {
    return "Distancia Manhattan";
  }
}
