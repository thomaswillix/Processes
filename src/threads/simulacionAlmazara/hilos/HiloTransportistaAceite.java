package threads.simulacionAlmazara.hilos;

import threads.simulacionAlmazara.sharedData.Datos;

import java.util.Random;


public class HiloTransportistaAceite extends Thread{
    private int esperasTransportistaAceite;
    private int cargamentoAceiteKG;
    private Datos d;

    public HiloTransportistaAceite(Datos datos, String name) {
        this.esperasTransportistaAceite = 0;
        this.d = datos;
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
            cargamentoAceiteKG = r.nextInt(1200) + 800;
            synchronized (d) {
                while (d.getDepositoAceite() - cargamentoAceiteKG < 0) {
                    try {
                        d.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    esperasTransportistaAceite++;
                }
                d.transportarAceite(cargamentoAceiteKG);
                d.notifyAll();
            }
        }
        System.out.println("["+this.getName()+"]" + " Esperas totales: " + esperasTransportistaAceite);
    }
}
