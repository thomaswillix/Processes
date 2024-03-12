package casino.hilos;

import casino.datos.Ruleta;

public class HiloRuleta extends Thread {

	private Ruleta bola;
	private boolean tienePasta = true;
	private int modoJuego;

	public HiloRuleta(Ruleta b, int juego) {
		this.bola = b;
		this.modoJuego = juego;
	}

	@Override
	public void run() {
		int aux = 0;
		int aux1 = 0;
		while (tienePasta || (aux == bola.getBanca() && aux1 != bola.getNum())) {

			/*if(!estanVivos(modoJuego)) {
				interrupt();
			}*/

			aux1 = bola.getNum();
			System.out.println("Ruleta girando...");
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
			
			if (bola.getBanca()<0) {
				tienePasta = false;
				break;
			}
			System.out.println("\nRuleta girando...\nEl número ganador es-----> "+bola.getNum());
			
			aux =  bola.getBanca();
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
