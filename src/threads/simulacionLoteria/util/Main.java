package threads.simulacionLoteria.util;

import threads.simulacionLoteria.hilos.ThreadApostante;
import threads.simulacionLoteria.hilos.ThreadLanzador;
import threads.simulacionLoteria.sharedData.Bombo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sorteos, numeroApostantes;
        System.out.println("Cuántos sorteos quieres que se realicen? ");
        sorteos = sc.nextInt();
        System.out.println("Cuántos apostantes quieres que haya? ");
        numeroApostantes = sc.nextInt();
        Bombo bombo = new Bombo(sorteos);

        ThreadLanzador lanzador = new ThreadLanzador(bombo);
        lanzador.start();

        ThreadApostante[] apostantes = new ThreadApostante[numeroApostantes];

        for (int i = 0; i < numeroApostantes; i++) {
            apostantes[i] = new ThreadApostante(bombo, "Apostante: " + (i+1));
            apostantes[i].start();
        }
        for (int i = 0; i < numeroApostantes; i++) {
            try {
                apostantes[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
