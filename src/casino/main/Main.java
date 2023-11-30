package casino.main;

import java.util.Scanner;

import casino.datos.Cuenta;
import casino.datos.Ruleta;
import casino.hilos.HiloJugador;
import casino.hilos.HiloJugadorv2;
import casino.hilos.HiloRuleta;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Ruleta ruleta=new Ruleta(80);
		Cuenta[] banco =new Cuenta[4];
		HiloJugador[] partidaNumAl = new HiloJugador[10];
		HiloJugadorv2[] partidaNumPar = new HiloJugadorv2[10];

		Cuenta saldo1=new Cuenta(100);
		Cuenta saldo2=new Cuenta(10000);
	//	Cuenta saldo3=new Cuenta(10000);

		
		System.out.println("Buenos días, bienvenido a la ruleta francesa del"
				+ "casino\nIntroduzca de qué manera quiere jugar su partida\n"
				+ "1 - A un número concreto (x36 si gana)\n"
				+ "2 - A número par o impar (x2 si gana)\n"
				+ "3 - A martingala (multiplicando por dos su apuesta hasta "
				+ "dar con el número ganador).");
		
		Scanner sc =  new Scanner(System.in);
		int juego = sc.nextInt();
		
		HiloRuleta lanzador=new HiloRuleta(ruleta);
		lanzador.start();

		for (int i = 0; i < 4; i++) {
			
			banco[i] = new Cuenta(100);
			
			switch(juego) {
			case 1:
				partidaNumAl[i] = new HiloJugador(ruleta, banco[i]);
				partidaNumAl[i].start();
				break;
			case 2:
				partidaNumPar[i] = new HiloJugadorv2(ruleta, banco[i]);
				partidaNumPar[i].start();
				break;
			case 3:
				break;
			}
		}
		
	
		System.out.println("hola");
		
	}

}
