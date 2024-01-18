package ejercicio2Hilos;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Hilo extends Thread {
	int cont;
	char voc;
	
	public Hilo(char i) {
		this.voc = i;
	}
	
	@Override
	public void run() {
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Entramos al bucle cuentaVocales " + voc);
		cuentaVocal(voc);	

	}
	
	public void cuentaVocal(char l) {
		try {
			File f = new File("ejemplo.txt");
			
			FileReader fr = new FileReader(f);
			int c = fr.read();
			
			while (c != -1) {
				if (Character.toLowerCase((char) c) == l) {
					cont++;
				}
				c = fr.read();
			}
			
			fr.close();
			System.out.println("la vocal " + l + " aparece " + cont + " veces");
			notify();
			cont = 0;
		} catch (IOException e) {
		}
	}
}
