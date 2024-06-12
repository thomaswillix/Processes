package threads.simulacionBancaria2.hilos;
import threads.simulacionBancaria2.datos.Cuenta;

import java.util.Random;

public class HiloSuma extends Thread{
    private Cuenta c;

    public HiloSuma(Cuenta c) {
        this.c = c;
    }

    @Override
    public void run() {
        Random random = new Random();
        int t = random.nextInt(20) +1;
        try {
            sleep(t);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        c.ingresar(123);
    }
}
