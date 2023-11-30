package casino.hilos;

import casino.datos.Ruleta;

public class HiloRuleta extends Thread {

	private Ruleta bola;
	private boolean tienePasta = true;
	
	public HiloRuleta(Ruleta b) {
		this.bola = b;
	}

	@Override
	public void run() {
		int aux = 0;
		int aux1 = 0;
		while (tienePasta || (aux == bola.getBanca() && aux1 != bola.getNum())) {
			aux1 = bola.getNum();
			try {
				sleep(3000);
				// TODO Auto-generated method stub
			} catch (InterruptedException e) {
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
		//El jugador
		interrupt();
	}
}