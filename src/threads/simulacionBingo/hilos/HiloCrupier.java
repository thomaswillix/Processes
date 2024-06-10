package threads.simulacionBingo.hilos;

import threads.simulacionBingo.datos.Lanzador;

public class HiloCrupier extends Thread{
    Lanzador lanzador;
    public HiloCrupier(Lanzador b) {
        this.lanzador = b;
    }

    @Override
    public void run() {
        while(!lanzador.hanGanado()){
            int num = 3;
            try {
                do{
                    num--;
                    sleep(100);
                    System.out.print(".");
                }while(num!=0);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lanzador){
                lanzador.sacarBola();
                lanzador.notifyAll();
            }
        }

    }
}


