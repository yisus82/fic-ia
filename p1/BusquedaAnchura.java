import java.util.*;

public class BusquedaAnchura extends Algoritmo
{
  private List abiertos = new ArrayList();
  private List cerrados = new ArrayList();

  public BusquedaAnchura(Nodo nodo, Posicion meta)
  {
    super(nodo, meta);   
  }
  
  public String toString()
  {
    return ("BUSQUEDA EN ANCHURA");
  }
  
  private boolean estaEnCerrados(Nodo nodo)
  {
    Posicion posNodo = nodo.getPosRobot();
    Posicion posCerrados = null;
   // boolean obstFulminado;
    for (int i = 0; i < cerrados.size(); i++)
    {
      posCerrados = ((Nodo)cerrados.get(i)).getHabitacion().getRobot().getPosicion();
     // obstFulminado = ((Nodo)cerrados.get(i)).obstFulminado;

      if ((nodo.obstFulminado == false) 
          && (posNodo.x == posCerrados.x) && (posNodo.y == posCerrados.y))
        return true;      
    }
    return false;
  }

  public List aplicar()
  {
    Nodo nodoHijo = null; 
    Operador op = null;
    int contador = 0;
  
    abiertos.add(nodoActual);
    
    // Aplicamos el test de realizacion
    if (nodoMeta(nodoActual))
    {
      Nodo temp = nodoActual;

      // Devolver el camino
      while (temp != null)
      {
        camino.add(0, temp);
        temp = temp.getNodoPadre();        
      }
          
      return camino;
    }
    
    if (Interfaz.verTrazas)
    {
      Interfaz.appendSalida(this.toString()+"\n===================\n");
      Interfaz.appendSalida("Nuevo subobjetivo:\n");
      Interfaz.appendSalida("Casilla inicial: ");
      Interfaz.appendSalida("("+nodoActual.getPosRobot().x+", "
                            +nodoActual.getPosRobot().y+")\n");
      Interfaz.appendSalida("Casilla final: ");
      Interfaz.appendSalida("("+meta.x+", "+meta.y+")\n");
    }
    
    while (true)
    {
      if (abiertos.isEmpty())
        return null;
      
      contador++;
      if (Interfaz.verTrazas)
        Interfaz.appendSalida("Nivel de exploracion para la iteracion "+contador+"\n");
      for (int i = 0; i < abiertos.size(); i++)
      {
        Nodo temp = (Nodo)(abiertos.get(i));
        if (Interfaz.verTrazas)
          Interfaz.appendSalida("("+temp.getPosRobot().x+", "+temp.getPosRobot().y+", "+temp.getEnergia()+")\n");
      }
      
      nodoActual = (Nodo)(abiertos.get(0));
      abiertos.remove(0);
      cerrados.add(nodoActual);
        
      while (nodoActual.hayOps())
      {
        op = nodoActual.getOp();
        nodoHijo = op.aplicar();
        
        if (!estaEnCerrados(nodoHijo))
          abiertos.add(nodoHijo);
        else
          continue;  
        
        // Aplicamos el test de realizacion
        if (nodoMeta(nodoHijo))
        {
          Nodo temp = nodoHijo;

          // Devolver el camino
          while (temp != null)
          {
            camino.add(0, temp);
            temp = temp.getNodoPadre();        
          }
          
          if (Interfaz.verTrazas)
            Interfaz.appendSalida("\n\n");          
          return camino;
        }
      }
    }
  }
}
