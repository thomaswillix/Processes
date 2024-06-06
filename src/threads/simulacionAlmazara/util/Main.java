package threads.simulacionAlmazara.util;

import threads.simulacionAlmazara.hilos.HiloAceitunero;
import threads.simulacionAlmazara.hilos.HiloTransportistaAceite;
import threads.simulacionAlmazara.hilos.HiloTransportistaOrujo;
import threads.simulacionAlmazara.sharedData.Datos;

public class Main {
    public static void main(String[] args) {
        Datos d = new Datos();
        HiloAceitunero[] aceituneros= new HiloAceitunero[200];
        HiloTransportistaAceite[] transportistasAceite = new HiloTransportistaAceite[20];
        HiloTransportistaOrujo[] transportistasOrujo = new HiloTransportistaOrujo[7];
        for (int i = 0; i < 200; i++) {
            aceituneros[i] = new HiloAceitunero(d, "Aceitunero " + (i+1));
            aceituneros[i].start();
        }
        for (int i = 0; i < 20; i++) {
            transportistasAceite[i] = new HiloTransportistaAceite(d, "TrAceite " + (i+1));
            transportistasAceite[i].start();
        }
        for (int i = 0; i < 7; i++) {
            transportistasOrujo[i] = new HiloTransportistaOrujo(d, "TrOrujo " + (i+1));
            transportistasOrujo[i].start();
        }
        try {
            for (HiloAceitunero thread : aceituneros) {
                thread.join();
            }
            for (HiloTransportistaAceite thread : transportistasAceite) {
                thread.join();
            }
            for (HiloTransportistaOrujo thread : transportistasOrujo) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("KG en el deposito de Aceite: " + d.getDepositoAceite());
        System.out.println("KG en el tanque de Orujo: " + d.getTanqueOrujo());
    }
}
