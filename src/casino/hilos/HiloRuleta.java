package casino.hilos;

import casino.datos.Ruleta;

public class HiloRuleta extends Thread {

	private Ruleta bola;
	private boolean tienePasta = true;
	private HiloJugador[] partida1;
	private HiloJugadorv2[] partida2;
	private HiloJugadorv3[] partida3;
	
	public HiloRuleta(Ruleta b, HiloJugador[] partidaNumAleatorios) {
		this.bola = b;
		this.partida1=partidaNumAleatorios;
	}
	public HiloRuleta(Ruleta b, HiloJugadorv2[] partidaNumPares) {
		this.bola = b;
		this.partida2=partidaNumPares;
	}
	public HiloRuleta(Ruleta b, HiloJugadorv3[] partidaMartingala) {
		this.bola = b;
		this.partida3=partidaMartingala;
	}

	@Override
	public void run() {
		int aux = 0;
		int aux1 = 0;
		while (tienePasta || (aux == bola.getBanca() && aux1 != bola.getNum())) {
			
			
			if(!estanVivos()) {
				interrupt();
			}
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
	
	public boolean estanVivos() {
		for (HiloJugador hiloJugador : partida1) {
			if(hiloJugador.isAlive()) {
				return true;
			}
		}
		return false;
	}
}
