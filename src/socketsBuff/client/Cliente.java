package socketsBuff.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static Socket socket;
    public static void main(String[] args) {
        try {
            socket = new Socket("127.0.0.1", 1234);

            String nombre;
            Scanner sc = new Scanner(System.in);
            System.out.println("Escribe tu nombre: ");
            nombre = sc.nextLine();

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write(nombre+"\n");
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String respuesta = in.readLine();
            System.out.println(respuesta);
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
