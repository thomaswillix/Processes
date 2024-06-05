package servidoresTCP.simulacionServidorFotocopias.server;
import servidoresTCP.simulacionServidorFotocopias.client.ConnectionHandler;
import servidoresTCP.simulacionServidorFotocopias.sharedData.Data;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket server;
    private static Data datos;
    public static void main(String[] args) {
        server = null;
        datos = new Data();
        try {
            server = new ServerSocket(12345);
            while(true) {
                Socket cliente = server.accept();
                System.out.println("connection accepted");
                ConnectionHandler hCliente = new ConnectionHandler(datos, cliente);
                Thread t = new Thread(hCliente);
                t.start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
