package threads.simulacionLoteria.hilos;

import threads.simulacionLoteria.sharedData.Bombo;

public class ThreadLanzador extends Thread{
    private Bombo b;
    private boolean dineroEnBanca = true;

    public ThreadLanzador(Bombo b) {
        this.b = b;
    }

    @Override
    public void run() {
        while (dineroEnBanca && b.getNumeroRondas() > 0) {
            System.out.println("NÚMERO DE RONDAS: " + b.getNumeroRondas());
            try {
                sleep(1000);
                System.out.println("[Lanzador] NUEVA RONDA");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (b) {
                b.numerosRonda();
                b.restarRonda();
                b.notifyAll();
            }
            if (b.getDineroBanca() <= 0) {
                dineroEnBanca = false;
            }
        }
        System.out.println("SALDO DE LA CUENTA DE LA BANCA: " + b.getDineroBanca());
    }
}
