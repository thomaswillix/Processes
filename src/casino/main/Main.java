package casino.main;

import java.util.Scanner;

import casino.datos.Cuenta;
import casino.datos.CuentaCasino;
import casino.datos.Ruleta;
import casino.hilos.HiloJugador;
import casino.hilos.HiloJugadorv2;
import casino.hilos.HiloJugadorv3;
import casino.hilos.HiloRuleta;

public class Main {
	public static void main(String[] args) {

		Ruleta ruleta=new Ruleta();
		CuentaCasino cc = new CuentaCasino(50000);
		Cuenta[] banco =new Cuenta[4];
		HiloJugador[] partidaNumAl = new HiloJugador[4];
		HiloJugadorv2[] partidaNumPar = new HiloJugadorv2[4];
		HiloJugadorv3[] partidaMartingala = new HiloJugadorv3[4];

		System.out.println("Buenos días, bienvenido a la ruleta francesa del"
				+ "casino\nIntroduzca de qué manera quiere jugar su partida\n"
				+ "1 - A un número concreto (x36 si gana)\n"
				+ "2 - A número par o impar (x2 si gana)\n"
				+ "3 - A martingala (multiplicando por dos su apuesta hasta "
				+ "dar con el número ganador).");

		Scanner sc =  new Scanner(System.in);
		int juego = sc.nextInt();

		for (int i = 1; i < 4; i++) {

			banco[i] = new Cuenta(1000);

			switch(juego) {
				case 1:
					partidaNumAl[i] = new HiloJugador(ruleta, cc, banco[i]);
					partidaNumAl[i].start();

					break;
				case 2:
					partidaNumPar[i] = new HiloJugadorv2(ruleta, cc, banco[i]);
					partidaNumPar[i].start();

					break;
				case 3:
					partidaMartingala[i] = new HiloJugadorv3(ruleta, cc, banco[i]);
					partidaMartingala[i].start();
					break;
			}
		}

		HiloRuleta lanzador= new HiloRuleta(ruleta, cc, juego);
		lanzador.start();
	}

}
