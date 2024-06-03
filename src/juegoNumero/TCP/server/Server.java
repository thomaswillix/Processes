package juegoNumero.TCP.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket server;
    public static void main(String[] args) {
        server = null;
        try {
            server = new ServerSocket(6001);
            while (true){
                Socket client = server.accept();
                System.out.println("Connection accepted");
                ConnectionHandler hCliente = new ConnectionHandler(client);
                Thread t = new Thread(hCliente);
                t.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
