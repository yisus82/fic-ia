public class Pieza
{
    public static final int CABEZA = 1;
    public static final int CUERPO = 2;
    public static final int BRAZOS = 3;
    public static final int PIERNAS = 4;
    public static final int BATERIAS = 5;
    
    private int peso = 0;
    private String nombre = null;
    private Posicion posicion;
    int piezaID; // Para representar en el tablero
    
    public int getID()
    {
      return piezaID;
    }    
    
    public Posicion getPosicion()
    {
      return posicion;
    }
    
    public void setPosicion(Posicion posicion)
    {
      this.posicion = posicion;
    }
    
    public int getPeso()
    {
      return peso;
    }
    
    public String getNombre()
    {
      return nombre;
    }
    
    public String toString()
    {
      return (nombre);
    }
    
    public Pieza clonar()
    {
      return (new Pieza(posicion.clonar(), piezaID));
    }

    public Pieza(Posicion posicion, int piezaID)
    {        
        this.posicion = posicion;
        this.piezaID = piezaID;
        
        switch (piezaID)
        {
          case CABEZA:
            this.nombre = "Cabeza";
            this.peso = 5;
            break;
          case CUERPO:
            this.nombre = "Cuerpo";
            this.peso = 10;
            break;
          case BRAZOS:
            this.nombre = "Brazos";
            this.peso = 6;
            break;
          case PIERNAS:
            this.nombre = "Piernas";
            this.peso = 6;
            break;
          case BATERIAS:
            this.nombre = "Baterias";
            this.peso = 1;
            break;
        }
    }
}