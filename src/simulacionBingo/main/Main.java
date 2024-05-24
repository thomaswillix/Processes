package simulacionBingo.main;

import simulacionBingo.hilos.HiloApostador;

public class Main {
    public static void main(String[] args) {
        HiloApostador ha = new HiloApostador();
        ha.mostrarCarton();
    }

}
