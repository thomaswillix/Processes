package threads.simulacionAlmazara.hilos;

import threads.simulacionAlmazara.sharedData.Datos;

import java.util.Random;


public class HiloTransportistaAceite extends Thread{
    private int esperasTransportistaAceite;
    private int cargamentoAceiteKG;
    private Datos d;
    private int viajes;

    public HiloTransportistaAceite(Datos datos, String name) {
        this.esperasTransportistaAceite = 0;
        this.d = datos;
        this.setName(name);
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
            cargamentoAceiteKG = r.nextInt(1200) + 800;
            synchronized (d) {
                long tiempoEspera = System.currentTimeMillis();
                while (d.getDepositoAceite() - cargamentoAceiteKG < 0) {
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
                    esperasTransportistaAceite++;
                }
                if (!tiemout) {
                    d.transportarAceite(cargamentoAceiteKG);
                    d.notifyAll();
                }
            }
            viajes--;
        }
        System.out.println("["+this.getName()+"]" + " Esperas totales: " + esperasTransportistaAceite);
    }
}
