package threads.simulacionLoteria.hilos;

import threads.simulacionLoteria.sharedData.Bombo;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ThreadApostante extends Thread {

    private int saldo;
    private Bombo b;
    boolean tieneDinero = true;
    private boolean dineroEnBanca = true;
    private Set<Integer> numerosAleatorios;

    public ThreadApostante(Bombo b, String nombre) {
        this.setName(nombre);
        this.saldo = 100;
        this.b=b;
        numerosAleatorios = new HashSet<>();
    }

    @Override
    public void run() {
        b.apostante();
        Random r = new Random();
        int num;
        //System.out.println(this.getName() + " EMPIEZA LA PARTIDA");
        while (tieneDinero && b.getNumeroRondas()>0 && dineroEnBanca){
            numerosAleatorios.clear();
            String s = this.getName() + "[Apostante] Numeros que apuesta en esta ronda: ";
            do{
                num = r.nextInt(48) + 1;
                if (!numerosAleatorios.contains(num)) {
                    numerosAleatorios.add(num);
                    s+=" " + num;
                }
            }while (numerosAleatorios.size()<3 &&  numerosAleatorios.contains(num));
            System.out.println(s);
            b.apostar(5);
            saldo-=5;
            synchronized (b){
                try {
                    b.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            int aciertos =0;
                if (Bombo.numerosPorRonda.contains(numerosAleatorios.toArray()[0])) {
                    aciertos++;
                } else if (Bombo.numerosPorRonda.contains(numerosAleatorios.toArray()[1])) {
                    aciertos++;
                } else if (Bombo.numerosPorRonda.contains(numerosAleatorios.toArray()[2])) {
                    aciertos++;
                }
                switch (aciertos) {
                    case 1:
                        b.hanGanado(5);
                        saldo += 5;
                        System.out.println(this.getName() + " HA ACERTADO 1 NÚMERO");
                        break;
                    case 2:
                        b.hanGanado(25);
                        saldo += 25;
                        System.out.println(this.getName() + " HA ACERTADO 2 NÚMEROS");
                        break;
                    case 3:
                        b.hanGanado(1000);
                        saldo += 1000;
                        System.out.println(this.getName() + " HA ACERTADO LOS 3 NÚMEROS");
                        break;
                    default:
                        break;
                }

            if (b.getDineroBanca()<=0) {
                dineroEnBanca=false;
            }
            else{
                dineroEnBanca = true;
            }
            if (saldo<=0){
                System.err.println(this.getName() + ": está sin dinero");
                tieneDinero=false;
            }else {
                tieneDinero = true;
            }
        }
        System.out.println("SALDO FINAL DE: " + this.getName() + " " + saldo );
    }
}
