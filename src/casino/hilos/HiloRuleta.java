package casino.hilos;

import casino.datos.Ruleta;

public class HiloRuleta extends Thread {

	private Ruleta bola;

	public HiloRuleta(Ruleta b) {
		this.bola = b;
	}

	@Override
	public void run() {
		for (int i = 0; i < 4; i++) {
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
			System.out.println("numero:"+bola.getNum());
		}
			// System.out.println(getName() + " contador vale " + contador.valor());
	}
}