import java.util.*;

public class Nodo
{
  public static final int NORTE = 0;
  public static final int SUR = 1;
  public static final int ESTE = 2;
  public static final int OESTE = 3;
  
  private Habitacion habitacion = null;
  private int tiempo = 0;
  private int energia = 0;
  private int piezasMontadas = 0;
  private  List operadores = new ArrayList();
  private Nodo nodoPadre = null;
  private Objetivo objetivo = null;
  public boolean obstFulminado = false; // Flag
  public int dirObstFulminado = -1;
  
  // Para el algoritmo A* se usa esta lista
  
  public List listaSucesores = new ArrayList();
  
  public Nodo clonar()
  {
    Nodo copia;
    copia = new Nodo(habitacion.clonar(), nodoPadre, false);
    copia.setTiempo(this.tiempo);
    copia.setEnergia(this.energia);
    copia.setPiezasMontadas(this.piezasMontadas);
    if (this.objetivo != null)
      copia.setObjetivo(this.objetivo.clonar());
    return copia;
  }
  
  public Posicion getPosRobot()
  {
    return this.getHabitacion().getRobot().getPosicion();
  }
  
  public Objetivo getObjetivo()
  {
    return this.objetivo;
  }
  
  public void setObjetivo(Objetivo objetivo)
  {
    this.objetivo = objetivo;
  }
  
  public void setNodoPadre(Nodo nodoPadre)
  {
    this.nodoPadre = nodoPadre;
  }
  
  public Nodo getNodoPadre()
  {
    return nodoPadre;
  }
  
  public void inicializarOperaciones()
  {
    // Anhadimos los operadores relevantes
    addOp(new Mover(this, Operador.NORTE));
    addOp(new Mover(this, Operador.SUR));
    addOp(new Mover(this, Operador.ESTE));
    addOp(new Mover(this, Operador.OESTE));
    addOp(new Disparar(this, Operador.NORTE));
    addOp(new Disparar(this, Operador.SUR));
    addOp(new Disparar(this, Operador.ESTE));
    addOp(new Disparar(this, Operador.OESTE));
  }
  
  public Nodo(Habitacion habitacion, Nodo nodoPadre, boolean iniOps)
  {
    this.habitacion = habitacion;
    this.nodoPadre = nodoPadre;
    
    if (iniOps)
    {
      inicializarOperaciones();
    }
  }
  
  public Nodo(Habitacion habitacion, Nodo nodoPadre)
  {
    this(habitacion, nodoPadre, true);
  }
  
  public Habitacion getHabitacion()
  {
    return this.habitacion;
  }
  
  public void setHabitacion(Habitacion habitacion)
  {
    this.habitacion = habitacion;
  }

  public int getTiempo()
  {
    return this.tiempo;
  }

  public void setTiempo(int tiempo)
  {
    this.tiempo = tiempo;
  }
  
  public void incTiempo(int inc)
  {
    this.tiempo += inc;
  }
  
  public int getEnergia()
  {
    return this.energia;
  }

  public void setEnergia(int energia)
  {
    this.energia = energia;
  }
  
  public int getPiezasMontadas()
  {
    return piezasMontadas;
  }
  
  public void setPiezasMontadas(int num)
  {
    this.piezasMontadas = num;
  }
  
  public void incPiezasMontadas(int inc)
  {
    this.piezasMontadas += inc;
  }
  
  public void addOp(Operador op)
  {
    if (op.esRelevante())
      operadores.add(op);
  }
  
  public boolean hayOps()
  {
    return (!(operadores.isEmpty()));
  }
  
  public Operador getOp()
  {
    Operador op;
 
    op = (Operador)operadores.get(0);
    operadores.remove(0);
    return op;      
  }
  
  public int getNumOps()
  {
    return (operadores.size());
  }
}
