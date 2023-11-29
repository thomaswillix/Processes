package casino.hilos;


import casino.datos.Cuenta;
import casino.datos.Ruleta;

public class HiloJugadorv2 extends Thread {
	private Cuenta pres;
	private Ruleta ruleta;

	public HiloJugadorv2(Ruleta ruleta, Cuenta pres) {
		super();
		this.ruleta = ruleta;
		this.pres = pres;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int numero = 0;
		synchronized (ruleta) {

			while (pres.getSaldo() >= 0|| ruleta.getBanca()>=0) {
				System.out.println("cositas");

				pres.retirar(10);
				ruleta.ingresar(10);

				numero = (int) (Math.random() * 36 + 1);

				try {
					ruleta.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("apuesto " + numero + " y gana " + ruleta.getNum());

				if (numero == ruleta.getNum()) {
					ruleta.retirar(360);
					pres.ingresar(360);
				}

				System.out.println("Saldo del jugador 2 " + pres.getSaldo());
			}
			interrupt();
		}
	}
}
