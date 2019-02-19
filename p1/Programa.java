import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.UIManager;

public class Programa
{
  private static Algoritmo algoritmo = null;
  private static Heuristica heuristica = null;
  private static int minimizacion;
  private static Objetivo objetivo = null;
  private static Habitacion habitacion = null;
  private static int numPaso = 0;
  private static List camino = new ArrayList();
  
   
  public static void verPrimerEstado()
  {
    numPaso = 0;
    Nodo nodoInicial = (Nodo)camino.get(0);
    Interfaz.setDatos(nodoInicial);
  }
  
  public static void verUltimoEstado()
  {
    numPaso = camino.size()-1;
    Nodo nodoFinal = (Nodo)camino.get(numPaso);
    Interfaz.setDatos(nodoFinal);
  }
  
  // Crea los objetos del dominio e inicializa los valores del interfaz
  // Esta funcion la llama el interfaz en su inicializacion
  public static void inicializar()
  {      
    if (!Interfaz.iniNuevoTablero)
    {
      if (camino.size() > 0)
      {
        numPaso = 0;
        Nodo nodoInicial = (Nodo)camino.get(0);
        habitacion = nodoInicial.getHabitacion();
        habitacion.setPosMontaje(Interfaz.casMontaje);
        Robot robot = habitacion.getRobot();
        robot.setBateria(Interfaz.maxBateria);
        robot.setMaxBateria(Interfaz.maxBateria);
        robot.setPosicion(Interfaz.casInicio);                
        
        Interfaz.setDatos(nodoInicial);
    
        camino = new ArrayList();    
      }
      else
      {
        habitacion.setPosMontaje(Interfaz.casMontaje);
        Robot robot = habitacion.getRobot();
        robot.setBateria(Interfaz.maxBateria);
        robot.setMaxBateria(Interfaz.maxBateria);
        robot.setPosicion(Interfaz.casInicio);
        
        Nodo nodoInicial = new Nodo(habitacion, null);
        
        Interfaz.setDatos(nodoInicial);
      }
    }
    else
    {
      // "Reseteamos" la pantalla del interfaz
      Interfaz.limpiarTablero();
 
      // Actualizamos los valores del estado inicial
      Interfaz.setTiempo(0);
      Interfaz.setEnergiaGastada(0);
      Interfaz.setPiezasMontadas(0);    
     
      // Aplicamos la nueva configuracion  
      Robot robot = new Robot(Interfaz.casInicio, Interfaz.maxBateria);
      habitacion = new Habitacion(Interfaz.dimension, robot, Interfaz.casMontaje);
      //robot.setHabitacion(habitacion);
    
      Interfaz.setPosIni(robot.getPosicion());
      Interfaz.setRobot(true, robot.getPosicion());
      Interfaz.setBateriaActual(robot.getBateria(), robot.getMaxBateria());
      Interfaz.setCasillaMontaje(habitacion.getPosMontaje());     
    
      if (Interfaz.iniAleat)
      {
        int dimension = habitacion.getDimension();
        int numEnchufes = Interfaz.numEnchufes;
        int numObstaculos = Interfaz.numObstaculos;
        Random rand = new Random();
        int x, y;
        int posUsadas [][];
      
        // Generamos posiciones aleatorias para los ENCHUFES
        posUsadas = new int[numEnchufes][2]; 

        for (int i = 0; i < numEnchufes; i++)
        {
          x = rand.nextInt(dimension);
          y = rand.nextInt(dimension);
        
          for (int j = 0; j < i; j++)
          {
            if ((x == posUsadas[j][0]) && (y == posUsadas[j][1]))
            {
              x = rand.nextInt(dimension);
              y = rand.nextInt(dimension);
              j = -1;
            }
          }
        
          posUsadas[i][0] = x;
          posUsadas[i][1] = y;
          habitacion.addEnchufe(x, y);
          Interfaz.setEnchufe(true, new Posicion(x, y));
        }
      
        // Generamos posiciones aleatorias para los OBSTACULOS
        posUsadas = new int[numObstaculos+6][2];
      
        // Metemos la posicion del robot para que no coincida
        // en la misma casilla con un obstaculo
        posUsadas[0][0] = robot.getPosicion().x;
        posUsadas[0][1] = robot.getPosicion().y;
        
        for (int i = 1; i <= numObstaculos; i++)
        {
          x = rand.nextInt(dimension);
          y = rand.nextInt(dimension);
        
          for (int j = 0; j < i; j++)
          {
            if ((x == posUsadas[j][0]) && (y == posUsadas[j][1]))
            {
              x = rand.nextInt(dimension);
              y = rand.nextInt(dimension);
              j = -1;
            }
          }
        
          posUsadas[i][0] = x;
          posUsadas[i][1] = y;
          habitacion.addObstaculo(x, y);
          Interfaz.setObstaculo(true, new Posicion(x, y));
        }     

        // Creamos las piezas y las asignamos a posiciones aleatorias
        Posicion posPiezas[] = new Posicion[5];
    
        for (int i = 0; i < 5; i++)
        {
          x = rand.nextInt(dimension);
          y = rand.nextInt(dimension);
                
          for (int j = 0; j < (numObstaculos+1+i); j++)
          {
            if ((x == posUsadas[j][0]) && (y == posUsadas[j][1]))
            {
              x = rand.nextInt(dimension);
              y = rand.nextInt(dimension);
              j = -1;
            }
          }
       
          posUsadas[i+numObstaculos+1][0] = x;
          posUsadas[i+numObstaculos+1][1] = y;
          posPiezas[i] = new Posicion(x, y);
        }
        Pieza cabeza = new Pieza(posPiezas[0], Pieza.CABEZA);
        Pieza cuerpo = new Pieza(posPiezas[1], Pieza.CUERPO);
        Pieza brazos = new Pieza(posPiezas[2], Pieza.BRAZOS);
        Pieza piernas = new Pieza(posPiezas[3], Pieza.PIERNAS);
        Pieza baterias = new Pieza(posPiezas[4], Pieza.BATERIAS);
        
        // Anhadimos las piezas a la habitacion
        habitacion.addPieza(cabeza);
        habitacion.addPieza(cuerpo);
        habitacion.addPieza(brazos);
        habitacion.addPieza(piernas);
        habitacion.addPieza(baterias);
      
        // Anhadimos las piezas al interfaz
        Interfaz.setPieza(true, posPiezas[0], cabeza.getID());
        Interfaz.setPieza(true, posPiezas[1], cuerpo.getID());
        Interfaz.setPieza(true, posPiezas[2], brazos.getID());
        Interfaz.setPieza(true, posPiezas[3], piernas.getID());
        Interfaz.setPieza(true, posPiezas[4], baterias.getID());    
      }
      else if (Interfaz.iniFich)
      {
        try
        {
          cargarDeArchivo(Interfaz.rutaFichero);
        }
        catch (Exception e){}
      }
    }
  }
  
  private static void comprobarModoNativo(String [] args)
  {
    // Si se ejecuta el comando "java interfaz -nativo" se carga 
    // con el aspecto nativo del SO en el que se ejecuta
    if ((args.length != 0) && (args[0].equalsIgnoreCase("-nativo")))
    {
      String nativeLF = UIManager.getSystemLookAndFeelClassName();
       
      try
      {
        UIManager.setLookAndFeel(nativeLF);
      } catch (Exception e)
      {
        System.out.println(e);
      }
    }
  }
  
  private static double distanciaEuclidea(Posicion origen, Posicion destino)
  {
    return (Math.sqrt(Math.pow((origen.x-destino.x),2) + Math.pow((origen.y-destino.y),2)));
  }
    
  private static Objetivo getSigObjetivo()
  {
    double minDistancia = Double.POSITIVE_INFINITY; 
    double distancia;
    Posicion posOrigen = null;
    Posicion posObjetivo = null;
    Robot robot = habitacion.getRobot();
    
    posOrigen = robot.getPosicion(); 
        
    if (robot.getBateria() <= 0)
    {
      // Casilla con enchufe
      for (int i = 0; i < habitacion.listaEnchufes.size(); i++)
      {
        distancia = distanciaEuclidea(posOrigen, (Posicion)(habitacion.listaEnchufes.get(i)));
        if (distancia < minDistancia)
        {
          minDistancia = distancia;
          posObjetivo = (Posicion)(habitacion.listaEnchufes.get(i));
        }
      }
      if (posObjetivo != null)
        return (new Objetivo(posOrigen, posObjetivo, Objetivo.ENCHUFE, "Enchufe"));
      else
        return null;
    }
    else if (robot.getPieza() == null)
    {
      Pieza piezaDest = null;
      
      // Si no tiene piezas, va a buscar una
      for (int i = 0; i < habitacion.listaPiezas.size(); i++)
      {
        distancia = distanciaEuclidea(posOrigen, (Posicion)(habitacion.listaPiezas.get(i)));
        if (distancia < minDistancia)
        {
          minDistancia = distancia;
          posObjetivo = (Posicion)(habitacion.listaPiezas.get(i));
          piezaDest = habitacion.getPieza(posObjetivo);
        }
      }
      if (posObjetivo != null)
        return (new Objetivo(posOrigen, posObjetivo, Objetivo.PIEZA, ("Piezas: "+piezaDest)));
      else
        return null;
    }
    else // Si lleva la pieza, y tiene bateria va a la casilla de montaje
    {
      if (habitacion.getPosMontaje() != null)
        return (new Objetivo(posOrigen, habitacion.getPosMontaje(), Objetivo.MONTAJE, "C.Montaje"));
      else
        return null;  
    }
  }
  
  public static void mostrarCamino()
  {
    Posicion pos;
    Nodo temp;
  
    temp = (Nodo)camino.get(((++numPaso) % camino.size()));

    if (temp.obstFulminado)
      System.out.println("OBSTACULO FULMINADO");
    else
    {
      pos = temp.getHabitacion().getRobot().getPosicion();
      System.out.println(""+pos);        
    }
    Interfaz.setDatos(temp);
  }
  
  private static boolean robotMontado()
  {
    if ((habitacion.getRobot().getPieza() == null)
        && (habitacion.listaPiezas.size() == 0))
      return true;
    else
      return false;  
  }
  
  public static void ejecutar()
  {
    Nodo nodoInicial = null;
    Nodo nodoFinal = null;
    
    camino.clear();
    numPaso = 0;
        
    while (!robotMontado())
    {
      // Buscamos el objetivo
      objetivo = getSigObjetivo();
      
      // Si no encuentra el siguiente objetivo, es que
      // el problema no tiene solucion
      if (objetivo == null)
      {
        Interfaz.setError("No existe solucion");
        break;
      }
    
      nodoInicial = new Nodo(habitacion, nodoFinal);
      if (nodoFinal != null)
      {
        nodoInicial.setTiempo(nodoFinal.getTiempo());
        nodoInicial.setEnergia(nodoFinal.getEnergia());
        nodoInicial.setPiezasMontadas(nodoFinal.getPiezasMontadas());
      }
      
      nodoInicial.setObjetivo(objetivo.clonar());
      
      if (Interfaz.heuristica1)
        heuristica = new DistEuclidea();
      else if (Interfaz.heuristica2)
        heuristica = new Manhattan();
    
      if (Interfaz.minEnergia)
        minimizacion = BusquedaAEstrella.ENERGIA;
      else
        minimizacion = BusquedaAEstrella.TIEMPO;
      
      if (Interfaz.busqCiega)
        algoritmo = new BusquedaAnchura(nodoInicial, objetivo.getDestino());
      else if (Interfaz.hillClimbing)
        algoritmo = new BusquedaHillClimbing(nodoInicial, objetivo.getDestino());
      else if (Interfaz.busqCiega2)
        algoritmo = new BusquedaAnchura2(nodoInicial, objetivo.getDestino());
      else if (Interfaz.hillClimbing2)
        algoritmo = new BusquedaHillClimbing2(nodoInicial, objetivo.getDestino(), heuristica);
      else if (Interfaz.algA) 
        algoritmo = new BusquedaAEstrella(nodoInicial, objetivo.getDestino(),
                                          heuristica, minimizacion);
                                          
      // DEBUG                                          
      System.out.println("Algoritmo: "+algoritmo);
      System.out.println("Heuristica: "+heuristica);
      System.out.println("Minimizacion: "+minimizacion);
                                                                                                    
      camino = algoritmo.aplicar();

      nodoFinal = (Nodo)camino.get(camino.size()-1);
      switch (objetivo.getTipoObjetivo())
      {
        case Objetivo.ENCHUFE:
          // Cargar bateria del robot
          nodoFinal.getHabitacion().getRobot().cargarBateria();
          nodoFinal.incTiempo(2);
          break;
        case Objetivo.PIEZA:
          nodoFinal.getHabitacion().getRobot().cogerPieza(nodoFinal.getHabitacion());
          break;
        case Objetivo.MONTAJE:
          nodoFinal.getHabitacion().getRobot().dejarPieza(nodoFinal.getHabitacion());
          nodoFinal.incPiezasMontadas(1);
      }      
      habitacion = nodoFinal.getHabitacion();
    }
  }
  
  public static void cargarDeArchivo(String fichero) throws java.io.IOException, java.io.FileNotFoundException 
  { 
    StringTokenizer st;
    String palabra;
  
    BufferedReader in = new BufferedReader(new FileReader(fichero));
    String linea = in.readLine();
    st = new StringTokenizer(linea); 
    String label;
    int posX, posY;
    int piezaID;
      
    while (linea!=null)  
    {
      palabra = st.nextToken();
      if (palabra.equalsIgnoreCase("piezas:")) 
      {
        while (!(palabra.equalsIgnoreCase("obstaculo:")))
        {
          linea=in.readLine();
          st = new StringTokenizer(linea);
          if (st.hasMoreTokens()) palabra = st.nextToken();
          if (!(palabra.equalsIgnoreCase("obstaculo:")) & (st.hasMoreTokens())) 
          {
            if (palabra.equalsIgnoreCase("cabeza")) piezaID = Pieza.CABEZA;
            else if (palabra.equalsIgnoreCase("cuerpo")) piezaID = Pieza.CUERPO;
            else if (palabra.equalsIgnoreCase("brazos")) piezaID = Pieza.BRAZOS;
            else if (palabra.equalsIgnoreCase("piernas")) piezaID = Pieza.PIERNAS;
            else piezaID = Pieza.BATERIAS;
            if (st.hasMoreTokens()) st.nextToken();
            posX = Integer.parseInt(st.nextToken());
            posY = Integer.parseInt(st.nextToken());
          
            Posicion pos = new Posicion(posX, posY);
          
            habitacion.addPieza(new Pieza(pos, piezaID));
            Interfaz.setPieza(true, pos, piezaID);    
          }
        }
        while (!(palabra.equalsIgnoreCase("enchufes:")))
        {
          linea=in.readLine();
          st = new StringTokenizer(linea);
          if (st.hasMoreTokens()) palabra = st.nextToken();
          if (!(palabra.equalsIgnoreCase("enchufes:")) & (st.hasMoreTokens())) 
          {
            label = "O";
            posX=Integer.parseInt(palabra);
            posY=Integer.parseInt(st.nextToken()); 
          
            habitacion.addObstaculo(posX, posY);
            Interfaz.setObstaculo(true, new Posicion(posX, posY));
          }
        }
        
        linea=in.readLine();
        while (linea!=null)
        {
          st = new StringTokenizer(linea);
          if (st.hasMoreTokens()) palabra = st.nextToken();
          if ((linea!=null) & (st.hasMoreTokens()))
          {
            label = "E";
            posX=Integer.parseInt(palabra);
            posY=Integer.parseInt(st.nextToken()); 
          
            habitacion.addEnchufe(posX, posY);
            Interfaz.setEnchufe(true, new Posicion(posX, posY));
          }
          linea=in.readLine();
        }
      }
      else System.out.println("Error de fichero");  
    } 
    in.close(); 
  }
    
  public static void main(String [] args)
  {
    comprobarModoNativo(args);
    inicializar();
  }
}
