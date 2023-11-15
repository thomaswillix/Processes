package ejercicioSynchronized.hilos;

import ejercicioSynchronized.datos.Contador;

public class HiloResta extends Thread {

	private Contador contador;
	int cant;

	public HiloResta(String n, Contador c, int cantidad) {
		setName(n);
		this.contador = c;
		this.cant = cantidad;
	}

	@Override
	public void run() {

		try {
			sleep((int)(Math.random()*100) + 1);
			synchronized (contador) {
				if (contador.valor()<cant) {
					contador.wait();
				}
				contador.decrementar(cant);	
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println(getName() + " contador vale " + contador.valor());
	}
}
