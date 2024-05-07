package simulacionBancaria.datos;

public class Cuenta {
    int saldo;

    public Cuenta(int c) {
        this.saldo = c;
    }

    public synchronized void ingresar(int c){
        this.saldo+=c;
        notify();
    }

    public synchronized void retirar(int c){
        while (saldo < c) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        saldo -= c;

        if (saldo < 0){
            System.err.println("Saldo negativo " + saldo);
        }
    }

    public int getC() {
        return saldo;
    }

}
