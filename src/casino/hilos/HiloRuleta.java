package casino.hilos;

import casino.datos.CuentaCasino;
import casino.datos.Ruleta;

public class HiloRuleta extends Thread {

	private Ruleta bola;
	private CuentaCasino casino;
	private boolean tienePasta = true;
	private int modoJuego;

	public HiloRuleta(Ruleta b, CuentaCasino cc, int juego) {
		this.bola = b;
		this.modoJuego = juego;
		this.casino = cc;
	}

	@Override
	public void run() {
		int aux = 0;
		int aux1 = 0;
		while (tienePasta || (aux == casino.getBanca() && aux1 != bola.getNum())) {

			/*if(!estanVivos(modoJuego)) {
				interrupt();
			}*/

			int num = 3;
			try {
				do{
					num--;
					sleep(1000);
					System.out.print(".");
				}while(num!=0);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (bola) {
				bola.numeroGanador();
				bola.notifyAll();
			}
			
			if (casino.getBanca()<0) {
				tienePasta = false;
				break;
			}
			System.out.println("\nEl número ganador es-----> "+bola.getNum());

			aux =  casino.getBanca();
		}
		interrupt();
	}

	/*public boolean estanVivos(int modoJuego) {
		if (modoJuego == 1){
			for (HiloJugador hiloJugador : partida1) {
				if(hiloJugador.isAlive()) {
					return true;
				}
			}
		}else if (modoJuego==2){
			for (HiloJugadorv2 hiloJugador : partida1) {
				if(hiloJugador.isAlive()) {
					return true;
				}
			}
		}else {
			for (HiloJugadorv3 hiloJugador : partida1) {
				if(hiloJugador.isAlive()) {
					return true;
				}
			}
		}
		return false;
	}*/
}
