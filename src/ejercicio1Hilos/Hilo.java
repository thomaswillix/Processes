package ejercicio1Hilos;

import java.util.Scanner;

public class Hilo extends Thread{
	
	private int res =  1997;
	Scanner sc =  new Scanner(System.in);
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("¿En qué año se empezó a publicar One Piece?"
				+ "\nSi respondes mal, te eliminaré todos los datos del disco duro.");
		int num =0;
		try {
			Thread.sleep(5000);
			while (!isInterrupted()) {
				comprobar(num);
				System.out.println("Respuesta correcta :D");
			}
		}catch (InterruptedException e) {
            System.err.println("Borrando todos los datos de tu disco...");
		}
	}
	
	public void comprobar(int num) {
		num = sc.nextInt();
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
