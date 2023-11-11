package ejercicio1Hilos;

public class Main {
	public static void main(String[] args) {
		Hilo h1 = new Hilo();
		h1.start();
		int segundos = 0;
		try {
			do {
				segundos++;
				Thread.sleep(1000);
			} while (segundos <= 5);
			h1.interrumpir();
			System.exit(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}