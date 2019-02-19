
public class Robot
{    
    private Posicion posicion = null;
    private int max_bateria = 100; // Maximo numero de unidades de energia
    private int bateria = 100; // bateria actual
    private Pieza pieza = null; // Pieza llevada por el robot
        
    public Robot clonar()
    {      
      Robot copia = new Robot(this.posicion.x, this.posicion.y,
                              this.bateria, this.max_bateria);
      if (pieza != null)
        copia.pieza = this.pieza.clonar();
      return copia;
    }
    
    public int getCarga()
    {
      if (pieza != null)
        return pieza.getPeso();
      else
        return 0;  
    }

    public boolean bateriaDescargada()
    {
      return (bateria == 0);
    }
    
    public void cargarBateria()
    {
      bateria = max_bateria;
    }
    
    public void cogerPieza(Habitacion habitacion)
    {
      // Si hay pieza en la casilla actual
      if (habitacion.hayPieza(posicion.x,posicion.y))
      {
        this.pieza = habitacion.getPieza(posicion);
        habitacion.quitarPieza(posicion.x,posicion.y);
      }
      else
        System.out.println("No hay ninguna pieza en la casilla actual");
    }
    
    public void dejarPieza(Habitacion habitacion)
    {
      this.pieza = null;
    }
    
    public Pieza getPieza()
    {
      return pieza;
    }
    
    public int getMaxBateria()
    {
      return this.max_bateria;
    }    
    
    public void setMaxBateria(int max_bateria)
    {
      this.max_bateria = max_bateria;
    }
    
    public Posicion getPosicion()
    {
        return this.posicion;
    }

    public void setPosicion(Posicion posicion)
    {
        this.posicion = posicion;
    }
    
    public int getBateria()
    {
        return this.bateria;
    }

    public void setBateria(int bateria)
    {
        this.bateria = bateria;
    }
    
    public Robot(Posicion posicion, int bateria, int max_bateria)
    {
      this.posicion = posicion;
      this.max_bateria = max_bateria;
      this.bateria = bateria;
      this.pieza = null;
    }
    
    public Robot(Posicion posicion, int max_bateria)
    {
      this(posicion, max_bateria, max_bateria);
    }
    
    public Robot(int posX, int posY, int bateria, int max_bateria)
    {
      this(new Posicion(posX, posY), bateria, max_bateria);
    }
    
    public Robot(int posX, int posY, int max_bateria)
    {
      this(new Posicion(posX, posY), max_bateria, max_bateria);
    }
}