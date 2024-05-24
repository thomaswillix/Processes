package P2ejercicioSynchronized.datos;

public class Contador {

	private int c = 100;

	public Contador (int c) {
		this.c =  c;
	}
	
	public synchronized void incrementar(int n) {
		this.c+=n;
		notify();
	}

	public synchronized void decrementar(int n) {
		while (c < n) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		c -= n;

		if (c < 0){
			System.err.println("Saldo negativo " + c);
		}

	}

	public synchronized int valor() {
		return c;
	}
	
}
