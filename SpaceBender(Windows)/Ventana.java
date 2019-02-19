
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana extends JFrame {
    
    private Icon bender = new ImageIcon("robot.gif");

    private JFrame reinicio = null;

    private JFrame ayuda = null;

    private Configuracion configuracion;

    private int estrategia;

    private int heuristica;

    private int dimension;

    private int i;

    private int j;

    private int k;

    private int robotx;

    private int roboty;

    private int destinox;

    private int destinoy;

    private int paso = 0;

    private JPanel panel = new JPanel();

    private JPanel panel2 = new JPanel();

    private JLabel mapa[][];

    private Vector obstaculos;

    private Vector solucion = null;

    public static Robot robot = null;

    private FuncionCoste g;

    private Heuristica h;
    
    private int variante;

    private Algoritmo algoritmo;

    private TextArea texto;

    private JTextField tRecarga;

    private JTextField tEnergia;

    private JTextField tInicial;

    private JTextField tFinal;

    private JTextField tAvances;

    private JTextField tGiros;
    
    private JCheckBox cTraza;

    private int casillas[][];

    private int libres = 0;

    private String imagenes[] = { "vacio.gif", "origen.gif", "destino.gif",
            "robot.gif", "crater.gif", "roca.gif" };

    private ImageIcon icono;

    private Image imagen;

    private int ancho;

    private int alto;
    
    private int giros = 0;
    
    private int avances = 0;
    
    private int recargada = 0;
    
    private int energia = 0;

    // MODO AMATEUR

    public Ventana(int estrategia, int heuristica, int variante, int dimension,
            int recarga, int num_obs, boolean girar, Configuracion configuracion)
    {

        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("robot.gif"));
        setTitle("PRACTICA ROBOT IA");
        this.estrategia = estrategia;
        this.heuristica = heuristica;
        this.variante = variante;
        this.dimension = dimension;
        this.configuracion = configuracion;
        getContentPane().setLayout(new GridLayout(1, 1));
        panel.setLayout(new GridLayout(dimension, dimension));
        panel.setSize(512, 720);
        ancho = panel.getWidth() / dimension;
        alto = panel.getHeight() / dimension;
        panel2.setSize(512, 720);
        getContentPane().add(panel);
        getContentPane().add(panel2);
        mapa = new JLabel[dimension][dimension];
        casillas = new int[dimension][dimension];
        for (i = 0; i < dimension; i++)
            for (j = 0; j < dimension; j++) {
                mapa[i][j] = new JLabel();
                casillas[i][j] = 0;
                libres++;
            }
        Random rand = new Random();
        for (;;) {
            robotx = Math.abs(rand.nextInt() % dimension);
            roboty = Math.abs(rand.nextInt() % dimension);
            if (casillas[robotx][roboty] == 0) {
                casillas[robotx][roboty] = 3;
                libres--;
                break;
            }
        }
        rand = new Random();
        for (;;) {
            destinox = Math.abs(rand.nextInt() % dimension);
            destinoy = Math.abs(rand.nextInt() % dimension);
            if (casillas[destinox][destinoy] == 0) {
                casillas[destinox][destinoy] = 2;
                libres--;
                break;
            }
        }
        rand = new Random();
        k = 0;
        obstaculos = null;
        if (num_obs > libres) {
            JOptionPane.showMessageDialog(configuracion,
                    "No hay suficientes casillas para poner los obstáculos",
                    "Error", JOptionPane.ERROR_MESSAGE, bender);
            this.setVisible(false);
            configuracion.setVisible(true);
        }
        else if (num_obs > 0) {
            obstaculos = new Vector();
            for (;;) {
                i = Math.abs(rand.nextInt() % dimension);
                j = Math.abs(rand.nextInt() % dimension);
                if (casillas[i][j] == 0) {
                    casillas[i][j] = 4 + (k % 2);
                    obstaculos.add(new Obstaculo(new Posicion(i, j), (k % 2)));
                    k++;
                    libres--;
                }
                if (k == num_obs) break;
            }
        }
        robot = new Robot(new Posicion(robotx, roboty), girar, recarga,
                variante, casillas, estrategia);
        for (i = 0; i < dimension; i++) {
            for (j = 0; j < dimension; j++) {
                imagen = Toolkit.getDefaultToolkit().getImage(
                        imagenes[casillas[i][j]]);
                imagen = imagen.getScaledInstance(ancho, alto,
                        Image.SCALE_DEFAULT);
                icono = new ImageIcon(imagen);
                mapa[i][j].setIcon(icono);
                mapa[i][j].setBorder(BorderFactory.createLineBorder(
                        Color.black, 1));
                panel.add(mapa[i][j]);
            }
        }
        JButton bFinal = new JButton("Ir al final");
        bFinal.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                bFinalMouseClicked(evt);
            }
        });
        JButton bPrincipio = new JButton("Ir al principio");
        bPrincipio.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                bPrincipioMouseClicked(evt);
            }
        });
        JButton bConfiguracion = new JButton("Configuración");
        bConfiguracion.addActionListener(new Conclusion(configuracion, this,
                null));
        texto = new TextArea(26, 40);
        texto.setEditable(false);
        JButton bSiguiente = new JButton("Siguiente");
        bSiguiente.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                bSiguienteMouseClicked(evt);
            }
        });
        JButton bAyuda = new JButton("Ayuda");
        bAyuda.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                bAyudaMouseClicked(evt);
            }
        });
        JLabel lRecarga = new JLabel("Energía recargada: ");
        JLabel lEnergia = new JLabel("Energía consumida: ");
        JLabel lInicial = new JLabel("Posición inicial: ");
        JLabel lFinal = new JLabel("Posición final: ");
        JLabel lAvances = new JLabel("Avances: ");
        JLabel lGiros = new JLabel("Giros: ");
        tAvances = new JTextField("0", 5);
        tAvances.setEditable(false);
        tGiros = new JTextField("0", 5);
        tGiros.setEditable(false);
        tRecarga = new JTextField("0", 5);
        tRecarga.setEditable(false);
        tEnergia = new JTextField("0", 5);
        tEnergia.setEditable(false);
        tInicial = new JTextField(" ", 7);
        tInicial.setEditable(false);
        tFinal = new JTextField(" ", 7);
        tFinal.setEditable(false);
        tInicial.setText((new Posicion(robotx, roboty)).toString());
        tFinal.setText((new Posicion(destinox, destinoy)).toString());
        cTraza =  new JCheckBox("Ver trazas");
        panel2.add(lRecarga);
        panel2.add(tRecarga);
        panel2.add(lEnergia);
        panel2.add(tEnergia);
        panel2.add(lInicial);
        panel2.add(tInicial);
        panel2.add(lFinal);
        panel2.add(tFinal);
        panel2.add(lAvances);
        panel2.add(tAvances);
        panel2.add(lGiros);
        panel2.add(tGiros);
        panel2.add(texto);
        panel2.add(bPrincipio);
        panel2.add(bFinal);
        panel2.add(bSiguiente);
        panel2.add(bConfiguracion);
        panel2.add(bAyuda);
        panel2.add(cTraza);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        this.setSize(800, 600);
        this.setVisible(true);
        configuracion.setVisible(false);

    }

    //  MODO EXPERTO

    public Ventana(int estrategia, int heuristica, int variante, int dimension,
            int recarga, int robotx, int roboty, int destinox, int destinoy,
            Vector obstaculos, boolean girar, Configuracion configuracion)
    {

        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("robot.gif"));
        this.estrategia = estrategia;
        this.heuristica = heuristica;
        this.variante = variante;
        this.dimension = dimension;
        this.robotx = robotx;
        this.roboty = roboty;
        this.destinox = destinox;
        this.destinoy = destinoy;
        this.obstaculos = obstaculos;
        this.configuracion = configuracion;
        this.setTitle("PRACTICA ROBOT IA");
        getContentPane().setLayout(new GridLayout(1, 1));
        panel.setLayout(new GridLayout(dimension, dimension));
        panel.setSize(512, 720);
        ancho = panel.getWidth() / dimension;
        alto = panel.getHeight() / dimension;
        panel2.setSize(512, 720);
        getContentPane().add(panel);
        getContentPane().add(panel2);
        JButton bFinal = new JButton("Ir al final");
        bFinal.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                bFinalMouseClicked(evt);
            }
        });
        JButton bPrincipio = new JButton("Ir al principio");
        bPrincipio.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                bPrincipioMouseClicked(evt);
            }
        });
        JButton bConfiguracion = new JButton("Configuración");
        bConfiguracion.addActionListener(new Conclusion(configuracion, this,
                null));
        texto = new TextArea(26, 40);
        texto.setEditable(false);
        JButton bSiguiente = new JButton("Siguiente");
        bSiguiente.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                bSiguienteMouseClicked(evt);
            }
        });
        JButton bAyuda = new JButton("Ayuda");
        bAyuda.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                bAyudaMouseClicked(evt);
            }
        });
        JLabel lRecarga = new JLabel("Energía recargada: ");
        JLabel lEnergia = new JLabel("Energía consumida: ");
        JLabel lInicial = new JLabel("Posición inicial: ");
        JLabel lFinal = new JLabel("Posición final: ");
        JLabel lAvances = new JLabel("Avances: ");
        JLabel lGiros = new JLabel("Giros: ");
        tAvances = new JTextField("0", 5);
        tAvances.setEditable(false);
        tGiros = new JTextField("0", 5);
        tGiros.setEditable(false);
        tRecarga = new JTextField("0", 5);
        tRecarga.setEditable(false);
        tEnergia = new JTextField("0", 5);
        tEnergia.setEditable(false);
        tInicial = new JTextField(" ", 7);
        tInicial.setEditable(false);
        tFinal = new JTextField(" ", 7);
        tFinal.setEditable(false);
        cTraza =  new JCheckBox("Ver trazas");
        panel2.add(lRecarga);
        panel2.add(tRecarga);
        panel2.add(lEnergia);
        panel2.add(tEnergia);
        panel2.add(lInicial);
        panel2.add(tInicial);
        panel2.add(lFinal);
        panel2.add(tFinal);
        panel2.add(lAvances);
        panel2.add(tAvances);
        panel2.add(lGiros);
        panel2.add(tGiros);
        panel2.add(texto);
        panel2.add(bPrincipio);
        panel2.add(bFinal);
        panel2.add(bSiguiente);
        panel2.add(bConfiguracion);
        panel2.add(bAyuda);
        panel2.add(cTraza);
        mapa = new JLabel[dimension][dimension];
        casillas = new int[dimension][dimension];
        for (i = 0; i < dimension; i++)
            for (j = 0; j < dimension; j++)
                casillas[i][j] = 0;
        casillas[robotx][roboty] = 3;
        casillas[destinox][destinoy] = 2;
        Obstaculo obs;
        for (i = 0; i < obstaculos.size(); i++) {
            obs = (Obstaculo) obstaculos.elementAt(i);
            casillas[obs.getPosicion().x][obs.getPosicion().y] = 4 + obs
                    .getTipo();
        }
        robot = new Robot(new Posicion(robotx, roboty), girar, recarga,
                variante, casillas, estrategia);
        tInicial.setText((new Posicion(robotx, roboty)).toString());
        tFinal.setText((new Posicion(destinox, destinoy)).toString());
        for (i = 0; i < dimension; i++) {
            for (j = 0; j < dimension; j++) {
                mapa[i][j] = new JLabel();
                imagen = Toolkit.getDefaultToolkit().getImage(
                        imagenes[casillas[i][j]]);
                imagen = imagen.getScaledInstance(ancho, alto,
                        Image.SCALE_DEFAULT);
                icono = new ImageIcon(imagen);
                mapa[i][j].setIcon(icono);
                mapa[i][j].setBorder(BorderFactory.createLineBorder(
                        Color.black, 1));
                panel.add(mapa[i][j]);
            }
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        this.setSize(800, 600);
        this.setVisible(true);
        configuracion.setVisible(false);

    }
    
    public boolean verTrazas() {
        return cTraza.isSelected();
    }

    private void bSiguienteMouseClicked(MouseEvent evt) {

        if (solucion == null) {
            calcular();
            paso = 1;
        }
        else if (paso == solucion.size()) {
            if (reinicio == null) {
                reinicio = new JFrame("Destino alcanzado");
                reinicio.setSize(300, 75);
                reinicio.setResizable(false);
                reinicio.setIconImage(Toolkit.getDefaultToolkit().getImage(
                        "robot.gif"));
                JButton principio = new JButton("Ir al principio");
                JButton conf = new JButton("Configuración");
                reinicio.getContentPane().setLayout(new FlowLayout());
                conf.addActionListener(new Conclusion(configuracion, this,
                        reinicio));
                principio.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        reinicio.setVisible(false);
                        bPrincipioMouseClicked(evt);
                    }
                });
                reinicio.getContentPane().add(principio);
                reinicio.getContentPane().add(conf);
            }
            reinicio.setVisible(true);
        } else {
            dibujar((Nodo) solucion.get(paso));
            paso++;
        }

    }

    private void bPrincipioMouseClicked(MouseEvent evt) {

        if (solucion == null) calcular();
        avances = 0;
        tAvances.setText("0");
        giros = 0;
        tGiros.setText("0");
        recargada = 0;
        tRecarga.setText("0");
        energia = 0;
        tEnergia.setText("0");
        Posicion pos_robot_old = robot.getPosicion();
        robot.setPosicion(new Posicion(robotx, roboty));
        casillas[pos_robot_old.x][pos_robot_old.y] = 0;
        casillas[robotx][roboty] = 3;
        casillas[destinox][destinoy] = 2;
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++) {
                imagen = Toolkit.getDefaultToolkit().getImage(
                        imagenes[casillas[i][j]]);
                imagen = imagen.getScaledInstance(ancho, alto,
                        Image.SCALE_DEFAULT);
                icono = new ImageIcon(imagen);
                mapa[i][j].setIcon(icono);
            }
        repaint();
        paso = 1;

    }

    private void bFinalMouseClicked(MouseEvent evt) {
        
        if (solucion == null) calcular();
        for (int i = paso; i < solucion.size(); i++) {
            Nodo n = (Nodo) solucion.get(i);
            recargada += n.getEnergiaRecargada();
            energia += n.getEnergiaConsumida();
            avances += n.getAvances();
            giros += n.getGiros();
        }
        tRecarga.setText((new Integer(recargada)).toString());
        tEnergia.setText((new Integer(energia)).toString());
        tAvances.setText((new Integer(avances)).toString());
        tGiros.setText((new Integer(giros)).toString());
        Posicion pos_robot_old = robot.getPosicion();
        Posicion pos_robot_new = new Posicion(destinox, destinoy);
        robot.setPosicion(pos_robot_new);
        if (casillas[robotx][roboty] != 1)
            casillas[robotx][roboty] = 1;
        else casillas[pos_robot_old.x][pos_robot_old.y] = 0;
        casillas[pos_robot_new.x][pos_robot_new.y] = 3;
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++) {
                imagen = Toolkit.getDefaultToolkit().getImage(
                        imagenes[casillas[i][j]]);
                imagen = imagen.getScaledInstance(ancho, alto,
                        Image.SCALE_DEFAULT);
                icono = new ImageIcon(imagen);
                mapa[i][j].setIcon(icono);
            }
        repaint();
        paso = solucion.size();

    }

    public void bAyudaMouseClicked(MouseEvent evt) {

        JLabel lOrigen = new JLabel("Origen", new ImageIcon("origen.gif"),
                JLabel.TRAILING);

        JLabel lDestino = new JLabel("Destino", new ImageIcon("destino.gif"),
                JLabel.TRAILING);

        JLabel lRobot = new JLabel("Robot", new ImageIcon("robot.gif"),
                JLabel.TRAILING);

        JLabel lCrater = new JLabel("Cráter", new ImageIcon("crater.gif"),
                JLabel.TRAILING);

        JLabel lRoca = new JLabel("Roca", new ImageIcon("roca.gif"),
                JLabel.TRAILING);

        JLabel lVacio = new JLabel("Vacío", new ImageIcon("vacio.gif"),
                JLabel.TRAILING);

        if (ayuda == null) {
            ayuda = new JFrame("Ayuda");
            ayuda.setSize(500, 400);
            ayuda.setResizable(false);
            ayuda.setIconImage(Toolkit.getDefaultToolkit()
                    .getImage("robot.gif"));
            ayuda.getContentPane().setLayout(new GridLayout(2, 3));
            ayuda.getContentPane().add(lOrigen);
            ayuda.getContentPane().add(lDestino);
            ayuda.getContentPane().add(lRobot);
            ayuda.getContentPane().add(lCrater);
            ayuda.getContentPane().add(lRoca);
            ayuda.getContentPane().add(lVacio);
        }
        ayuda.setVisible(true);
    }

    public void escribir(String s) {

        texto.append(s + "\n");
        System.out.println(s);

    }

    private void dibujar(Nodo n) {

        if (n != null) {
            recargada += n.getEnergiaRecargada();
            tRecarga.setText((new Integer(recargada)).toString());
            energia += n.getEnergiaConsumida();
            tEnergia.setText((new Integer(energia)).toString());
            avances += n.getAvances();
            tAvances.setText((new Integer(avances)).toString());
            giros += n.getGiros();
            tGiros.setText((new Integer(giros)).toString());
            Posicion pos_robot_old = robot.getPosicion();
            Posicion pos_robot_new = n.getPosicion();
            robot.setPosicion(pos_robot_new);
            if (casillas[robotx][roboty] != 1)
                casillas[robotx][roboty] = 1;
            else casillas[pos_robot_old.x][pos_robot_old.y] = 0;
            casillas[pos_robot_new.x][pos_robot_new.y] = 3;
            for (int i = 0; i < dimension; i++)
                for (int j = 0; j < dimension; j++) {
                    imagen = Toolkit.getDefaultToolkit().getImage(
                            imagenes[casillas[i][j]]);
                    imagen = imagen.getScaledInstance(ancho, alto,
                            Image.SCALE_DEFAULT);
                    icono = new ImageIcon(imagen);
                    mapa[i][j].setIcon(icono);
                }
            repaint();
        }

    }

    private void calcular() {

        switch (heuristica) {
            case (0):
                h = new Manhattan();
                break;
            case (1):
                h = new DistanciaEuclidea();
                break;
            case (2):
                h = new DistanciaMedia();
                break;
            case (3):
                h = new DistanciaMaxima();
                break;
        }
        Posicion destino = new Posicion(destinox, destinoy);
        switch (estrategia) {
            case (0): //A*
            	if (variante == 0)
            		g = new MinimizarTiempo();
            	else g = new MaximizarEnergia();
                break;
            case (1): //Anchura
                h = new FuncionCero();
                g = new MinimizarTiempo();
                break;
            case (2): //Hill-Climbing
                g = new CosteCero();
                break;
        }

        algoritmo = new BusquedaAEstrella(h, g, robot, destino, this, estrategia);
        solucion = algoritmo.aplicar();

        escribir("Solución: " + solucion);
        int energiaRecargada = 0;
        for (int i = 0; i < solucion.size(); i++) {
            Nodo n = (Nodo) solucion.get(i);
            energiaRecargada += n.getEnergiaRecargada();
        }
        escribir("Energía recargada: " + energiaRecargada);

    }

}