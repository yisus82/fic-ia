public class Disparar extends Operador
{
  private Robot robot;
  private Posicion posicion;
  private Habitacion habitacion;
  private int dimension, direccion;

  public Disparar(Nodo nodo, int direccion)
  {
    this.nodo = nodo;
    this.direccion = direccion;
    robot = nodo.getHabitacion().getRobot();
    posicion = robot.getPosicion();
    habitacion = nodo.getHabitacion();
    dimension = habitacion.getDimension();  
  }
  
  public boolean esRelevante()
  {
    switch (direccion)
    {
      case 0: // Norte
        if (posicion.y > 0)
          if (habitacion.hayObstaculo(posicion.x, posicion.y-1))
            return true;        
        return false;
      case 1: // Sur
        if (posicion.y < (dimension-1)) 
          if (habitacion.hayObstaculo(posicion.x, posicion.y+1))
            return true;
        return false;
      case 2: // Este
        if (posicion.x < (dimension-1)) 
          if (habitacion.hayObstaculo(posicion.x+1, posicion.y))
            return true;
        return false;  
      case 3: // Oeste
        if (posicion.x > 0) 
          if (habitacion.hayObstaculo(posicion.x-1, posicion.y))
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
    Habitacion habitacion = copia.getHabitacion();
    Robot robot = habitacion.getRobot();
    // Fulminamos el obstaculo
    switch (direccion)
    {
      case 0: // Norte
        habitacion.quitarObstaculo(posicion.x, posicion.y-1);
        copia.dirObstFulminado = Nodo.NORTE;
        break;
      case 1: // Sur
        habitacion.quitarObstaculo(posicion.x, posicion.y+1);
        copia.dirObstFulminado = Nodo.SUR;
        break;
      case 2: // Este
        habitacion.quitarObstaculo(posicion.x+1, posicion.y);
        copia.dirObstFulminado = Nodo.ESTE;
        break;
      case 3: // Oeste
        habitacion.quitarObstaculo(posicion.x-1, posicion.y);
        copia.dirObstFulminado = Nodo.OESTE;
        break;
    }
    copia.setTiempo(copia.getTiempo() + 1); // Consume una unidad de tiempo
    robot.setBateria(robot.getBateria() - 6); // Consume 6 unidades de energia
    copia.setEnergia(copia.getEnergia() + 6);
    copia.obstFulminado = true;
    copia.inicializarOperaciones();
    nodo.listaSucesores.add(copia);
    return copia;
  }  
}
