package P2casino.hilos;

import P2casino.datos.CuentaCasino;
import P2casino.datos.Ruleta;

public class HiloRuleta extends Thread {

	private Ruleta bola;
	private CuentaCasino casino;
	private boolean tienePasta = true;

	public HiloRuleta(Ruleta b, CuentaCasino cc) {
		this.bola = b;
		this.casino = cc;
	}

	@Override
	public void run() {
		int aux = 0;
		int aux1 = 0;
		while (tienePasta || (aux == casino.getBanca() && aux1 != bola.getNum())) {

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

}
