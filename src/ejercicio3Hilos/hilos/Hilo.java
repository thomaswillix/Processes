package ejercicio3Hilos.hilos;

import ejercicio3Hilos.datos.Contador;

import java.util.Random;
import java.util.Scanner;

public class Hilo implements Runnable{

    public Hilo() {}

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        int num, n1, n2;
        Contador c = new Contador(0);
        Temporizador t = new Temporizador(c);
        t.start();
        while (true){
            n1 = (int) (Math.random() * 10) + 1;
            n2 = (int) (Math.random() * 10) + 1;
            System.out.println("¿Cuánto es " + n1 + " + " + n2 + " ?");
            num = sc.nextInt();

            if (num == n1 + n2) {
                c.correctAnswer();
            } else c.incorrectAnswer();
        }
    }
}
