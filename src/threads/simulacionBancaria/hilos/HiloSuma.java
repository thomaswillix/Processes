package threads.simulacionBancaria.hilos;

import threads.simulacionBancaria.datos.Cuenta;

import java.util.Random;

public class HiloSuma extends Thread{
    private Cuenta c;

    public HiloSuma(Cuenta c) {
        this.c = c;
    }

    @Override
    public void run() {
        Random random = new Random();
        int t = random.nextInt(50) +10;
        try {
            sleep(t);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        c.ingresar(10);
    }
}
