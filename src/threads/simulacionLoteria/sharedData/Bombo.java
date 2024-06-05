package threads.simulacionLoteria.sharedData;

import java.util.*;

public class Bombo {
    private Set<Integer> numerosAleatorios;
    public static List<Integer> numerosPorRonda;
    private int banca;
    private int numeroRondas;

    public Bombo(int numeroRondas) {
        banca = 1000;
        this.numeroRondas = numeroRondas;
        numerosAleatorios = new HashSet<>();
        numerosPorRonda = new ArrayList<>();
    }

    public synchronized void numerosRonda() {
        numerosPorRonda.clear();
        Random r = new Random();
        int num;
        String s = "[Lanzador] Números de los premios: ";
        do{
            num = r.nextInt(48) + 1;
            if (!numerosAleatorios.contains(num)){
                s+=" "+ num;
                numerosAleatorios.add(num);
                numerosPorRonda.add(num);
            }
        }while (numerosPorRonda.size()<3 &&  numerosAleatorios.contains(num));
        System.out.println(s);
    }

    public synchronized void apostante(){
        this.banca+=10;
    }

    public synchronized int getNumeroRondas() {
        return numeroRondas;
    }

    public synchronized void restarRonda() {
        this.numeroRondas--;
    }

    public synchronized int getDineroBanca() {
        if (banca<=0) System.err.println("LA BANCA HA QUEBRADO");
        return banca;
    }

    public synchronized void apostar(int apuesta) {
        this.banca += apuesta;
    }

    public synchronized void hanGanado(int premio) {
        this.banca -= premio;
    }
}
