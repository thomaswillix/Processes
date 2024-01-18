package ejercicio2Hilos;

public class Main {
	public static void main(String[] args) {
		char[] vocales = {'a','e','i','o','u'};
		
		for (int i = 0; i < vocales.length; i++) {
			Hilo h = new Hilo(vocales[i]);
			h.start();
			try {
				h.join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}