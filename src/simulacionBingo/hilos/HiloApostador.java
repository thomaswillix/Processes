package simulacionBingo.hilos;

import simulacionBingo.datos.Bola;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class HiloApostador extends Thread{

    private boolean bingo = false;
    private Bola lanzador;
    private Set<Integer>[] carton = new HashSet[3];

    public HiloApostador() {
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            carton[i] = new HashSet<Integer>();
        }
        while (carton[0].size()<5) {
            int numero = r.nextInt(90) + 1;
            carton[1].add(numero);
        }
        while (carton[1].size()<5) {
            int numero = r.nextInt(  90) + 1;
            if (!carton[0].contains(numero)) {
                carton[1].add(numero);
            }
        }
        while (carton[2].size()<5) {
            int numero = r.nextInt( 90  ) + 1;
            if (!carton[0].contains(numero) && !carton[1].contains(numero)) {
                carton[2].add(numero);
            }
        }
    }

    @Override
    public void run() {

    }


    public void mostrarCarton(){
        for (int i = 0; i < 3; i++) {
            for (int num : carton[i]){
                System.out.print(num+", ");
            }
            System.out.println();
        }
    }
}
