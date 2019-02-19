public class BusquedaAnchura2 extends BusquedaAEstrella
{
  public BusquedaAnchura2(Nodo nodo, Posicion meta)
  {
    super (nodo, meta, null, TIEMPO);
  }
  
  protected double G(Nodo nodo)
  {
    return 0;
  }
  
  protected double H(Nodo nodo)
  {
    return 0;
  }
  
  public String toString()
  {
    return ("Busqueda en Anchura (a partir de A*)");
  }
}
