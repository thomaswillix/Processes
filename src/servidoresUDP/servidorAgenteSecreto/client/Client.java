package servidoresUDP.servidorAgenteSecreto.client;

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
    private static int nroCriador;
    private static int nroKg;


    static {
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private static final int SERVER_PORT = 9876; // send to server
    private static byte[] incoming = new byte[1024];


    public static void main(String[] args) {
        System.out.println("---------------------- TIA ----------------------");
        String received;
        System.out.println("Introduzca su nombre, agente: ");
        String nombreAgente;

        nombreAgente = sc.nextLine();

        try {

            byte[] data = (nombreAgente).getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, SERVER_PORT);
            socket.send(packet);

            packet = new DatagramPacket(incoming, incoming.length);
            socket.receive(packet);
            received = new String(packet.getData(), 0, packet.getLength()) + "\n";
            System.out.print(received);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

