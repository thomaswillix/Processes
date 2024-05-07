package simulacionBancaria.hilos;

import simulacionBancaria.datos.Cuenta;

import java.util.Random;

public class HiloResta extends Thread{

    private Cuenta c;

    public HiloResta(Cuenta c) {
        this.c = c;
    }

    @Override
    public void run() {
        Random random = new Random();
        int t = random.nextInt(50) +10;
        try {
            sleep(t);
            c.retirar(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
