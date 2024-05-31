package simulacionServidorAlpiste.client;

import simulacionServidorAlpiste.sharedData.Datos;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private static Socket socket;

    public static void main(String[] args) {
        System.out.println("---------------------- COMPRA DE ALPISTE ----------------------");

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
