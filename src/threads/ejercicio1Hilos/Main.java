package threads.ejercicio1Hilos;

public class Main {
	public static void main(String[] args) {
		Hilo h1 = new Hilo();
		h1.start();
		int segundos = 0;
		try {
			do {
				segundos++;
				//Se duerme 1 segundo
				Thread.sleep(1000);
				//Hasta llegar a los 30 segundos
			} while (segundos <= 30);
			h1.interrumpir();
			System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}