package servidoresTCP.iesPerico.server;

import servidoresTCP.iesPerico.sharedData.Database;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ConnectionHandler implements Runnable {
    private Socket cliente;
    private Database d;
    private double notaFinal = 0;
    private static int num;

    public ConnectionHandler(Socket cliente, Database datos) {
        this.cliente = cliente;
        this.d = datos;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(cliente.getInputStream());
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            num = in.readInt();
            Server.usuarios.put(num, cliente);
            int i = 1;
            do {
                out.writeUTF("/nota;Dame la nota de tu ejercicio " + i + " (0 para acabar)");
                num = in.readInt();
                if (i!=0){

                i++;
            } while (num != 0);
            out.writeUTF("Precio total de las operaciones: " + precioTotal);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        if (!cliente.isClosed()) {
                cliente.close();
            }
        } catch (SocketException e) {
            System.err.println("Client closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}