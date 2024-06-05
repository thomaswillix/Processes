package servidoresTCP.simulacionServidorFotocopias.server;

import servidoresTCP.simulacionServidorFotocopias.sharedData.Data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ThreadAdmin implements Runnable {

    private Data d;
    private Socket cliente;
    private static Scanner sc = new Scanner(System.in);

    public ThreadAdmin(Data d, Socket cliente) {
        this.d = d;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(cliente.getInputStream());
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            int n;
            String s = "";
            boolean salir = false;
            System.out.println("Admin: " + in.readUTF());
            do {
                out.writeUTF("1.- lista empleados y saldos\n" +
                        "2.- renovar saldo\n" +
                        "3.- terminar");
                n = in.readInt();
                switch (n) {
                    case 1:
                        for (String clave : Data.usuarios.keySet()) {
                            s += "USUARIO: "+clave+", SALDO: "+Data.usuarios.get(clave)+"\n";
                        }
                        out.writeUTF(s.toString());
                        in.readInt();
                        break;
                    case 2:
                        out.writeUTF("/renovar;¿A quién le desea renovar el saldo?");
                        s = in.readUTF();
                        if (Data.usuarios.containsKey(s)) {
                            Data.usuarios.replace(s, 5000);
                            out.writeUTF("Usuario renovado con éxito");
                        } else out.writeUTF("No se ha podido encontrar el usuario que ha solicitado renovar");
                        in.readInt();
                        break;
                    case 3:
                        out.writeUTF("exit");
                        salir = true;
                        break;
                }
            } while (!salir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}