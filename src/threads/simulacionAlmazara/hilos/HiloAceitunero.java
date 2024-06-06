package threads.simulacionAlmazara.hilos;

import threads.simulacionAlmazara.sharedData.Datos;

import java.util.Random;

public class HiloAceitunero extends Thread{
    private int cargamentoKG;
    private int esperasAceitunero;
    private int viajes;
    private Datos d;
    public HiloAceitunero(Datos datos, String name) {
        this.d = datos;
        esperasAceitunero = 0;
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
            cargamentoKG = r.nextInt(300) + 100;
            synchronized (d) {
                long tiempoEspera = System.currentTimeMillis();
                while (d.getTanqueOrujo() + cargamentoKG*0.3 > 1600 || d.getDepositoAceite()+cargamentoKG*0.7>3600) {
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
                    esperasAceitunero++;
                }
                if (!tiemout) {
                    d.depositarOrujo((int) (cargamentoKG * 0.3));
                    d.depositarAceite((int) (cargamentoKG * 0.7));
                    d.notifyAll();
                }
            }
            viajes--;
        }
        System.out.println("["+this.getName()+"] Esperas totales: " + esperasAceitunero);
    }
}
