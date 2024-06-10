package threads.simulacionBingo.hilos;

import threads.simulacionBingo.datos.Lanzador;
import java.util.*;

public class HiloApostador extends Thread{

    private Lanzador lanzador;
    private Set<Integer>[] carton = new HashSet[3];
    private int numero;
    private int fila;
    private HashMap<Integer, Integer> aciertosPorLinea = new HashMap<>();
    private int contador = 0;
    public HiloApostador(Lanzador lanzador) {
        //Le damos al hilo los datos correspondientes
        this.lanzador = lanzador;

        //Creamos el cartón
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            carton[i] = new HashSet<Integer>();
        }
        while (carton[0].size()<5) {
            numero = r.nextInt(90) + 1;
            carton[0].add(numero);
        }
        while (carton[1].size()<5) {
            numero = r.nextInt(  90) + 1;
            if (!carton[0].contains(numero)) {
                carton[1].add(numero);
            }
        }
        while (carton[2].size()<5) {
            numero = r.nextInt( 90  ) + 1;
            if (!carton[0].contains(numero) && !carton[1].contains(numero)) {
                carton[2].add(numero);
            }
        }
    }

    @Override
    public void run() {
        while(!lanzador.hanGanado()){
            synchronized (lanzador) {
                try {
                    lanzador.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                numero = lanzador.getNum();
            }
            for (int i = 0; i < 3; i++) {
                fila = i;
                if(carton[i].contains(numero)){
                    contador++;
                    System.out.println("\nEl " + this.getName() + " tiene el número en su fila: " + (fila +1));
                    if(!aciertosPorLinea.containsKey(fila)){
                        aciertosPorLinea.put(fila, 1);
                    } else{
                        int nuevoValor = aciertosPorLinea.get(fila);
                        aciertosPorLinea.replace(fila, ++nuevoValor);
                        System.out.println("Número de aciertos del " + this.getName() + " en la misma fila (" + (fila+1) +"): " + aciertosPorLinea.get(fila));
                    }
                }
            }
            if (aciertosPorLinea.getOrDefault(0, 0)==5){
                System.out.println(this.getName()+ " ha completado la línea 1 de su cartón!\n");
                aciertosPorLinea.remove(0);
            }
            if( aciertosPorLinea.getOrDefault(1, 0)==5) {
                System.out.println(this.getName()+ " ha completado la línea 2 de su cartón!\n");
                aciertosPorLinea.remove(1);
            }
            if ( aciertosPorLinea.getOrDefault(2, 0)==5){
                System.out.println(this.getName()+ " ha completado la línea 3 de su cartón!\n");
                aciertosPorLinea.remove(2);
            }
            if (contador==15){
                System.out.println("Bingo!");
                lanzador.ganador();
            }
        }

    }


    public void mostrarCarton(){
        for (int i = 0; i < 3; i++) {
            for (int num : carton[i]){
                System.out.print(num+", ");
            }
            System.out.println();
        }
    }
}

