package threads.simulacionAlmazara.hilos;

import threads.simulacionAlmazara.sharedData.Datos;

import java.util.Random;

public class HiloTransportistaOrujo extends Thread{
    private int esperasTransportistaOrujo;
    private int cargamentoOrujoKG;
    private Datos d;
    private int viajes;

    public HiloTransportistaOrujo(Datos datos, String name) {
        this.d = datos;
        this.setName(name);
        this.esperasTransportistaOrujo = 0;
        this.viajes = 10;
    }

    @Override
    public void run() {
        Random r = new Random();
        boolean tiemout = false;
        while (viajes>0 && !tiemout) {
            try {
                sleep(r.nextInt(500)+150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cargamentoOrujoKG = r.nextInt(1200)+800;
            synchronized (d){
                long tiempoEspera = System.currentTimeMillis();
                while (d.getTanqueOrujo()-cargamentoOrujoKG<0){
                    if (System.currentTimeMillis()-tiempoEspera>5000) {
                        System.err.println("["+this.getName()+"]" + " HA MUERTO POR TIMEOUT");
                        tiemout = true;
                        break;
                    }
                    try {
                        d.wait(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    esperasTransportistaOrujo++;
                }
                if (!tiemout) {
                    d.transportarOrujo(cargamentoOrujoKG);
                    d.notifyAll();
                }
            }
            viajes--;
        }
        System.out.println("["+this.getName()+"]" + " Esperas totales: " + esperasTransportistaOrujo);
    }
}
