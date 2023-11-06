package util;
//  25/10/23
import java.util.Scanner;

public class MainLectura {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado=new Scanner(System.in);
		String nombre;
		int edad;
		System.out.println("Nombre:");
		nombre=teclado.nextLine();
		
		System.out.println("Edad:");
		edad=teclado.nextInt();	
		teclado.close();
		System.out.print("Hola "+nombre + ",");
		if(edad <18) {
			System.out.println(" no puedes votar");
		}else {
			System.out.println(" puedes votar");
		}

	}
}
