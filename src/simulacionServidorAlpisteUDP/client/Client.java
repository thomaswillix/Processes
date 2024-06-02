package simulacionServidorAlpisteUDP.client;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final DatagramSocket socket;

    static {
        try {
            socket = new DatagramSocket(); // init to any available port
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    static Scanner sc = new Scanner(System.in);

    private static final InetAddress address;

    static {
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private static String username;
    private static final int SERVER_PORT = 8000; // send to server
    private static byte[] incoming = new byte[1024];


    public static void main(String[] args) {
        System.out.println("---------------------- COMPRA DE ALPISTE ----------------------");
        // send initialization message to the server and validate username
        String received;
        try {
            do {
            username = sc.nextLine();
            byte[] uuid = ("init; " + username).getBytes();
            DatagramPacket initialize = new DatagramPacket(uuid, uuid.length, address, SERVER_PORT);
            socket.send(initialize);

            DatagramPacket packet = new DatagramPacket(incoming, incoming.length);
                socket.receive(packet);

            received = new String(packet.getData(), 0, packet.getLength()) + "\n";
            System.out.print(received);

        }while (received.equals("This user is currently unavailable, please introduce another nickname\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
