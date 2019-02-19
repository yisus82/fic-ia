
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Configuracion extends JFrame {

    private Icon bender = new ImageIcon("robot.gif");
    
    private File fichero;

    private File directorio;

    private int estrategia;

    private int heuristica;

    private int variante;

    private int dimension;

    private int recarga;
    
    private int num_obs;

    private JButton bAceptar;

    private JButton bCancelar;

    private ButtonGroup buttonGroup1;

    private JLabel lEstrategia;

    private JComboBox cEstrategia;

    private JLabel lHeuristica;

    private JComboBox cHeuristica;

    private JLabel lVariante;

    private JComboBox cVariante;

    private JRadioButton rAmateur;

    private JRadioButton rExperto;

    private JLabel lRecarga;

    private JTextField tRecarga;

    private JLabel lDimension;

    private JTextField tDimension;
    
    private JCheckBox cGiros;

    private JLabel lObstaculos;

    private JTextField tObstaculos;

    public Configuracion() {

        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("robot.gif"));
        buttonGroup1 = new ButtonGroup();
        rAmateur = new JRadioButton();
        rExperto = new JRadioButton();
        lEstrategia = new JLabel("Método de búsqueda");
        cEstrategia = new JComboBox();
        lHeuristica = new JLabel("Función heurística");
        cHeuristica = new JComboBox();
        lVariante = new JLabel("Variante del problema");
        cVariante = new JComboBox();
        bAceptar = new JButton();
        bCancelar = new JButton();
        lDimension = new JLabel("Tamaño del planeta");
        tDimension = new JTextField();
        lRecarga = new JLabel("Recarga de energía");
        tRecarga = new JTextField();
        lObstaculos = new JLabel("Número de obstáculos");
        tObstaculos = new JTextField();
        cGiros = new JCheckBox("Permitir giros de 45º");
        directorio = new File(".");
        fichero = null;

        getContentPane().setLayout(null);

        setTitle("Opciones de Configuración");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
            }

        });

        rAmateur.setSelected(true);
        rAmateur.setText("Modo amateur (Elegir sólo el número de obstáculos)");
        buttonGroup1.add(rAmateur);

        getContentPane().add(rAmateur);
        rAmateur.setBounds(20, 160, 325, 40);
        rAmateur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tObstaculos.setEnabled(true);
            }
        });
        
        rExperto.setText("Modo experto (Elegir todas las posiciones)");
        rExperto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tObstaculos.setEnabled(false);
            }
        });
        buttonGroup1.add(rExperto);

        getContentPane().add(rExperto);
        rExperto.setBounds(20, 200, 300, 40);

        getContentPane().add(lEstrategia);
        lEstrategia.setBounds(20, 10, 150, 25);

        cEstrategia.setModel(new DefaultComboBoxModel(new String[] {
                "Algoritmo A*", "Anchura", "Hill-Climbing" }));
        cEstrategia.setName("Estrategia de búsqueda");
        cEstrategia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cHeuristica.setEnabled(cEstrategia.getSelectedIndex() != 1);
                cVariante.setEnabled(cEstrategia.getSelectedIndex() != 1);
                tRecarga.setEnabled((cEstrategia.getSelectedIndex() != 1) && 
                		(cVariante.getSelectedIndex() != 0));
            }
        });

        getContentPane().add(cEstrategia);
        cEstrategia.setBounds(170, 10, 150, 25);

        getContentPane().add(lHeuristica);
        lHeuristica.setBounds(20, 60, 150, 25);

        cHeuristica.setModel(new DefaultComboBoxModel(
                new String[] { "Manhattan", "Euclídea", "Distancia Media",
                        "Distancia Máxima" }));
        cHeuristica.setName("Heurística");

        getContentPane().add(cHeuristica);
        cHeuristica.setBounds(170, 60, 150, 25);

        getContentPane().add(lVariante);
        lVariante.setBounds(20, 110, 150, 25);

        cVariante.setModel(new DefaultComboBoxModel(new String[] {
                "Minimizar tiempo", "Maximizar avance", "Maximizar giros" }));
        cVariante.setName("Variante del problema");
        cVariante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tRecarga.setEnabled(cVariante.getSelectedIndex() != 0);
            }
        });

        getContentPane().add(cVariante);
        cVariante.setBounds(170, 110, 150, 25);

        bAceptar.setText("Aceptar");

        bAceptar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                bAceptarMouseClicked(evt);
            }
        });

        getContentPane().add(bAceptar);
        bAceptar.setBounds(375, 180, 100, 26);

        bCancelar.setText("Cancelar");
        bCancelar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                bCancelarMouseClicked(evt);
            }
        });

        getContentPane().add(bCancelar);
        bCancelar.setBounds(500, 180, 100, 26);

        getContentPane().add(lDimension);
        lDimension.setBounds(350, 10, 175, 20);

        tDimension.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tDimensionMouseClicked(evt);
            }
        });

        getContentPane().add(tDimension);
        tDimension.setBounds(480, 10, 175, 20);

        getContentPane().add(lRecarga);
        lRecarga.setBounds(350, 40, 175, 20);

        tRecarga.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tRecargaMouseClicked(evt);
            }
        });
        
        getContentPane().add(tRecarga);
        tRecarga.setBounds(480, 40, 175, 20);
        tRecarga.setEnabled(false);
        
        getContentPane().add(lObstaculos);
        lObstaculos.setBounds(350, 70, 175, 20);

        tObstaculos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tObstaculosMouseClicked(evt);
            }
        });
        
        getContentPane().add(tObstaculos);
        tObstaculos.setBounds(480, 70, 175, 20);

        getContentPane().add(cGiros);
        cGiros.setBounds(345, 110, 200, 20);

        pack();

    }

    private void tDimensionMouseClicked(MouseEvent evt) {
        tDimension.setText("");
    }

    private void tRecargaMouseClicked(MouseEvent evt) {
        tRecarga.setText("");
    }
    
    private void tObstaculosMouseClicked(MouseEvent evt) {
        tObstaculos.setText("");
    }

    private void bCancelarMouseClicked(MouseEvent evt) {
        System.exit(0);
    }

    private void exitForm(WindowEvent evt) {
        System.exit(0);
    }

    private void bAceptarMouseClicked(java.awt.event.MouseEvent evt) {

        boolean girar = false;
        estrategia = cEstrategia.getSelectedIndex();
        heuristica = cHeuristica.getSelectedIndex();
        variante = cVariante.getSelectedIndex();

        try {
            dimension = (new Integer(tDimension.getText())).intValue();
            if (tRecarga.isEnabled())
            	recarga = (new Integer(tRecarga.getText())).intValue();
            else recarga = 0;
            if (tObstaculos.isEnabled())
                num_obs = (new Integer(tObstaculos.getText())).intValue();
            if ((dimension < 1) || ((recarga < 2) && (tRecarga.isEnabled()))) {
                JOptionPane.showMessageDialog(this,
                        "Los valores numéricos no son válidos", "Error",
                        JOptionPane.ERROR_MESSAGE, bender);
            } else {
                if (cGiros.isSelected()) girar = true;

                if (rExperto.isSelected()) {
                    if (dimension < 2) {
                        JOptionPane.showMessageDialog(this,
                                "No hay suficientes casillas", "Error",
                                JOptionPane.ERROR_MESSAGE, bender);
                    } else {
                        setVisible(false);
                        JFileChooser ficheros;
                        ficheros = new JFileChooser(directorio);
                        if (fichero != null) ficheros.setSelectedFile(fichero);
                        int aux;
                        if ((aux = ficheros.showOpenDialog(this)) == JFileChooser.CANCEL_OPTION)
                            this.setVisible(true);
                        else {
                            fichero = ficheros.getSelectedFile();
                            directorio = fichero.getParentFile();
                            Vector obstaculos = new Vector();
                            int tipo = 0;
                            int posicionx = 0;
                            int posiciony = 0;
                            int destinox = 0;
                            int destinoy = 0;
                            int robotx = 0;
                            int roboty = 0;
                            int fase = 0;
                            boolean error = false;

                            try {
                                BufferedReader f = new BufferedReader(
                                        new FileReader(fichero));
                                String linea = f.readLine();
                                int ini = 0;
                                while (linea != null) {
                                    StringTokenizer st = new StringTokenizer(
                                            linea);
                                    Vector v = new Vector();
                                    for (; st.hasMoreTokens();) {
                                        v.add(st.nextToken());
                                    }
                                    if (v.size() == 1) {
                                        if (v.elementAt(0).equals("robot:")) {
                                            fase = 1;
                                            ini = 1;
                                        } else if (v.elementAt(0).equals(
                                                "obstaculo:"))
                                        {
                                            fase = 3;
                                            ini = 1;
                                        } else System.out
                                                .println("Error al leer el fichero. "
                                                        + "Token '"
                                                        + v.elementAt(0)
                                                        + "' no valido");
                                    } else if (v.size() == 0) fase = 4;
                                    if (fase == 0)
                                            System.out
                                                    .println("Error al leer el fichero");
                                    if (fase == 1) {
                                        if (v.size() != 2) {
                                            if (ini == 1)
                                                ini = 0;
                                            else System.out
                                                    .println("Error al leer el fichero");
                                        } else {
                                            robotx = (new Integer((String) v
                                                    .elementAt(0))).intValue();
                                            if ((robotx > dimension - 1)
                                                    || (robotx < 0)) {
                                                    JOptionPane
                                                            .showMessageDialog(
                                                                    this,
                                                                    "Posición no válida (" + robotx
                                                                    + "," + roboty +")",
                                                                    "Error",
                                                                    JOptionPane.ERROR_MESSAGE,
                                                                    bender);
                                                    error = true;
                                            }
                                            roboty = (new Integer((String) v
                                                    .elementAt(1))).intValue();
                                            if ((roboty > dimension - 1)
                                                    || (roboty < 0)) {
                                                    JOptionPane
                                                            .showMessageDialog(
                                                                    this,
                                                                    "Posición no válida (" + robotx
                                                                    + "," + roboty +")",
                                                                    "Error",
                                                                    JOptionPane.ERROR_MESSAGE,
                                                                    bender);
                                                    error = true;
                                            }
                                            fase = 2;
                                        }
                                    }
                                    if (fase == 2) {
                                        if (v.size() != 2) {
                                            if (ini == 1)
                                                ini = 0;
                                            else System.out
                                                    .println("Error al leer el fichero");
                                        } else {
                                            destinox = (new Integer((String) v
                                                    .elementAt(0))).intValue();
                                            if ((destinox > dimension - 1)
                                                    || (destinox < 0)) {
                                                    JOptionPane
                                                            .showMessageDialog(
                                                                    this,
                                                                    "Posición no válida (" + destinox
                                                                    + "," + destinoy +")",
                                                                    "Error",
                                                                    JOptionPane.ERROR_MESSAGE,
                                                                    bender);
                                                    error = true;
                                            }
                                            destinoy = (new Integer((String) v
                                                    .elementAt(1))).intValue();
                                            if ((destinoy > dimension - 1)
                                                    || (destinoy < 0)) {
                                                    JOptionPane
                                                            .showMessageDialog(
                                                                    this,
                                                                    "Posición no válida (" + destinox
                                                                    + "," + destinoy +")",
                                                                    "Error",
                                                                    JOptionPane.ERROR_MESSAGE,
                                                                    bender);
                                                    error = true;
                                            }
                                        }
                                    }
                                    if (fase == 3) {
                                        if (v.size() != 2) {
                                            if (ini == 1)
                                                ini = 0;
                                            else System.out
                                                    .println("Error al leer el fichero");
                                        } else {
                                            posicionx = (new Integer((String) v
                                                    .elementAt(0))).intValue();
                                            if ((posicionx > dimension - 1)
                                                    || (posicionx < 0)) {
                                                    JOptionPane
                                                            .showMessageDialog(
                                                                    this,
                                                                    "Posición no válida (" + posicionx
                                                                    + "," + posiciony +")",
                                                                    "Error",
                                                                    JOptionPane.ERROR_MESSAGE,
                                                                    bender);
                                                    error = true;
                                            }
                                            posiciony = (new Integer((String) v
                                                    .elementAt(1))).intValue();
                                            if ((posiciony > dimension - 1)
                                                    || (posiciony < 0)) {
                                                    JOptionPane
                                                            .showMessageDialog(
                                                                    this,
                                                                    "Posición no válida (" + posicionx
                                                                    + "," + posiciony +")",
                                                                    "Error",
                                                                    JOptionPane.ERROR_MESSAGE,
                                                                    bender);
                                                    error = true;
                                            }
                                            obstaculos.add(new Obstaculo(
                                                    new Posicion(posicionx,
                                                            posiciony),
                                                    (tipo++) % 2));
                                        }
                                    }
                                    linea = f.readLine();
                                }
                                f.close();

                            } catch (FileNotFoundException e) {
                                System.err.println("Error: fichero " + fichero
                                        + " no encontrado.");
                            } catch (IOException e) {
                                System.err
                                        .println("Error en lectura del fichero "
                                                + fichero + ": " + e.getMessage());
                            }
                            if (!error) {
                                if (aux == JFileChooser.APPROVE_OPTION) {
                                    new Ventana(estrategia,
                                            heuristica, variante, dimension,
                                            recarga, robotx, roboty, destinox,
                                            destinoy, obstaculos, girar, this);
                                }
                            }
                            else this.setVisible(true);
                        }
                    }
                }
                else {
                    new Ventana(estrategia, heuristica, variante,
                            dimension, recarga, num_obs, girar, this); 
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Los campos de texto deben ser numéricos", "Error",
                    JOptionPane.ERROR_MESSAGE, bender);
        }

    }

}