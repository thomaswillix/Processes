package simulacionServidorFotocopias.client;

import simulacionServidorFotocopias.server.ThreadAdmin;
import simulacionServidorFotocopias.sharedData.Data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionHandler implements Runnable{
    private Data d;
    private Socket cliente;
    private String username;
    private int saldo;

    public ConnectionHandler(Data data, Socket socket) {
        this.d = data;
        this.cliente = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(cliente.getInputStream());
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            username = in.readUTF();
            if (username.equalsIgnoreCase("A000")) {
                out.writeUTF("/login;Bienvenido, admin");
                ThreadAdmin admin = new ThreadAdmin(d, cliente);
                Thread t = new Thread(admin);
                t.start();
            }else {
                boolean tieneSaldo = false;
                if (Data.usuarios.containsKey(username)) {
                    this.saldo = Data.usuarios.get(username);
                    System.out.println("Saldo: " + saldo);
                    out.writeUTF("/login;Bienvenido de nuevo, " + username);
                } else {
                    saldo = 5000;
                    Data.usuarios.put(username, 5000); // saldo en céntimos para que sea coherente con el de Data
                    out.writeUTF("/login;Bienvenido, " + username);
                }
                if (saldo>0) tieneSaldo = true;
                int copias;
                while(tieneSaldo){

                    out.writeUTF("/copias;¿Cuántas fotocopias en blanco y negro quieres hacer?");
                    copias = in.readInt();
                    saldo -= copias*d.getPrecioColor();
                    Data.usuarios.replace(username, saldo);
                    out.writeUTF("/copias;¿Cuántas fotocopias a color quieres hacer?");
                    copias = in.readInt();
                    saldo -= copias*d.getPrecioBN();
                    Data.usuarios.replace(username, saldo);
                    if (Data.usuarios.get(username)<=0) {
                        tieneSaldo = false;
                        out.writeUTF("Saldo insuficiente, pida al administrador una recarga.");
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
