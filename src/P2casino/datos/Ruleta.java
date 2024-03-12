package P2casino.datos;

public class Ruleta {
	private int numero;

	public Ruleta () {
	}

	public void numeroGanador() {
		numero = (int) (Math.random() * 36);
	}
	
	public int getNum() {
		return numero;
	}

	
}
