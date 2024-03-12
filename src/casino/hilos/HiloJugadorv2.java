package casino.hilos;


import casino.datos.Cuenta;
import casino.datos.Ruleta;

public class HiloJugadorv2 extends Thread {
	private Cuenta pres;
	private Ruleta ruleta;
	boolean esPar = false;
	private String cadena;

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

				pres.retirar(10);
				ruleta.ingresar(10);

				numero = (int) (Math.random() * 2);

				try {
					ruleta.wait();
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

				if ((ruleta.getNum()%2==0 && esPar) || (ruleta.getNum()%2==1 && !esPar)) {
					System.out.println("Jugador:\nHe ganado apostando a los " + cadena +
							", el num ganador es: " + ruleta.getNum());
					ruleta.retirar(20);
					pres.ingresar(20);
				}else {
					System.out.println("Jugador:\nHe perdido apostando a los " + cadena +
							", el num ganador es: " + ruleta.getNum());
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
