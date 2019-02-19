import java.util.*;

public class Habitacion
{
    private int dimension;    
    private Robot robot = null;
    private Posicion posMontaje = null;
    public Casilla tablero [][];
    public List listaPiezas = new ArrayList();
    public List listaEnchufes = new ArrayList();
    public List listaObstaculos = new ArrayList();
    
    public int getDimension()
    {
      return dimension;
    }
    
    public Posicion getPosMontaje()
    {
      return posMontaje;
    }
    
    public void setPosMontaje(Posicion posMontaje)
    {
      this.posMontaje = posMontaje;
    }
    
    public Casilla getCasilla(int x, int y)
    {
      return tablero[x][y];
    }
    
    public void addEnchufe(int x, int y)
    {
      listaEnchufes.add(new Posicion(x,y));
      tablero[x][y].setEnchufe(true);
    }
    
    public boolean hayEnchufe(int x, int y)
    {
      return (tablero[x][y].hayEnchufe());
    }
    
    public void addObstaculo(int x, int y)
    {
      listaObstaculos.add(new Posicion(x,y));
      tablero[x][y].setObstaculo(true);
    }
    
    public void addPieza(Pieza pieza)
    {
      int x, y;
      
      x = pieza.getPosicion().x;
      y = pieza.getPosicion().y;
    
      listaPiezas.add(pieza.getPosicion());
      tablero[x][y].setPieza(pieza);
    }
    
    public void quitarPieza(int x, int y)
    {
      Posicion pos;
      for (int i = 0; i < listaPiezas.size(); i++)
      {
        pos = ((Posicion)listaPiezas.get(i));
        if ((pos.x == x) && (pos.y == y))
        {
          listaPiezas.remove(i);
          break;
        }
      }
      tablero[x][y].setPieza(null);
    }
    
    public boolean hayPieza(int x, int y)
    {
      return (tablero[x][y].hayPieza());
    }
    
    public Pieza getPieza(Posicion pos)
    {
      int x, y;
      
      x = pos.x; y = pos.y;
      return (tablero[x][y].getPieza());
    }
    
    public void quitarObstaculo(int x, int y)
    {
      Posicion pos;
      for (int i = 0; i < listaObstaculos.size(); i++)
      {
        pos = ((Posicion)listaObstaculos.get(i));
        if ((pos.x == x) && (pos.y == y))
        {
          listaObstaculos.remove(i);
          break;
        }
      }
      tablero[x][y].setObstaculo(false);
    }
    
    public boolean hayObstaculo(int x, int y)
    {
      return (tablero[x][y].hayObstaculo());
    }

    public Habitacion(int dimension, Robot robot, Posicion posMontaje)
    {
        this.dimension = dimension;
        this.robot = robot.clonar();
        this.posMontaje = posMontaje.clonar();
        tablero = new Casilla[dimension][dimension];
        for (int i = 0; i < dimension; i++)
        {
          for (int j = 0; j < dimension; j++)
          {
            tablero[i][j] = new Casilla(new Posicion(i,j));
          }
        }
    }
    
    public Habitacion clonar()
    {
      Habitacion copia = new Habitacion(dimension, robot.clonar(), posMontaje);
      //copia.getRobot().setHabitacion(copia);
      
      for (int i = 0; i < dimension; i++)
      {
        for (int j = 0; j < dimension; j++)
          copia.tablero[i][j] = this.getCasilla(i,j).clonar();
      }
      
      for (int i = 0; i < listaEnchufes.size(); i++)
      {
        List lEnchufes = copia.listaEnchufes;
        lEnchufes.add(((Posicion)this.listaEnchufes.get(i)).clonar());
      }
      
      for (int i = 0; i < listaPiezas.size(); i++)
      {
        List lPiezas = copia.listaPiezas;
        lPiezas.add(((Posicion)this.listaPiezas.get(i)).clonar());
      }
        
      return copia;
    }
    
    public Robot getRobot()
    {
      return robot;
    }
}