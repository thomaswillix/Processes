package casino.hilos;

import casino.datos.Cuenta;
import casino.datos.Ruleta;

public class HiloJugador extends Thread {
	private Cuenta pres;
	private Ruleta ruleta;

	public HiloJugador(Ruleta ruleta, Cuenta pres) {
		super();
		this.ruleta = ruleta;
		this.pres = pres;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int numero = 0;
		synchronized (ruleta) {

			while (pres.getSaldo() > 0) {

				pres.retirar(10);
				ruleta.ingresar(10);

				numero = (int) (Math.random() * 36 + 1);

				try {
					ruleta.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (numero == ruleta.getNum()) {
					System.out.println("Jugador:\nHe ganado apostando al: " + numero);
					ruleta.retirar(360);
					pres.ingresar(360);
				}else {
					System.out.println("Jugador:\nHe perdido apostando al: " + numero + 
							" el num ganador es: " + ruleta.getNum());
				}
				System.out.println("Dinero de la Banca: " + ruleta.getBanca());

				System.out.println("Dinero del jugador " + pres.getSaldo() + 
						"\n------------------------------------------------");
				
				if (ruleta.getBanca()<=0) {
					System.out.println("La banca ha quebrado");
					break;
				}
			}
			interrupt();
		}
	}
}
