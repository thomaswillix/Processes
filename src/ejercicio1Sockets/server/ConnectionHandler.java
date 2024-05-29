package ejercicio1Sockets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ConnectionHandler implements Runnable{

        private Socket cliente;
        public ConnectionHandler(Socket s) {
            this.cliente = s;
        }

        @Override
        public void run() {

            try {
                DataInputStream in = new DataInputStream(cliente.getInputStream());
                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                while(true) {
                    String dataReceived;
                    while (!(dataReceived = in.readUTF()).equals("adios")) {
                        System.out.println("Server received: " + dataReceived);
                        out.writeUTF(Server.escribirMensajeAmbiguo());
                    }
                    out.writeUTF("/salir");
                    cliente.close();
                }
            }catch (SocketException e){
                System.err.println("Client closed");
            }catch (IOException e){
                e.printStackTrace();
            }
        }

}
