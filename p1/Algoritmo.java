import java.util.*;

public abstract class Algoritmo
{
  protected List camino = new ArrayList();
  protected Posicion origen, meta;
  protected Nodo nodoActual = null;
  
  protected boolean nodoMeta(Nodo nodo)
  {
    boolean esMeta = false;
    if ((nodo.getHabitacion().getRobot().getPosicion().x == meta.x)
        && (nodo.getHabitacion().getRobot().getPosicion().y == meta.y))
      esMeta = true;
    
    return (esMeta);
  }
  
  public Algoritmo(Nodo nodo, Posicion meta)
  {
    this.meta = meta;
    this.nodoActual = nodo;
    this.origen = nodo.getHabitacion().getRobot().getPosicion();
  }
  
  public abstract List aplicar();
}
