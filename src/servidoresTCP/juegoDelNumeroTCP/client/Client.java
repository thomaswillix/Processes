package servidoresTCP.juegoDelNumeroTCP.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Scanner sc = new Scanner(System.in);
    private static Socket cliente;
    private static int received;
    private static int n;

    public static void main(String[] args) {
        try {
            cliente = new Socket("localhost", 6001);
            DataInputStream in =new DataInputStream(cliente.getInputStream());
            DataOutputStream out =new DataOutputStream(cliente.getOutputStream());
            do {
                System.out.println("Guess the number (1-10)");
                n = sc.nextInt();
                out.writeInt(n);
                received = in.readInt();
                if (received == 1){
                    System.out.println("Higher");
                } else if (received==-1) {
                    System.out.println("Lower");
                }else System.out.println("Correct!");

            } while (received!=0);
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
