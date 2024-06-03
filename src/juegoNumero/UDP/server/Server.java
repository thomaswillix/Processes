package juegoNumero.UDP.server;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Random;

public class Server {
    private static byte[] incoming = new byte[256];

    public static void main(String[] args) {
        Random r =  new Random();
        int numeroAleatorio = r.nextInt(10)+1, n;
        String message;
        DatagramSocket socket = null;
        InetAddress address = null;
        try {
            address = InetAddress.getByName("localhost");
            socket = new DatagramSocket(60001);
            while(true){
                DatagramPacket packet= new DatagramPacket(incoming, incoming.length); //prepare packet
                socket.receive(packet);

                incoming = packet.getData();
                message = new String(packet.getData(), 0, packet.getLength()); // create a string
                n = Integer.parseInt(message);

                if(n<numeroAleatorio){
                    message = "Higher";
                } else if (n>numeroAleatorio) {
                    message  ="Lower";
                }else{
                    message ="Correct!";
                    numeroAleatorio = r.nextInt(10)+1;

                }

                byte[] data = (message).getBytes();
                int userPort = packet.getPort();
                DatagramPacket forward = new DatagramPacket(data, data.length, address, userPort);

                try {
                    socket.send(forward);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
