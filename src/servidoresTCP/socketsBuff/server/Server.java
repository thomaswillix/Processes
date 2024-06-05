package servidoresTCP.socketsBuff.server;
//Servidor con puerto 12345

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static ServerSocket ssocket;
    private static Socket cliente;
    public static void main(String[] args){
        try {
           ssocket = new ServerSocket(1234);

            while (true) {
                cliente = ssocket.accept();
                System.out.println("conexión aceptada");
                BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                String dataReceived = in.readLine();
                System.out.println("Server received: " + dataReceived);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
                out.write("Hola, " + dataReceived+"\n");
                out.flush();
                cliente.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
