package threads.simulacionBancaria2.util;

import threads.simulacionBancaria2.datos.Cuenta;
import threads.simulacionBancaria2.hilos.HiloResta;
import threads.simulacionBancaria2.hilos.HiloSuma;

public class Main {
    public static void main(String[] args) {
        HiloSuma[] hiloS = new HiloSuma[10];
        HiloResta[] hiloR = new HiloResta[5];
        Cuenta c = new Cuenta(100);

        for (int i = 0; i < 10 ; i++) {
            hiloS[i] = new HiloSuma(c);
            hiloS[i].start();
            if (i<5){
                hiloR[i] = new HiloResta(c);
                hiloR[i].start();
            }
        }
        for (int i = 0; i < 10; i++) {
            try {
                hiloS[i].join();
                if (i<5)  hiloR[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Saldo final: " + c.getC());
    }
}
