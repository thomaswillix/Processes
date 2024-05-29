package ejercicio1Sockets.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {

    private static List<String> mensajesAmbiguos;
    public static void main(String[] args){
        cargarMensajes();
        ServerSocket ssocket = null;
        try {
            ssocket = new ServerSocket(12345);
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
    public static void cargarMensajes(){
        File f = new File("files/frases.txt");
        String line;
        mensajesAmbiguos = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(f));
            try {
                while ((line = bf.readLine())!=null){
                    mensajesAmbiguos.add(line);
                }
            } catch (EOFException e) {
                System.err.println("End of file");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }
    public static String escribirMensajeAmbiguo(){
        Random r = new Random();
        int n = r.nextInt(mensajesAmbiguos.size());
        return mensajesAmbiguos.get(n);
    }
}
