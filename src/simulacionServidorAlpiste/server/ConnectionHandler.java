package simulacionServidorAlpiste.server;

import simulacionServidorAlpiste.sharedData.Datos;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

public class ConnectionHandler implements Runnable{

    private Socket cliente;
    private Datos d = new Datos();
    private static int cod;
    private static HashMap<Integer, Socket> usuarios = new HashMap<>();

    public ConnectionHandler(Socket s){
        this.cliente = s;
    }

    @Override
    public void run() {

        try {
            DataInputStream in = new DataInputStream(cliente.getInputStream());
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            cod = in.readInt();
            usuarios.put(cod, cliente);
            String data[];
            while(true) {
                if (cod==0){
                    do {
                        out.writeUTF(menuAdmin());
                        cod = in.readInt();
                        if (cod<0 || cod>6){
                            out.writeUTF("codigo mal introducido");
                        }else{
                            switch (cod){
                                case 0:
                                    out.writeUTF("exit");
                                    break;
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                    cambiarPrecio(in,out,cod);
                                    break;
                                case 5:
                                    verRecaucadionPedidos(out);
                                    break;
                                case 6:
                                    break;
                            }
                        }
                    }while (cod!=0);
                }else {
                    String dataReceived;
                    int importe;
                    dataReceived = in.readUTF();
                    System.out.println("Server received: " + dataReceived);
                    data = dataReceived.split(" ");
                    synchronized (d){
                        importe = Datos.getPrecioAlpiste()*Integer.parseInt(data[0]) + Datos.getPrecioNabina()*Integer.parseInt(data[1]) +
                                +Datos.getPrecioAvena()*Integer.parseInt(data[2]) + Datos.getPrecioPerilla()*Integer.parseInt(data[3]);
                        out.writeInt(importe);
                    }
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
        out.writeDouble(recaudacionTotal);
    }

    private void cambiarPrecio(DataInputStream in, DataOutputStream out, int cod) throws IOException {
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
                    System.err.println();
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
