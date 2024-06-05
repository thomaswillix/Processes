package servidoresUDP.servidorCifradoDNIUDP.server;

import java.io.IOException;
import java.net.*;

public class Server {
    private static byte[] incoming = new byte[1024];
    private static final int PORT = 12345;

    private static DatagramSocket socket;

    static {
        try {
            socket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    private static final InetAddress address;
    static {
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Server started on port " + PORT);
        while (true) {
            String received;
            DatagramPacket packet = new DatagramPacket(incoming, incoming.length); // prepare packet
            try {
                socket.receive(packet); // receive packet
                received = new String(packet.getData(), 0, packet.getLength()); // create a string
                System.out.println("DNI SIN CIFRAR: " + received);
                int n;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < received.length() - 1; i++) {
                    n = Integer.parseInt(String.valueOf(received.charAt(i)));
                    switch (n) {
                        case 0:
                            n = 3;
                            break;
                        case 1:
                            n = 4;
                            break;
                        case 2:
                            n = 5;
                            break;
                        case 3:
                            n = 6;
                            break;
                        case 4:
                            n = 7;
                            break;
                        case 5:
                            n = 8;
                            break;
                        case 6:
                            n = 9;
                            break;
                        case 7:
                            n = 0;
                            break;
                        case 8:
                            n = 1;
                            break;
                        case 9:
                            n = 2;
                            break;
                    }
                    sb.append(n);
                }
                sb.append(received.charAt(received.length() - 1));
                System.out.println("DNI CIFRADO: " + sb);

                byte[] data = (sb.toString()).getBytes();
                int userPort = packet.getPort();
                DatagramPacket forward = new DatagramPacket(data, data.length, address, userPort);
                try {
                    socket.send(forward);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}