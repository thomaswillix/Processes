package casino.hilos;

import casino.datos.Cuenta;
import casino.datos.Ruleta;

public class HiloJugadorv3 extends Thread {
	private Cuenta pres;
	private Ruleta ruleta;
	private int i = 10;

	public HiloJugadorv3(Ruleta ruleta, Cuenta pres) {
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
					
					pres.retirar(i);
					ruleta.ingresar(i);
					i*=2;
					
					numero = (int) (Math.random() * 36 + 1);
	
					try {
						ruleta.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if (numero == ruleta.getNum()) {
						i=10;
						ruleta.retirar(i*36);
						pres.ingresar(i*36);
					}
					
					System.out.println("apuesto " + numero + " y gana " + ruleta.getNum());
					
					System.out.println("Saldo del jugador-----> " + pres.getSaldo());
					
			}	
			interrupt();
		}
	}
}
