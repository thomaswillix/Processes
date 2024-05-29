package simulacionServidorAlpiste.server;

import simulacionServidorAlpiste.sharedData.Datos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

public class ConnectionHandler implements Runnable{

    private Socket cliente;
    public ConnectionHandler(Socket s) {
        this.cliente = s;
    }
    private static int cod;
    private static HashMap<Socket, Integer> usuarios = new HashMap<>();
    private static Datos d;
    @Override
    public void run() {

        try {
            DataInputStream in = new DataInputStream(cliente.getInputStream());
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            cod = in.readInt();
            usuarios.put(cliente,cod);
            String data[];
            while(true) {
                if (usuarios.get(cliente)==0){
                    do {
                        out.writeUTF(Server.menuAdmin());

                        cod = in.readInt();
                        if (cod<0 || cod>6){
                            out.writeUTF("codigo mal introducido");
                        }else{
                            switch (cod){
                                case 1:

                                    break;
                                case 2:
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    break;
                                case 5:
                                    break;
                                case 6:
                                    break;
                            }
                        }
                    }while (cod!=0);
                }else {
                    String dataReceived, send;
                    dataReceived = in.readUTF();
                    System.out.println("Server received: " + dataReceived);
                    data = dataReceived.split(" ");
                    synchronized (d){
                        send = d.getPrecioAlpiste()*Integer.parseInt(data[0]) + " " + d.getPrecioNabina()*Integer.parseInt(data[1]) +
                                " " +d.getPrecioAvena()*Integer.parseInt(data[2]) + " " + d.getPrecioPerilla()*Integer.parseInt(data[3]);
                    }
                    out.writeUTF(send);
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
