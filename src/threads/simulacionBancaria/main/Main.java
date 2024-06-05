package threads.simulacionBancaria.main;

import threads.simulacionBancaria.datos.Cuenta;
import threads.simulacionBancaria.hilos.HiloResta;
import threads.simulacionBancaria.hilos.HiloSuma;

public class Main {
    public static void main(String[] args) {
        HiloSuma[] hiloS = new HiloSuma[100];
        HiloResta[] hiloR = new HiloResta[100];
        Cuenta c = new Cuenta(1);

        for (int i = 0; i < 100 ; i++) {
            hiloS[i] = new HiloSuma(c);
            hiloS[i].start();

            hiloR[i] = new HiloResta(c);
            hiloR[i].start();
        }
        for (int i = 0; i < 100; i++) {
            try {
                hiloS[i].join();
                hiloR[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Saldo final: " + c.getC());
    }
}
