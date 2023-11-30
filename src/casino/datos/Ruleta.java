package casino.datos;

public class Ruleta {
	private int banca;
	private int numAleatorio;

	public Ruleta (int c) {
		this.banca = c;
	}
	public void ingresar(int n) {
		banca+=n;
		if (banca <0) {
			System.out.println("Negativo");
		}
	}

	public void retirar(int n) {
		banca-=n;
		if (n < 0) {
			System.out.println("Negativo");
		}
	}

	public void numeroGanador() {
		numAleatorio = (int) (Math.random() * 36);
	}
	
	public int getNum() {
		return numAleatorio;
	}
	public int getBanca() {
		return banca;
	}
	
}
