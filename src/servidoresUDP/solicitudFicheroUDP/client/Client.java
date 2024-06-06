package servidoresUDP.solicitudFicheroUDP.client;

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

    private static final int SERVER_PORT = 12345; // send to server
    private static byte[] incoming = new byte[1024];
    public static void main(String[] args) {
        System.out.println("---------------------- SOLICITUD DE FICHERO ----------------------");
        String file;
        System.out.println("Escriba el nombre del archivo que quieras leer: ");
        file = sc.nextLine().toUpperCase();

        try {

            byte[] data = (file).getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, SERVER_PORT);
            socket.send(packet);

            packet = new DatagramPacket(incoming, incoming.length);
            socket.receive(packet);
            file = new String(packet.getData(), 0, packet.getLength()) + "\n";
            if (file.startsWith("Error,")) System.err.println(file);
            else System.out.println(file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
