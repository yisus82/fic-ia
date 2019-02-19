public class Objetivo
{
  public static final int ENCHUFE = 0;
  public static final int PIEZA = 1;
  public static final int MONTAJE = 2;

  private Posicion origen, destino = null; // La posicion con el objetivo
  private String descripcion = null;
  private int tipoObj;
  
  public Objetivo clonar()
  {
    return (new Objetivo(origen.clonar(), destino.clonar(), tipoObj, descripcion));
  }
  
  public Objetivo(Posicion origen, Posicion destino, int tipoObj, String str)
  {
    this.origen = origen;
    this.destino = destino;
    this.tipoObj = tipoObj;
    this.descripcion = str;
  }
  
  public int getTipoObjetivo()
  {
    return this.tipoObj;
  }
  
  public void setTipoObjetivo(int tipo)
  {
    this.tipoObj = tipo;
  }
  
  public String getDescripcion()
  {
    return descripcion;
  }
  
  public Posicion getOrigen()
  {
    return origen;
  }
  
  public Posicion getDestino()
  {
    return destino;
  }
}
