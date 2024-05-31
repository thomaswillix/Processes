package simulacionServidorAlpiste.server;


import simulacionServidorAlpiste.sharedData.Datos;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Server {
    private static ServerSocket server;
    private static  Datos datos;
    public static void main(String[] args) {
        server = null;
        datos = new Datos();
        try {
            server = new ServerSocket(9876);
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