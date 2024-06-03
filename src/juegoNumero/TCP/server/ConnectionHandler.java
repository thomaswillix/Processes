package juegoNumero.TCP.server;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class ConnectionHandler implements Runnable{
    private Socket client;
    private int n;
    private int numeroAleatorio;
    private Random r = new Random();
    public ConnectionHandler(Socket client) {
        this.client = client;
        this.numeroAleatorio =r.nextInt(10)+1;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            do {
                n = in.readInt();
                if (n<numeroAleatorio){
                    out.writeInt(1);
                } else if (n>numeroAleatorio) {
                    out.writeInt(-1);
                }else{
                    out.writeInt(0);
                }
            }while (n!=numeroAleatorio);
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
