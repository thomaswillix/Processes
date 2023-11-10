package ejercicioHilos;

import java.util.Scanner;

public class Hilo extends Thread {

	private int res = 1997;
	Scanner sc = new Scanner(System.in);
	int num;


	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("¿En qué año se empezó a publicar One Piece?"
				+ "\nSi respondes mal, te eliminaré todos los datos del disco duro.");
		do {
			num = sc.nextInt();
			comprobar(num);
		} while (!(num == res));
		System.out.println("Respuesta correcta :D");
		System.exit(0);
	}

	public void comprobar(int num) {
		if (num != res) {
			System.out.println("Ese no es el año correcto, dame otro");
		}
	}

	public void interrumpir() {
		System.err.println("Borrando todos los datos de tu disco...");
		interrupt();
	}
}
