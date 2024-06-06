package threads.simulacionAlmazara.hilos;

import threads.simulacionAlmazara.sharedData.Datos;

import java.util.Random;

public class HiloAceitunero extends Thread{
    private int cargamentoKG;
    private int esperasAceitunero;
    private Datos d;
    public HiloAceitunero(Datos datos, String name) {
        this.d = datos;
        esperasAceitunero = 0;
        this.setName(name);
    }

    @Override
    public void run() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            try {
                sleep(r.nextInt(500)+150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cargamentoKG = r.nextInt(300) + 100;
            synchronized (d) {
                while (d.getTanqueOrujo() + cargamentoKG*0.3 > 1600 || d.getDepositoAceite()+cargamentoKG*0.7>3600) {
                    try {
                        d.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    esperasAceitunero++;
                }
                d.depositarOrujo((int) (cargamentoKG*0.3));
                d.depositarAceite((int)(cargamentoKG*0.7));
                d.notifyAll();
            }
        }
        System.out.println("["+this.getName()+"] Esperas totales: " + esperasAceitunero);
    }
}
