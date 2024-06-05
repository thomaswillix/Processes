package threads.P2productoresConsumidores.hilos;

import threads.P2productoresConsumidores.datos.Cola;

public class Productor extends Thread{
    private Cola c;
    private int aux;

    public Productor(Cola c) {
        this.c = c;
    }

    @Override
    public void run() {
        //escribe un numero aleatorio y si la cola esta llena espeara
        for (int i = 0; i < 5; i++) {
            aux = generarNumero();
            c.rellenar(aux);
            System.out.println("Numero aleatorio " + aux);

            try {
                Thread.sleep(generarNumero());
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }
    public static int generarNumero() {
        int aleatorio = (int)(Math.random()*10+1);
        return aleatorio;
    }
}
