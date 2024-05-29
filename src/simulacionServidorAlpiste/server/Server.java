package simulacionServidorAlpiste.server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Server {

    private static List<String> mensajesAmbiguos;
    public static void main(String[] args){
        ServerSocket ssocket = null;
        try {
            ssocket = new ServerSocket(9876);
            while(true) {
                Socket cliente = ssocket.accept();
                System.out.println("connection accepted");

                ConnectionHandler hCliente = new ConnectionHandler(cliente);
                Thread t = new Thread(hCliente);
                t.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String  menuAdmin(){
        return ("Administración del servidor:\n" +
                "1.- Cambiar precio alpiste\n" +
                "2.- Cambiar precio nabina\n" +
                "3.- Cambiar precio avena\n" +
                "4.- Cambiar precio perilla\n" +
                "5.- Ver recaudación de los pedidos\n" +
                "6.- Numero de pedidos atendidos\n" +
                "0.- Finalizar servicio\n" +
                "Elige la opción:");

    }
}