package ejercicioSynchronized.main;

import ejercicioSynchronized.datos.Contador;
import ejercicioSynchronized.hilos.HiloResta;
import ejercicioSynchronized.hilos.HiloSuma;

public class Main {
	
	public static void main(String[] args) {
		Contador cuenta = new Contador(100);
		int n ;
		HiloSuma[] hilosS= new HiloSuma[120];
		HiloResta[] hilosR= new HiloResta[120];  
		
		for (int i = 0; i < 120; i++) {
			if (i<40) 
				n = 100;
			else if(i<60) 
				n = 50;
			else 
				n = 20;
			
			hilosS[i] = new HiloSuma("hilosuma " + i, cuenta, n);
			hilosS[i].start();
			hilosR[i] = new HiloResta("hiloresta" + i, cuenta, n);
			hilosR[i].start();
		}
		for (int i = 0; i < 120; i++) {
			try {
				hilosS[i].join();
				hilosR[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(cuenta.valor());
	}
}
