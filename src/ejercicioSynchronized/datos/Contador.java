package ejercicioSynchronized.datos;

public class Contador {

	private int c = 100;

	public Contador (int c) {
		this.c =  c;
	}
	
	public synchronized void incrementar(int n) {
		c+=n;
	}

	public synchronized void decrementar(int n) {
		c-=n;
	}
	
	public synchronized void movimiento(int n) {
		c+=n;
	}

	public synchronized int valor() {
		return c;
	}
	
}
