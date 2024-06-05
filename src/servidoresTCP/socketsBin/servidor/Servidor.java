package servidoresTCP.socketsBin.servidor;
//Servidor con puerto 12345

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static ServerSocket ssocket;
    private static Socket cliente;
    public static void main(String[] args){
        try {
            ssocket = new ServerSocket(1234);

            while (true) {
                cliente = ssocket.accept();
                System.out.println("conexión aceptada");
                DataInputStream in = new DataInputStream(cliente.getInputStream());
                String dataReceived = in.readUTF();
                System.out.println("Server received: " + dataReceived);
                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                out.writeUTF("Hola, " + dataReceived+"\n");
                cliente.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
