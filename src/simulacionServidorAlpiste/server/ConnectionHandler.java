package simulacionServidorAlpiste.server;

import simulacionServidorAlpiste.sharedData.Datos;

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
                if (num==0){
                    do {
                        out.writeUTF(menuAdmin());
                        num = in.readInt();
                        if (num<0 || num>6){
                            out.writeUTF("codigo mal introducido");
                        }else{
                            switch (num){
                                case 0:
                                    out.writeUTF("exit");
                                    break;
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                    cambiarPrecio(in,out,num);
                                    out.writeUTF("Operación realizada con éxito");
                                    break;
                                case 5:
                                    verRecaucadionPedidos(out);
                                    break;
                                case 6:
                                    numeroPedidosAtendidos(out);
                                    break;
                            }
                        }
                    }while (num!=0);
                }else {
                    int precio =d.getPrecioAlpiste();
                    out.writeInt(precio);
                    System.out.println(in.readUTF());

                    precio =d.getPrecioNabina();
                    out.writeInt(precio);
                    System.out.println(in.readUTF());

                    precio =d.getPrecioAvena();
                    out.writeInt(precio);
                    System.out.println(in.readUTF());

                    precio =d.getPrecioPerilla();
                    out.writeInt(precio);
                    System.out.println(in.readUTF());

                }
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
                String line;
                while ((line = br.readLine())!=null){
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

    private void verRecaucadionPedidos(DataOutputStream out) throws IOException {
        File f = new File("files/pedidos.csv");
        double recaudacionTotal = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            try {
                String line;
                while ((line = br.readLine())!=null){
                    String[] datos = line.split(";");
                    recaudacionTotal += Double.parseDouble(datos[5]);
                }
            } catch (EOFException e) {
                System.err.println("End of file");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        out.writeUTF("/recaudacion " +recaudacionTotal);
    }

    private void cambiarPrecio(DataInputStream in, DataOutputStream out, int cod) throws IOException {
        out.writeUTF("/precio");
        synchronized (d){
            switch (cod){
                case 1:
                    d.setPrecioAlpiste(in.readInt());
                    break;
                case 2:
                    d.setPrecioNabina(in.readInt());
                    break;
                case 3:
                    d.setPrecioAvena(in.readInt());
                    break;
                case 4:
                    d.setPrecioPerilla(in.readInt());
                    break;
                default:
                    //ignore
                    break;
            }
        }

    }

    public static String  menuAdmin(){
        return ("Administración del servidor:\n" +
                "1.- Cambiar precio alpiste\n" +
                "2.- Cambiar precio nabina\n" +
                "3.- Cambiar precio avena\n" +
                "4.- Cambiar precio perilla\n" +
                "5.- Ver recaudación de los pedidos\n" +
                "6.- Numero de pedidos atendidos\n" +
                "0.- Finalizar servicio\n" +
                "Elige la opción:");

    }
}
