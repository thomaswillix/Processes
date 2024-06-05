package threads.P2productoresConsumidores.main;

import threads.P2productoresConsumidores.datos.Cola;
import threads.P2productoresConsumidores.hilos.Consumidor;
import threads.P2productoresConsumidores.hilos.Productor;

public class Main {
    public static void main(String[] args) {
        Cola c = new Cola();
        Productor h1 = new Productor(c);
        Consumidor h2 = new Consumidor(c);

        //Ejecutamos los dos hilo con una clase en comun
        h1.start();
        h2.start();

        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
