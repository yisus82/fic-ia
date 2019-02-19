import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Interfaz extends JFrame 
{
  private static JLabel listaEnchufes [][];
  private static JLabel listaRobotObst [][];
  private static JLabel listaPiezas [][];
  private static int MAXPANELHAB = 700;

  
  // Variables que especifican la configuracion del programa
  public static int dimension = 10, maxBateria = 100;
  public static int numEnchufes = 25, numObstaculos = 25;
  public static boolean iniAleat = true, iniFich = false;
  public static boolean minTiempo = true, minEnergia = false;
  public static boolean busqCiega = false, hillClimbing = false, algA = true;
  public static boolean busqCiega2 = false, hillClimbing2 = false;
  public static boolean heuristica1 = false, heuristica2 = true;
  public static Posicion casInicio = new Posicion(0,0);
  public static Posicion casMontaje = new Posicion(dimension-1, dimension-1);
  public static String rutaFichero = null;
  public static boolean verTrazas = false, iniNuevoTablero = true;;
    
  private static JFrame ventConf = null;
  private static JFrame ventSalida = null;
        
  private static JLabel lError;
  private static JComboBox cbBusqueda;
  private static JLabel lvPiezaActual;
  private static JLabel lvPosFin;
  private static JLabel lDimension;
  private static JPanel panelConf;
  private static JTextField tMaxBateria;
  private static JLabel lEnergia;
  private static JLabel lvCasMontaje;
  private static JPanel panelDatos;
  private static JLabel lPosFin;
  private static JLabel lBateria;
  private static JLabel lXini;
  private static JPanel panelIni;
  private static JLabel lHeuristica;
  private static JRadioButton rbEnergia;
  private static JLabel lPosIni;
  private static JLabel lCasMontaje;
  private static JLabel lvTiempo;
  private static JTextField tYini;
  private static JButton bNuevo;
  private static JPanel panelEstado;
  private static JLabel lvEnergia;
  private static JPanel panelInicializar;
  private static JCheckBox cbInicializar;
  private static JButton bEstadoFin;
  private static JPanel panelHab;
  private static ButtonGroup bgMinimizar;
  private static JLabel lPosActual;
  private static JPanel panelSubObj;
  private static JButton bCalcular;
  private static JPanel panelRelleno;
  private static JComboBox cbHeuristica;
  private static JLabel lFichero;
  private static JLabel lMaxBateria;
  private static JTextField tYmont;
  private static JTextField tXmont;
  private static JPanel pCasMont;
  private static JLabel lPiezasMontadas;
  private static JLabel lMetaActual;
  private static JTextArea saSalida;
  private static JTextField tDimension;
  private static JTextField tNumEnchufes;
  private static JButton bSalida;
  private static JRadioButton rbTiempo;
  private static JButton bEstadoIni;
  private static JButton bConfiguracion;
  private static JLabel lYini;
  private static JPanel pCasIni;
  private static JLabel lNumEnchufes;
  private static JTextField tFichero;
  private static JPanel panelMinimizar;
  private static JPanel panelCentro;
  private static JLabel lBusqueda;
  private static JLabel lYmont;
  private static JLabel lCasMont;
  private static JPanel panelBotones;
  private static JPanel panelIniAleat;
  private static JLabel lvPiezasMontadas;
  private static JLabel lNumObst;
  private static JTextField tNumObst;
  private static JTextField tXini;
  private static JLabel lTiempo;
  private static JPanel panelBusqueda;
  private static JLabel lvPosIni;
  private static JScrollPane spSalida;
  private static ButtonGroup bgIni;
  private static JRadioButton rbIniAleat;
  private static JRadioButton rbIniFich;
  private static JLabel lPiezaActual;
  private static JLabel lXmont;
  private static JLabel lvPosActual;
  private static JButton bPasoAPaso;
  private static JPanel panelIniFich;
  private static JLabel lvMetaActual;
  private static JPanel panelIzda;
  private static JLabel lCasini;
  private static JLabel lvBateria;
  private static JCheckBox chbVerTrazas;
    
  public static void limpiarTablero()
  {
    for (int i = 0; i < dimension; i++)
      for (int j = 0; j < dimension; j++)
      {
        listaEnchufes[i][j].setText("");
        listaRobotObst[i][j].setText("");
        listaPiezas[i][j].setText("");
      }
  }
  
  public static void setEnchufe(boolean poner, Posicion pos)
  {
    int x, y;

    x = pos.x;
    y = pos.y;
    
    if (poner)
    {         
      if (listaEnchufes[x][y].getText().equals(""))
        listaEnchufes[x][y].setText("e");
    }
    else if (!poner)
    {
      if (!(listaEnchufes[x][y].getText()).equals(""))
      {
        listaEnchufes[x][y].setText("");
      }
    }    
  }
 
  public static void setObstaculo(boolean poner, Posicion pos)
  {
    int x, y;

    x = pos.x;
    y = pos.y;
    
    if (poner)
    {         
      if (listaRobotObst[x][y].getText().equals(""))
        listaRobotObst[x][y].setText("O");
    }
    else if (!poner)
    {
      if (!(listaRobotObst[x][y].getText()).equals(""))
      {
        listaRobotObst[x][y].setText("");
      }
    }
  }
  
  public static void setRobot(boolean poner, Posicion pos)
  {
    int x, y;

    x = pos.x;
    y = pos.y;
 
    if (poner)
    {         
      if (listaRobotObst[x][y].getText().equals(""))
        listaRobotObst[x][y].setText("R");
    }
    else if (!poner)
    {
      if (!(listaRobotObst[x][y].getText()).equals(""))
      {
        listaRobotObst[x][y].setText("");
      }
    }   
  }
  
  // numPieza es un identificativo de la pieza:
  //   1 -> Cabeza
  //   2 -> Cuerpo
  //   3 -> Brazos
  //   4 -> Piernas
  //   5 -> Baterias
  public static void setPieza(boolean poner, Posicion pos, int numPieza)
  {
    int x, y;

    x = pos.x;
    y = pos.y;
    
    if (poner)
    {         
      if (listaPiezas[x][y].getText().equals(""))
        listaPiezas[x][y].setText("P");//+numPieza);
    }
    else if (!poner)
    {
      if (!((listaPiezas[x][y].getText()).equals("")))
      {
        listaPiezas[x][y].setText("");
      }
    }  
  }
    
  public static void setBateriaActual(int bateria, int maxBateriaActual)
  {
    lvBateria.setText(" " + bateria + " / " + maxBateriaActual);
  }
  
  public static void setEnergiaGastada(int energia)
  {
    lvEnergia.setText(" " + energia);
  }
  
  public static void setTiempo(int tiempo)
  {
    lvTiempo.setText(" " + tiempo);
  }

  public static void setPosIni(Posicion pos)
  {
    lvPosIni.setText(" "+pos);
  }
  
  public static void setPosIni(int x, int y)
  {
    lvPosIni.setText(" (" + x + "," + y +")");
  }

  public static void setPosFin(Posicion pos)
  {
    lvPosFin.setText(" "+pos);
  }
  
  public static void setPosActual(Posicion pos)
  {    
    lvPosActual.setText(" "+pos);
  }

  public static void setPosFin(int x, int y)
  {
    lvPosFin.setText(" (" + x + "," + y +")");
  }

  public static void setMetaActual(String meta)
  {
    lvMetaActual.setText(" " + meta);
  }

  public static void setPiezaActual(String pieza, int peso)
  {
    if (peso != 0)
      lvPiezaActual.setText(" "+pieza+"("+peso+" Kg)");
    else
      lvPiezaActual.setText("");  
  }
  
  public static void setCasillaMontaje(Posicion pos)
  {
    lvCasMontaje.setText(" "+pos);
  }
  
  public static void setCasillaMontaje(int x, int y)
  {
    lvCasMontaje.setText(" (" + x + "," + y +")");
  }
  
  public static void setPiezasMontadas(int n)
  {
   lvPiezasMontadas.setText(" "+n);
  }
  
  public static void appendSalida(String texto)
  {
    saSalida.append(texto);
  }
  
  public static void setError(String str)
  {
    lError.setText(str);
  }
  
  public static void setDatos(Nodo nodo)
  {
    Habitacion habitacion;
    int dimension;
    Casilla casilla;
    Posicion pos;
    Pieza pieza;
    Robot robot;
    
    limpiarTablero();
    setTiempo(nodo.getTiempo());
    setPiezasMontadas(nodo.getPiezasMontadas());
    setEnergiaGastada(nodo.getEnergia());
    
    habitacion = nodo.getHabitacion();
    setCasillaMontaje(habitacion.getPosMontaje());
    dimension = habitacion.getDimension();
    for (int i = 0; i < dimension; i++)
    {
      for (int j = 0; j < dimension; j++)
      {
        pos = new Posicion(i,j);
        casilla = habitacion.getCasilla(i,j);

        if (casilla.hayEnchufe())
          setEnchufe(true, pos);

        if (casilla.hayObstaculo())
          setObstaculo(true, pos);
        
        if ((pieza = casilla.getPieza()) != null)
        {
          setPieza(true, pos, pieza.getID());
        }    
      }
    }
    
    robot = habitacion.getRobot();
    setBateriaActual(robot.getBateria(), robot.getMaxBateria());
    setRobot(true, robot.getPosicion());
    
    if ((pieza = robot.getPieza()) != null)
      setPiezaActual(pieza.toString(), pieza.getPeso());
    else
      setPiezaActual("", 0);
    
    if (nodo.getObjetivo() != null)
    {
      pos = nodo.getObjetivo().getOrigen();
      setPosIni(pos);
      pos = nodo.getObjetivo().getDestino();
      setPosFin(pos);
      pos = nodo.getPosRobot();
      setPosActual(pos);
      setMetaActual(nodo.getObjetivo().getDescripcion());
    }
    else
    {
      lvPosIni.setText("");
      lvPosFin.setText("");
      lvPosActual.setText("");
      lvMetaActual.setText("");
    }
  }
  
  private void iniPanelesHab()
  {
    JPanel panelCasilla;
    JLabel label, lEnchufes, lRobotObst, lPiezas;    
    
    panelHab = new JPanel(new GridLayout(dimension+1, dimension+1, 2, 2));    
    panelHab.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Habitacion"));
    panelHab.setMaximumSize(new Dimension(MAXPANELHAB, MAXPANELHAB));
    panelHab.setMinimumSize(new Dimension(MAXPANELHAB, MAXPANELHAB));
    panelHab.setPreferredSize(new Dimension(MAXPANELHAB, MAXPANELHAB));
        
    listaEnchufes = new JLabel[dimension][dimension];
    listaRobotObst = new JLabel[dimension][dimension];
    listaPiezas = new JLabel[dimension][dimension];
    
    for (int i=-1; i < dimension; i++)
      for (int j=-1; j < dimension; j++)
      {
        if (i == -1 && j == -1)
        {
          //label = new JLabel("y / x", JLabel.CENTER);
          //panelHab.add(label);
          panelHab.add(new JLabel());
        }
        else if (i == -1)
        {
          label = new JLabel(""+j, JLabel.CENTER);
          panelHab.add(label);
        }
        else if (j == -1)
        {    
          label = new JLabel(""+i, JLabel.CENTER);
          panelHab.add(label);
        }
        else // Si i >= 0 y j >= 0
        {
          panelCasilla = new JPanel(new GridLayout(1,2));
          panelCasilla.setBackground(Color.white);
                             
          lEnchufes = new JLabel("", JLabel.CENTER);
          lEnchufes.setBackground(Color.white);
          lRobotObst = new JLabel("", JLabel.CENTER);
          lRobotObst.setBackground(Color.white);
          lPiezas = new JLabel("", JLabel.CENTER);
          lPiezas.setBackground(Color.white);
          panelCasilla.add(lEnchufes);
          panelCasilla.add(lRobotObst);
          panelCasilla.add(lPiezas);

          listaEnchufes[i][j] = lEnchufes;
          listaRobotObst[i][j] = lRobotObst;
          listaPiezas[i][j] = lPiezas;
          panelHab.add(panelCasilla);
        }
      }
  }

  private void activarIniAleatoria()
  {
    lNumEnchufes.setEnabled(true);
    tNumEnchufes.setEnabled(true);
    lNumObst.setEnabled(true);
    tNumObst.setEnabled(true);
    lFichero.setEnabled(false);
    tFichero.setEnabled(false);
  }
  
  private void activarIniFichero()
  {
    lNumEnchufes.setEnabled(false);
    tNumEnchufes.setEnabled(false);
    lNumObst.setEnabled(false);
    tNumObst.setEnabled(false);
    lFichero.setEnabled(true);
    tFichero.setEnabled(true);
  }
  
  private void iniPanelConfiguracion()
  {  
    tDimension.setText("" + dimension);
    tMaxBateria.setText("" + maxBateria);
    tXini.setText("" + casInicio.x);
    tYini.setText("" + casInicio.y);
    tXmont.setText("" + casMontaje.x);
    tYmont.setText("" + casMontaje.y);
    rbTiempo.setSelected(true);
    rbIniAleat.setSelected(true);
    tNumEnchufes.setText("" + numEnchufes);
    tNumObst.setText("" + numObstaculos);

    // Colocamos la configuracion inicial    
    
    // Configuramos inicialmente con busqueda A*
    cbHeuristica.setEnabled(true);
    rbTiempo.setEnabled(true);
    rbEnergia.setEnabled(true);
    rbTiempo.setSelected(true);
    
    // Por defecto, inicializacion aleatoria
    activarIniAleatoria();
    
    cbBusqueda.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        int index = cbBusqueda.getSelectedIndex();
        
        // Si se selecciona el algoritmo A*        
        if (index == 0)
        {
          cbHeuristica.setEnabled(true);
          rbTiempo.setEnabled(true);
          rbEnergia.setEnabled(true);
          
          seleccionarBusqueda(index);
        }
        // Si se selecciona Busqueda Ciega
        else if ((index == 1) || (index == 3))
        {
          cbHeuristica.setEnabled(false);
          rbTiempo.setEnabled(false);
          rbEnergia.setEnabled(false);
          
          seleccionarBusqueda(index);
        }
        // Si se selecciona Hill-Climbing
        else if (index == 2)
        {
          cbHeuristica.setEnabled(false);
          rbTiempo.setEnabled(false);    
          rbEnergia.setEnabled(false);
          
          seleccionarBusqueda(index);
        }        
        // Si se selecciona Hill Climbing (basado en A*)
        else if (index == 4)
        {
          cbHeuristica.setEnabled(true);
          rbTiempo.setEnabled(false);    
          rbEnergia.setEnabled(false);
          
          seleccionarBusqueda(index);
        }
      }
    });
    
    rbTiempo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        minTiempo = true;
        minEnergia = false;
      }
    });
    
    rbEnergia.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        minTiempo = false;
        minEnergia = true;
      }
    });
    
    cbInicializar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        iniNuevoTablero = cbInicializar.isSelected();
        
        rbIniAleat.setEnabled(iniNuevoTablero);
        rbIniFich.setEnabled(iniNuevoTablero);
        lDimension.setEnabled(iniNuevoTablero);
        tDimension.setEnabled(iniNuevoTablero);
        lNumEnchufes.setEnabled(iniNuevoTablero);
        tNumEnchufes.setEnabled(iniNuevoTablero);
        lNumObst.setEnabled(iniNuevoTablero);
        tNumObst.setEnabled(iniNuevoTablero);
      }
    });
    
    rbIniAleat.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        activarIniAleatoria();
      }
    });
    
    rbIniFich.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        activarIniFichero();
      }
    });   
  }
  
  private void seleccionarBusqueda(int index)
  {
    // Si se ha seleccionado el algoritmo A*        
    if (index == 0)
    {
      busqCiega = false;
      hillClimbing = false;
      algA = true;
      busqCiega2 = false;
      hillClimbing2 = false;            
    }
    // Busqueda en anchura
    else if (index == 1)
    {
      busqCiega = true;
      hillClimbing = false;
      algA = false;
      busqCiega2 = false;
      hillClimbing2 = false;
      
      // En este caso, siempre se minimiza el tiempo
      minTiempo = true;
      minEnergia = false;
    }
    // Si se ha seleccionado Hill-Climbing
    else if (index == 2)
    {
      busqCiega = false;
      hillClimbing = true;
      algA = false;
      busqCiega2 = false;
      hillClimbing2 = false;
      
      // En este caso, siempre se minimiza el tiempo
      minTiempo = true;
      minEnergia = false;
    }    
    // Si se ha seleccionado busqueda ciega (basado en A*)
    else if (index == 3)
    {
      busqCiega = false;
      hillClimbing = false;
      algA = false;
      busqCiega2 = true;
      hillClimbing2 = false;
      
      // En este caso, siempre se minimiza el tiempo
      minTiempo = true;
      minEnergia = false;
    }
    // Si se ha seleccionado Hill-Climbing (basado en A*)
    else if (index == 4)
    {
      busqCiega = false;
      hillClimbing = false;
      algA = false;
      busqCiega2 = false;
      hillClimbing2 = true;
      
      // En este caso, siempre se minimiza el tiempo
      minTiempo = true;
      minEnergia = false;           
    }
  }
  
  private void seleccionarHeuristica(int index)
  {
    if (index == 0)
    { 
      heuristica1 = false;
      heuristica2 = true;
    }
    else if (index == 1)
    {
      heuristica1 = true;
      heuristica2 = false;
    }
  }
  
  private void seleccionarMinimizacion()
  {    
    if (rbTiempo.isSelected())
    {
      minTiempo = true;
      minEnergia = false;
    }
    else if (rbEnergia.isSelected())
    {
      minTiempo = false;
      minEnergia = true;
    }
  }
  
  private void aplicarConfiguracion()
  {
    // TAMANHO HABITACION
    dimension = Integer.parseInt(tDimension.getText());
    if (panelHab != null)
      panelIzda.remove(panelHab);
    iniPanelesHab();    
    panelIzda.add(panelHab, BorderLayout.CENTER);
    //this.pack();
    
    // MAX BATERIA
    maxBateria = Integer.parseInt(tMaxBateria.getText());
    
    // CASILLAS DE INICIO Y MONTAJE
    casInicio = new Posicion(Integer.parseInt(tXini.getText()),Integer.parseInt(tYini.getText()));
    casMontaje = new Posicion(Integer.parseInt(tXmont.getText()),Integer.parseInt(tYmont.getText()));
    
    // TIPO DE BUSQUEDA
    seleccionarBusqueda(cbBusqueda.getSelectedIndex());
        
    // Miramos la heuristica seleccionada
    seleccionarHeuristica(cbHeuristica.getSelectedIndex());
    
    // Miramos la minimizacion escogida
    seleccionarMinimizacion();
            
    // TIPO DE INICIALIZACION
    iniNuevoTablero = cbInicializar.isSelected();
    
    if (rbIniAleat.isSelected())
    {
      iniAleat = true;
      iniFich = false;
      
      numEnchufes = Integer.parseInt(tNumEnchufes.getText());
      numObstaculos = Integer.parseInt(tNumObst.getText());
    }
    else if (rbIniFich.isSelected())
    {
      iniAleat = false;
      iniFich = true;
      
       rutaFichero = new String(tFichero.getText());
    }
     
    Programa.inicializar();
  }
    
  public Interfaz() 
  {
    initComponents();
    iniPanelConfiguracion();
    Dimension tamanhoVentana=Toolkit.getDefaultToolkit().getScreenSize();
    setSize(tamanhoVentana.width,tamanhoVentana.height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(true);
    setVisible(true);
  }

  private void initComponents()
  {
    bgMinimizar = new ButtonGroup();
    bgIni = new ButtonGroup();
    panelIzda = new JPanel();
    spSalida = new JScrollPane();
    saSalida = new JTextArea();
    panelCentro = new JPanel();
    panelSubObj = new JPanel();
    lMetaActual = new JLabel();
    lvMetaActual = new JLabel();
    lPosIni = new JLabel();
    lvPosIni = new JLabel();
    lPosFin = new JLabel();
    lvPosFin = new JLabel();
    lPosActual = new JLabel();
    lvPosActual = new JLabel();
    panelEstado = new JPanel();
    lBateria = new JLabel();
    lvBateria = new JLabel();
    lEnergia = new JLabel();
    lvEnergia = new JLabel();
    lTiempo = new JLabel();
    lvTiempo = new JLabel();
    lPiezasMontadas = new JLabel();
    lvPiezasMontadas = new JLabel();
    lPiezaActual = new JLabel();
    lvPiezaActual = new JLabel();
    lCasMontaje = new JLabel();
    lvCasMontaje = new JLabel();
    panelBotones = new JPanel();
    panelRelleno = new JPanel();
    bCalcular = new JButton();
    bConfiguracion = new JButton();
    bEstadoIni = new JButton();
    bEstadoFin = new JButton();
    bSalida = new JButton();
    bPasoAPaso = new JButton();
    bNuevo = new JButton();
    panelConf = new JPanel();
    panelDatos = new JPanel();
    lDimension = new JLabel();
    tDimension = new JTextField();
    lMaxBateria = new JLabel();
    tMaxBateria = new JTextField();
    lCasini = new JLabel();
    pCasIni = new JPanel();
    lXini = new JLabel();
    tXini = new JTextField();
    lYini = new JLabel();
    tYini = new JTextField();
    lCasMont = new JLabel();
    pCasMont = new JPanel();
    lXmont = new JLabel();
    tXmont = new JTextField();
    lYmont = new JLabel();
    tYmont = new JTextField();
    panelBusqueda = new JPanel();
    lBusqueda = new JLabel();
    lError = new JLabel();
    cbBusqueda = new JComboBox();
    lHeuristica = new JLabel();
    cbHeuristica = new JComboBox();
    panelMinimizar = new JPanel();
    rbTiempo = new JRadioButton();
    rbEnergia = new JRadioButton();
    rbIniAleat = new JRadioButton();
    rbIniFich = new JRadioButton();
    panelIni = new JPanel();
    panelIniAleat = new JPanel();
    lNumEnchufes = new JLabel();
    tNumEnchufes = new JTextField();
    lNumObst = new JLabel();
    tNumObst = new JTextField();
    panelIniFich = new JPanel();
    lFichero = new JLabel();
    tFichero = new JTextField();

    getContentPane().setLayout(new FlowLayout());

    setTitle("Interfaz de IA");
    setName("");
    addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent evt) {
            exitForm(evt);
        }
    });

    panelIzda.setLayout(new FlowLayout());

    panelIzda.setMinimumSize(new Dimension(200, 200));

    iniPanelesHab();        

    panelIzda.add(panelHab);

    getContentPane().add(panelIzda);

    panelCentro.setLayout(new BorderLayout(5, 5));

    panelSubObj.setLayout(new GridLayout(4, 2, 5, 5));

    panelSubObj.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Informacion subobjetivo actual"));
    lMetaActual.setHorizontalAlignment(SwingConstants.RIGHT);
    lMetaActual.setText("Meta actual: ");
    panelSubObj.add(lMetaActual);

    lvMetaActual.setBackground(Color.white);
    lvMetaActual.setHorizontalAlignment(SwingConstants.CENTER);
    lvMetaActual.setBorder(BorderFactory.createLineBorder(Color.black));
    panelSubObj.add(lvMetaActual);

    lPosIni.setHorizontalAlignment(SwingConstants.RIGHT);
    lPosIni.setText("Posicion inicial: ");
    panelSubObj.add(lPosIni);

    lvPosIni.setBackground(Color.white);
    lvPosIni.setHorizontalAlignment(SwingConstants.CENTER);
    lvPosIni.setBorder(BorderFactory.createLineBorder(Color.black));
    panelSubObj.add(lvPosIni);

    lPosFin.setHorizontalAlignment(SwingConstants.RIGHT);
    lPosFin.setText("Posicion final: ");
    panelSubObj.add(lPosFin);

    lvPosFin.setBackground(Color.white);
    lvPosFin.setHorizontalAlignment(SwingConstants.CENTER);
    lvPosFin.setBorder(BorderFactory.createLineBorder(Color.black));
    panelSubObj.add(lvPosFin);

    lPosActual.setHorizontalAlignment(SwingConstants.RIGHT);
    lPosActual.setText("Posicion actual: ");
    panelSubObj.add(lPosActual);

    lvPosActual.setBackground(Color.white);
    lvPosActual.setHorizontalAlignment(SwingConstants.CENTER);
    lvPosActual.setBorder(BorderFactory.createLineBorder(Color.black));
    panelSubObj.add(lvPosActual);

    panelCentro.add(panelSubObj, BorderLayout.CENTER);

    panelEstado.setLayout(new GridLayout(6, 2, 5, 5));

    panelEstado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Informacion estado actual"));
    lBateria.setHorizontalAlignment(SwingConstants.RIGHT);
    lBateria.setText("Bateria actual: ");
    panelEstado.add(lBateria);

    lvBateria.setBackground(Color.white);
    lvBateria.setHorizontalAlignment(SwingConstants.CENTER);
    lvBateria.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    panelEstado.add(lvBateria);

    lEnergia.setHorizontalAlignment(SwingConstants.RIGHT);
    lEnergia.setText("Energia empleada: ");
    panelEstado.add(lEnergia);

    lvEnergia.setBackground(Color.white);
    lvEnergia.setHorizontalAlignment(SwingConstants.CENTER);
    lvEnergia.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    panelEstado.add(lvEnergia);

    lTiempo.setHorizontalAlignment(SwingConstants.RIGHT);
    lTiempo.setText("Tiempo empleado: ");
    panelEstado.add(lTiempo);

    lvTiempo.setBackground(Color.white);
    lvTiempo.setHorizontalAlignment(SwingConstants.CENTER);
    lvTiempo.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    panelEstado.add(lvTiempo);

    lPiezasMontadas.setHorizontalAlignment(SwingConstants.RIGHT);
    lPiezasMontadas.setText("Piezas montadas: ");
    panelEstado.add(lPiezasMontadas);

    lvPiezasMontadas.setBackground(Color.white);
    lvPiezasMontadas.setHorizontalAlignment(SwingConstants.CENTER);
    lvPiezasMontadas.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    panelEstado.add(lvPiezasMontadas);

    lPiezaActual.setHorizontalAlignment(SwingConstants.RIGHT);
    lPiezaActual.setText("Pieza actual: ");
    panelEstado.add(lPiezaActual);

    lvPiezaActual.setBackground(Color.white);
    lvPiezaActual.setHorizontalAlignment(SwingConstants.CENTER);
    lvPiezaActual.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    panelEstado.add(lvPiezaActual);

    lCasMontaje.setHorizontalAlignment(SwingConstants.RIGHT);
    lCasMontaje.setText("Casilla montaje: ");
    panelEstado.add(lCasMontaje);

    lvCasMontaje.setBackground(Color.white);
    lvCasMontaje.setHorizontalAlignment(SwingConstants.CENTER);
    lvCasMontaje.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    panelEstado.add(lvCasMontaje);

    panelCentro.add(panelEstado, BorderLayout.NORTH);

    panelBotones.setLayout(new GridLayout(10, 1, 5, 5));                
    
    panelBotones.add(panelRelleno);
        
    bCalcular.setText("Calcular camino");
    panelBotones.add(bCalcular);

    bEstadoIni.setText("Ver estado inicial");
    bEstadoIni.setEnabled(false);
    panelBotones.add(bEstadoIni);

    bEstadoFin.setText("Ver ultimo estado");
    bEstadoFin.setEnabled(false);
    panelBotones.add(bEstadoFin);

    bPasoAPaso.setText("Ejecutar paso a paso");
    bPasoAPaso.setEnabled(false);
    panelBotones.add(bPasoAPaso);        
    
    bConfiguracion.setText("Configuracion");
    panelBotones.add(bConfiguracion);

    bNuevo.setText("Aplicar Configuracion / Reiniciar");
    panelBotones.add(bNuevo);
    
    bSalida.setText("Ver Salida");
    panelBotones.add(bSalida);
  
    chbVerTrazas = new JCheckBox("Ver trazas en la salida", false);
    panelBotones.add(chbVerTrazas);             
    
    lError.setText("");
    lError.setHorizontalAlignment(SwingConstants.CENTER);
    lError.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    panelBotones.add(lError);

    panelCentro.add(panelBotones, BorderLayout.SOUTH);
    
    getContentPane().add(panelCentro);
    
    ventSalida = new JFrame("Salida");
    ventSalida.getContentPane().setLayout(new BorderLayout(5,5)); 
    
    spSalida.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    spSalida.setViewportBorder(BorderFactory.createEtchedBorder());
    saSalida.setEditable(false);
    saSalida.setLineWrap(true);
    saSalida.setWrapStyleWord(true);
    saSalida.setRows(11);
    saSalida.setMaximumSize(null);
    saSalida.setMinimumSize(null);
    spSalida.setViewportView(saSalida);
        
    JButton bLimpiarSalida = new JButton("Limpiar pantalla");
        
    bLimpiarSalida.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        saSalida.setText("");
      }
    });        
        
    ventSalida.getContentPane().add(spSalida, BorderLayout.CENTER);
    ventSalida.getContentPane().add(bLimpiarSalida, BorderLayout.SOUTH);
    
    //Dimension tamanhoVentana=Toolkit.getDefaultToolkit().getScreenSize();
    //ventSalida.setSize(tamanhoVentana.width,tamanhoVentana.height);
    ventSalida.setSize(500,700);
    ventSalida.setVisible(false);
    ventSalida.setResizable(true);       
    
    ventConf = new JFrame("Configuracion");
    ventConf.getContentPane().setLayout(new BorderLayout(5,5)); 
    
    panelConf.setLayout(new GridLayout(1,2,5,5));
    panelConf.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Configuracion"));

    JPanel panelConfIzq = new JPanel();
    panelConfIzq.setLayout(new BoxLayout(panelConfIzq, BoxLayout.Y_AXIS));
    
    panelDatos.setLayout(new GridLayout(4, 2, 5, 5));        

    lMaxBateria.setHorizontalAlignment(SwingConstants.RIGHT);
    lMaxBateria.setText("Maximo de bateria: ");
    panelDatos.add(lMaxBateria);

    panelDatos.add(tMaxBateria);

    lCasini.setHorizontalAlignment(SwingConstants.RIGHT);
    lCasini.setText("Casilla de inicio: ");
    panelDatos.add(lCasini);

    pCasIni.setLayout(new GridLayout(1, 4, 5, 5));

    lXini.setHorizontalAlignment(SwingConstants.RIGHT);
    lXini.setText("X: ");
    lXini.setToolTipText("null");
    pCasIni.add(lXini);

    pCasIni.add(tXini);

    lYini.setHorizontalAlignment(SwingConstants.RIGHT);
    lYini.setText("Y: ");
    lYini.setToolTipText("null");
    pCasIni.add(lYini);

    pCasIni.add(tYini);

    panelDatos.add(pCasIni);

    lCasMont.setHorizontalAlignment(SwingConstants.RIGHT);
    lCasMont.setText("Casilla de montaje");
    panelDatos.add(lCasMont);

    pCasMont.setLayout(new GridLayout(1, 4, 5, 5));

    lXmont.setHorizontalAlignment(SwingConstants.RIGHT);
    lXmont.setText("X: ");
    lXmont.setToolTipText("null");
    pCasMont.add(lXmont);

    pCasMont.add(tXmont);

    lYmont.setHorizontalAlignment(SwingConstants.RIGHT);
    lYmont.setText("Y: ");
    lYmont.setToolTipText("null");
    pCasMont.add(lYmont);

    pCasMont.add(tYmont);

    panelDatos.add(pCasMont);

    panelConfIzq.add(panelDatos);

    panelBusqueda.setLayout(new GridLayout(4, 1, 5, 5));

    panelBusqueda.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Tipo de busqueda"));
    lBusqueda.setText("Algoritmo de busqueda:");
    panelBusqueda.add(lBusqueda);

    cbBusqueda.setMaximumRowCount(4);
    cbBusqueda.setModel(new DefaultComboBoxModel(new String[] 
    { "Algoritmo A*", "Busqueda en anchura", "Ascension de colinas",
      "Busqueda en anchura (usando A*)", "Ascension de colinas (usando A*)"
    }));
    cbBusqueda.setMinimumSize(new Dimension(70, 24));
    cbBusqueda.setPreferredSize(new Dimension(70, 24));

    panelBusqueda.add(cbBusqueda);

    lHeuristica.setText("Heuristica: ");
    panelBusqueda.add(lHeuristica);

    cbHeuristica.setMaximumRowCount(3);
    cbHeuristica.setModel(new DefaultComboBoxModel(new String[] { "Distancia Manhattan", "Distancia Euclidea" }));
    panelBusqueda.add(cbHeuristica);

    panelConfIzq.add(panelBusqueda);

    panelMinimizar.setLayout(new GridLayout(2, 1, 5, 5));

    panelMinimizar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Minimizacion (algoritmo A*)"));
    rbTiempo.setText("Minimizar tiempo");
    bgMinimizar.add(rbTiempo);
    panelMinimizar.add(rbTiempo);

    rbEnergia.setText("Minimizar energia");
    bgMinimizar.add(rbEnergia);
    panelMinimizar.add(rbEnergia);

    panelConfIzq.add(panelMinimizar);
    panelConf.add(panelConfIzq);
    
    // Panel de inicializacion
    panelIni.setLayout(new GridLayout(6, 1, 5, 5));
    
    bgIni.add(rbIniAleat);
    bgIni.add(rbIniFich);
    
    panelIni.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Inicializacion"));
    
    cbInicializar = new JCheckBox("Inicializar nuevo tablero", true);
    
    panelIni.add(cbInicializar);
           
    panelInicializar = new JPanel(new GridLayout(2,2,5,5));
    
    lDimension.setHorizontalAlignment(SwingConstants.LEFT);
    lDimension.setText("Dimension:");
    panelInicializar.add(lDimension);
    
    panelInicializar.add(tDimension);
    
    panelIni.add(panelInicializar);
    
    rbIniAleat.setText("Inicializacion aleatoria:");
    panelIni.add(rbIniAleat);

    panelIniAleat.setLayout(new GridLayout(2, 2, 5, 5));

    panelIniAleat.setBorder(BorderFactory.createEtchedBorder());
    lNumEnchufes.setHorizontalAlignment(SwingConstants.RIGHT);
    lNumEnchufes.setText("Num. Enchufes: ");
    lNumEnchufes.setToolTipText("null");
    panelIniAleat.add(lNumEnchufes);

    panelIniAleat.add(tNumEnchufes);

    lNumObst.setHorizontalAlignment(SwingConstants.RIGHT);
    lNumObst.setText("Num. Obstaculos: ");
    panelIniAleat.add(lNumObst);

    panelIniAleat.add(tNumObst);

    panelIni.add(panelIniAleat);

    rbIniFich.setText("Inicializacion por fichero");
    panelIni.add(rbIniFich);

    panelIniFich.setLayout(new GridLayout(2, 2, 5, 5));

    panelIniFich.setBorder(BorderFactory.createEtchedBorder());
    lFichero.setText("Fichero:");
    panelIniFich.add(lFichero);

    panelIniFich.add(tFichero);

    panelIni.add(panelIniFich);

    panelConf.add(panelIni);

    ventConf.getContentPane().add(panelConf);
    
    ventConf.setVisible(false);
    ventConf.setResizable(false);
    ventConf.pack();
    
    bCalcular.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Programa.ejecutar();
        bCalcular.setEnabled(false);
        bEstadoIni.setEnabled(true);
        bEstadoFin.setEnabled(true);
        bPasoAPaso.setEnabled(true);
      }
    });
    
    bEstadoIni.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Programa.verPrimerEstado();
      }
    });
    
    bEstadoFin.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Programa.verUltimoEstado();
      }
    });
    
    bPasoAPaso.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Programa.mostrarCamino();
      }
    });
    
    bNuevo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        aplicarConfiguracion();
        bCalcular.setEnabled(true);
        bEstadoIni.setEnabled(false);
        bEstadoFin.setEnabled(false);
        bPasoAPaso.setEnabled(false);
        setError("");
        lvMetaActual.setText("");
        lvPosIni.setText("");
        lvPosFin.setText("");
        lvPosActual.setText("");
      }
    });
    
    bConfiguracion.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        ventConf.setVisible(true);
      }
    });
    
    bSalida.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        ventSalida.setVisible(true);
      }
    });
    
    chbVerTrazas.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        verTrazas = chbVerTrazas.isSelected();
      }
    });
  }

  private void exitForm(WindowEvent evt)
  {
    System.exit(0);
  }   
}
