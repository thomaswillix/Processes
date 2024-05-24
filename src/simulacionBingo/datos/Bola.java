package simulacionBingo.datos;

public class Bola {

    private int numero;

    public Bola() {
    }

    public void numeroGanador() {
        numero = (int) (Math.random() * 90) +1;
    }

    public int getNum() {
        return numero;
    }
}

