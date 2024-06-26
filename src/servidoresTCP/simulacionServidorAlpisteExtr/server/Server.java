package servidoresTCP.simulacionServidorAlpisteExtr.server;


import servidoresTCP.simulacionServidorAlpisteExtr.client.ConnectionHandler;
import servidoresTCP.simulacionServidorAlpisteExtr.sharedData.Datos;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket server;
    private static  Datos datos;
    public static void main(String[] args) {
        server = null;
        datos = new Datos();
        MenuAdmin menu = new MenuAdmin(datos);
        Thread t = new Thread(menu);
        t.start();
        try {
            server = new ServerSocket(9876);
            while(true) {
                Socket cliente = server.accept();
                System.out.println("connection accepted");

                ConnectionHandler hCliente = new ConnectionHandler(cliente, datos);
                t = new Thread(hCliente);
                t.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}