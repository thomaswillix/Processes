package juegoNumero.UDP.client;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static Scanner sc =new Scanner(System.in);
    private static byte[] incoming = new byte[256];

    public static void main(String[] args) {
        DatagramSocket socket = null;
        String message, out;
        try {
            socket = new DatagramSocket();
            InetAddress serverAdress =InetAddress.getByName("localhost");
            int serverPort = 60001,  num;
            boolean haGanado = false;
            while (!haGanado){
                System.out.println("Guess the number (1-10)");
                num = sc.nextInt();

                out = String.valueOf(num);
                byte[] sendData = out.getBytes();

                DatagramPacket packet = new DatagramPacket(sendData, sendData.length, serverAdress, serverPort);
                socket.send(packet);

                DatagramPacket in = new DatagramPacket(incoming, incoming.length);
                socket.receive(in);

                message = new String(in.getData(), 0, in.getLength());

                if (message.equals("Correct!")) {
                    haGanado = true;
                }
                System.out.println(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        socket.close();
    }
}
