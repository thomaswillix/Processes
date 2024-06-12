package servidoresTCP.iesPerico.server;

import servidoresTCP.iesPerico.sharedData.Database;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private static Database datos;
    private static ServerSocket server;
    public static HashMap<Integer, Socket> usuarios = new HashMap<>();


    public static void main(String[] args) {
        server = null;
        datos = new Database()  ;
        try {
            server = new ServerSocket(12345);
            while(true) {
                Socket cliente = server.accept();
                System.out.println("connection accepted");

                ConnectionHandler hCliente = new ConnectionHandler(cliente, datos);
                Thread t = new Thread(hCliente);
                t.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
