package servidoresUDP.servidorAgenteSecreto.server;

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Server {
    private static byte[] incoming = new byte[1024];
    private static final int PORT = 9876;
    private static HashMap<String, String> agentes = new HashMap();
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
        cargarAgentes();
        System.out.println("Server started on port " + PORT);
        while (true) {
            String received;
            DatagramPacket packet = new DatagramPacket(incoming, incoming.length); // prepare packet
            try {
                socket.receive(packet); // receive packet
                received = new String(packet.getData(), 0, packet.getLength()); // create a string
                System.out.println("Nombre de agente recibido: " + received);
                if (!agentes.containsKey(received)) received = "El agente introducido no coincide con ninguno de los del archivo";
                else received = "La puerta que debe utilizar es la que tiene el código: "  + agentes.get(received);

                byte[] data = (received).getBytes();
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

    private static void cargarAgentes() {
        File f = new File("files/agentes.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            String[] data;
            try {
                while ((line = br.readLine()) != null) {
                    data = line.split(":");
                    agentes.put(data[0], data[1]);
                }
            }catch (EOFException e){
                System.err.println("End of file");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}