package ejercicio1Hilos;

import java.util.Scanner;

public class Hilo extends Thread {

	private int res = 1997;
	Scanner sc = new Scanner(System.in);
	int num;

	@Override
	public void run() {
		System.out.println("¿En qué año se empezó a publicar One Piece?"
				+ "\nSi respondes mal, te eliminaré todos los datos del disco duro.");
		num = sc.nextInt();
		comprobar(num);
	}

	public void comprobar(int num) {
		while (num != res) {
			System.out.println("Ese no es el año correcto, dame otro");
			num = sc.nextInt();
		}
		System.out.println("Respuesta correcta :D");
		System.exit(0);
	}

	public void interrumpir() {
		System.err.println("Borrando todos los datos de tu disco...");
		interrupt();
	}
}
