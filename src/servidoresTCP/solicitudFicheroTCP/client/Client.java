package servidoresTCP.solicitudFicheroTCP.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private static Socket socket;

    public static void main(String[] args) {
        try {
            socket = new Socket("127.0.0.1", 12345);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String archivo;
            Scanner sc = new Scanner(System.in);
            System.out.println("Escribe el nombre del archivo que quieras leer: ");
            archivo = sc.nextLine();
            //Envío
            out.writeUTF(archivo);
            //Recibo
            String result = in.readUTF();
            if (result.startsWith("/error")) System.err.println(result.split(";")[1]); //Imprimimos caso de error
            else System.out.println(result);

            socket.close();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
