import java.util.*;
import java.lang.Math;

public class BusquedaHillClimbing extends Algoritmo
{
  public BusquedaHillClimbing(Nodo nodo, Posicion meta)
  {
    super(nodo, meta);
  }
  
  public String toString()
  {
    return ("BUSQUEDA HILL CLIMBING");
  }
  
  // Utilizamos la distancia Manhattan como heuristica
  private double heuristica(Nodo nodo)
  {
    Posicion pos = nodo.getHabitacion().getRobot().getPosicion();    
    double distancia = Math.abs(pos.x - meta.x) + Math.abs(pos.y - meta.y);
    
    // Si se FULMINA un obstaculo, le restamos 0.5 a la heuristica para
    // representar que la situacion mejora con respecto a la original, pero
    // que sigue siendo peor con respecto a otra opcion en la que no haya
    // ningun obstaculo
    if (nodo.obstFulminado)
    {
      switch (nodo.dirObstFulminado)
      {
        case Nodo.NORTE:
          if (pos.y > meta.y)
            distancia = distancia - 0.5;
          break;
        case Nodo.SUR:
          if (pos.y < meta.y)
            distancia = distancia - 0.5;
          break;
        case Nodo.ESTE:
          if (pos.x < meta.x)
            distancia = distancia - 0.5;
          break;
        case Nodo.OESTE:
          if (pos.x > meta.x)
            distancia = distancia - 0.5;
      }
    }
    return distancia;  
  }
  
  public List aplicar()
  {
    Operador op;
    Nodo temp, nodoHijo, mejorNodo = null;
    double mejorHeuristica;
    boolean hayMejorHeuristica = true;
    int contador = 0;
    
    if (Interfaz.verTrazas)
    {
      Interfaz.appendSalida(this.toString()+"\n=========================\n");
      Interfaz.appendSalida("Nuevo subobjetivo:\n");
      Interfaz.appendSalida("Casilla inicial: ");
      Interfaz.appendSalida("("+nodoActual.getPosRobot().x+", "
                            +nodoActual.getPosRobot().y+")\n");
      Interfaz.appendSalida("Casilla final: ");
      Interfaz.appendSalida("("+meta.x+", "+meta.y+")\n");
    }
    
    while (true)
    {  
      contador++;
      if (Interfaz.verTrazas)
      {
        Interfaz.appendSalida("\nITERACION "+contador+"\n");
        Interfaz.appendSalida("Mejor nodo: ("+nodoActual.getPosRobot().x+", "+
                           nodoActual.getPosRobot().y+", "+nodoActual.getEnergia()
                           +", "+heuristica(nodoActual)+")\n");
      } 
        
      // Escribimos en la salida el camino actual
      // hasta el mejor nodo seleccionado
      List caminoActual = new ArrayList();
      temp = nodoActual;
      while (temp != null)
      {        
        caminoActual.add(0, temp);
        temp = temp.getNodoPadre();
      }
      
      if (Interfaz.verTrazas)
      {
      Interfaz.appendSalida("Camino actual hasta el mejor nodo:\n");
        
      for (int i = 0; i < caminoActual.size(); i++)
      {
        temp = (Nodo)(caminoActual.get(i));
        Interfaz.appendSalida("  ("+temp.getPosRobot().x+", "+temp.getPosRobot().y
                           +", "+temp.getEnergia()+", "+heuristica(temp)+")\n");
      }
      }
    
      // Aplicamos el test de realizacion
      if (nodoMeta(nodoActual))
      {
        temp = nodoActual;

        while (temp != null)
        {
          camino.add(0, temp);
          temp = temp.getNodoPadre();
        }
          
        if (Interfaz.verTrazas)
          Interfaz.appendSalida("\n\n");
        return camino;
      }
        
      mejorNodo = nodoActual;
      mejorHeuristica = heuristica(nodoActual);
      hayMejorHeuristica = false;

      if (Interfaz.verTrazas)
        Interfaz.appendSalida("\nDescendientes generados:\n");
      while (nodoActual.hayOps())
      {
        op = nodoActual.getOp();
        nodoHijo = op.aplicar();
        if (Interfaz.verTrazas)
          Interfaz.appendSalida("  ("+nodoHijo.getPosRobot().x+", "+nodoHijo.getPosRobot().y
                           +", "+nodoHijo.getEnergia()+", "+heuristica(nodoHijo)+")\n");
        
        if (heuristica(nodoHijo) < mejorHeuristica)
        {
          hayMejorHeuristica = true;
          mejorHeuristica = heuristica(nodoHijo);
          mejorNodo = nodoHijo;
        }
      }
      if (hayMejorHeuristica)
        nodoActual = mejorNodo;
      else
      {
        if (Interfaz.verTrazas)
          Interfaz.appendSalida("\n\n");
        return null;
      }
    }
  }
}
