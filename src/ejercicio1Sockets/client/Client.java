package ejercicio1Sockets.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    public static void main(String[] args) {
        try {
            socket = new Socket("127.0.0.1", 1234);

            String nombre;
            Scanner sc = new Scanner(System.in);
            System.out.println("Escribe tu nombre: ");
            nombre = sc.nextLine();

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(nombre);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            String respuesta = in.readUTF();
            System.out.println(respuesta);
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
