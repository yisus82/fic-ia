import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class Calcular extends JFrame{

	int origenx = 0;
	int origeny = 0;
	int destinox = 0;
	int destinoy = 0;
	JTextField tOrigenx = new JTextField(10);
	JTextField tOrigeny = new JTextField(10);
	JTextField tDestinox = new JTextField(10);
	JTextField tDestinoy = new JTextField(10);
	JTextField tResultado = new JTextField(20);
	JButton bCalcular = new JButton("Calcular");
	
	public Calcular(){
		
		setSize(300,200);
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(tOrigenx);
		getContentPane().add(tOrigeny);
		getContentPane().add(tDestinox);
		getContentPane().add(tDestinoy);
		getContentPane().add(tResultado);
		getContentPane().add(bCalcular);
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }

        });
		bCalcular.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                bCalcularMouseClicked(evt);
            }

			private void bCalcularMouseClicked(MouseEvent evt) {
				origenx = (new Integer(tOrigenx.getText())).intValue();
				origeny = (new Integer(tOrigeny.getText())).intValue();
				destinox = (new Integer(tDestinox.getText())).intValue();
				destinoy = (new Integer(tDestinoy.getText())).intValue();
				tResultado.setText("" + Math.sqrt(Math.pow(origenx - destinox, 2)
		                + Math.pow(origeny - destinoy, 2)));
			}
        });
		
	}

}
