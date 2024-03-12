package P2casino.hilos;

import P2casino.datos.Cuenta;
import P2casino.datos.CuentaCasino;
import P2casino.datos.Ruleta;

public class HiloJugador extends Thread {
	private Cuenta pres;
	private Ruleta bola;
	private CuentaCasino casino;

	public HiloJugador(Ruleta ruleta, CuentaCasino cc, Cuenta pres) {
		super();
		this.bola = ruleta;
		this.pres = pres;
		this.casino = cc;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int numero = 0;


		while (pres.getSaldo() > 0) {
			synchronized (bola) {
				pres.retirar(10);
				casino.ingresar(10);

				numero = (int) (Math.random() * 36 + 1);

				try {
					bola.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (numero == bola.getNum()) {
					System.out.println("Jugador:\nHe ganado apostando al: " + numero);
					casino.retirar(360);
					pres.ingresar(360);
				} else {
					System.out.println("Jugador:\nHe perdido apostando al: " + numero +
							" el num ganador es: " + bola.getNum());
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
