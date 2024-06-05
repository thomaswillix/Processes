package threads.P2productoresConsumidores.hilos;

import threads.P2productoresConsumidores.datos.Cola;

public class Consumidor extends Thread{
    private Cola c;
    private int aux;

    public Consumidor(Cola c) {
        this.c = c;
    }

    @Override
    public void run() {
        //lee del array cuando este lleno y si no lo esta espera
        for(int i = 0; i < 5;i++) {
            aux = c.leer();
            System.out.println("Numero leido "+ aux);

            try {
                Thread.sleep(generarNumero());
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public static int generarNumero() {
        int aleatorio = (int) (Math.random() * 10 + 1);
        return aleatorio;
    }
}
