import java.awt.Point;
import java.util.Random;
import java.util.Vector;

public class Generar {

	public static void main(String[] args) {

		Vector obstaculos = new Vector();
		Vector origenes = new Vector();
		Vector destinos = new Vector();
		Point posicion;
		int posx;
		int posy;
		int num_pos = 45;
		int dimension = 20;

		obstaculos.add(new Point(0, 0));
		obstaculos.add(new Point(0, 1));
		obstaculos.add(new Point(0, 7));
		obstaculos.add(new Point(1, 11));
		obstaculos.add(new Point(2, 1));
		obstaculos.add(new Point(2, 9));
		obstaculos.add(new Point(2, 11));
		obstaculos.add(new Point(3, 7));
		obstaculos.add(new Point(3, 8));
		obstaculos.add(new Point(3, 16));
		obstaculos.add(new Point(4, 8));
		obstaculos.add(new Point(4, 9));
		obstaculos.add(new Point(4, 19));
		obstaculos.add(new Point(5, 12));
		obstaculos.add(new Point(5, 13));
		obstaculos.add(new Point(6, 4));
		obstaculos.add(new Point(6, 5));
		obstaculos.add(new Point(6, 9));
		obstaculos.add(new Point(6, 13));
		obstaculos.add(new Point(6, 15));
		obstaculos.add(new Point(7, 10));
		obstaculos.add(new Point(8, 0));
		obstaculos.add(new Point(8, 11));
		obstaculos.add(new Point(8, 12));
		obstaculos.add(new Point(8, 16));
		obstaculos.add(new Point(8, 18));
		obstaculos.add(new Point(9, 1));
		obstaculos.add(new Point(9, 4));
		obstaculos.add(new Point(9, 13));
		obstaculos.add(new Point(9, 15));
		obstaculos.add(new Point(10, 16));
		obstaculos.add(new Point(11, 3));
		obstaculos.add(new Point(11, 9));
		obstaculos.add(new Point(12, 16));
		obstaculos.add(new Point(13, 4));
		obstaculos.add(new Point(13, 17));
		obstaculos.add(new Point(14, 8));
		obstaculos.add(new Point(14, 10));
		obstaculos.add(new Point(14, 18));
		obstaculos.add(new Point(15, 4));
		obstaculos.add(new Point(15, 12));
		obstaculos.add(new Point(15, 17));
		obstaculos.add(new Point(16, 0));
		obstaculos.add(new Point(16, 4));
		obstaculos.add(new Point(16, 9));
		obstaculos.add(new Point(17, 1));
		obstaculos.add(new Point(17, 5));
		obstaculos.add(new Point(17, 9));
		obstaculos.add(new Point(18, 12));
		obstaculos.add(new Point(19, 3));

		Random rand = new Random();
		for (int i = 0; i < num_pos; i++) {
			for (;;) {
				posx = Math.abs(rand.nextInt() % dimension);
				posy = Math.abs(rand.nextInt() % dimension);
				posicion = new Point(posx, posy);
				if ((!obstaculos.contains(posicion))
						&& (!origenes.contains(posicion))) {
					origenes.add(posicion);
					break;
				}
			}
		}

		rand = new Random();
		for (int i = 0; i < num_pos; i++) {
			for (;;) {
				posx = Math.abs(rand.nextInt() % dimension);
				posy = Math.abs(rand.nextInt() % dimension);
				posicion = new Point(posx, posy);
				if ((!obstaculos.contains(posicion))
						&& (!origenes.elementAt(i).equals(posicion))
						&& (!destinos.contains(posicion))) {
					destinos.add(posicion);
					break;
				}
			}
		}

		for (int i = 0; i < num_pos; i++) {
			System.out.println(origenes.elementAt(i) + "   "
					+ destinos.elementAt(i));
		}

	}

}