package P2casino.datos;

public class Cuenta {
	private int c = 0;

	public Cuenta(int c) {
		this.c = c;
	}

	public void ingresar(int n) {
		c += n;
		if (c < 0) {
			System.out.println("Negativo");
		}
	}

	public void retirar(int n) {
		c -= n;
		if (n < 0) {
			System.out.println("Negativo");
		}
	}

	public int getSaldo() {
		return c;
	}

}
