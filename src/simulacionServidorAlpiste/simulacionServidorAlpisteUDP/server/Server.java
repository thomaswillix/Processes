package simulacionServidorAlpiste.simulacionServidorAlpisteUDP.server;

import simulacionServidorAlpiste.simulacionServidorAlpisteUDP.sharedData.Data;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;

public class Server {
    private static byte[] incoming = new byte[256];
    private static final int PORT = 8000;

    private static DatagramSocket socket;

    static {
        try {
            socket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
    private static HashMap<Integer, Integer> criadores = new HashMap<>();

    private static final InetAddress address;
    static {
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Data d = new Data();
        System.out.println("Server started on port " + PORT);
        int n;
        while (true) {
            DatagramPacket packet = new DatagramPacket(incoming, incoming.length); // prepare packet
            try {
                socket.receive(packet); // receive packet
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String message = new String(packet.getData(), 0, packet.getLength()); // create a string
            n = Integer.parseInt(message.split(";")[1]);
            if (message.startsWith("/nroC")) {
                System.out.println("Nuevo cliente con número de cliente: " + n + " y puerto " + packet.getPort());
                criadores.put(n, packet.getPort());
                message = "¿Cuántos kg quieres de alpiste?";
                byte[] data = (message).getBytes();
                int userPort = packet.getPort();
                DatagramPacket forward = new DatagramPacket(data, data.length, address, userPort);
                try {
                    socket.send(forward);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (message.startsWith("/kg")){
                double precio = (double)(n * d.getAlpiste())/100;
                message = "El precio total es de: " +precio;
                byte[] data = (message).getBytes();
                int userPort = packet.getPort();
                DatagramPacket forward = new DatagramPacket(data, data.length, address, userPort);
                try {
                    socket.send(forward);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}