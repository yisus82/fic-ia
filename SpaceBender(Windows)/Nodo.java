import java.util.Vector;

public class Nodo {

	public static final int AESTRELLA = 0;

	public static final int ANCHURA = 1;

	public static final int COLINAS = 2;

	private Nodo padre;

	private Vector sucesores;

	private Posicion posicion;

	private int energiaRecargada;

	private int energiaConsumida;

	private float g;

	private float h;

	private Posicion avance;

	private float f;

	private int giros;

	private int avances;

	private int algoritmo;

	public Nodo(Posicion posicion, int energiaRecargada, int energiaConsumida,
			float g, float h, Posicion avance, int algoritmo) {

		this.posicion = posicion;
		this.energiaConsumida = energiaConsumida;
		this.energiaRecargada = energiaRecargada;
		this.g = g;
		this.h = h;
		f = g + h;
		sucesores = new Vector();
		padre = null;
		this.avance = avance;
		this.algoritmo = algoritmo;
		giros = 0;
		avances = 0;

	}

	public Nodo(Posicion posicion, Posicion avance, int algoritmo) {

		this.posicion = posicion;
		this.energiaConsumida = 0;
		this.energiaRecargada = 0;
		this.g = 0;
		this.h = 0;
		this.f = 0;
		padre = null;
		this.avance = avance;
		sucesores = new Vector();
		this.algoritmo = algoritmo;
		giros = 0;
		avances = 0;

	}

	public Posicion getPosicion() {
		return posicion;
	}

	public int getEnergiaConsumida() {
		return energiaConsumida;
	}

	public int getEnergiaRecargada() {
		return energiaRecargada;
	}

	public Posicion getAvance() {
		return avance;
	}

	public float getG() {
		return g;
	}

	public float getH() {
		return h;
	}

	public float getF() {
		return f;
	}

	public Nodo getPadre() {
		return padre;
	}

	public Vector getSucesores() {
		return sucesores;
	}

	public int getGiros() {
		return giros;
	}

	public int getAvances() {
		return avances;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public void setEnergiaConsumida(int energiaConsumida) {
		this.energiaConsumida = energiaConsumida;
	}

	public void setEnergiaRecargada(int energiaRecargada) {
		this.energiaRecargada = energiaRecargada;
	}

	public void setAvance(Posicion posicion) {
		this.avance = posicion;
	}

	public void setG(float g) {
		this.g = g;
	}

	public void setH(float h) {
		this.h = h;
	}

	public void setF(float f) {
		this.f = f;
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	public void setSucesores(Vector sucesores) {
		this.sucesores.addAll(sucesores);
	}

	public void setGiros(int giros) {
		this.giros = giros;
	}

	public void setAvances(int avances) {
		this.avances = avances;
	}

	public String toString() {
		switch (algoritmo) {
			case (AESTRELLA):
				return "(" + posicion.x + ", " + posicion.y + ", "
						+ energiaRecargada + ", " + g + ", " + h + ")";
			case (ANCHURA):
				return "(" + posicion.x + ", " + posicion.y + ")";
			case (COLINAS):
				return "(" + posicion.x + ", " + posicion.y + ", "
						+ energiaRecargada + ", " + h + ")";
			default:
				return posicion.toString();
		}
	}

}
