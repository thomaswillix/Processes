package P2ejercicioSynchronized.hilos;

import P2ejercicioSynchronized.datos.Contador;

public class HiloSuma extends Thread {

	private Contador contador;
	private int cant;

	public HiloSuma(String n, Contador c, int cantidad) {
		setName(n);
		this.contador = c;
		this.cant = cantidad;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (contador) {

			try {
				sleep((int) (Math.random() * 100) + 1);
				synchronized (contador) {
					contador.incrementar(cant);
					contador.notify();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// System.out.println(getName() + " contador vale " + contador.valor());
		}
	}
}
