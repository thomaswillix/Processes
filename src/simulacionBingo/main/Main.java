package simulacionBingo.main;

import simulacionBingo.datos.Lanzador;
import simulacionBingo.hilos.HiloApostador;
import simulacionBingo.hilos.HiloCrupier;

public class Main {
    public static void main(String[] args) {
        Lanzador b = new Lanzador();
        HiloApostador[] ha = new HiloApostador[3];
        HiloCrupier hiloCrupier = new HiloCrupier(b);

        for (int i = 0; i < 3; i++) {
            ha[i] = new HiloApostador(b);
            ha[i].setName("jugador " + (i+1));
            System.out.println("-------- CARTÓN DEL JUGADOR " + (i+1) +" --------");
            ha[i].mostrarCarton();
            ha[i].start();
        }

        hiloCrupier.start();
        for (int i = 0; i < 3; i++) {
            try {
                ha[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
