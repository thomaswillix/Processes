package threads.ejercicio2Hilos;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CountVowels extends Thread {
	public int cont;
	private char voc;
	private File f;

	public CountVowels(char i, File f) {
		this.voc = i;
		this.f = f;
		cont = 0;
	}

	@Override
	public void run() {
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Entramos al bucle cuentaVocales " + voc);
		countVowel(voc);
	}

	public void countVowel(char l) {
		try {
			FileReader fr = new FileReader(f);
			int c = fr.read();

			while (c != -1) {
				if (Character.toLowerCase((char)c)== l) {
					cont++;
				}
				c = fr.read();
			}

			fr.close();
		} catch (IOException e) {
		}
	}
	public static void main(String[] args) {
		char[] vocales = {'a','e','i','o','u'};
		File f = new File("ejemplo.txt");

		for (int i = 0; i < vocales.length; i++) {
			CountVowels h = new CountVowels(vocales[i], f);
			h.start();
			try {
				h.join();
				System.out.println("la vocal " + vocales[i] + " aparece " + h.cont + " veces");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
