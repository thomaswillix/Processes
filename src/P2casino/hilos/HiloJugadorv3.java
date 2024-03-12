package P2casino.hilos;

import P2casino.datos.Cuenta;
import P2casino.datos.CuentaCasino;
import P2casino.datos.Ruleta;

public class HiloJugadorv3 extends Thread {
    private Cuenta presupuesto;
    private Ruleta bola;
    private CuentaCasino casino;
    private int i = 10;

    public HiloJugadorv3(Ruleta casino, CuentaCasino cc, Cuenta presupuesto) {
        super();
        this.bola = casino;
        this.presupuesto = presupuesto;
        this.casino = cc;
        
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        int numero = 0;
        synchronized (bola) {

            while (presupuesto.getSaldo() > 0) {

                presupuesto.retirar(i);
                casino.ingresar(i);
                i*=2;

                numero = (int) (Math.random() * 36 + 1);

                try {
                    bola.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (presupuesto.getSaldo()<i){
                    i= presupuesto.getSaldo();
                }

                if (numero == bola.getNum()) {
                    System.out.println("Jugador:\nHe ganado apostando al: " + numero);
                    casino.retirar(i*36);
                    presupuesto.ingresar(i*36);
                    i=10;
                } else {
                    System.out.println("Jugador:\nHe perdido apostando al: " + numero +
                            " el num ganador es: " + bola.getNum());
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
