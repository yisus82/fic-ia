import java.util.*;

public class BusquedaAEstrella extends Algoritmo
{
  public static final int TIEMPO = 0;
  public static final int ENERGIA = 1;

  protected Heuristica heuristica;
  protected List abiertos = new ArrayList();
  protected List cerrados = new ArrayList();
  protected boolean minTiempo = false, minEnergia = false;

  public BusquedaAEstrella(Nodo nodo, Posicion meta, Heuristica heuristica, int minimizar)
  {
    super(nodo, meta);
    this.heuristica = heuristica; 
    switch (minimizar)
    {
      case TIEMPO:
        minTiempo = true;
        minEnergia = false;
        break;
      case ENERGIA:
        minTiempo = false;
        minEnergia = true;
        break;
    }
  }
  
  public String toString()
  {
    return ("BUSQUEDA A*");
  }
  
  // G(n) es la energia o el tiempo empleado segun lo que queramos minimizar
  protected double G(Nodo nodo)
  {
    if (minTiempo)
      return nodo.getTiempo();
    else if (minEnergia)
      return nodo.getEnergia();  
    else
      return 0;
  }
  
  protected double H(Nodo nodo)
  {
    return (heuristica.calcular(nodo, meta));
  }
  
  // Funcion F(n) = G(n) + H(n), donde G(n) es la energia o el tiempo
  // empleado segun lo que queramos minimizar
  protected double F(Nodo nodo)
  {
    return (G(nodo) + H(nodo));
  }
  
  protected void setG_n(Nodo sucesor, Nodo viejo)
  {
    sucesor = viejo.clonar();
  }
  
  protected Nodo estaEnAbiertos(Nodo nodo)
  {
    Nodo temp;  
    Posicion posNodo, posTemp;
    
    for (int i = 0; i < abiertos.size(); i++)
    {
      posNodo = nodo.getHabitacion().getRobot().getPosicion();
      temp = (Nodo)abiertos.get(i);
      posTemp = temp.getHabitacion().getRobot().getPosicion();

      if ((nodo.obstFulminado == false)
           && (posNodo.x == posTemp.x) && (posNodo.y == posTemp.y))
        return temp;
    }
    return null;
  }
  
  protected Nodo estaEnCerrados(Nodo nodo)
  {
    Nodo temp;  
    Posicion posNodo, posTemp;
    
    for (int i = 0; i < cerrados.size(); i++)
    {
      posNodo = nodo.getHabitacion().getRobot().getPosicion();
      temp = (Nodo)cerrados.get(i);
      posTemp = temp.getHabitacion().getRobot().getPosicion();

      if ((nodo.obstFulminado == false)
           && (posNodo.x == posTemp.x) && (posNodo.y == posTemp.y))
        return temp;
    }
    return null;
  }
  
  protected void actualizarG_n(Nodo viejo, Nodo sucesor)
  {
    Robot robotViejo = viejo.getHabitacion().getRobot();
    Robot robotSucesor = sucesor.getHabitacion().getRobot();
    int energiaViejo = viejo.getEnergia();
    int tiempoViejo = viejo.getTiempo();
    int energiaSucesor, tiempoSucesor;
    
    tiempoSucesor = tiempoViejo + 1;
    sucesor.setTiempo(tiempoSucesor);
    
    if (sucesor.obstFulminado)
    {
      energiaSucesor = energiaViejo + 6;
      robotSucesor.setBateria(robotViejo.getBateria() - 6);
    }
    else
    {
      energiaSucesor = energiaViejo + (1 + robotViejo.getCarga());  
      robotSucesor.setBateria(robotViejo.getBateria() - (1 + robotViejo.getCarga()));
    }
    sucesor.setEnergia(energiaSucesor);
  }
  
  protected void propagarMejora(Nodo viejo)
  {
    Nodo temp;
    
    for (int i = 0; i < viejo.listaSucesores.size(); i++)
    {
      temp = (Nodo)viejo.listaSucesores.get(i);
      if (temp.getNodoPadre() == viejo)
      {
        actualizarG_n(viejo, temp);
        if (estaEnCerrados(temp) != null)
          propagarMejora(temp);
      }
      else
      {
        if (G(viejo) < G(temp.getNodoPadre()))
        {
          temp.setNodoPadre(viejo);
          actualizarG_n(viejo, temp);
          if (estaEnCerrados(temp) != null)
            propagarMejora(temp);
        }
      }
    }
  }

  public List aplicar()
  {
    Operador op;
    double mejorResultado, resultado;
    Nodo mejorNodo = null, temp, sucesor, viejo;
    int contador = 0;
    
    abiertos.add(nodoActual);
    
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
    mejorNodo = nodoActual;
    while (true)
    {
    
      contador++;
      if (Interfaz.verTrazas)
      {
      Interfaz.appendSalida("\nITERACION "+contador+"\n");
      Interfaz.appendSalida("Lista de nodos abiertos:\n");
      for (int i = 0; i < abiertos.size(); i++)
      { 
        temp = (Nodo)(abiertos.get(i));
        Interfaz.appendSalida("  ("+temp.getPosRobot().x+", "+temp.getPosRobot().y+", "+temp.getEnergia()+", "+G(temp)+", "+H(temp)+")\n");
      }
      }
      
      if (abiertos.isEmpty())
        return null;
      
      mejorNodo = (Nodo)abiertos.get(0);
      mejorResultado = F(mejorNodo);      
      for (int i = 1; i < abiertos.size(); i++)
      {
        temp = (Nodo)abiertos.get(i);
        resultado = F(temp);
        if (resultado < mejorResultado)
        {
          mejorResultado = resultado;
          mejorNodo = temp;
        }
      }
      abiertos.remove(mejorNodo);
      cerrados.add(mejorNodo);
      if (Interfaz.verTrazas)
        Interfaz.appendSalida("Mejor nodo: ("+mejorNodo.getPosRobot().x+", "+
                           mejorNodo.getPosRobot().y+", "+mejorNodo.getEnergia()+", "+G(mejorNodo)
                           +", "+H(mejorNodo)+")\n");
        
      // Escribimos en la salida el camino actual
      // hasta el mejor nodo seleccionado
      List caminoActual = new ArrayList();
      temp = mejorNodo;
      while (temp != null)
      {        
        caminoActual.add(0, temp);
        temp = temp.getNodoPadre();
      }
      
      if (Interfaz.verTrazas)
        Interfaz.appendSalida("Camino actual hasta el mejor nodo:\n");
      for (int i = 0; i < caminoActual.size(); i++)
      {
        temp = (Nodo)(caminoActual.get(i));
        if (Interfaz.verTrazas)
          Interfaz.appendSalida("  ("+temp.getPosRobot().x+", "+temp.getPosRobot().y
                             +", "+temp.getEnergia()+", "+G(temp)+", "+H(temp)+")\n");
      }
      // Aplicamos el test de realizacion
      if (nodoMeta(mejorNodo))
      {
        temp = mejorNodo;

        while (temp != null)
        {
          camino.add(0, temp);
          temp = temp.getNodoPadre();        
        }
          
        return camino;
      }
      if (Interfaz.verTrazas)
        Interfaz.appendSalida("\nDescendientes generados:\n");
      // Expandir mejorNodo generando todos sus sucesores
      while (mejorNodo.hayOps())
      {
        op = mejorNodo.getOp();
        sucesor = op.aplicar();
        
        if (Interfaz.verTrazas)
          Interfaz.appendSalida("  ("+sucesor.getPosRobot().x+", "+sucesor.getPosRobot().y
                           +", "+sucesor.getEnergia()+", "+G(sucesor)+", "+H(sucesor)+")\n");
        
        
        // SUCESOR ya apunta a MEJORNODO despues de realizarse
        // la operacion op.aplicar(), colocando un puntero a MEJORNODO 
        // en la variable "nodoPadre" de SUCESOR
        
        // G(SUCESOR) no es necesario calcularla porque ya se actualiza
        // su valor e. el nodo automaticamente al llamar a op.aplicar(), 
        // tanto si es el tiempo como si es la energia lo que se minimiza
        
        if ((viejo = estaEnAbiertos(sucesor)) != null)
        {
          mejorNodo.listaSucesores.add(viejo);
          if (G(viejo) > G(sucesor))
          {
            viejo.setNodoPadre(mejorNodo);
            setG_n(sucesor, viejo);
            mejorNodo.listaSucesores.remove(sucesor);
          }
        }
        else if ((viejo = estaEnCerrados(sucesor)) != null)
        {
          mejorNodo.listaSucesores.add(viejo);
          if (G(viejo) > G(sucesor))
          {
            viejo.setNodoPadre(mejorNodo);
            setG_n(sucesor, viejo);
            mejorNodo.listaSucesores.remove(sucesor);
            propagarMejora(viejo);
          }
        }
        else
        {
          abiertos.add(sucesor);
        }        
      }
    }
  }
}
