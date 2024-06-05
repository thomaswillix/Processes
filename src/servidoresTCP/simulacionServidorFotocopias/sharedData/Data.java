package servidoresTCP.simulacionServidorFotocopias.sharedData;

import java.util.HashMap;

public class Data {
    private int precioBN;
    private int precioColor;
    public static HashMap<String, Integer> usuarios;

    public Data() {
        this.precioBN = 10;
        this.precioColor =50;
        usuarios = new HashMap<>();
    }

    public synchronized int getPrecioBN() {
        return precioBN;
    }

    public synchronized int getPrecioColor() {
        return precioColor;
    }
}
