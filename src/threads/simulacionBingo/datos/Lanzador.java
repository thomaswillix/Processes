package threads.simulacionBingo.datos;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Lanzador {

    private int numero;
    private boolean bingo;
    private Random r = new Random();
    private Set<Integer> bolas = new HashSet<>();


    public Lanzador() {
        bingo = false;
    }

    public void sacarBola() {
        do {
            numero = r.nextInt(90) + 1;
        }while (bolas.contains(numero));

        bolas.add(numero);
        System.out.println("El número que ha salido es: " + numero);
    }
    public int getNum() {
        return numero;
    }


    public boolean hanGanado(){
        return bingo;
    }
    public synchronized void ganador(){
        this.bingo = true;
    }
}

