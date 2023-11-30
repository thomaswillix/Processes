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

			while (pres.getSaldo() > 0) {
				System.out.println("cositas");

				pres.retirar(10);
				ruleta.ingresar(10);

				numero = (int) (Math.random() * 2);
				boolean esPar = false;
				
				if(numero==0) {
					esPar= true;
					System.out.println("El jugador apuesta a pares");
				}else {
					System.out.println("El jugador apuesta a impares");
				}
				try {
					ruleta.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("apuesto " + numero + " y gana " + ruleta.getNum());

				if ((ruleta.getNum()%2==0 && esPar==true) || (ruleta.getNum()%2==1 && esPar==false)) {
					ruleta.retirar(20);
					pres.ingresar(20);
				}

				System.out.println("Saldo del jugador-----> " + pres.getSaldo());
			}
			interrupt();
		}
	}
}
