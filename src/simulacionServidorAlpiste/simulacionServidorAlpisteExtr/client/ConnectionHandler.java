package simulacionServidorAlpiste.simulacionServidorAlpisteExtr.client;

import simulacionServidorAlpiste.simulacionServidorAlpisteExtr.sharedData.Datos;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

public class ConnectionHandler implements Runnable{

    private Socket cliente;
    private Datos d;
    private static int num;
    private static HashMap<Integer, Socket> usuarios = new HashMap<>();
    private static File f = new File("files/pedidos.csv");

    public ConnectionHandler(Socket s, Datos datos){
        this.cliente = s;
        this.d = datos;
    }

    @Override
    public void run() {

        try {
            DataInputStream in = new DataInputStream(cliente.getInputStream());
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            num = in.readInt();
            usuarios.put(num, cliente);
            while(true) {
                int precio =d.getPrecioAlpiste();
                    out.writeInt(precio);
                    System.out.println("Cliente: " + in.readUTF());

                    precio =d.getPrecioNabina();
                    out.writeInt(precio);
                    System.out.println("Cliente: " + in.readUTF());

                    precio =d.getPrecioAvena();
                    out.writeInt(precio);
                    System.out.println("Cliente: " + in.readUTF());

                    precio =d.getPrecioPerilla();
                    out.writeInt(precio);
                    System.out.println("Cliente: " + in.readUTF());

                    numeroPedidosAtendidos(out);

                    out.writeUTF("exit");
                    cliente.close();
            }
        }catch (SocketException e){
            System.err.println("Client closed");
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private void numeroPedidosAtendidos(DataOutputStream out) throws IOException {
        int lineastotales = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            try {
                while (br.readLine()!=null){
                    lineastotales++;
                }
            } catch (EOFException e) {
                System.err.println("End of file");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        out.writeUTF("/pedidosAtendidos " + lineastotales);
    }

}
