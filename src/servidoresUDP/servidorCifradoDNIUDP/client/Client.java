package servidoresUDP.servidorCifradoDNIUDP.client;

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

    private static final int SERVER_PORT = 12345; // send to server
    private static byte[] incoming = new byte[1024];


    public static void main(String[] args) {
        System.out.println("---------------------- CIFRADO DNI ----------------------");
        String received;
        System.out.println("Introduzca su DNI: ");
        String dni;
        do {
            dni = sc.nextLine().toUpperCase();
            if(!dni.matches("^[0-9]{8}[A-Z]$")) System.err.println("DNI mal introducido");
        } while (!dni.matches("^[0-9]{8}[A-Z]$")); //^: marca el inicio,
        // [A-Za-z]: cualquier letra del abecedario (mayúscula o minúscula)
        // [0-9]: cualquier número
        // {8} que haya 8 números seguidos (a la letra no hace falta ponerle {1} porque sería redundante)

        try {

            byte[] data = (dni).getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, SERVER_PORT);
            socket.send(packet);

            packet = new DatagramPacket(incoming, incoming.length);
            socket.receive(packet);
            received = new String(packet.getData(), 0, packet.getLength()) + "\n";
            System.out.print("DNI CODIFICADO: " + received);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

