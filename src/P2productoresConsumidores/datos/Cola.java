package P2productoresConsumidores.datos;

public class Cola {
    private int[] numeros = new int[2];
    private int posicion = 0;

    // Comprobaciones del estado de la cola
    private boolean estaLlena = false;
    private boolean estaVacia = true;
    public Cola() {

    }
    //metodo de rellenar
    public synchronized void rellenar(int num) {
        //si la cola esta llena espera hasta recibir la notificacion de que esta vacia
        while(estaLlena) {
            try {
                this.wait();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        //vamos rellenando el array con los numeros que nos pasan y con posicion nos situamos en psicion del array que leemos
        numeros[posicion] = num;
        posicion++;

        //si posicion es igual que el tamaño de la array esta completa si no esta vacia
        if(posicion == 2) {
            estaLlena = true;
        }
        estaVacia = false;
        notify();

    }
    public synchronized int leer() {
        //si la cola esta vacia espera hasta recibir la notificacion de que esta llena
        while(estaVacia) {
            try {
                this.wait();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        //vamso decrementando para ir recoriendo el array
        posicion--;

        // si la posicion es igual a 0 la cadena esta vacia y se notifica a rellenar
        if(posicion == 0) {
            estaVacia = true;
        }
        estaLlena = false;
        notify();

        // Se devuelve el elemento del array
        return numeros[posicion] ;
    }

}
