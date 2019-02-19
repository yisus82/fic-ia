public class BusquedaHillClimbing2 extends BusquedaAEstrella
{
  public BusquedaHillClimbing2(Nodo nodo, Posicion meta, Heuristica heuristica)
  {
    super (nodo, meta, heuristica, TIEMPO);
  }
  
  protected double G(Nodo nodo)
  {
    return 0;
  }
  
  public String toString()
  {
    return ("Busqueda por Hill Climbing (a partir de A*)");
  }
}
