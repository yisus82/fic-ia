
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Conclusion implements ActionListener {

    private Ventana ventana;

    private Configuracion configuracion;

    private JFrame reinicio;

    public Conclusion(Configuracion configuracion, Ventana ventana,
            JFrame reinicio)
    {
        this.configuracion = configuracion;
        this.ventana = ventana;
        this.reinicio = reinicio;
    }

    public void actionPerformed(ActionEvent e) {

        if (reinicio != null) reinicio.setVisible(false);
        if (ventana != null) ventana.setVisible(false);
        if (configuracion != null) configuracion.setVisible(true);

    }

}