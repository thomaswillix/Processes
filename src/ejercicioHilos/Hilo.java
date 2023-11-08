package ejercicioHilos;

import java.util.Scanner;

public class Hilo extends Thread{
	
	private int res =  1997;
	Scanner sc =  new Scanner(System.in);
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("¿En qué año se empezó a publicar One Piece?"
				+ "\nSi respondes mal, te eliminaré todos los datos del disco duro.");
		int num = sc.nextInt();
		try {
			while (!isInterrupted()) {
				Thread.sleep(5);
				System.err.println("Borrando todos los datos de tu disco...");
			}
		}catch (InterruptedException e) {
			System.out.println("Fin del hilo");
		}
		comprobar(num);
	}
	
	public void comprobar(int num) {
		while (num!=res) {
			System.out.println("Ese no es el año correcto, dame otro");
			num= sc.nextInt();
		}
		interrumpir();
	}
	
	public void interrumpir() {
		interrupt();
	}
}
