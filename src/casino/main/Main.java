package casino.main;

import casino.datos.Cuenta;
import casino.datos.Ruleta;
import casino.hilos.HiloJugador;
import casino.hilos.HiloRuleta;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Cuenta banca=new Cuenta(10000);
		Ruleta ruleta=new Ruleta(50000);
		HiloRuleta lanzador=new HiloRuleta(ruleta);
		HiloJugador j1=new HiloJugador(ruleta,banca);
		HiloJugador j2=new HiloJugador(ruleta,banca);
		
		lanzador.start();	
		j1.start();
		j2.start();
		
		System.out.println("hola");
		
	}

}
