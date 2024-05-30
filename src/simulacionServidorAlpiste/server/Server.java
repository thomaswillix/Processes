package simulacionServidorAlpiste.server;


import simulacionServidorAlpiste.sharedData.Datos;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Server implements Runnable{
    private static ServerSocket server;

    @Override
    public void run() {
        server = null;
        try {
            server = new ServerSocket(9876);
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