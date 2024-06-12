package servidoresTCP.iesPerico.client;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("---------------------- IES PERICO ----------------------");
        try {
            socket = new Socket("127.0.0.1", 12345);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            String response;
            System.out.println("Indica el número de expediente");
            int n;
            n = sc.nextInt();
            out.writeInt(n);

            int nota = 0;
            do {
                response = in.readUTF();
                if (response.startsWith("/nota")) {
                    System.out.println(response.split(";")[1]);
                    nota = sc.nextInt();
                    out.writeInt(nota);
                }else if (response.startsWith("/exit")){
                    System.out.println("Exiting...");
                }
            } while(nota!=0);

            System.out.println();
            double media= 0;
            if (response.split(";")[1].startsWith("/media")){
                media = Double.parseDouble(response.split(";")[2]);
                System.out.println("Media: " + media);
            }
            else System.err.println("Error calculando la media");

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}