package P2casino.hilos;


import P2casino.datos.Cuenta;
import P2casino.datos.CuentaCasino;
import P2casino.datos.Ruleta;

public class HiloJugadorv2 extends Thread {
	private Cuenta pres;
	private CuentaCasino casino;
	private Ruleta bola;
	boolean esPar = false;
	private String cadena;

	public HiloJugadorv2(Ruleta ruleta, CuentaCasino cc, Cuenta pres) {
		super();
		this.bola = ruleta;
		this.pres = pres;
		this.casino = cc;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int numero = 0;
		synchronized (bola) {

			while (pres.getSaldo() > 0) {

				pres.retirar(10);
				casino.ingresar(10);

				numero = (int) (Math.random() * 2);

				try {
					bola.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if(numero==0) {
					esPar= true;
					cadena = "pares";
				}else if (numero ==1){
					esPar = false;
					cadena = "impares";
				}

				if ((bola.getNum()%2==0 && esPar) || (bola.getNum()%2==1 && !esPar)) {
					System.out.println("Jugador:\nHe ganado apostando a los " + cadena +
							", el num ganador es: " + bola.getNum());
					casino.retirar(20);
					pres.ingresar(20);
				}else {
					System.out.println("Jugador:\nHe perdido apostando a los " + cadena +
							", el num ganador es: " + bola.getNum());
				}
				System.out.println("Dinero de la Banca: " + casino.getBanca());

				System.out.println("Dinero del jugador " + pres.getSaldo() +
						"\n------------------------------------------------");
				if (casino.getBanca()<=0) {
					System.out.println("La banca ha quebrado");
					break;
				}
			}
			interrupt();
		}
	}
}
