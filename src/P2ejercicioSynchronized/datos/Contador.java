package P2ejercicioSynchronized.datos;

public class Contador {

	private int c = 100;

	public Contador (int c) {
		this.c =  c;
	}
	
	public synchronized void incrementar(int n) {
		c+=n;
		if (c <0) {
			System.out.println("Negativo");
		}
	}

	public synchronized void decrementar(int n) {
		c-=n;
		if (n < 0) {
			System.out.println("Negativo");
		}
	}
	
	public synchronized void movimiento(int n) {
		c+=n;
	}

	public synchronized int valor() {
		return c;
	}
	
}
