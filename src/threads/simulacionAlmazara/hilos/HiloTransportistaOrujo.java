package threads.simulacionAlmazara.hilos;

import threads.simulacionAlmazara.sharedData.Datos;

import java.util.Random;

public class HiloTransportistaOrujo extends Thread{
    private int esperasTransportistaOrujo;
    private int cargamentoOrujoKG;
    private Datos d;

    public HiloTransportistaOrujo(Datos datos, String name) {
        this.d = datos;
        this.setName(name);
        this.esperasTransportistaOrujo = 0;
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
            cargamentoOrujoKG = r.nextInt(1200)+800;
            synchronized (d){
                while (d.getTanqueOrujo()-cargamentoOrujoKG<0){
                    try {
                        d.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    esperasTransportistaOrujo++;
                }
                d.transportarOrujo(cargamentoOrujoKG);
                d.notifyAll();
            }
        }
        System.out.println("["+this.getName()+"]" + " Esperas totales: " + esperasTransportistaOrujo);
    }
}
