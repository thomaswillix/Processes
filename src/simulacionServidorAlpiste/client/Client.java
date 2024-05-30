package simulacionServidorAlpiste.client;

import simulacionServidorAlpiste.sharedData.Datos;

import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable {
    private static Socket socket;

    @Override
    public void run() {
        System.out.println("---------------------- COMPRA DE ALPISTE ----------------------\n" +
                "\n----------------------COMANDOS----------------------\n/salir --> Salir" +
                " del chat\n----------------------------------------------------");

        try {
            socket = new Socket("127.0.0.1", 9876);
            InputHandler handler = new InputHandler(socket);
            Thread t = new Thread(handler);
            t.start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
