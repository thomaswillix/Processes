package servidoresTCP.solicitudFicheroTCP.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static ServerSocket server;
    private static Socket client;

    public static void main(String[] args) {
        server = null;
        try {
            server = new ServerSocket(12345);
            while(true) {
                Socket cliente = server.accept();
                System.out.println("connection accepted");
                ConnectionHandler hCliente = new ConnectionHandler(cliente);
                Thread t = new Thread(hCliente);
                t.start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
