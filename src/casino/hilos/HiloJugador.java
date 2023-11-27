package casino.hilos;

import casino.datos.Cuenta;
import casino.datos.Ruleta;

public class HiloJugador extends Thread {
	private Cuenta pres;
	private Ruleta ruleta;
	

	public HiloJugador(Ruleta ruleta, Cuenta pres) {
		super();
		this.ruleta=ruleta;
		this.pres = pres;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int numero = 0;
		for (int i = 0; i < 4; i++) {
			pres.retirar(10);
			ruleta.ingresar(10);
			numero=(int)(Math.random()*36+1);
			synchronized(ruleta) {
				try {
					ruleta.wait();
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
			System.out.println("apuesto "+numero+" y gana "+ruleta.getNum());
			if(numero== ruleta.getNum()){
				
			}
			System.out.println(pres.getSaldo());
		}
	}
}
