package casino.hilos;

import casino.datos.Cuenta;
import casino.datos.Ruleta;

public class HiloJugadorv3 extends Thread {
    private Cuenta presupuesto;
    private Ruleta casino;
    private int i = 10;

    public HiloJugadorv3(Ruleta casino, Cuenta presupuesto) {
        super();
        this.casino = casino;
        this.presupuesto = presupuesto;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        int numero = 0;
        synchronized (casino) {

            while (presupuesto.getSaldo() > 0) {

                presupuesto.retirar(i);
                casino.ingresar(i);
                i*=2;

                numero = (int) (Math.random() * 36 + 1);

                try {
                    casino.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (presupuesto.getSaldo()<i){
                    i= presupuesto.getSaldo();
                }

                if (numero == casino.getNum()) {
                    System.out.println("Jugador:\nHe ganado apostando al: " + numero);
                    casino.retirar(i*36);
                    presupuesto.ingresar(i*36);
                    i=10;
                } else {
                    System.out.println("Jugador:\nHe perdido apostando al: " + numero +
                            " el num ganador es: " + casino.getNum());
                }
                System.out.println("Dinero de la Banca: " + casino.getBanca());

                System.out.println("Dinero del jugador " + presupuesto.getSaldo() +
                        "\n------------------------------------------------");

                if (casino.getBanca()<=0) {
                    System.out.println("La banca ha quebrado");
                    break;
                }
            }
            interrupt();
        }
    }
}
