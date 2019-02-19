public class Mover extends Operador
{
  private Robot robot;
  private Posicion posicion;
  private Habitacion habitacion;
  private int dimension, direccion;
  
  public Mover(Nodo nodo, int direccion)
  {
    this.nodo = nodo;
    this.direccion = direccion;
    posicion = nodo.getHabitacion().getRobot().getPosicion();
    habitacion = nodo.getHabitacion();
    dimension = habitacion.getDimension();  
  }
  
  public boolean esRelevante()
  {
    switch (direccion)
    {
      case 0: // Norte
        if (posicion.y > 0)
          if (!(habitacion.hayObstaculo(posicion.x, posicion.y-1)))
            return true;        
        return false;
      case 1: // Sur
        if (posicion.y < (dimension-1)) 
          if (!(habitacion.hayObstaculo(posicion.x, posicion.y+1)))
            return true;
        return false;
      case 2: // Este
        if (posicion.x < (dimension-1)) 
          if (!(habitacion.hayObstaculo(posicion.x+1, posicion.y)))
            return true;
        return false;  
      case 3: // Oeste
        if (posicion.x > 0) 
          if (!(habitacion.hayObstaculo(posicion.x-1, posicion.y)))
            return true;
        return false;
      default:
        return false;  
    }  
  }

  public Nodo aplicar ()
  {     
    Nodo copia = nodo.clonar();    
    copia.setNodoPadre(nodo);    
    Robot robot = copia.getHabitacion().getRobot();
    Posicion posicion = robot.getPosicion();
  
    switch (direccion)
    {
      case 0: // Norte
        posicion.y--;
        break;
      case 1: // Sur
        posicion.y++;
        break;
      case 2: // Este
        posicion.x++;
        break;
      case 3: // Oeste
        posicion.x--;
        break;
    }
    copia.setTiempo(copia.getTiempo() + 1);
    robot.setBateria(robot.getBateria() - (1 + robot.getCarga()));
    copia.setEnergia(copia.getEnergia() + (1 + robot.getCarga()));
    copia.obstFulminado = false;
    copia.inicializarOperaciones();
    nodo.listaSucesores.add(copia);
    return copia;
  }  
}
