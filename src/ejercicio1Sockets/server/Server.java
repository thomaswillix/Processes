package ejercicio1Sockets.server;
//Servidor con puerto 12345

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
        try {
            ServerSocket ssocket = new ServerSocket(12345);
            Socket cliente = ssocket.accept();
            System.out.println("connection accepted");

            DataInputStream in = new DataInputStream(cliente.getInputStream());
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());

            String dataReceived;
            while (!(dataReceived = in.readUTF()).equals("adios")) {
                System.out.println("Server received: " + dataReceived);
                out.writeUTF(escribirMensajeAmbiguo());
            }
            out.writeUTF("/salir");
            cliente.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void cargarMensajes(){
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
    private static String escribirMensajeAmbiguo(){
        Random r = new Random();
        int n = r.nextInt(mensajesAmbiguos.size());
        return mensajesAmbiguos.get(n);
    }
}
