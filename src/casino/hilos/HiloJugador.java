package casino.hilos;

import casino.datos.Cuenta;
import casino.datos.Ruleta;

public class HiloJugador extends Thread {
	private Cuenta pres;
	private Ruleta casino;

	public HiloJugador(Ruleta ruleta, Cuenta pres) {
		super();
		this.casino = ruleta;
		this.pres = pres;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int numero = 0;


		while (pres.getSaldo() > 0) {
			synchronized (casino) {
				pres.retirar(10);
				casino.ingresar(10);

				numero = (int) (Math.random() * 36 + 1);

				try {
					casino.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (numero == casino.getNum()) {
					System.out.println("Jugador:\nHe ganado apostando al: " + numero);
					casino.retirar(360);
					pres.ingresar(360);
				} else {
					System.out.println("Jugador:\nHe perdido apostando al: " + numero +
							" el num ganador es: " + casino.getNum());
				}
				System.out.println("Dinero de la Banca: " + casino.getBanca());

				System.out.println("Dinero del jugador " + pres.getSaldo() +
						"\n------------------------------------------------");

				if (casino.getBanca() <= 0) {
					System.out.println("La banca ha quebrado");
					break;
				}
			}
		}
		interrupt();
	}
}
