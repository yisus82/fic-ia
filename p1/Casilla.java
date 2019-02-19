public class Casilla
{
  private Pieza pieza = null;
  private boolean obstaculo = false;
  private boolean enchufe = false;
  private Posicion posicion = null;
  
  public Casilla clonar()
  {
    Casilla copia;
    if (pieza != null)
      copia = new Casilla(posicion.clonar(), obstaculo, enchufe, pieza.clonar());
    else  
      copia = new Casilla(posicion.clonar(), obstaculo, enchufe, null);
    return copia;
  }
  
  public boolean hayPieza()
  {
    return (pieza != null);
  }
  
  public boolean hayObstaculo()
  {
    return obstaculo;
  }
  
  public Pieza getPieza()
  {
    return pieza;
  }
  
  public void setPieza(Pieza pieza)
  {
    this.pieza = pieza;
  }
  
  public boolean hayEnchufe()
  {
    return enchufe;
  }
  
  public void setEnchufe(boolean enchufe)
  {
    this.enchufe = enchufe;
  }
  
  public void setObstaculo(boolean obstaculo)
  {
    this.obstaculo = obstaculo;
  }
  
  public Posicion getPosicion()
  {
    return posicion;
  }
  
  public void setPosicion(Posicion posicion)
  {  
    this.posicion = posicion;
  }
  
  public Casilla(Posicion posicion, boolean obstaculo, boolean enchufe, Pieza pieza)
  {    
    this.obstaculo = obstaculo;
    this.enchufe = enchufe;
    this.posicion = posicion;
    this.pieza = pieza;
  }
  
  public Casilla(Posicion posicion, boolean obstaculo, boolean enchufe)
  {    
    this(posicion, obstaculo, enchufe, null);
  }
  
  public Casilla(Posicion posicion, boolean obstaculo)
  {    
    this(posicion, obstaculo, false, null);
  }
  
  public Casilla(Posicion posicion)
  {    
    this(posicion, false, false, null);
  }
}