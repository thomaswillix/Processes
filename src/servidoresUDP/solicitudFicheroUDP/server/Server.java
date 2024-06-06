package servidoresUDP.solicitudFicheroUDP.server;

import java.io.*;
import java.net.*;

public class Server {

    private static byte[] incoming = new byte[1024];

    public static void main(String[] args) {
        String file;
        DatagramSocket socket = null;
        InetAddress address = null;
        try {
            address = InetAddress.getByName("localhost");
            socket = new DatagramSocket(12345);

            while (true) {
                DatagramPacket packet = new DatagramPacket(incoming, incoming.length); //prepare packet
                socket.receive(packet);
                incoming = packet.getData();
                file = new String(packet.getData(), 0, packet.getLength()); // create a string
                File f  = new File(file);
                if (f.exists()) file = readContent(f, file);
                else file="Error, el archivo que se ha solicitado no existe";

                byte[] data = (file).getBytes();
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
    private static String readContent(File f, String file) throws IOException {
        file = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            try {
                StringBuilder sb = new StringBuilder(file);
                while ((line = br.readLine())!=null){
                    sb.append(line);
                    sb.append("\n");
                }
                file = sb.toString();
            } catch (EOFException e) {
                System.err.println("End of file");
            }
        } catch (FileNotFoundException e) {
            //Ignore
        }
        return file;
    }
}